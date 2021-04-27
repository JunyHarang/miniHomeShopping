package shopping.utility;

public class PageTest {
	public static void main(String[] args) {
		String param_pageNumber = "13";
		int totalCount = 250;
		String url = "boList";
		String mode = "writer"; // 검색 모드
		String keyword = "홍길동"; // 검색 키워드
		
		Paging pageinfo = new Paging(param_pageNumber, totalCount, url, mode, keyword);
		
	}
	
} // Class 끝
