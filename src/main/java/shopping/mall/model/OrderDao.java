package shopping.mall.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import shopping.common.model.SuperDao;

public class OrderDao extends SuperDao{

	public List<Order> SelectDataList() {
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;				
		String sql = "select * from orders " ;
		List<Order> lists = new ArrayList<Order>() ;
		try {
			if( this.conn == null ){ this.conn = this.getConnection() ; }			
			pstmt = this.conn.prepareStatement(sql) ;			
			rs = pstmt.executeQuery() ; 
			while ( rs.next() ) {
				Order bean = new Order() ; 
				bean.setMid( rs.getString("mid") );				
				bean.setOid( rs.getInt("oid") );				
				bean.setOrderdate( String.valueOf( rs.getDate("orderdate") ));			
				lists.add( bean ) ; 
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally{
			try {
				if( rs != null){ rs.close(); } 
				if( pstmt != null){ pstmt.close(); } 
				this.closeConnection() ;
			} catch (Exception e2) {
				e2.printStackTrace(); 
			}
		} 		
		return lists  ;
	}

	
	
	public int InsertData(String id) {
		String sql = " insert into orders(oid, mid, orderdate) " ; 
		sql += " values( seqoid.nextval, ?, sysdate )" ;

		PreparedStatement pstmt = null ;
		int cnt = -99999 ;
		try {
			if( conn == null ){ super.conn = super.getConnection() ; }
			conn.setAutoCommit( false );
			pstmt = super.conn.prepareStatement(sql) ;
			//�����ڰ� ������ �� 2 : ? ������ ��
			pstmt.setString(1, id );   
			
			cnt = pstmt.executeUpdate() ; 
			conn.commit(); 
		} catch (Exception e) {
			SQLException err = (SQLException)e ;
			//getErrorCode() : ����Ŭ ���� ����� ����
			//�� : not null �̸� 1400 
			cnt = - err.getErrorCode() ;			
			e.printStackTrace();
			try {
				conn.rollback(); 
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} finally{
			try {
				if( pstmt != null ){ pstmt.close(); }
				super.closeConnection(); 
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return cnt ;		
	}

	public int GetMaxOrderId() {
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;				
		String sql = " select max(oid) as mycol from orders " ; 
		int cnt = -99999 ;
		try {
			if( this.conn == null ){ this.conn = this.getConnection() ; }			
			pstmt = this.conn.prepareStatement(sql) ;			 
			rs = pstmt.executeQuery() ; 
			
			if ( rs.next() ) { 
				cnt = rs.getInt("mycol");
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally{
			try {
				if( rs != null){ rs.close(); } 
				if( pstmt != null){ pstmt.close(); } 
				this.closeConnection() ;
			} catch (Exception e2) {
				e2.printStackTrace(); 
			}
		} 		
		return cnt  ; 
	}

	public List<Order> OrderMall(String id) {
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;				
		String sql = "select * from orders " ;
		sql += " where mid = ? order by orderdate desc  " ;
		List<Order> lists = new ArrayList<Order>() ;
		try {
			if( this.conn == null ){ this.conn = this.getConnection() ; }			
			pstmt = this.conn.prepareStatement(sql) ;
			pstmt.setString( 1, id ); 
			rs = pstmt.executeQuery() ; 
			while ( rs.next() ) {
				Order bean = new Order() ; 
				bean.setMid( rs.getString("mid") );				
				bean.setOid( rs.getInt("oid") );				
				bean.setOrderdate( String.valueOf( rs.getDate("orderdate") ));			
				lists.add( bean ) ; 
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally{
			try {
				if( rs != null){ rs.close(); } 
				if( pstmt != null){ pstmt.close(); } 
				this.closeConnection() ;
			} catch (Exception e2) {
				e2.printStackTrace(); 
			}
		} 		
		return lists  ;
	}


	
	public Order SelectDataByPk(int oid) {
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;				
		String sql = "select * from orders " ;  
		sql += " where oid = ?" ; 

		Order bean = null ;
		try {
			if( this.conn == null ){ this.conn = this.getConnection() ; }			
			pstmt = this.conn.prepareStatement(sql) ;			
			pstmt.setInt( 1, oid   ); 
			rs = pstmt.executeQuery() ;			
			if ( rs.next() ) {
				bean = new Order() ; 
				bean.setMid( rs.getString("mid") );
				bean.setOid( rs.getInt("oid") );
				bean.setOrderdate( String.valueOf( rs.getDate("orderdate") )); 	
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally{
			try {
				if( rs != null){ rs.close(); } 
				if( pstmt != null){ pstmt.close(); } 
				this.closeConnection() ;
			} catch (Exception e2) {
				e2.printStackTrace(); 
			}
		} 		
		return bean  ;
	}
}