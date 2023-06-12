FROM openjdk:17-alpine

WORKDIR /app

RUN apk update && apk add curl jq

ADD gcp-json-from-vault.sh /app/gcp-json-from-vault.sh

ADD build/libs/user-service-0.0.1-SNAPSHOT.jar /app/user-service.jar

ENTRYPOINT ["sh","gcp-json-from-vault.sh"]