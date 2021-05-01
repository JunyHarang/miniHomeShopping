package shopping.mall.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import shopping.common.model.SuperDao;
import shopping.member.model.Member;

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

} // class 끝