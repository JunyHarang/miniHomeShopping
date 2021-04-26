# 주니하랑의 첫 웹 페이지 구성기

### 본 내용은 설진욱(ugcadman@naver.com)강사의 강의를 토대로 작업 중 입니다.

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

													비회원					일반회원					관리자

===============================================================================

계시물 등록									X								O							 O
계시물 수정									X								O							 O
계시물 삭제									X								O							 O
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

<c:set var="address" value="${fn:trim(oneitem.si_nm)} 
																${fn:trim(oneitem.sgg_nm)} 
																${fn:trim(oneitem.rd_nm)} 
																${fn:trim(oneitem.bd_ma_sn)} 
																(${fn:trim(oneitem.emd_nm)} 
																${fn:trim(oneitem.search_word)})" />

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
		