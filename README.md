#  🍳Overview

### 익명 의견 교환 게시판


#  🚩Project
<details>
<summary><strong>ERD</strong></summary>
<div markdown="1"> 
  <img alt="image" src="https://github.com/KwonHyeokGeon/Misson_hyeokgeon/blob/main/src/main/resources/static/images/erd.png">
</div>
</details>

<details>
  <summary><strong>URL</strong></summary>
<div markdown="1">
  <img src="https://github.com/KwonHyeokGeon/Misson_hyeokgeon/blob/main/src/main/resources/static/images/endpoint.png">
</div>
</details>


#  📍 주요 기능

## 필수 과제
### 1. 게시판 기능
* Board
  * 게시판의 목록과 선택한 게시판의 게시글을 볼 수 있다.
  * 모든 게시판의 게시글 전체를 볼 수 있다
### 2. 게시글 기능
* Article
  * 게시글을 작성할 때 제목, 내용, 비밀번호를 제출한다.
  * 각 게시글은 단일 게시글 화면이 존재한다.
  * 게시글 작성 시 입력했던 비밀번호와 수정 및 삭제 시 입력한 비밀번호를 비교하여 일치할 때 수정 및 삭제가 이루어진다.
  * 비밀번호가 일치하지 않을 때 경고창을 띄운다.
  
### 3. 댓글 기능
* Comment
  * 단일 게시글 화면에서 댓글 작성, 수정 및 삭제가 가능하다
  * 댓글을 작성할 때 제목, 내용, 비밀번호를 제출한다.
  * 댓글 수정 및 삭제는 게시글 수정 및 삭제 기능과 동일하다.

#  💊 진행 중 발생한 어려움
<details>
<summary><strong>1. There was an unexpected error (type=Internal Server Error, status=500).
Name for argument of type [java.lang.Long] not specified, and parameter name information not available via reflection. </strong></summary>

<div markdown="1"> 

* 접속에 문제가 없음을 확인하고 이후 코드변경이 없었음에도 불구하고 article/{articleId}로 단일게시글을 조회하려할 때 제목에 상기한 에러가 발생했다.
코드가 같은데 어쩔 땐 접속이 되고 어쩔 땐 에러가 발생하여 controller나 service의 문제는 아닌 것 같아 검색해보니 에러 메세지 그대로 클래스 파일의 파라미터 이름 정보가 없는 것이 문제인 것 같았다.
</div>

```
public String readOne(@PathVariable Long articleId, Model model) {
    // 메소드 내용...
}
``` 
* 위의 코드는 @PathVariable의 name과 파라미터명이 동일하여 @PathVariable(name = "articleId")이 생략되어 있는 상태이다
생략했을 때 컴파일러 debug모드 컴파일이 설정되어있어야만 스프링이 @PathVariable의 name을 찾을 수 있다고 한다.
- build.gradle에 아래의 코드 추가
```
compileJava {
	options.compilerArgs.addAll(['-parameters', '-Xlint:unchecked'])
	options.debug = true
	options.encoding = 'UTF-8'
}
```
</details>
<details>
  <summary><strong> 2. 비밀번호 인증 </strong></summary>

* 게시글이나 댓글 수정 및 삭제 시 비밀번호가 일치하지 않을 경우 return boolean으로 alert창에서 '비밀번호가 일치하지 않습니다'을 띄우고 싶었다. 

* 비밀번호가 일치하는 지 아닌 지는 controller에서 비교하는데 controller의 return 값을 자바스크립트에서 어떻게 가져올 지가 문제였다. controller에서 model로 전달해도 되지만 axios로 post요청을 보내는 포스팅을 보고 따라해보았다.

* button을 클릭하면 비밀번호 인증 경로로 입력된 password를 전송해 일치여부에 따라 받아온 response.data로 form을 제출할지 alert를 띄울 지 동작하도록 했다. RequestBody로 프론트에서 컨트롤러로 데이터를 받아와서 ResponseEntity를 사용해서 컨트롤러에서 프론트로 값을 넘겨줬다.
</details>

<details>
<summary><strong>3. 게시글 삭제 후 뒤로가기</strong></summary>

* 게시글 삭제 후 redirect:/boards/{boardId}로 이동하는데 여기서 뒤로가기 하면 게시글이 이미 DB에서 삭제되었음에도 삭제 이전화면에 값이 input value에 들어간 채로 남아있었고 그 상태에서 새로고침을 하면 에러페이지가 나온다. 
* 이건 해결하지못했다.
</details>




# 🖥️ 프로젝트 실행/테스트 방법

#### 실행
##### git clone 후 실행 전 src/main/resources/static 경로에 npm install로 package.json내의 라이브러리(bootstrap, axios) 설치 필요


#### 테스트
1. localhost:8080/boards에서 전체 게시판 조회
2. 게시판으로 이동 후 작성 버튼 클릭하여 게시글 작성
3. 작성 후 홈 및 게시판에서 게시글 게시 확인
4. 게시글 제목 클릭하여 게시글 상세조회 및 댓글 조회
5. 게시글 수정 비밀번호 입력 후 수정 오른쪽 확인 눌러 수정 (비밀번호 : 1111)
6. 댓글 내용과 비밀번호 입력 후 댓글 작성
7. 댓글 삭제 비밀번호 입력 후 삭제 오른쪽 확인 눌러 삭제 (비밀번호 : 1111)
8. 게시글 삭제 비밀번호 입력 후 삭제 오른쪽 확인 눌러 삭제 (비밀번호 : 1111)
