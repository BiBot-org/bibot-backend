# 🤖 Bibot _**자동 영수증 경비처리 솔루션** API Server_

<div align="center">
 <img src="https://github.com/BiBot-org/bibot/assets/36991763/5d7d35c4-16f4-404a-ac0d-9443342687ab" />

</div>

# 🔍 Intro

> 자동 경비처리 RPA 서비스 Bibot 입니다.  
> 카드사와 연동되었다는 가정 하에, 카드사 기록과 영수증 데이터를 대조하여 자동으로 경비를 처리 해 주는 B2B 서비스입니다. 

<div align="center">
 <img src="https://github.com/BiBot-org/bibot/assets/36991763/9fdfd058-e55c-46e4-a718-c1f15b95eeb6" width="30%" height="auto" />
 <img src="https://github.com/BiBot-org/bibot/assets/36991763/4d8d72c5-2402-47e2-a164-4eb2b8152969" width="30%" height="auto" />
 <img src="https://github.com/BiBot-org/bibot/assets/36991763/14627b68-4d5f-4972-aa7e-326a0e7058e4" width="30%" height="auto" />
</div>

# 😃 팀 소개

<div align="center">
  <img src="https://github.com/BiBot-org/bibot/assets/36991763/383c3ce4-8701-4208-97b6-651fd14db664"/>
</div>

<div align="center" margin="10 0 0 10">
  <h2>맛있게 먹어요! 우리는 Code Recipe!</h3>
</div>
<div align="center" margin="10 0 0 10">
  <h4>맛있는 음식을 만들기 위해선 요리사의 역량 만큼이나 구성 된 재료도 중요합니다.</h2>
  <h4>팀원 개개인이 좋은 재료로써도 역할 하지만, 더 좋고 맛있는 소프트웨어를 위해 노력하는 팀입니다.</h2>
</div>

|노홍기|박준석|손우진(Team Leader)|박노명|김효은|
|----|-----|-----|-----|----|
|![image](https://github.com/BiBot-org/.github/assets/36991763/d23f51cd-e437-4ad7-b7e0-efe7e44d894d)|![image](https://github.com/BiBot-org/.github/assets/36991763/a086b038-38f8-437a-a696-66c2cec28c3e)|![KakaoTalk_20230612_000331814](https://github.com/BiBot-org/.github/assets/36991763/9b1a6129-8646-42bc-a4de-878fe1f873a1)|<p align='center'><img src = https://github.com/BiBot-org/bibot/assets/45251697/605d8991-98c2-4074-83f6-8238516dd6da width=130 height=160 /></p>|![깃헙프사](https://github.com/BiBot-org/.github/assets/125327573/bd69c2bc-04ca-499c-8d1c-e5cb82ccf48a)
|[@shghdrl1234](https://github.com/shghdrl1234)|[@Junseokee](https://github.com/Junseokee)|[@swj9707](https://github.com/swj9707)|[@pakc918](https://github.com/pakc918)|[@Kimpopo](https://github.com/Kimpopo)|
|BE|DevOps|BE & FE|FE, Publisher|FE, Publisher|
|- OCR API 연동 및 로직 개발</br>- 스켸쥴러 기반 경비처리 주기 업데이트 로직 개발</br>- Redis 기반 API 캐싱 환경 구축</br>- 테스트 엔지니어</br>|- Google Cloud Platform k8s 서버 운영</br> - GitOps CI/CD 구축</br>- Vault 기반 시크릿 서버 구축</br>- Prometheus & Grafana & Loki 기반 로그 수집 및 모니터링 구축 </br>- DB 서버 운영 및 연결</br>|- 애플리케이션 아키텍트 (멀티모듈 설계) </br>- Kafka기반 비동기 로직 개발</br>- SSO 구축, RBAC 구현(Keycloak)</br>- 앱 인증 로직 개발(NextAuth)</br>- 관리자 애플리케이션 개발</br>- 유저 앱 경비처리 프로세스 개발</br>- ReactQuery 기반 API 캐싱</br> - JPA Specification 기반 동적쿼리 검색 로직 개발</br>- 가상 결제 시스템 개발</br>|- 유저 애플리케이션 화면 및 기능 구성</br>- 유저 애플리케이션 퍼블리싱</br>- 서버 api(유저, 공지사항, 카드내역, 결재내역) 연동</br>- 이미지 업로드 기능</br>- NextUI(components, theme, darkmode) 적용</br>- PWA</br>|- 유저 애플리케이션 화면 및 기능 구성</br>- 유저 애플리케이션 퍼블리싱</br>- NextUI(components) 적용</br>- PWA</br>|


# [백엔드 Wiki](https://github.com/BiBot-org/bibot-backend/wiki)
- v1.0.0
  - API 문서
  - 아키텍쳐 문서
  - ERD 문서
  - 캐싱, OCR API 등 트러블슈팅 기록

# 🛠 Tech Stacks
## 🖥 Back End
|Language|Framework|DB|Messaging|Security|OCR|
|-------|--------|---|----|-----|----|
|<img src="https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=Java&logoColor=white"/>|<img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=for-the-badge&logo=Spring Boot&logoColor=white"/>| <img src="https://img.shields.io/badge/PostgreSQL-4169E1?style=for-the-badge&logo=PostgreSQL&logoColor=white"/> </br> <img src="https://img.shields.io/badge/redis-DC382D?style=for-the-badge&logo=redis&logoColor=white"/> |  <img src="https://img.shields.io/badge/Apache Kafka-231F20?style=for-the-badge&logo=Apache kafka&logoColor=white"/> |<img src="https://img.shields.io/badge/keycloak-008aaa?style=for-the-badge&logo=data:image/svg+xml;base64,PCEtLSBSZXBsYWNlIHRoZSBjb250ZW50cyBvZiB0aGlzIGVkaXRvciB3aXRoIHlvdXIgU1ZHIGNvZGUgLS0+Cgo8c3ZnIHdpZHRoPSI4MDBweCIgaGVpZ2h0PSI4MDBweCIgdmlld0JveD0iMCAwIDEwMjQgMTAyNCIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4KICAgPGNpcmNsZSBjeD0iNTEyIiBjeT0iNTEyIiByPSI1MTIiIHN0eWxlPSJmaWxsOiMwMDhhYWEiLz4KICAgPHBhdGggZD0iTTc4Ni4yIDM5NS41aC04MC42Yy0xLjUgMC0zLS44LTMuNy0yLjFsLTY0LjctMTEyLjJjLS44LTEuMy0yLjItMi4xLTMuOC0yLjFoLTI2NGMtMS41IDAtMyAuOC0zLjcgMi4xbC02Ny4zIDExNi40LTY0LjggMTEyLjJjLS43IDEuMy0uNyAyLjkgMCA0LjNsNjQuOCAxMTIuMiA2Ny4yIDExNi41Yy43IDEuMyAyLjIgMi4yIDMuNyAyLjFoMjY0LjFjMS41IDAgMy0uOCAzLjgtMi4xTDcwMiA2MzAuNmMuNy0xLjMgMi4yLTIuMiAzLjctMi4xaDgwLjZjMi43IDAgNC44LTIuMiA0LjgtNC44VjQwMC40Yy0uMS0yLjctMi4zLTQuOS00LjktNC45ek00NzcuNSA2MzAuNmwtMjAuMyAzNWMtLjMuNS0uOCAxLTEuMyAxLjMtLjYuMy0xLjIuNS0xLjkuNWgtNDAuM2MtMS40IDAtMi43LS43LTMuMy0ybC02MC4xLTEwNC4zLTUuOS0xMC4zLTIxLjYtMzYuOWMtLjMtLjUtLjUtMS4xLS40LTEuOCAwLS42LjItMS4zLjUtMS44bDIxLjctMzcuNiA2NS45LTExNGMuNy0xLjIgMi0yIDMuMy0ySDQ1NGMuNyAwIDEuNC4yIDIuMS41LjUuMyAxIC43IDEuMyAxLjNsMjAuMyAzNS4yYy42IDEuMi41IDIuNy0uMiAzLjhsLTY1LjEgMTEyLjhjLS4zLjUtLjQgMS4xLS40IDEuNiAwIC42LjIgMS4xLjQgMS42bDY1LjEgMTEyLjdjLjkgMS41LjggMy4xIDAgNC40em0yMDIuMS0xMTYuN0w2NTggNTUwLjhsLTUuOSAxMC4zTDU5MiA2NjUuNGMtLjcgMS4yLTEuOSAyLTMuMyAyaC00MC4zYy0uNyAwLTEuMy0uMi0xLjktLjUtLjUtLjMtMS0uNy0xLjMtMS4zbC0yMC4zLTM1Yy0uOS0xLjMtLjktMi45LS4xLTQuMmw2NS4xLTExMi43Yy4zLS41LjQtMS4xLjQtMS42IDAtLjYtLjItMS4xLS40LTEuNmwtNjUuMS0xMTIuOGMtLjctMS4yLS44LTIuNi0uMi0zLjhsMjAuMy0zNS4yYy4zLS41LjgtMSAxLjMtMS4zLjYtLjQgMS4zLS41IDIuMS0uNWg0MC40YzEuNCAwIDIuNy43IDMuMyAybDY1LjkgMTE0IDIxLjcgMzcuNmMuMy42LjUgMS4yLjUgMS44IDAgLjQtLjIgMS0uNSAxLjZ6IiBzdHlsZT0iZmlsbDojZmZmIi8+Cjwvc3ZnPg=="/>  | <img src="https://img.shields.io/badge/clova-00FF00?style=for-the-badge&logo=clova&logoColor=white"/>  |

- v1.0.0
  - OpenJDK 17
  - Spring boot 3.0.6
  - Keycloak 21.1.1
 
