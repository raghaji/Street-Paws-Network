# Use an official Maven image to build the application
FROM maven:3.8.5-openjdk-17 AS build

# Set the working directory
WORKDIR /street-paw-network

# Copy the pom.xml file and the src directory
COPY /street-paw-network/pom.xml .
COPY /street-paw-network/src ./src

# Package the application
RUN mvn clean package -DskipTests

# Use a smaller base image to run the application
FROM openjdk:17-jdk-alpine

# Set the working directory
WORKDIR /street-paw-network

# Copy the packaged application from the build stage
COPY --from=build /street-paw-network/target/*.jar app.jar

# Expose the application port
EXPOSE 8083

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
