# Etapa de construcción
FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .

# Ejecución de la compilación
RUN ./gradlew bootJar --no-daemon

# Etapa final
FROM openjdk:17-jdk-slim

EXPOSE 8443

# Copia el JAR generado desde la etapa de construcción
COPY --from=build /build/libs/*.jar app.jar

COPY --from=build /app/src/main/resources/wallet /app/src/main/resources/wallet

ENTRYPOINT ["java", "-jar", "app.jar"]