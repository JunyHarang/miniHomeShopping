package shopping.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shopping.board.model.Board;
import shopping.board.model.BoardDao;
import shopping.common.controller.SuperClass;

public class BoardInsertController extends SuperClass {
	private Board bean = null;
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		String gotopage = "/board/boInsertForm.jsp" ;
		super.GotoPage(gotopage);
	}	
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		bean = new Board();
		bean.setContent(request.getParameter("content"));
		bean.setPassword(request.getParameter("password"));
		bean.setSubject(request.getParameter("subject"));
		bean.setWriter(request.getParameter("writer"));
		
		System.out.println( "bean 정보 확인" );
		System.out.println( bean.toString () );
		
		if ( this.validate( request ) == true ) {
			System.out.println("게시글 입력 유효성 검사 성공");
			
			BoardDao dao = new BoardDao();
			int cnt = - 1;
			cnt = dao.InsertData(bean);
			
			new BoardListController().doGet(request, response);
			
		} else {
			System.out.println("게시글 입력 유효성 검사 실패");
			
			request.setAttribute("bean", bean);
			
			String gotopage = "/board/boInsertForm.jsp" ;
			super.GotoPage(gotopage) ;
			
		}
	}
		@Override
		public boolean validate(HttpServletRequest request) {
			boolean isCheck = true;
			
			// 유효성 검사 수행(PREFIX는 접두사로 err이 들어 있음.)
				if (bean.getSubject().length() < 3 || bean.getSubject().length() > 10) {
					request.setAttribute(super.PREFIX + "subject", "제목은 3자리 이상 10자리 이하로 입력 해 주시기 바랍니다!");
					isCheck = false;
				}
				
				if (bean.getPassword().length() < 4 || bean.getPassword().length() > 10) {
					request.setAttribute(super.PREFIX + "password", "비밀번호은 4자리 이상 10자리 이하로 입력 해 주시기 바랍니다!");
					isCheck = false;
				}
				
				if (bean.getContent().length() < 4 || bean.getContent().length() > 10) {
					request.setAttribute(super.PREFIX + "password", "비밀번호는 4자리 이상 10자리 이하로 입력 해 주시기 바랍니다!");
					isCheck = false;
				}
				
			return isCheck;
	} // validate 끝
} // Class 끝