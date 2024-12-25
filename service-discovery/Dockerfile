ARG JAVA_VERSION
ARG SERVICE_DISCOVERY_PORT

FROM openjdk:${JAVA_VERSION}

COPY build/libs/service-discovery-0.0.1-SNAPSHOT.jar app.jar

EXPOSE ${SERVICE_DISCOVERY_PORT}

ENTRYPOINT ["java", "-jar", "app.jar"]