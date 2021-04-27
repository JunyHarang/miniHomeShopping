package shopping.utility;

public class PageTest {
	public static void main(String[] args) {
		String param_param_pageNumber = "2";
		int totalCount = 35;
		String url = "boList";
		String mode = "writer"; // 검색 모드
		String keyword = "홍길동"; // 검색 키워드
		
		Paging pageinfo = new Paging(param_param_pageNumber, totalCount, url, mode, keyword);
		
	}
	
} // Class 끝
