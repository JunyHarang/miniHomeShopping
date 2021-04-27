package shopping.utility;

import java.util.Iterator;

public class Paging {
	private int totalCount = 0;					//	총 행(레코드)수
	private int totalPage = 0;						// 전체 페이지 수
	private int pageNumber = 0;				// 현재 페이지 번호
	private int pageSize = 10;						// 한 페이지에 보여줄 건수
	private int beginRow = 0;						// 현재 페이지의 시작 행 (랭킹)
	private int endRow = 0;							// 현재 페이지의 끝 행(랭킹)
	private int pageCount = 10;					// 하단에 보여줄 페이지 링크 수
	private int beginPage = 0;						// 페이징 처리 시작 페이지 번호
	private int endPage = 0;							// 페이징 처리 끝 페이지 번호
	
	private String url = "";								//	URL
	private String pagingHtml = "";		// 하단 숫자 페이지 링크
	private String pagingStatus = "";	// 상단 우측 현재 페이지 위치 
	
	private String mode = "";						// 검색 모드(전체 검색은 all)
	private String keyword = "";				// 검색 키워드
	
	
	public Paging(String param_pageNumber, int totalCount, String url, String mode, String keyword) {
		if (param_pageNumber == null || param_pageNumber.equals("null") || param_pageNumber.equals("")) {
			param_pageNumber = "1";
		}
		
		this.pageNumber = Integer.parseInt(param_pageNumber);
		this.totalCount = totalCount;
		this.url = url;
		this.mode = mode;
		this.keyword = keyword;
		
		// 계산이 필요한 나머지 변수 선언
		
		this.totalPage = (int)Math.ceil((double)totalCount / pageSize);
		this.beginRow = (pageNumber - 1) * pageSize + 1;
		this.endRow = pageNumber * pageSize;
		this.beginPage = (pageNumber - 1) / pageCount * pageCount + 1;
		this.endPage = this.beginPage + pageCount - 1;
		
		if(totalPage < endPage) {
			endPage = totalPage;
		}
		
		this.pagingHtml = this.getPagingHtml(url);
		this.pagingStatus = "총" + totalCount + "건[" + pageNumber + "/" + totalPage + "]";
		
		
		this.Display();
	} // pageTest Method 끝


	private String getPagingHtml(String url) {
		
		String result = "";
		
		// part 맨 처음, 이전
		if (pageNumber <= pageCount) {
			System.out.println("이전 페이지가 없습니다.");
		} else {
			
		}
		// part 중간(beginPage ~ endPage 까지)
		for (int i = beginPage; i <= endPage; i++) {
			if (i == pageNumber) {	// i가 현재 페이지이면
				
			} else {
				
			}			
		}
		// part 다음, 끝
		if (pageNumber >= (totalPage / pageCount * pageCount + 1)) {
			System.out.println("다음 페이지가 없습니다.");		
		} else {
					
		}
		
		return result;
		
	} // getPagingHtml Method 끝


	private void Display() {
		System.out.println("totalCount : " + totalCount);
		System.out.println("totalPage : " + totalPage);
		System.out.println("pageNumber : " + pageNumber );
		System.out.println("pageSize : " + pageSize);
		System.out.println("beginRow : " + beginRow);
		System.out.println("endRow : " + endRow);
		System.out.println("pageCount : " + pageCount);
		System.out.println("beginPage : " + beginPage);
		System.out.println("endPage : " + endPage);
		System.out.println("url : " + url);
		System.out.println("pagingHtml : " + pagingHtml);
		System.out.println("pagingStatus : " + pagingStatus);
		System.out.println("mode : " + mode);
		System.out.println("keyword : " + keyword);
		
	}
	
} // Class 끝
