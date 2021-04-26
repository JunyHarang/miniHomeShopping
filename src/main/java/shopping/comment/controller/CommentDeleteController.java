package shopping.comment.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shopping.comment.model.Comment;
import shopping.common.controller.SuperClass;
import shopping.common.model.DatabaseDao;

public class CommentDeleteController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		String id = request.getParameter("id") ;
		int no = Integer.parseInt(request.getParameter("no")) ;
		
		Comment bean = null ;
		DatabaseDao dao = new DatabaseDao();
		String data = dao.toString() ; 
		
		request.setAttribute("bean", null);
		
		String gotopage = "/comment/main.jsp" ;
		super.GotoPage(gotopage);
	}	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
	}
}