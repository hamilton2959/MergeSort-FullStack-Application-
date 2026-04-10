# Stage 1: Build the Spring Boot application
# Uses a JDK image from Eclipse Temurin for compilation.
FROM eclipse-temurin:17-jdk-focal AS builder

# Set the working directory inside the container for the build stage.
WORKDIR /app

# Copy the Maven wrapper and its directory.
# This allows you to use the Maven wrapper (mvnw) inside the container.
COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .

# Copy the source code.
# The `src` directory contains your Java source files, resources, etc.
COPY src src

# Make the Maven wrapper script executable.
RUN chmod +x mvnw

# Build the Spring Boot application into an executable JAR.
# `clean package` creates the executable JAR in the target folder.
# `-DskipTests` skips running tests during the Docker build to speed up the process.
RUN ./mvnw clean package -DskipTests

# Stage 2: Create the final, lightweight runtime image
FROM eclipse-temurin:17-jre-focal

# Set the working directory inside the container for the runtime stage.
WORKDIR /app

# Copy the executable JAR from the builder stage to the final image.
# Maven places the jar in the /target folder.
# We rename it to `app.jar` for a consistent entry point.
COPY --from=builder /app/target/MergeSort-0.0.1-SNAPSHOT.jar app.jar

# Expose the port on which your Spring Boot application will listen.
EXPOSE 8080

# Define the command to run your application.
ENTRYPOINT ["java", "-jar", "app.jar"]
