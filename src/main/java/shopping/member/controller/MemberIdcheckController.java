package shopping.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shopping.common.controller.SuperClass;
import shopping.member.model.Member;
import shopping.member.model.MemberDao;

public class MemberIdcheckController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		String id = request.getParameter("id") ;
		MemberDao dao = new MemberDao();
		
		Member bean = dao.SelectDataByPk(id);
		
			if (bean == null) {
				request.setAttribute("message", id + "<font color='blue'>은(는) <b>사용 가능</b>합니다!</font>");
				request.setAttribute("isCheck", true);
				
			} else {
				
				if (bean.getId().equals("admin")) { // 관리자
					request.setAttribute("message", "<font color='red'>admin은 <b>사용 불가능</b> 합니다!</font><br><font color='blue'><b>관리자</b>를 위한 계정 입니다!</font>");
					request.setAttribute("isCheck", false);
					
				} else { // 일반 사용자
					request.setAttribute("message", id + "<font color='red'>은(는) 이미 <b>사용 중</b> 입니다!</font>");
					request.setAttribute("isCheck", false);
				}
				
			}

		String gotopage = "/member/idCheck.jsp" ;
		super.GotoPage(gotopage);
	}	
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
	}
}