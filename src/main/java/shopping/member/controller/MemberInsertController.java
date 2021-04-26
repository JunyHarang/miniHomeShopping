package shopping.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shopping.common.controller.SuperClass;
import shopping.member.model.Member;
import shopping.member.model.MemberDao;

public class MemberInsertController extends SuperClass {
	Member bean = null;
	
	@Override
	public boolean validate(HttpServletRequest request) {
		boolean isCheck = true;
		
		// 유효성 검사 수행(PREFIX는 접두사로 err이 들어 있음.)
			if (bean.getId().length() < 4 || bean.getId().length() > 10) {
				request.setAttribute(super.PREFIX + "id", "아이디는 4자리 이상 10자리 이하로 입력 해 주시기 바랍니다!");
				isCheck = false;
			}
			
			if (bean.getName().length() < 2 || bean.getName().length() > 10) {
				request.setAttribute(super.PREFIX + "name", "이름은 2자리 이상 10자리 이하로 입력 해 주시기 바랍니다!");
				isCheck = false;
			}
			
			if (bean.getPassword().length() < 4 || bean.getPassword().length() > 10) {
				request.setAttribute(super.PREFIX + "password", "비밀번호는 4자리 이상 10자리 이하로 입력 해 주시기 바랍니다!");
				isCheck = false;
			}
			
			if (bean.getGender() == null) {
				request.setAttribute(super.PREFIX + "gender", "성별은 하나를 꼭 선택 해 주시기 바랍니다!");
				isCheck = false;
			}
			
			// 날짜 형식은 yyyy/mm/dd 또는 yyyy-mm-dd로 만들고 싶다.
			String regex = "\\d{4}[-/]\\d{2}[-/]\\d{2}";
			
				if (bean.getBirth() == null) {
					bean.setBirth("");
				}
				// pattern.matches는 앞에 매개변수 값을 토대로 쉼표 뒤에 매개변수 값을 검사하는 메서드
			boolean result = java.util.regex.Pattern.matches(regex, bean.getBirth());
			
				if (result == false) {
					request.setAttribute(super.PREFIX + "birth", "생일을 입력 해 주시기 바랍니다!");
					isCheck = false;
				}
				
			int salary = 100;
			
				if (bean.getSalary() < salary) {
					request.setAttribute(super.PREFIX + "salary", "최소 급여는 " + salary + "이상이여야 합니다.");
					isCheck = false;
				}
				
				if (bean.getManager().equals("-")) {
					request.setAttribute(super.PREFIX + "manager", "담당 매니져를 선택 해 주세요!");
					isCheck = false;
				}
				
				if (bean.getMarriage().equals("-")) {
					request.setAttribute(super.PREFIX + "marriage", "결혼 여부를 선택 해 주세요!");
					isCheck = false;
				}
		
				if (bean.getZipcode() == null || bean.getZipcode() == "") {
					request.setAttribute(super.PREFIX + "zipcode", "우편 번호는 필수 입력 사항 입니다.");
					isCheck = false;
				}
				
				if (bean.getAddress1() == null || bean.getAddress1() == "") {
					request.setAttribute(super.PREFIX + "address1", "주소는 필수 입력 사항 입니다.");
					isCheck = false;
				}
			
		return isCheck;
		
	} // validate 끝
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		MemberDao dao = new MemberDao();
		
		List<Member> managers = dao.GetManagerList();
		System.out.println("매니져 수 : " + managers.size());
		
		request.setAttribute("managers", managers);
		
		String gotopage = "/member/meInsertForm.jsp" ;
		super.GotoPage(gotopage);
				
	}	
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		bean = new Member();
		
		bean.setAddress1(request.getParameter("address1"));
		bean.setAddress2(request.getParameter("address2"));
		bean.setBirth(request.getParameter("birth"));
		bean.setGender(request.getParameter("gender"));
		bean.setId(request.getParameter("id"));
		bean.setManager(request.getParameter("manager"));
		bean.setMarriage(request.getParameter("marriage"));
		bean.setName(request.getParameter("name"));
		bean.setPassword(request.getParameter("password"));
		bean.setZipcode(request.getParameter("zipcode"));
		
			if (request.getParameter("mpoint") == null || request.getParameter("moint").equals("")) {
				bean.setMpoint(0);
			} else {
				bean.setMpoint(Integer.parseInt(request.getParameter("mpoint")));
			}
			
			if (request.getParameter("salary") == null || request.getParameter("salary").equals("")) {
				bean.setSalary(0);
			} else {
				bean.setSalary(Integer.parseInt(request.getParameter("salary")));
			}
			
			MemberDao dao = new MemberDao();
		
			if (this.validate(request) == true) {
				System.out.println("Member Insert validation check Success");
				int cnt = -1;
				cnt = dao.InsertData(bean);
				
				new MemberLoginController().doGet(request, response);
				
			} else {
				System.out.println("Member Insert validation check Failure");
				
				List<Member> managers = dao.GetManagerList();
				System.out.println("매니져 수 : " + managers.size());
				
				request.setAttribute("managers", managers);
				
				request.setAttribute("bean", bean);
				super.doPost(request, response);
				String gotopage = "/member/meInsertForm.jsp";
				super.GotoPage(gotopage);
			}		
	}
	
} // Class 끝