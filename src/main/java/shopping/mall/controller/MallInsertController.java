package shopping.mall.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shopping.common.controller.SuperClass;
import shopping.common.model.MyCartList;

public class MallInsertController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		String id = request.getParameter("id") ;
		int no = Integer.parseInt(request.getParameter("no")) ;
		
		MyCartList mycart = new MyCartList(); 
		
		request.setAttribute("bean", null);
		
		
	}	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		if (super.session.getAttribute("loginfo") == null) {
			// 로그인을 하지 않았을 때, 로그인 유도를 위해 로그인 폼으로 이동
			String message = "로그인 후 이용해 주시면 고맙겠습니다!" ;
			String gotopage = "/member/meLoginForm.jsp" ;
			super.GotoPage(gotopage);
			
		} else {
			int num = Integer.parseInt(request.getParameter("num"));
			int stock = Integer.parseInt(request.getParameter("stock"));
			int qty = Integer.parseInt(request.getParameter("qty"));
		}
	}
}