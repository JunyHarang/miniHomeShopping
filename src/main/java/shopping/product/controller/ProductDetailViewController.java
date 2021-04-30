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

public class ProductDetailViewController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		int num = Integer.parseInt(request.getParameter("num")) ;
		
		ProductDao dao = new ProductDao();
		
		Product bean = dao.SelectDataByPk(num);
		
		if ( bean == null ) {
			new ProductListController().doGet(request, response);
			
		} else {
			request.setAttribute("bean", bean);
			
			String gotopage = "/product/prDetailView.jsp" ;
			super.GotoPage(gotopage);
		}
		
		
	}	
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
	}
}