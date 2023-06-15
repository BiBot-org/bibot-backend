FROM openjdk:17-alpine

WORKDIR /app/BiBot

COPY card-service/build/libs/card-service-0.0.1-SNAPSHOT.jar .
COPY user-service/build/libs/user-service-0.0.1-SNAPSHOT.jar .
COPY expense-service/build/libs/expense-service-0.0.1-SNAPSHOT.jar .
COPY receipt-service/build/libs/receipt-service-0.0.1-SNAPSHOT.jar .

CMD ["java","-jar","card-service-0.0.1-SNAPSHOT.jar"]
