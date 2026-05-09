# Etapa 1 - build
FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copia só o pom.xml primeiro — aproveita cache do Docker
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Agora copia o código e compila
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2 - runtime
FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]