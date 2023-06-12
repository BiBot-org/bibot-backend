# vault 쿠버네티스 로그인

export VAULT_TOKEN=$(cat /var/run/secrets/kubernetes.io/serviceaccount/token)


export GOOGLE_APPLICATION_CREDENTIALS=/home/bibot_recipe/my-credentials.json

# jar 실행
java -jar user-service.jar