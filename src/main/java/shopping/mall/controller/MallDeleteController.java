package shopping.mall.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shopping.common.controller.SuperClass;
import shopping.common.model.MyCartList;

public class MallDeleteController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		/* 로그인 여부 확인 */
		// 사실 로그인을 한 상태에서 장바구니가 보이는 것이기 때문에 굳이 할 필요는 없습니다.
		// 다만, 한번 더 개념을 잡기 위해 복습 해 보았습니다.
		
		if (super.session.getAttribute("loginfo") == null) {
			
			String gotopage = "/member/meLoginForm.jsp" ;
			super.GotoPage(gotopage);
		} else {
		
			MyCartList mycart = (MyCartList)super.session.getAttribute("mycart");
			
				if (mycart == null) {
					mycart = new MyCartList();
				}
			
			int pnum = Integer.parseInt(request.getParameter("pnum")) ;
			mycart.DeleteOrder(pnum);
			super.session.setAttribute("mycart", mycart);
			new MallListController().doGet(request, response);
		}
	
		String id = request.getParameter("id") ;
		
		request.setAttribute("bean", null);

	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
	}
}