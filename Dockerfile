FROM eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /app

# Copia Maven wrapper e pom.xml
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Baixa dependências
RUN ./mvnw dependency:go-offline

# Copia código e builda
COPY src src
RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copia o JAR da imagem builder
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
