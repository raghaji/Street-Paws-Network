# Use an official Maven image to build the application
FROM maven:3.8.5-openjdk-17 AS build

# Set the working directory
WORKDIR /Street-Paw-Network

# Copy the pom.xml file and the src directory
COPY /Street-Paw-Network/pom.xml .
COPY /Street-Paw-Network/src ./src

# Package the application
RUN mvn clean package -DskipTests

# Use a smaller base image to run the application
FROM openjdk:17-jdk-alpine

# Set the working directory
WORKDIR /Street-Paw-Network

# Copy the packaged application from the build stage
COPY --from=build /Street-Paw-Network/target/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "pawNetwork.jar"]
