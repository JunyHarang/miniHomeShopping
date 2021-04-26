package shopping.board.model ;

public class Board {
	private int no ;
	private String subject ;
	private String writer ;
	private String password ;
	private String content ;
	private int readhit ;	
	private String regdate ; 
	private int groupno ;
	private int orderno ;
	private int depth ;
	private String remark ;	
  
	public Board() { }
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getReadhit() {
		return readhit;
	}
	public void setReadhit(int readhit) {
		this.readhit = readhit;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public int getGroupno() {
		return groupno;
	}
	public void setGroupno(int groupno) {
		this.groupno = groupno;
	}
	public int getOrderno() {
		return orderno;
	}
	public void setOrderno(int orderno) {
		this.orderno = orderno;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	@Override
	public String toString() {
		return "Board [no=" + no + ", subject=" + subject + ", writer=" + writer + ", password=" + password
				+ ", content=" + content + ", readhit=" + readhit + ", regdate=" + regdate + ", groupno=" + groupno
				+ ", orderno=" + orderno + ", depth=" + depth + ", remark=" + remark + "]";
	}
	
	
}    