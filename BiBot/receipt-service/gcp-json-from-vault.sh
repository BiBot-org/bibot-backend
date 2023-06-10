# vault 쿠버네티스 로그인

export jwt_token=$(cat /var/run/secrets/kubernetes.io/serviceaccount/token)

curl --request POST \
         --data '{"jwt": "'$jwt_token'", "role": "app"}' \
         http://34.22.82.97:8200/v1/auth/kubernetes/login > response.json

# vault gcp secret 요청
gcp_credential=$(cat response.json | jq -r '.data')
echo "$gcp_credential" > gcp_credential.json

# jq 를 사용해 response 에서 gcp credential json 가져와서 파일로 저장

# env 세팅
export GOOGLE_APPLICATION_CREDENTIALS=gcp_credential.json

# jar 실행
java -jar receipt-service.jar