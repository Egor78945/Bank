FROM openjdk:17-jdk-alpine

WORKDIR /app

ARG POSTGRES_USER_DATABASE_NAME
ARG POSTGRES_USER_HOST
ARG POSTGRES_USER_PORT
ARG POSTGRES_USER_USERNAME
ARG POSTGRES_USER_PASSWORD

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src

RUN chmod +x gradlew

RUN apk add gcompat

RUN ./gradlew build -x generateJooq -x test

ENTRYPOINT ["sh", "-c", "./gradlew update && ./gradlew generateJooq && java -jar build/libs/*-SNAPSHOT.jar"]