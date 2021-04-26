package shopping.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shopping.common.controller.SuperClass;
import shopping.common.model.CompositeDao;
import shopping.common.model.Postcode;

public class MemberZipcheckController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		String dong = request.getParameter("dong");
		System.out.println("dong name : " + dong);
		
		List<Postcode> lists = null;
		
		CompositeDao dao = new CompositeDao();
				
			if (dong != null) {
				lists = dao.SelectDataZipcode(dong);
				System.out.println("동네 레코드 수는(dong record size) : " + lists.size());
			}
		
		request.setAttribute("lists", lists);
		request.setAttribute("dong", dong);
		
		String gotopage = "/member/zipCheck.jsp" ;
		super.GotoPage(gotopage);
	}	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}