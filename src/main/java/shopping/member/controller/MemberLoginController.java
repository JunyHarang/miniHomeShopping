package shopping.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shopping.common.controller.MainController;
import shopping.common.controller.SuperClass;
import shopping.member.model.Member;
import shopping.member.model.MemberDao;

public class MemberLoginController extends SuperClass {
	
	private String id;
	private String pwd;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
//		Member bean = null ;
//		MemberDao dao = new MemberDao();
//		String data = dao.toString() ;
//		
//		String id = request.getParameter("id") ;
//		int no = Integer.parseInt(request.getParameter("no")) ;
//		
//		List<Member> lists = new ArrayList<Member>() ;
//		
//		request.setAttribute("bean", bean);
//		
		String gotopage = "/member/meLoginForm.jsp" ;
		super.GotoPage(gotopage);
	}	
	@Override
	public boolean validate(HttpServletRequest request) {
		boolean isCheck = true;						// 유효성 검사에 문자가 없습니다.
		
		if(this.id.length() <4 || this.id.length() > 10) {
			request.setAttribute(super.PREFIX + "id", "id는 4자리 이상 10자리 이하로 입력해야 합니다!");
			isCheck = false;
		}
		
		if(this.pwd.length() <4 || this.pwd.length() > 10) {
			request.setAttribute(super.PREFIX + "password", "비밀번호는 4자리 이상 10자리 이하로 입력해야 합니다!");
			isCheck = false;
		}
		
		return isCheck;
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		this.id = request.getParameter("id") ;
		this.pwd = request.getParameter("password") ;
		System.out.println(id + "/" + pwd);
		
		String gotopage = "";
		
		if (this.validate(request) == true) {
			System.out.println("유효성 검사에 문제가 없습니다.");
			
		  MemberDao dao = new MemberDao();
		  Member bean = dao.selectData(id, pwd);
		  
		  if (bean == null) { // Login 실패
			  	System.out.println("로그인 실패");
			  	String message = "아이디나 비밀번호를 잘 못 입력하셨습니다!";
			  	super.setErrorMessage(message);
			  	gotopage = "/member/meLoginForm.jsp";
			  	super.GotoPage(gotopage);
			  	
		  } else {	// Login 성공
			  System.out.println("로그인 성공");
			  //로그인 정보 세션에 바인딩
			  super.session.setAttribute("loginfo", bean);
			  
			  // 장바구니 테이블에 들어 있는 나의 쇼핑 정보가 있으면 
			  // Session 영역에 mycart라는 이름으로 바인딩함.
			  
			  //Main Page로 이동
			  new MainController().doGet(request, response);
		}
		  
//		  String date = dao.toString();
			
		} else { // 유효성 검사에 문제 발견!
			// 이전 입력 값 정보 다시 바인딩
			request.setAttribute("id", this.id);
			request.setAttribute("password", this.pwd);
			gotopage = "/member/meLoginForm.jsp";
			
			super.GotoPage(gotopage);
		} // if문 끝
	}
}