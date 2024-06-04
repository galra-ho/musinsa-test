# 무신사 과제

## 1. 기능 설명
구현한 기능은 아래와 같습니다
1. 카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회하는 API
2. 단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을 조회하는 API 
3. 카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회하는 API
4. 상품 브랜드 추가/업데이트/삭제 하는 API

### 구현방향
- 카테고리 코드의 경우 추가,등록,삭제가 존재하지 않기 때문에 로컬캐싱을 이용하여 캐싱하여 사용하도록 하였습니다.
- 각각의 레이어마다 Role 을 정해 해당 역할만 수행하도록 하였습니다.
- 도메인영역에 최대한 핵심 로직을 넣어 테스트가 쉽도록 하였습니다.

## 2. 개발환경
- SpringBoot - 3.3.0 
- h2 - 1.4.197
- kotlin - 1.9.24
- Spring Data JPA

## 3. 코드 빌드 방법

1. git 프로젝트 저장소 복제 
```
https://github.com/galra-ho/musinsa-test.git
```

2. 파일 오픈 후 OK 버튼을 눌러서 실행
![https://github-production-user-asset-6210df.s3.amazonaws.com/170947624/336161188-fafc7a28-0fe0-4e2c-a654-a6844940ceef.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAVCODYLSA53PQK4ZA%2F20240603%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20240603T165957Z&X-Amz-Expires=300&X-Amz-Signature=78081ecd9a685607876af12cef680c4fd923a27881c78252209abf50e117490e&X-Amz-SignedHeaders=host&actor_id=170947624&key_id=0&repo_id=680064111](img.png)

## 4. 테스트 실행 방법
application, domain, presenter 영역을 잡고 
Run Test in ... 을 클릭하며 실행시키면 전체 테스트가 돌아갑니다
![https://github-production-user-asset-6210df.s3.amazonaws.com/170947624/336162620-6d822e0f-bc5c-4a55-a15e-60212fd7e6d8.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAVCODYLSA53PQK4ZA%2F20240603%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20240603T170622Z&X-Amz-Expires=300&X-Amz-Signature=d7f8653f5fac552661ac300b5b737c9f28629de489fa1863419f613d7c1a0f4a&X-Amz-SignedHeaders=host&actor_id=170947624&key_id=0&repo_id=806562167](img_1.png)
