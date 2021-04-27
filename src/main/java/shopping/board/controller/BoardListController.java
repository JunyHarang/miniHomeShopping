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
import shopping.utility.Paging;

public class BoardListController extends SuperClass {
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		String pageNumber = request.getParameter("pageNumber");
		String mode = request.getParameter("mode");
		String keyword = request.getParameter("keyword");
		
		// mode와 keyword에 대한 null 값 유효성 검사
		
		if (mode == null || mode.equals("null") || mode.equals("")) {
			mode = "all";
		}
		
		if (keyword == null || keyword.equals("null") || keyword.equals("") || keyword.equals("all")) {
			keyword = "";
		}
		
		String url = super.CommandName + "boList";
		
		BoardDao dao = new BoardDao();
		
		int totalCount = dao.SelectTotalCount(mode, keyword);		// 행(row) 총 개수 담을 변수
		
		System.out.println("total data size : " + totalCount);
		
		Paging pageInfo = new Paging(pageNumber, totalCount, url, mode, keyword);
		
		List<Board> lists = dao.SelectDataList(pageInfo.getBeginRow(), pageInfo.getEndRow(), mode, keyword );
		
//		String data = dao.toString() ;
		
//		String id = request.getParameter("id") ;
//		int no = Integer.parseInt(request.getParameter("no")) ;
		
		System.out.println("board list count : " + lists.size());
		
		request.setAttribute("lists", lists);
		
		String gotopage = "/board/boList.jsp" ;
		super.GotoPage(gotopage);
	}	
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
	}
	
} // Class 끝