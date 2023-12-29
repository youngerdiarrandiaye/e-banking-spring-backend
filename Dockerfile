FROM openjdk:17-jdk-slim

LABEL maintainer="You_Dev"

EXPOSE 8085

ADD target/ebankig-backend-0.0.1-SNAPSHOT.jar ebankig-backend.jar

ENTRYPOINT ["java", "-jar", "ebankig-backend.jar"]





