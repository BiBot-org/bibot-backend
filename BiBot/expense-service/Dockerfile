FROM openjdk:17-alpine

WORKDIR /app
ADD build/libs/expense-service-0.0.1-SNAPSHOT.jar /app/expense-service.jar
ENTRYPOINT ["java","-jar","expense-service.jar"]