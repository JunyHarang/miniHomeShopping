package shopping.utility;

public class pageTest {
	public static void main(String[] args) {
		String _pageNumber = "3" ;
		String _pageSize = "10" ;
		int totalCount = 35 ;
		String url = "boList.jsp" ;
		String mode = "" ;
		String keyword = "" ;
		
		Paging pageInfo = new Paging( _pageNumber, _pageSize, totalCount, url, mode, keyword ) ;
		
		System.out.println( pageInfo.getPagingHtml() );
	}
}