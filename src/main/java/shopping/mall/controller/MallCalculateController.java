package shopping.mall.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shopping.common.controller.SuperClass;
import shopping.common.model.MyCartList;
import shopping.mall.model.MallDao;
import shopping.member.model.Member;

public class MallCalculateController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		System.out.println("장바구니 내역을 이용하여 계산을 할 것이에요.");
		
		MyCartList mycart = (MyCartList)super.session.getAttribute("mycart");
		
		MallDao dao = new MallDao();
		
		if (mycart != null) {
			Map<Integer, Integer> maplists = mycart.GetAllOrderList();

			System.out.println("shopping list count : " + maplists.size());
			
			int totalPoint = (Integer)super.session.getAttribute("totalPoint");
			
			Member mem = (Member)super.session.getAttribute("loginfo");
			
			System.out.println("mall dao에 Calculate() Method가 호출 되려고 합니다!");
			
			dao.Calculate(mem, maplists, totalPoint);
			
			System.out.println("세션에 속성을 모두 삭제 하겠습니다!");
			
			super.session.removeAttribute("totalAmount");
			super.session.removeAttribute("totalPoint");
			super.session.removeAttribute("mycart");
			super.session.removeAttribute("shoplists");
			
			String message = "결재를 완료 하였습니다! 고맙습니다!";
			super.session.setAttribute("message", message);
			
		} else {
			
			new MallOrderController().doGet(request, response);
			
		}
		
	}	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
	}
}