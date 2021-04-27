package shopping.utility;

public class pageTest_bak {
	public static void main(String[] args) {
		String _pageNumber = "3" ;
		String _pageSize = "10" ;
		int totalCount = 35 ;
		String url = "boList.jsp" ;
		String mode = "" ;
		String keyword = "" ;
		
		Paging_bak pageInfo = new Paging_bak( _pageNumber, _pageSize, totalCount, url, mode, keyword ) ;
		
		System.out.println( pageInfo.getPagingHtml() );
	}
}