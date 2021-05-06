package shopping.composite.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shopping.common.controller.SuperClass;
import shopping.common.model.Combo03;
import shopping.common.model.CompositeDao;

public class View03Controller extends SuperClass {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		CompositeDao dao = new CompositeDao();
		
		List<Combo03> lists = dao.View03();
		
		request.setAttribute("lists", lists);
		
		String gotopage = "view/View03.jsp";
		super.GotoPage(gotopage);
	} // doGet 끝
	
} // Class 끝
