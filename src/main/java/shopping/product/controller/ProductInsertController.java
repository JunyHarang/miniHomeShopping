package shopping.product.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import shopping.common.controller.SuperClass;
import shopping.product.model.Product;
import shopping.product.model.ProductDao;

public class ProductInsertController extends SuperClass {
	
	private Product bean = null;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		String gotopage = "/product/prInsertForm.jsp" ;
		super.GotoPage(gotopage);
		
	}	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		MultipartRequest multi = (MultipartRequest)request.getAttribute("multi");
		bean = new Product();
		
		// 문자열
		bean.setCategory(multi.getParameter("category"));
		bean.setCompany(multi.getParameter("company"));
		bean.setContents(multi.getParameter("contents"));
		bean.setInputdate(multi.getParameter("inputdate"));
		bean.setName(multi.getParameter("name"));
		bean.setRemark(multi.getParameter("remark"));
		
		// 업로드 할 파일 객체
		bean.setImage(multi.getFilesystemName("image"));
		
		
		// 정수
		if ( multi.getParameter("point") != null && multi.getParameter("point").equals("") == false ) {
			bean.setPoint(Integer.parseInt(multi.getParameter("point")));
		}
		
		if ( multi.getParameter("price") != null && multi.getParameter("price").equals("") == false ) {
			bean.setPrice(Integer.parseInt(multi.getParameter("price")));
		}
		
		if ( multi.getParameter("stock") != null && multi.getParameter("stock").equals("") == false ) {
			bean.setStock(Integer.parseInt(multi.getParameter("stock")));
		}
		
		/*
		 *  시퀀스가 자동으로 처리함으로 필요 없음.
		if ( multi.getParameter("Num") != null && multi.getParameter("Num").equals("") == false ) {
			bean.setNum(Integer.parseInt(multi.getParameter("Num")));
		} 
		*/ 
		
		if (this.validate(request) == true) {
			System.out.println("상품 등록 유효성 검사가 성공하였습니다!");
			
			ProductDao dao = new ProductDao();
			
			int cnt = - 1;
			cnt = dao.InsertData(bean);
			
			new ProductInsertController().doGet(request, response);
			
		} else {
			System.out.println("상품 등록 유효성 검사 실패 하였습니다!");
			
			request.setAttribute("bean", bean);
			
			super.doPost(request, response);
			
			String gotopage = "/product/prInsertForm.jsp" ;
			super.GotoPage(gotopage);
		}
	} // doPost 끝
	
	@Override
	public boolean validate(HttpServletRequest request) {
//		MemberInsertController 컨트롤러의 validate 내용 복사
boolean isCheck = true;
		
		// 유효성 검사 수행(PREFIX는 접두사로 err이 들어 있음.)
			if (bean.getName().length() < 1 || bean.getName().length() > 50) {
				request.setAttribute(super.PREFIX + "name", "상품명은 1자 이상 50자 이하로 입력 해 주시기 바랍니다!");
				isCheck = false;
			}
			
			if (bean.getCompany().length() < 1 || bean.getCompany().length() > 10) {
				request.setAttribute(super.PREFIX + "company", "회사명은 1자 이상 10자 이하로 입력 해 주시기 바랍니다!");
				isCheck = false;
			}
			
			if (bean.getContents().length() < 1 || bean.getContents().length() > 2000) {
				request.setAttribute(super.PREFIX + "contents", "상품 상세 내용은 1자 이상 2000자 이하로 입력 해 주시기 바랍니다!");
				isCheck = false;
			}
			
			if (bean.getCategory().equals("-")) {
				request.setAttribute(super.PREFIX + "category", "상품 유형을 선택 하여 주시기 바랍니다!");
				isCheck = false;
			}
			
			// 날짜 형식은 yyyy/mm/dd 또는 yyyy-mm-dd로 만들고 싶다.
			String regex = "\\d{4}[-/]\\d{2}[-/]\\d{2}";
			
				if (bean.getInputdate() == null) {
					bean.setInputdate("");
				}
				
				// pattern.matches는 앞에 매개변수 값을 토대로 쉼표 뒤에 매개변수 값을 검사하는 메서드
			boolean result = java.util.regex.Pattern.matches(regex, bean.getInputdate());
			
				if (result == false) {
					request.setAttribute(super.PREFIX + "inputdate", "상품 제조일을 입력 해 주시기 바랍니다!");
					isCheck = false;
				}
			
			if (bean.getImage() == null || bean.getImage().equals("")) {
				request.setAttribute(super.PREFIX + "image", "상품 사진은 하나 이상 첨부 해 주시기 바랍니다!");
				isCheck = false;
			}
			
			int stock = 10;
			if (bean.getStock() < stock) {
				request.setAttribute(super.PREFIX + "stock", "재고수량은" + stock + "보다 많아야 합니다.");
				isCheck = false;
			}

				if (bean.getPoint() < 5 || bean.getPoint() > 10) {
					request.setAttribute(super.PREFIX + "point", "포인트는 최소 5이상 10 이하로 입력 하여 주시기 바랍니다! " );
					isCheck = false;
				}
							
		return isCheck;
	} // validate 끝
	
} // class 끝

//Product bean = null ;
//ProductDao dao = new ProductDao();
//String data = dao.toString() ;
//
//String id = request.getParameter("id") ;
//int no = Integer.parseInt(request.getParameter("no")) ;
//
//List<Product> lists = new ArrayList<Product>() ;
//
//request.setAttribute("bean", bean);