package shopping.member.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shopping.common.controller.SuperClass;
import shopping.common.model.MyCartList;
import shopping.mall.model.MallDao;
import shopping.member.model.Member;
import shopping.member.model.MemberDao;

public class MemberLogoutController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		// 만약 세션영역 장바구니 정보가 들어 있으면 이 정보를 장바구니 임시 Table(shppinginfos Table)에 저장
		MyCartList mycart = (MyCartList)super.session.getAttribute("mycart");
		
		if (mycart != null) {
			Map<Integer, Integer> maplist = mycart.GetAllOrderList();
			
			Member mem = (Member)super.session.getAttribute("loginfo");
			
			MallDao dao = new MallDao();
			dao.InsertCartData(mem, maplist);
		} // if문 끝
		
		// 나의 로그인 정보 및 관련 모든 것들을 비웁니다.
		super.session.invalidate();
		
		String GotoPage = "/member/meLoginForm.jsp";
		super.GotoPage(GotoPage);
		
		Member bean = null ;
		MemberDao dao = new MemberDao();
		String data = dao.toString() ;
	} // doGet 끝 
	
} // Class 끝
//		
//		String id = request.getParameter("id") ;
//		int no = Integer.parseInt(request.getParameter("no")) ;
//		
//		List<Member> lists = new ArrayList<Member>() ;
//		
//		request.setAttribute("bean", bean);
//		
//		String gotopage = "/member/main.jsp" ;
//		super.GotoPage(gotopage);
//	}	
//	@Override
//	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		super.doPost(request, response);
//	}
//}