# 🔮 MEGAZINE만들기

### 협업 규칙

 <br>

## 💻 Commit 규칙
---
```
< 예시 >
[BE] feat: SPRING MEGAZINE #12
```

### 1. 커밋 종류
> 두 번째로 수정한 종류에 따라 커밋 메시지를 선택

|메시지명|설명|
|---|---|
|feat|새로운 기능 추가 관련|
|fix|버그 수정|
|test|테스트 코드, 리팩토링 테스트 코드 추가|
|refactor|코드 리팩토링(기능향상)|
|chore|빌드 업무 수정, 패키지 매니저 수정|
|docs|문서 수정(md, git관련 파일, 이미지파일 수정)|
|style|코드 formatting, 세미콜론(;) 누락, 코드 변경이 없는 경우|

### 2. 관련 이슈
> 작성한 커밋과 관련된 이슈 번호를 매핑

- 이슈 번호뒤에 아래에 써놓은 명령어를 붙여서 커밋 날리면 자동으로 이슈가 close 된다.   
  `close / closes / closed / fix / fixes / fixed / resolve /resolves / resolved`
```
< 예시 >
[BE] feat: SPRING MEGAZINE close #1
```

### 3. 보안 관련

- **(중요)** 어떠한 KEY값이나 DB 접속 정보가 포함된 커밋을 날리지 않는다.
- 한 번이라도 날리면 커밋 로그가 남아서 보안에 취약하기 때문~
- 환경변수나 json/gitignore 등의 방식을 사용해서 원격 repo에는 절대 올리지 않는다.



---

### 4. Issue

- 앞으로 할 일이나 버그 등을 기록한다.
- 이슈에 맞는 라벨을 알맞게 선택한다.

 <br>
 
---

### 회원가입 
요구사항
- 닉네임은 `최소 3자 이상, 알파벳 대소문자(a~z, A~Z), 숫자(0~9)`로 구성하기
- 비밀번호는 `최소 4자 이상이며, 닉네임과 같은 값이 포함된 경우 회원가입에 실패`로 만들기
- 비밀번호 확인은 비밀번호와 정확하게 일치하기

|컬럼명|컬럼 타입|중복허용|설명|
|:---:|:---:|:---:|:---:|
|username|String|X|회원ID(PK)|
|realName|String|O|회원 이름|
|password|String|O|패스워드|
