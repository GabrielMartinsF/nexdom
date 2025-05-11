FROM openjdk:17-jdk-slim

WORKDIR /nexdom

COPY target/nexdom-backend.jar nexdom.jar

ENTRYPOINT ["java", "-jar", "nexdom.jar"]
