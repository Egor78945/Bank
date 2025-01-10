ARG JAVA_VERSION

FROM openjdk:${JAVA_VERSION}

COPY build/libs/user-database-service-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]