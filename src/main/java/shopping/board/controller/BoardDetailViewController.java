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

public class BoardDetailViewController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		int no = Integer.parseInt(request.getParameter( "no" )) ;
		
		BoardDao dao = new BoardDao();
		Board bean = dao.SelectDataByPk( no ) ;
		
		request.setAttribute( "bean", bean );
		
		String gotopage = "/board/boDetailView.jsp" ;
		super.GotoPage( gotopage );
	}	
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
	}
}