# Use an official Maven image to build the application
FROM maven:3.8.5-openjdk-17 AS build

# Set the working directory
WORKDIR /demo

# Copy the pom.xml file and the src directory
COPY /demo/pom.xml .
COPY /demo/src ./src

# Package the application
RUN mvn clean package -DskipTests

# Use a smaller base image to run the application
FROM openjdk:17-jdk-alpine

# Set the working directory
WORKDIR /demo

# Copy the packaged application from the build stage
COPY --from=build /demo/target/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
