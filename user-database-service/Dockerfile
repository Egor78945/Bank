FROM openjdk:17-jdk-alpine

WORKDIR /app

ARG POSTGRES_USER_DATABASE_NAME
ARG POSTGRES_USER_HOST
ARG POSTGRES_USER_PORT_INNER
ARG POSTGRES_USER_USERNAME
ARG POSTGRES_USER_PASSWORD

COPY . .

RUN chmod +x gradlew

RUN apk add gcompat

#RUN ./gradlew build -x generateJooq -x test

ENTRYPOINT ["sh", "-c", "./gradlew update -x generateJooq && ./gradlew generateJooq && ./gradlew build -x test && java -jar build/libs/*-SNAPSHOT.jar"]