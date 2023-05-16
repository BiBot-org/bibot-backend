FROM openjdk:17-alpine

WORKDIR /app
ADD build/libs/receipt-service-0.0.1-SNAPSHOT.jar /app/receipt-service.jar
ENTRYPOINT ["java","-jar","receipt-service.jar"]