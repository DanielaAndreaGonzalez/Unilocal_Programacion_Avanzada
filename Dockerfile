# Use an OpenJDK base image
FROM openjdk:17
# Set the working directory in the container
WORKDIR /app
# Copy the Gradle build files
COPY build.gradle .
COPY settings.gradle .
COPY gradlew .
COPY gradle/ ./gradle/
# Make gradlew executable
RUN chmod +x gradlew
# Copy the application source code
COPY src/ ./src/
# Build the application using Gradle
RUN ./gradlew build --stacktrace --info
# Set the port to expose
EXPOSE ${PORT}
# Set the entry point to run the application
ENTRYPOINT ["java", "-jar", "build/libs/UniLocal-proyecto-final-1.0-SNAPSHOT.jar"]