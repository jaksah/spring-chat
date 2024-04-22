# Stage 1: Gradle Build
FROM gradle:latest AS builder
VOLUME /tmp
WORKDIR /app
COPY . .
RUN ./gradlew clean build

# Stage 2: Final image
FROM eclipse-temurin:21-jdk-alpine
VOLUME /tmp
COPY --from=builder /app/build/libs/*.jar /app.jar
# Expose the application port
EXPOSE 8080
# Run the application
ENTRYPOINT ["java","-jar","/app.jar"]
