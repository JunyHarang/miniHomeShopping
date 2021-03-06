package shopping.mall.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import shopping.common.model.ShoppingInfo;
import shopping.common.model.SuperDao;
import shopping.member.model.Member;
import shopping.product.controller.ProductDetailViewController;
import shopping.product.model.Product;
import shopping.product.model.ProductDao;

public class MallDao extends SuperDao {

	public void Calculate(Member mem, Map<Integer, Integer> maplists, int totalPoint) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		int cnt = -1;
		int invoice = -1; // invoice max number after inserted

		try {
			if (this.conn == null) {
				this.conn = this.getConnection();
			}
			
			/* 1단계: orders table에 Data 추가하기 */
			sql = " insert into orders(oid, mid, orderdate) values (seqoid.nextval, ?, sysdate) ";

			pstmt = this.conn.prepareStatement(sql);

			// 치환
			pstmt.setString(1, mem.getId());

			cnt = pstmt.executeUpdate();

			if (pstmt != null) {
				pstmt.close();
			}

			/* 2단계: 최대 송장번호를 가져오기 */
			sql = " select max(oid) as invoice from orders ";

			pstmt = this.conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				invoice = rs.getInt("invoice");
			}

			if (pstmt != null) {
				pstmt.close();
			}

			System.out.println("최대 송장번호 (Max invoice Number) : " + invoice);

			Set<Integer> keylist = maplists.keySet();
			System.out.println("쇼핑 목록 크기 확인 : " + keylist.size());

			for (Integer pnum : keylist) {
				/* 3단계: orderdetails table에 Data 추가하기 */
				sql = " insert into orderdetails (odid, oid, pnum, qty) values(seqodid.nextval, ?, ?, ?) ";

				pstmt = this.conn.prepareStatement(sql);

				int qty = maplists.get(pnum);

				// 치환
				pstmt.setInt(1, invoice);
				pstmt.setInt(2, pnum);
				pstmt.setInt(3, qty);

				cnt = pstmt.executeUpdate();

				if (pstmt != null) {
					pstmt.close();
				}

				/* 4단계: 상품 제고 제거하기 */
				sql = " update products set stock = stock - ? where num = ? ";

				pstmt = this.conn.prepareStatement(sql);

				// 치환
				pstmt.setInt(1, qty);
				pstmt.setInt(2, pnum);

				cnt = pstmt.executeUpdate();

				if (pstmt != null) {
					pstmt.close();
				}

			}

			/* 5단계: 회원 포인트 최신화 작업 */
			sql = " update members set mpoint = mpoint + ? where id = ? ";

			pstmt = this.conn.prepareStatement(sql);

			// 치환
			pstmt.setInt(1, totalPoint);
			pstmt.setString(2, mem.getId());

			cnt = pstmt.executeUpdate();

			if (pstmt != null) {
				pstmt.close();
			}

			conn.commit();

			System.out.println("Calculate Method Complate!!");

		} catch (Exception e) {
			e.printStackTrace();

			try {
				conn.rollback();
			} catch (Exception e1) {
				// TODO: handle exception
			}

		} finally {

			try {
				if (rs != null) {
					rs.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public List<Order> OrderMall(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = " select * from orders where mid = ? order by orderdate desc ";
		
		List<Order> lists = new ArrayList<Order>();
		
		try {
			
			if (this.conn == null) {
				this.conn = this.getConnection();
			} // if 끝
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, id);
				
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					Order bean = new Order();
					
					bean.setMid(rs.getString("mid"));
					
					bean.setOid(rs.getInt("oid"));
					
					bean.setOrderdate(rs.getString("orderdate"));
					
					/* 시간 제외하고, 일자만 출력하고 싶을 때 사용 */
//					bean.setOrderdate(String.valueOf(rs.getString("orderdate")));
					
					bean.setRemark(rs.getString("remark"));
				
					lists.add(bean);
					
				} // while 끝
				
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			
			try {
				
			} catch (Exception e2) {
				e2.printStackTrace();
			} // try-catch 끝
			
		} // finally(try-catch) 끝
		
		return lists;
		
	} // OrderMall 끝

	public Order selectDataByPK(int oid) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = " select * from orders where oid = ? " ;
		Order bean = null;
		
		try {
			if ( this.conn == null) {
				this.conn = this.getConnection();
			}
			pstmt = super.conn.prepareStatement(sql);
			pstmt.setInt(1, oid);
			
			rs = pstmt.executeQuery();
			
			if ( rs.next() ) {
				bean = new Order();
				
				pstmt.setInt( 1, oid );
				
				bean.setMid(rs.getString("mid"));
				
				bean.setOid(rs.getInt("oid"));
				
				bean.setOrderdate(rs.getString("orderdate"));
				
				/* 시간 제외하고, 일자만 출력하고 싶을 때 사용 */
//				bean.setOrderdate(String.valueOf(rs.getString("orderdate")));
				
				bean.setRemark(rs.getString("remark"));
			
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			
			try {
				if (rs != null) {
					rs.close();
				}
				
				if (pstmt != null) {
					pstmt.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			} // try-catch 끝
			
		}  // finally 끝
		
		return bean;
	} // selectDataByPK 끝

	public void InsertCartData(Member mem, Map<Integer, Integer> maplist) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = - 1;
		
		try {
			if ( this.conn == null) {
				this.conn = this.getConnection();
			} // if문 끝
			
			conn.setAutoCommit(false);
			
			Set<Integer> keylist = maplist.keySet();
			System.out.println("입력된 Cart Data keylist 크기는 " + keylist.size() + " 입니다." );
			
			// 이전 내역 삭제 작업
			String sql = " delete from shoppinginfos where mid = ? " ;
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, mem.getId());
			
			cnt = pstmt.executeUpdate();
			
			if (pstmt != null ) {
				pstmt.close();
			}
			
			
			// 반복문을 사용하여 현재 장바구니 정보 저장
			sql = " insert into shoppinginfos(mid, pnum, pname, qty, price, image, point, inputdate) values(?, ?, ?, ?, ?, ?, ?, default) ";
			
			for ( Integer pnum : keylist) {
				
				pstmt = conn.prepareStatement(sql);

				ProductDao pdao = new ProductDao();
				
				Product bean = pdao.SelectDataByPk(pnum);
				
				//치환 작업
				/* mid, pnum, pname, qty, price, image, point */
				int qty = maplist.get(pnum);
				
				pstmt.setString(1, mem.getId());
				pstmt.setInt		 (2, pnum);
				pstmt.setString(3, bean.getName());
				pstmt.setInt		 (4, qty);
				pstmt.setInt		 (5, bean.getPrice());
				pstmt.setString(6, bean.getImage());
				pstmt.setInt		 (7, bean.getPoint());
				
				cnt = pstmt.executeUpdate();
				
				if ( pstmt != null) {
					pstmt.close();
				} // if 문 끝
			} // for 문 끝
			
			conn.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			
			try {
				conn.rollback();
				
			} catch (Exception e2) {
				e2.printStackTrace();
				
			} finally {
				
				try {
					if (rs != null) {
						rs.close();
					}// if문 끝
					
					if(pstmt != null) {
						pstmt.close();
					}// if문 끝
					
				} catch (Exception e3) {
					e3.printStackTrace();
				}// try-catch 끝
			} // finally 끝
		}// try-catch 끝
	} // InsertCartDate 끝

	public List<ShoppingInfo> GetshoppingInfo(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = " select * from shoppinginfos where mid = ? ";
		
		List<ShoppingInfo> lists = new ArrayList<ShoppingInfo>();
		
		try {
			
			if (this.conn == null) {
				this.conn = this.getConnection();
			} // if 끝
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, id);
				
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					ShoppingInfo bean = new ShoppingInfo();
					
					bean.setImage(rs.getString("image"));
					bean.setPname(rs.getString("pname"));
					bean.setPnum(rs.getInt("pnum"));
					bean.setPoint(rs.getInt("point"));
					bean.setPrice(rs.getInt("price"));
					bean.setQty(rs.getInt("qty"));
					
					lists.add(bean);
				} // while 끝
				
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			
			try {
				
			} catch (Exception e2) {
				e2.printStackTrace();
			} // try-catch 끝
			
		} // finally(try-catch) 끝
		
		return lists;
	} // GetshoppingInfo 끝

} // class 끝