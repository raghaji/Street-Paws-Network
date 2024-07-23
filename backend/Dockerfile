# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jre-slim

# Set the working directory
WORKDIR /app

# Copy the Maven build file and the source code
#COPY pom.xml /app
#COPY src /app/src
COPY . .
# Install Maven and build the application
RUN apt-get update && apt-get install -y maven && mvn -f /app/pom.xml clean package

# Copy the Spring Boot jar to the container
COPY target/demo-0.0.1-SNAPSHOT.jar /app/demo.jar

# Expose the port the app runs on
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/demo.jar"]
