package shopping.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.jdbc.dbaccess.Message;
import shopping.board.model.Board;
import shopping.board.model.BoardDao;
import shopping.common.controller.SuperClass;

public class BoardReplyController extends SuperClass {
	private Board bean = null;
	private BoardDao dao = null;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		dao = new BoardDao();
		
		int groupno = Integer.parseInt(request.getParameter("groupno"));
		
		int cnt = - 1;
		cnt = dao.GetGroupnoCount(groupno);
		
		int replyNum = 5;
		
		if (cnt == replyNum) { /* 답글(댓글)작성 개수 초과 시 처리 */
			String message = "답글 작성 개수 " + replyNum + " 를 초과하였습니다.";
			super.setErrorMessage(message);
			new BoardListController().doGet(request, response);
			
		} else {
			String gotopage = "/board/boReplyForm.jsp" ;
			super.GotoPage(gotopage);
		}
		
		
		
	}	// doGet 끝
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		bean = new Board();
		bean.setContent(request.getParameter("content"));
		bean.setPassword(request.getParameter("password"));
		bean.setSubject(request.getParameter("subject"));
		bean.setWriter(request.getParameter("writer"));
		
		int groupno = Integer.parseInt(request.getParameter("groupno"));
		int orderno = Integer.parseInt(request.getParameter("orderno"));
		int depth = Integer.parseInt(request.getParameter("depth"));
		
		bean.setGroupno( groupno );
		bean.setOrderno( orderno );
		bean.setDepth( depth );
		
		System.out.println( "bean 정보 확인" );
		System.out.println( bean.toString () );
		
		if ( this.validate( request ) == true ) {
			System.out.println("답글(댓글) 입력 유효성 검사 성공");
			
			dao = new BoardDao();
			int cnt = - 1;
			cnt = dao.ReplyData(bean);
			
			new BoardListController().doGet(request, response);
			
		} else {
			System.out.println("답글(댓글) 입력 유효성 검사 실패");
			
			request.setAttribute("bean", bean);
			
			String gotopage = "/board/boReplyForm.jsp" ;
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
				System.out.println(bean.getPassword());
				
				if (bean.getContent().length() < 1 || bean.getContent().length() > 2000) {
					request.setAttribute(super.PREFIX + "content", "내용은 1자리 이상 2000자리 이하로 입력 해 주시기 바랍니다!");
					isCheck = false;
				}
				
			return isCheck;

	} // doPost 끝
} // Class 끝