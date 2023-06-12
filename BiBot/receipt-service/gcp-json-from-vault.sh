# vault 쿠버네티스 로그인

export VAULT_TOKEN=$(cat /var/run/secrets/kubernetes.io/serviceaccount/token)

#export GOOGLE_APPLICATION_CREDENTIALS=/home/bibot_recipe/my-credentials.json
# shellcheck disable=SC2002
export GOOGLE_APPLICATION_CREDENTIALS=$(cat /home/bibot_recipe/my-credentials.json | jq -r '.data')
echo "$GOOGLE_APPLICATION_CREDENTIALS" > gcp_credential.json
# jar 실행
java -jar receipt-service.jar