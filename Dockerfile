FROM eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /app

# Instala o Maven
RUN apk add --no-cache maven

# Copia tudo
COPY . .

# Builda o projeto
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
