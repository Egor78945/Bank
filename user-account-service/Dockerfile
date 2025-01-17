ARG JAVA_VERSION

FROM openjdk:${JAVA_VERSION}

COPY build/libs/user-account-service-0.0.1-SNAPSHOT-plain.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]