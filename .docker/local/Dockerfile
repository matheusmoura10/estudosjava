FROM openjdk:17
FROM maven:3.8.1-openjdk-17-slim AS build

WORKDIR /app

COPY .env /app
COPY ./src /app/src
COPY pom.xml /app

RUN mvn -f /app/pom.xml clean install -DskipTests

 

EXPOSE ${APP_PORT}

 

ENTRYPOINT ["java", "-jar", "/app/target/agenda-0.0.1-SNAPSHOT.jar"]