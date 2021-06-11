FROM openjdk:17-ea-11-jdk-slim

VOLUME /tmp

COPY server/build/libs/auth-resource-service-0.0.1.jar AuthResourceService.jar

ENTRYPOINT ["java", "-jar", "AuthResourceService.jar"]

