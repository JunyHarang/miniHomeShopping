package shopping.mall.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shopping.common.controller.SuperClass;
import shopping.common.model.MyCartList;
import shopping.product.controller.ProductListController;

public class MallInsertController extends SuperClass {
		
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
			
				if (stock < qty) {
					String message = "재고가 부족 합니다!";
					super.setErrorMessage(message);
					new ProductListController().doGet(request, response);
					
				} else {
					MyCartList mycart = (MyCartList)session.getAttribute("mycart");
					
					/* 카트를 준비 하지 않았다면 카트를 준비 시켜야 한다 */
					if (mycart == null) {
						mycart = new MyCartList();
					}
					
					/* 카트 안에 상품 목록 추가 */
					mycart.AddOrder(num, qty);
					
					/* 카트를 세션에 바인딩 */
					super.session.setAttribute("mycart", mycart);
					
					new MallListController().doGet(request, response);
				}
		}
	}
	
} // Class 끝