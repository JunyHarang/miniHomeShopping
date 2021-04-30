package shopping.mall.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shopping.common.controller.SuperClass;
import shopping.common.model.MyCartList;
import shopping.common.model.ShoppingInfo;
import shopping.member.controller.MemberLoginController;
import shopping.product.controller.ProductListController;
import shopping.product.model.Product;
import shopping.product.model.ProductDao;

public class MallListController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		/* 회원의 로그인 여부 확인*/
		if (super.session.getAttribute("loginfo") == null) {
			
			/* 로그인 하지 않았다면 로그인을 하도록 유도 */
			new MemberLoginController().doGet(request, response);
		
		} else { /* 로그인을 했다면? */
			
			/* mycart에 쇼핑 목록 바인딩 값을 불러 세션에 넣은 뒤 그것을 mycart 변수에 넣는다 */
			MyCartList mycart = (MyCartList)super.session.getAttribute("mycart");
			
			/* 쇼핑 목록 값 확인 */
			if (mycart == null) { /* 쇼핑 한 목록이 없다면? */
				String meesage = "쇼핑 목록이 없습니다.";
								meesage += "상품 목록 페이지로 이동 하겠습니다!";
				
				super.setErrorMessage(meesage);
				
				/* 쇼핑을 하게 만들기 위해 위치 이동 */
				new ProductListController().doGet(request, response);
				
			} else { /* 목록이 있다면? */
				
				/* map에 카트 목록을 모두 담기 */
				Map<Integer, Integer> maplists = mycart.GetAllOrderList();
				
				/* 카트 정상 작동 여부 확인을 위해 출력 */
				System.out.println("cart에 담긴 내용 크기 : " + maplists.size());
				
				Set<Integer> keylist = maplists.keySet();
				
				List<ShoppingInfo> shoplists = new ArrayList<ShoppingInfo>();
				
				// 총 금액
				int totalAmount = 0;
				
				// 총 누적 포인트
				int totalPoint = 0;
				
				for(Integer pnum : keylist) {
					// pnum은 상품 번호
					
					int qty = maplists.get(pnum);
					
					ProductDao dao = new ProductDao();
					Product bean = dao.SelectDataByPk(pnum);
					
					// shopInfo: 상품 1개에 대한 정보를 저장하기 위한 Class
					ShoppingInfo shopInfo = new ShoppingInfo();
					
					int point = bean.getPoint();
					int price = bean.getPrice();
					
					totalAmount += qty * price;
					totalPoint += qty * point;
					
					shopInfo.setImage(bean.getImage());
					shopInfo.setPname(bean.getName());
					shopInfo.setPnum(pnum);
					shopInfo.setPoint(point);
					shopInfo.setPrice(price);
					shopInfo.setQty(qty);
					
					shoplists.add(shopInfo);
				}
				
				super.session.setAttribute("totalAmount", totalAmount);
				super.session.setAttribute("totalPoint", totalPoint);
				super.session.setAttribute("shoplists", shoplists);
				
				String gotopage = "/mall/mallList.jsp" ;
				super.GotoPage(gotopage);
			}
		}
		
		String id = request.getParameter("id") ;
		int no = Integer.parseInt(request.getParameter("no")) ;
		
		MyCartList mycart = new MyCartList(); 
		
		request.setAttribute("bean", null);
		
		String gotopage = "/member/main.jsp" ;
		super.GotoPage(gotopage);
	}	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
	}
}