# FROM maven:3.8.5-openjdk-17-slim AS build-stage
# WORKDIR /app
# COPY pom.xml .
# RUN mvn dependency:go-offline
# COPY src/ ./src/
# RUN mvn package -DskipTests

# Stage 2: Run the integration tests
#FROM maven:3.8.5-openjdk-17-slim AS test-stage
#WORKDIR /app
#COPY pom.xml .
#COPY --from=build-stage /app/target/cloudrun-0.0.1.jar .
#COPY src/ ./src/
#RUN mvn test

# Stage 3: Build the final image
# FROM openjdk:17-alpine AS production
# WORKDIR /app
# COPY --from=build-stage /app/target/cloudrun-0.0.1.jar ./app.jar
# CMD ["java", "-jar", "app.jar"]
FROM openjdk:17-alpine
ARG JAR_FILE=target/*.jar
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/cloudrun-*.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]