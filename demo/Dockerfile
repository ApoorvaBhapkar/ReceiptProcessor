# Use a base image with JDK
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot jar file into the container
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your Spring Boot application listens on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
