FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copie o JAR da build
COPY target/*.jar app.jar

# Exponha a porta do Spring Boot
EXPOSE 8080

# Use ENTRYPOINT com CMD para facilitar substituição via docker run
ENTRYPOINT ["java", "-jar"]
CMD ["app.jar"]