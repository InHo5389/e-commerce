# 이커머스 프로젝트 만들기

## 프로젝트 기능 및 설계와 요구 사항

### 공통

* 회원을 포함한 모든 엔티티는 데이터베이스에 저장될 때, 등록 시간과 업데이트 시간을 등록하도록 하겠습니다.

* 로그인 기능
  * 고객이나 셀러나 로그인을 할 수 있고 로그인시 회원가입때 사용한 아이디와 패스워드가 일치해야 합니다.
  * 로그인에 성공하면 JWT를 발급해주며 이를 통해 장바구니 접근이 허가 되고 로그인 하지 않으면 장바구니 접근이 불가합니다.

* 상품 목록 조회 기능
  * 로그인하지 않은 사용자를 포함한 모든 사용자는 상품을 조회할 수 있습니다.
  * 상품은 최신순으로 기본 정렬 되며,추천이 많은순/적은순으로도 정렬이 가능합니다.
  * 상품 목록 조회 시 응답에는 상품명과 작성일, 추천 수의 정보가 필요합니다.
  * 상품 종류가 많을수 있으므로 paging 처리를 합니다.

* 특정 상품 조회 기능
  * 로그인하지 않은 사용자를 포함한 모든 사용자는 상품을 조회할 수 있습니다.
  * 상품명,상품 등록일,추천수가 조회됩니다.

### 고객

* 회원가입 기능
  * 이메일과 비밀번호,전화번호,이름, 닉네임(별명), 나이,주소를 입력받습니다.
  * 이메일은 중복될 수 없습니다.
  * 비밀번호와 이름, 닉네임과 나이는 변경할 수 있습니다.
  * 비밀번호는 암호화되어 데이터베이스에 저장됩니다.
  * USER 권한이 주어집니다

* 장바구니 상품 담기 기능
  * 로그인을 하여야 장바구니 접근이 가능하고 상품을 한 개나 여러개 담을 수 있습니다. 
  
* 장바구니 조회 기능
  * 로그인을 하여야 장바구니 접근이 가능하고 장바구니를 조회할 수 있습니다.
  
* 장바구니 상품 삭제 기능
  * 로그인을 하여야 장바구니 접근이 가능하고 상품을 삭제할 수 있습니다. 

### 셀러

* 회원가입 기능
  * 이메일과 비밀번호, 이름, 닉네임(별명), 나이,주소를 입력받습니다.
  * 이메일은 중복될 수 없습니다.
  * 비밀번호와 이름, 닉네임과 나이는 변경할 수 있습니다.
  * 비밀번호는 암호화되어 데이터베이스에 저장됩니다.
  * ADMIN 권한이 주어집니다

* 상품 등록 기능
  * ADMIN 권한을 가져야 상품 등록을 할 수 있습니다.

* 상품 수정 기능
  * ADMIN 권한을 가져야 상품 수정을 할 수 있습니다.

* 상품 삭제 기능
  * ADMIN 권한을 가져야 상품 삭제를 할 수 있습니다.
  
  #### 추후 추가할것
  
  * 주문 기능
  * 결제 기능


## ERD
![e-commerce-erd](https://user-images.githubusercontent.com/80667642/236681485-72c255f6-85a3-44bf-9277-458e8c5d700c.png)

## Trouble Shooting
[go to the trouble shooting section](https://github.com/InHo5389/e-commerce/blob/main/doc/TROUBLE_SHOOTING.md#trouble-shooting)

### Tech Stack
<div align=center> 
  <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> 
  <img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> 
  <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> 
  <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
  <img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">
</div>
