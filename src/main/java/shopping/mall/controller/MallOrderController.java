package shopping.mall.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shopping.common.controller.SuperClass;
import shopping.mall.model.MallDao;
import shopping.mall.model.Order;
import shopping.member.controller.MemberLoginController;
import shopping.member.model.Member;
import shopping.product.controller.ProductListController;

public class MallOrderController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		Member loginfo = (Member)super.session.getAttribute("loginfo");
		
		if (loginfo == null) {
			new MemberLoginController().doGet(request, response);
			
		} else {
			MallDao dao = new MallDao();
			
			List<Order> lists = dao.OrderMall(loginfo.getId());
			
			if (lists.size() == 0) {
				String message = "이전 쇼핑 내역이 존재하지 않습니다.";
				
				// alert 상자가 나오게 작업
				super.setErrorMessage(message);
				
				new ProductListController().doGet(request, response);
			
			} else {
				request.setAttribute("lists", lists);
				
				String gotopage = "/mall/shopList.jsp" ;
				super.GotoPage(gotopage);
			}
		}
		
		
	}	// doGet 끝
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
	} // doPost 끝
} // Class 끝