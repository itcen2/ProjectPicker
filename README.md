# ProjectPicker (BE)

### 프로젝트 구인 사이트 입니다.

---



Team name: 갈비탕 

Project name : Project Picker

Project excution period : 2023. 02. 03 ~ 2023. 02.16

Logo

![로고](https://user-images.githubusercontent.com/33335762/217688415-a68c412c-75ea-43fa-ab33-49e9d8bb368e.png)


## 핵심

---
- 프로젝트 구인 웹 사이트
-  작성중 ...


## 구현 및 구현 환경


![구현환경](https://user-images.githubusercontent.com/33335762/217688446-53ee2a65-314a-4b67-ae5b-f011bfec9e5c.png)


## 설계


![관계형 DB 설계](https://user-images.githubusercontent.com/33335762/217688455-2637eb6a-19cd-40b4-b7d4-b23dba5e16a6.png)



### Study 내용

DB - Repository - service - controller - view 


- DTO :
계층 간 데이터를 주고 받는데 사용되는 객체 

계층이란? 뷰, 컨트롤러, 서비스, DAO, DB

- Controller : 사용자의 요청을 받아 해당 요청을 수행하는 데 필요한 로직을 호출하고,
그 결과를 포함해 응답을 해 주는 디스패처(Dispatcher) 역할

- service: 비지니스 로직을 수행하기 위한 메서드를 정의함


---
[ 빌더 패턴(Builder Pattern)의 장점 ]
필요한 데이터만 설정할 수 있음

유연성을 확보할 수 있음

가독성을 높일 수 있음

변경 가능성을 최소화할 수 있음