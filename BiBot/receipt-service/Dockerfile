FROM openjdk:17-alpine

WORKDIR /app
RUN chmod +x /app/gcp-json-from-vault.sh
RUN apk update && apk add curl jq

ADD gcp-json-from-vault.sh /app/gcp-json-from-vault.sh
ADD build/libs/receipt-service-0.0.1-SNAPSHOT.jar /app/receipt-service.jar
ENTRYPOINT ["sh","gcp-json-from-vault.sh"]