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
접속에 문제가 없음을 확인하고 이후 코드변경이 없었음에도 불구하고 article/{articleId}로 단일게시글을 조회하려할 때 제목에 상기한 에러가 발생했다.
코드가 같은데 어쩔 땐 접속이 되고 어쩔 땐 에러가 발생하여 controller나 service의 문제는 아닌 것 같아 검색해보니 에러 메세지 그대로 클래스 파일의 파라미터 이름 정보가 없는 것이 문제인 것 같았다.
</div>

```
public String readOne(@PathVariable Long articleId, Model model) {
    // 메소드 내용...
}
``` 
위의 코드는 @PathVariable의 name과 파라미터명이 동일하여 @PathVariable(name = "articleId")이 생략되어 있는 상태이다
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
