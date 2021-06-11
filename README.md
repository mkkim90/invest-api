# 투자 API

## 기술 스택
- SpringBoot, JPA, H2, Junit, gradle

## 기능 요구 사항

### 1. 전체 투자 상품 조회 API
- 상품 모집 기간 내의 상품만 응답
- 상품ID, 상품 제목, 총 모집 금액, 현재 모집금액, 투자자 수, 투자모집상태(모집중, 모집완료), 상품 모집 기간

## 예제
- 테스트용으로 데이터는 하기 값으로 넣어놨습니다.
- GET http://localhost:8080/api/products
```  
  [
  {
  "id": 1,
  "title": "개인신용 포트폴리오",
  "totalInvestingAmount": 1000000.00,
  "currentInvestingAmount": 2000.00,
  "numberInvestors": 2,
  "productStatus": "INVESTING",
  "startedAt": "2021-06-11T18:50:09.388",
  "finishedAt": "2021-07-11T18:50:09.388"
  },
  {
  "id": 2,
  "title": "부동산 포트폴리오",
  "totalInvestingAmount": 50000000.00,
  "currentInvestingAmount": 8000.00,
  "numberInvestors": 2,
  "productStatus": "INVESTING",
  "startedAt": "2021-06-11T18:50:09.388",
  "finishedAt": "2021-07-11T18:50:09.388"
  }
  ]
```

### 2. 투자하기 API
- 사용자 식별값 숫자 형태 X-USER-ID라는 HTTP Header로 전달
- 입력값 : 사용자 식별값, 상품ID, 투자 금액을 입력값으로 받습니다.
- 총 투자 모집 금액(total_investing_amount)을 넘어서면 sold-out상태
- 상품ID가 없을 경우, 없는 투자 상품입니다.
- 상품ID가 기간이 모집기간에 해당하지 않을 경우, 모집 기간이 아닙니다.

## 예제
- 테스트용으로 데이터는 하기 값으로 넣어놨습니다.
- HTTP Header X-USER-ID 1234, HTTP METHOD GET, http://localhost:8080/api/invest

```
[
    {
        "id": 1,
        "productId": 1,
        "productTitle": "개인신용 포트폴리오",
        "totalInvestingAmount": 1000000.00,
        "myInvestingAmount": 1000.00,
        "investDate": "2021-06-11T19:04:56.835"
    },
    {
        "id": 2,
        "productId": 2,
        "productTitle": "부동산 포트폴리오",
        "totalInvestingAmount": 50000000.00,
        "myInvestingAmount": 4000.00,
        "investDate": "2021-06-11T19:04:56.868"
    }
]
```

### 3. 나의 투자상품 조회 API
- 사용자 식별값 숫자 형태 X-USER-ID라는 HTTP Header로 전달
- 내가 투자한 모든 상품을 반환
- 상품ID, 상품제목, 총 모집금액, 나의 투자금액, 투자일시

## 예제
- HTTP METHOD POST, http://localhost:8080/api/invest
- Parameter
    - HTTP Header X-USER-ID :  1234
    - BODY
        ```
            {
                "productId" : 1,
                "amount" : 100
            }
        ```
      
- OUT PUT
```
{
    "id": 5,
    "productId": 1,
    "productTitle": "개인신용 포트폴리오",
    "totalInvestingAmount": 1000000.00,
    "myInvestingAmount": 100,
    "investDate": "2021-06-11T19:08:35.365"
}
``