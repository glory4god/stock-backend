# 주(식하는) 사(람들의) (이야)기

## 프로젝트 소개
- 처음 개발 공부를 시작하면서 단순히 공부만 하는것에 그치지않고 직접 내가 해보고 싶었던 것을 개발하면서 성장하자는 생각과 당시 가장 흥미롭게 생각했던 주식이라는 소재를 갖고, 기존에 존재하는 게시글, 채팅으로만 소통하던 주식 관련 커뮤니티가 아닌 주식에 관심있는 사람들의 더 다양한 정보를 통해 소통하는 커뮤니티를 만들어보자라는 생각으로 시작했던 프로젝트입니다.
- 다양한 정보란 사용자가 원하는 기업에 내가 원하는 조건에 따른 차트를 직접 그려보고 그 차트를 통해서 전달하고자하는 말이 무엇인지를 소개하는 리포트 형식의 커뮤니티 게시판을 만드려합니다.


#### 로고

- 로고의 의미는 프로젝트 이름의 주사기를 누를 때 나오는 물줄기가 주식차트의 실시간 틱이 움직임과 비슷함을 통해 제작하였다.
<img src="https://user-images.githubusercontent.com/78658208/145834912-be7df61c-3174-44d9-8961-f907dde6ea16.png" width="200" height="200"/>


## Stack

#### BE

- java / spring boot framework
- mariaDB / SQL
- AWS EC2 / AWS RDS
- tool - (inteliJ, heidi SQL)
- WebSocket

#### FE

 - https://github.com/glory4god/next-typescript-tailwindcss-stock



## Page 별 Service

### 1. /chart/line
<img src="https://user-images.githubusercontent.com/78658208/145825785-651ce2ed-4c9e-4b84-a9b7-45f651b2361b.png" width="100%" height="500"/>

 - 주식 종목의 조건별 조회기능을 RestApi를 통해 제공한다. (GetService / ApiController)
 - 조회한 차트정보에 대한 게시글을 작성하는 기능을 제공한다. (PostService / PostApiController)

### 2. /news

<img src="https://user-images.githubusercontent.com/78658208/145825444-471be70e-448d-4ad2-8eaf-ef17885d4b72.png" width="100%" height="500"/>

 - Front로 부터 검색할 토픽을 요청받은 후, Naver News API를 이용해 해당 검색어에 대한 데이터를 제공받아 Front에 다시 응답한다. (NewsService / NewsApiController)
 - Front에 응답 전에 DB에 검색내용을 저장하여 데이터를 쌓는다. (NewsService)

### 3. /board/chart, /board/chart/:[boardId]

<img src="https://user-images.githubusercontent.com/78658208/145831613-a5a3a529-f7a5-4be3-a6d6-a9b75160be32.png" width="100%" height="500"/>

 - 글작성, 수정, 삭제, 좋아요, 싫어요, 조회수 등의 기능으로 게시글에 대한 CRUD 기능이 존재한다. (PostService / PostApiController)
 - 로그인(카카오 계정)한 유저만 사용 가능하다. ( KakaoLoginService / KakaoLoginController)


## 실시간 채팅

 - webSocket을 이용한 실시간채팅으로 양방향 통신이 가능하다. (webSocketService)
 - 로그인(카카오 API)한 유저만 채팅에 참여할 수 있다. ( KakaoLoginService / KakaoLoginController)
 
<img src="https://user-images.githubusercontent.com/78658208/145834369-8e6634d3-c560-45c7-b8b7-529344ef9496.png" width="330" height="450"/>
 

## 프로젝트 기술서는 아래 링크에 있습니다.
https://fixed-mask-ed8.notion.site/1e018fbaa0b34f5d86be186ccd062544
