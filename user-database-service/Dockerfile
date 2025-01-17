FROM openjdk:17-jdk-alpine

WORKDIR /app

ARG POSTGRES_DB=${POSTGRES_DB}
ARG POSTGRES_HOST=${POSTGRES_HOST}
ARG POSTGRES_PORT=${POSTGRES_PORT}
ARG POSTGRES_USERNAME=${POSTGRES_USERNAME}
ARG POSTGRES_PASSWORD=${POSTGRES_PASSWORD}

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src

RUN chmod +x gradlew

RUN ./gradlew clean build -x generateJooq -x test

ENTRYPOINT ["sh", "-c", "./gradlew update && ./gradlew generateJooq && java -jar build/libs/*-SNAPSHOT.jar"]