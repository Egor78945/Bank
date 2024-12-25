ARG JAVA_VERSION
ARG API_GATEWAY_SERVICE_PORT

FROM openjdk:${JAVA_VERSION}

COPY build/libs/api-gateway-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE ${API_GATEWAY_SERVICE_PORT}

ENTRYPOINT ["java", "-jar", "app.jar"]