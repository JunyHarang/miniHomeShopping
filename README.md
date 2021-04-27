# 주니하랑의 미니 홈쇼핑 제작

### 본 내용은 설진욱(ugcadman@naver.com)강사(님)의 강의를 토대로 작업 중 입니다.
본 내용에 대한 저작권 등에 대한 문의는 위의 강사(님)에게 부탁 드립니다.

2021년 04월 22일 작업  내용

사용자 프로맨 생성
프로맨 접속 아이콘 생성
프로맨 위한 테이블 생성
우편 번호 테이블 생성 및 벌크 로딩
WEB-INF/lib/*.jar 파일 쫀쨰 여부 확인
WEB-INF/todolist.txt 파일 확인
최초 시작 파일 : start.jsp

common.jsp : 모든 문서에서 공통적으로 사용되는 내용을 기록한 파일
twelve : 그리드 시스템의 100%를 의미하는 숫자 (12)
JSTL 태그 라이브러리
loginfo 속성은 로그인 한 사람의 member 객체 정보, session 영역에 바인딩 되어 있다.
whologin은 현재 로그인 상태 저장 변수
whologin은 미로그인(0), 일반 로그인(1), 관리자 로그인(2)로 설정
						- 관리자는 아이디가 'admin'이다.
						
contextpath : 현재 진행중인 프로젝트(컨텍스트)의 이름
mappingName: 요청에 응답하는 uri pattern 이름
YesForm : 폼 태그에서 사용할 공용 변수
NoForm : 폼이 없을 때 사용할 공용 변수

부트 스트랩 관련 파일 링크 

네비게이션 바 만들기

아래 내용은 실제 프로젝트 시 액셀과 같은 걸로 표로 미리 만들어 본 뒤 작업할 것!

-- 계시판 필요 기능 --

									     비회원	      				               일반회원					            관리자

============================================================================================================

계시물 등록                              X                          O                        O
계시물 수정								X								O							 O
계시물 삭제								X								O							 O
계시물 목록 보기							O								O							 O
계시물 상세 보기							X								O							 O
계시물 댓글 달기							X								O							 O


게시물 수정, 삭제, 상세 보기, 답글 작성은 풀다운 메뉴가 아니고, 목록 보기 페이지에서 구현하겠다.

-- 상품 필요 기능 --

													비회원					일반회원					관리자

================================

상품 등록										X								X							 O
상품 수정										X								X							 O
상품 삭제										X								X							 O
상품 목록 보기								X								O							 O
상품 상세 보기								X								O							 O
상품 리뷰										X								O							 O


상품 수정, 삭제, 상세 보기, 리뷰는 풀다운 메뉴가 아니고, 목록 보기 페이지에서 구현하겠다.


-- 쇼핑몰 필요 기능 --

															비회원					일반회원					관리자

================================

나의 쇼핑 내역										X								O							 O
장바구니 보기										X								O							 O



Validator 인터페이스 만들기
		유효성 검사 수행하기 위한 인터페이스
		유효성 검사가 필요한 일반 컨트롤러 XXXController는 오버라이팅 하여 구현
		유효성 검사에 실패 시 false 값 반환
		public boolean validate(HttpServletRequest request);
		SuperClass가 상속 받는다.
		
유효성 검사 절차

validate 메서드 에서 문제가 있는 파라미터에 접두가 err을 붙혀 request 영역에 바인딩한다.
양식 태그에 별도 <span> 태그를 다음과 같이 작성.
	<span class="form-control-static err">${errname}</span>
공용 파일에 class="err"에 대한 스타일 지정

오류 경고 메시지

어떤 로직에서 발생한 오류나 문제점에 대하여 사용자에게 경고 메시지는 다음과 같이 보여줄 것이다.
SuperClass에 setErrorMessage(보여줄 메시지) 메서드 사용
컨트롤러 파일에서 request 영역에 "errmsg"라는 속성에 메시지 저장
common file에서 해당 오류/경고 메시지를 부트 스트랩의 Alerts 기능으로 출력

회원 목록 보기
관리자만 가능

게시물 목록 보기
Command : boList
boList.jsp
Borad Dao : SelectDataList(int beginRow, int endRow) 

상품 목록 보기
Command : prList
prList.jsp 파일은 boList.jsp를 복사하여 생성
ProductDao의 SelectDataList(int beginRow, int endRow) 


*/2021년 04월 26일 */

회원 가입
아이디 중복 체크
우편 번호 찾기
폼 로딩 시 매니져 콤보 박스 자동으로 채우기
회원 가입 클릭 시 유효성 검사
DB에 추가

주요 파라미터
isCheck :
	값이 false이면 회원 가입 불가
	아이디 중복 체크를 통과했을 경우에만 true로 변경
	사용자가 새로운 아이디 입력(onkeyup Event) 시 false로 변경
	
postcodes Table : 우편 번호 관련 Table

우편 번호 Bean Class
shopping.common.model.postcode

우편 번호 관련 Dao Class
CompositeDao Class SelectDataZipcode() Method
'도로 이름' 또는 '동면읍 이름' 또는 '검색할 단어' 컬럼에서 조건이 맞으면 모두 읽어 온다.

zipCheck 70 ~ 75 Line :

~~~

<c:set var="address" value="${fn:trim(oneitem.si_nm)} 
																${fn:trim(oneitem.sgg_nm)} 
																${fn:trim(oneitem.rd_nm)} 
																${fn:trim(oneitem.bd_ma_sn)} 
																(${fn:trim(oneitem.emd_nm)} 
																${fn:trim(oneitem.search_word)})" />

~~~

fn은 function의 약자이고, trim은 공백을 없애기 위해 사용하는 메서드이다.

AREA_CD 우편번호
SI_NM 시도 (서울특별시, 경기도 등)
SGG_NM 시구군(군산시, 창원시 합포구 등)
BC_MA_SN 번지
RD_NM 도로 이름
EMD_NM 동면읍
SEARCH_WORD 검색할 단어

폼 로딩 시 매니저 콤보 박스 자동 채우기
MemberInsertController의 doGet()에 작업

MemberDao Class의 GetManagerList() 만들기
meInsertForm.jsp에서 콤보 박스 채워 넣기


### 2021년 04월 27일 작업 내용

** 1. 게시물 목록 보기 **
** 2. 페이지 당 10개씩 목록 출력되게 만들기 **
** 3. 페이징 처리 가능하게 만들기 **
** 4. 필드 검색이 가능하게 만들기 **
** 5. 하이퍼 링크 이용 수정, 삭제 답글 페이지 이동 가능하게 만들기 **

##### 게시판 관련 사용 변수
---<br>

<p align="center"><img src="./src/main/webapp/common/ref_images/페이징_관련_변수_목록.png", width="700" height="500"></p>

totalCount: 총 행(레코드) 건수<br>
totalPage: 전체 페이지 수<br>
pageNumber: 현재 페이지 번호<br>
pageSize: 한 페이지에 보여줄 행(레코드) 수<br>
url: Command 이름(boList)입력<br>
beginRow: 게시판 맨 위에 오는 행(내용)<br>
endRow: 게시판 맨 밑에 오는 행(내용)<br>
pageCount: 게시판 목록을 넘기기 위해 사용<br>
beginPage: 게시판 목록을 넘기기 위해 사용하는 것 중 맨 앞<br>
endPage: 게시판 목록을 넘기기 위해 사용하는 것 중 맨 뒤<br>
pagingHtml: 게시판 목록 넘기기 위해 사용하는 버튼(?)<br>
pagingStatus: 검색 결과 건수 표현<br>
	- "총" + totalCount + "건[" + pageNumber + "/" + totalPage + "]" 
mode: 필드 검색 관련<br>
keyword: 검색할 Keyword<br><br>
---

### 관련 Command : boList

### 관련 Dao : BoardDao
	- SelectDataList(int beginRow, int endRow, String mode, String keyword)
	  	beginRow: 해당 페이지의 시작 랭킹 숫자
		endRow: 해당 페이지의 끝 랭킹 숫자
		mode: 필드 검색 컬럼(Table에 실제 존재하는 컬럼 이름으로 정해야 한다.)
		keyword: 검색 키워드

### 관련 Controller : BoardListController

### 관련 JSP : boList.jsp
		
### 게시물 데이터 30개 추가를 위해 topN 구문 만들기
#### paging.java Class 구현하기
	- Paging Class는 페이징 처리를 도와주는 Utility Class입니다.

### 게시판 Table에 대한 pl-sql For문
#### (연습을 위해 게시판에 30개의 동일한 게시물을 올리기 위해 pl-spl문 사용)
~~~ 
begin
	for i in 1..30 loop
	insert into boards 
	values(myboard.nextval, '열공합시다', 'kang', '1234', '멍멍멍', default, default,  
	default, default, default, default);
end loop;

end;
/

commit;
~~~

### topN 구문
#### 게시판 페이징 처리를 위해 실습 하겠습니다.
~~~
select ranking, no, subject, writer, password, content, readhit, regdate, groupno, orderno, depth, remark
from ( select ranking, no, subject, writer, password, content, readhit, regdate, groupno, orderno, depth, remark, rank() over (oder by no desc) as ranking from boards )
where ranking between 1 and 10;
~~~

## paing Class 작성
shopping.utility 패키지에서 작성

기존 Paging Class Backup 이 후 작업

pageTest.java: Paging Test를 위한 Java Class
paging.java: Paging Class


