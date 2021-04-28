package shopping.utility;

public class FlowParameters {
	private String pageNumber;
	private String mode;
	private String keyword;
	
	public FlowParameters(String pageNumber, String mode, String keyword) {
		this.pageNumber = pageNumber;
		
		if (mode == null || mode.equals("null") || mode.equals("")) {
			mode = "all";
		}
		
		this.mode = mode;
		
		if (keyword == null || keyword.equals("null") || keyword.equals("")) {
			keyword = "";
		}
		
		this.keyword = keyword;
	}
	
	
		public String getPageNumber() {
			return pageNumber;
		}
		public void setPageNumber(String pageNumber) {
			this.pageNumber = pageNumber;
		}
		public String getMode() {
			return mode;
		}
		public void setMode(String mode) {
			this.mode = mode;
		}
		public String getKeyword() {
			return keyword;
		}
		public void setKeyword(String keyword) {
			this.keyword = keyword;
		}


		@Override
		public String toString() {
			return "pageNumber=" + pageNumber + "&mode=" + mode + "&keyword=" + keyword;
		}
	
	
}


