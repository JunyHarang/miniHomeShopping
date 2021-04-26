package shopping.product.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shopping.common.controller.SuperClass;
import shopping.product.model.Product;
import shopping.product.model.ProductDao;

public class ProductListController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		Product bean = null ;
		ProductDao dao = new ProductDao();
//		String data = dao.toString() ;
//		
//		String id = request.getParameter("id") ;
//		int no = Integer.parseInt(request.getParameter("no")) ;
		
		List<Product> lists = dao.SelectDataList(0, 0, null, null) ;
		
		request.setAttribute("lists", lists);
		
		String gotopage = "/product/prList.jsp" ;
		super.GotoPage(gotopage);
	}	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
	}
}