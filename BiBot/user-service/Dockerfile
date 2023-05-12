FROM openjdk:17-alpine

WORKDIR /app
ADD build/libs/user-service-0.0.1-SNAPSHOT.jar /app/user-service.jar
ENTRYPOINT ["java","-jar","user-service.jar"]