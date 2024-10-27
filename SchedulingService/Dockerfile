FROM amazoncorretto:17

WORKDIR /app

COPY gradlew .
COPY gradle/ ./gradle/
COPY build.gradle .
COPY settings.gradle .

COPY src ./src

# Make the Gradle wrapper executable
RUN chmod +x ./gradlew

# Build the application using Gradle
RUN ./gradlew clean build

# Run the application
ENTRYPOINT ["java", "-jar", "build/libs/demo-0.0.1-SNAPSHOT.jar"]
