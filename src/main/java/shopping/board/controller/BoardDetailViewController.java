package shopping.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shopping.board.model.Board;
import shopping.board.model.BoardDao;
import shopping.common.controller.SuperClass;
import shopping.member.model.Member;

public class BoardDetailViewController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		int no = Integer.parseInt(request.getParameter( "no" )) ;
		
		BoardDao dao = new BoardDao();
		Board bean = dao.SelectDataByPk( no ) ;
		
		// 로그인 한 사람의 객체 정보
		Member loginfo = (Member)super.session.getAttribute("loginfo");
		
		// 로그인 한 사람과 게시물 작성자가 다르거나, 작성자 정보가 없는 경우 필터링
		if (loginfo.getId().equals( bean.getWriter()) == false || bean.getWriter() == null) {
			dao.UpdateReadhit(no); // 조회수 1증가 시키기
			bean.setReadhit( bean.getReadhit() + 1 );
		}
		
		request.setAttribute( "bean", bean );
		
		String gotopage = "/board/boDetailView.jsp" ;
		super.GotoPage( gotopage );
	}	
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
	}
}