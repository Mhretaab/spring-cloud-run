FROM maven:3.8.5-openjdk-17-slim AS build-stage
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src/ ./src/
RUN mvn package -DskipTests

# Stage 2: Run the integration tests
FROM maven:3.8.5-openjdk-17-slim AS test-stage
WORKDIR /app
COPY pom.xml .
#COPY --from=build-stage /app/target/cloudrun-0.0.1.jar .
COPY src/ ./src/
RUN mvn test

# Stage 3: Build the final image
FROM adoptopenjdk:17-jre AS production
WORKDIR /app
COPY --from=build-stage /app/target/cloudrun-0.0.1.jar .
CMD ["java", "-jar", "cloudrun-0.0.1.jar"]