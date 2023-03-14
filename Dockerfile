FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY pom.xml ./
RUN mvn dependency:go-offline
COPY . ./
RUN mvn test && mvn package
EXPOSE 8080
CMD ["java", "-jar", "target/myapp.jar"]