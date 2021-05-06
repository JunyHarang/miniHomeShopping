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
import shopping.utility.FlowParameters;

public class ProductDeleteController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		int num = Integer.parseInt(request.getParameter("num")) ;
		
		FlowParameters parameters = new FlowParameters( request.getParameter("pageNumber"), request.getParameter("mode"), request.getParameter("keyword") );
		
		System.out.println( parameters.toString() );
		
		ProductDao dao = new ProductDao();
		
		int cnt = - 1;
		
		cnt = dao.DeleteData(num);

		new ProductListController().doGet(request, response);
		
	}	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
	}
}