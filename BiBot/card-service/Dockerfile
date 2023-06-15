FROM openjdk:17-alpine
WORKDIR /app
ADD build/libs/card-service-0.0.1-SNAPSHOT.jar /app/card-service.jar
ENTRYPOINT ["java","-jar","card-service.jar"]