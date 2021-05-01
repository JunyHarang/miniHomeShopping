package shopping.mall.controller;

import java.io.IOException;
import java.util.List;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shopping.common.controller.SuperClass;
import shopping.common.model.CompositeDao;
import shopping.common.model.MyCartList;
import shopping.common.model.ShoppingInfo;
import shopping.mall.model.MallDao;
import shopping.mall.model.Order;
import shopping.member.controller.MemberLoginController;
import shopping.member.model.Member;

public class MallDetailController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);

		Member loginfo = (Member)super.session.getAttribute("loginfo");
		
		if (loginfo == null) {
			new MemberLoginController().doGet(request, response);
			
		} else {
			int oid = Integer.parseInt(request.getParameter("oid")) ;
			
			MallDao mdao = new MallDao();
			
			Order order = mdao.selectDataByPK(oid);
			
			
			CompositeDao cdao = new CompositeDao();
			
			List<ShoppingInfo> lists = cdao.ShowDetail(oid);
			
			request.setAttribute("order", order);	// order 정보
			
			request.setAttribute("lists", lists); // shopping 정보

			String gotopage = "/mall/mallDetail.jsp" ;
			super.GotoPage(gotopage);
		} //if-else 끝
		
	}	//doGet 끝
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
	}
}