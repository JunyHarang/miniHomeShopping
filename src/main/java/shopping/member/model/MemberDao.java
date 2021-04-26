package shopping.member.model ;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import shopping.common.model.SuperDao;

public class MemberDao extends SuperDao {

	
	public int InsertData( Member bean ){
		String sql = " insert into members(id, name, password, gender, birth, marriage, salary, address1, address2, manager, zipcode, mpoint) " ; 
		sql += " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, default) " ;

		PreparedStatement pstmt = null ;
		int cnt = -1 ;
		try {
			if( conn == null ){ super.conn = super.getConnection() ; }
			conn.setAutoCommit( false );
			pstmt = super.conn.prepareStatement(sql) ;
			
			pstmt.setString(1, bean.getId());
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getPassword());
			pstmt.setString(4, bean.getGender());
			pstmt.setString(5, bean.getBirth());
			pstmt.setString(6, bean.getMarriage());
			pstmt.setInt		 (7, bean.getSalary());
			pstmt.setString(8, bean.getAddress1());
			pstmt.setString(9, bean.getAddress2());
			pstmt.setString(10, bean.getManager());
			pstmt.setString(11, bean.getZipcode());
			
			cnt = pstmt.executeUpdate() ; 
			conn.commit(); 
			
		} catch (Exception e) {
			SQLException err = (SQLException)e ;
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
	public int UpdateData( Member bean ){
		String sql = " " ; 
		sql += " " ;
		sql += " " ;		

		PreparedStatement pstmt = null ;
		int cnt = -1 ;
		try {
			if( conn == null ){ super.conn = super.getConnection() ; }
			conn.setAutoCommit( false );
			pstmt = super.conn.prepareStatement(sql) ;			
			
			cnt = pstmt.executeUpdate() ; 
			conn.commit(); 
		} catch (Exception e) {
			SQLException err = (SQLException)e ;			
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
	public int DeleteData( String pmkey ){		
		String sql = " " ; 
		sql += " " ;
		sql += " " ;	

		
		PreparedStatement pstmt = null ;
		int cnt = -1 ;
		try {
			if( conn == null ){ super.conn = super.getConnection() ; }
			conn.setAutoCommit( false );
			pstmt = super.conn.prepareStatement(sql) ;
			pstmt.setString(1, pmkey);			
			cnt = pstmt.executeUpdate() ; 
			conn.commit(); 
		} catch (Exception e) {
			SQLException err = (SQLException)e ;			
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
	
	public List<Member> SelectDataList(int beginRow, int endRow) {
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;

		String sql = " select * from members " ; 
		sql += " order by name asc " ;

		
		List<Member> lists = new ArrayList<Member>();
		try {
			if( conn == null ){ super.conn = super.getConnection() ; }
			pstmt = super.conn.prepareStatement(sql) ;			
//			pstmt.setInt(1, beginRow);
//			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery() ;		
			
			while( rs.next() ){
				Member bean = new Member();
				
				bean.setBirth(String.valueOf(rs.getDate("birth")));
				
				bean.setMpoint(rs.getInt("mpoint"));
				bean.setSalary(rs.getInt("salary"));
				
				bean.setAddress1(rs.getString("address1"));
				bean.setAddress2(rs.getString("address2"));
				bean.setGender(rs.getString("gender"));
				bean.setId(rs.getString("id"));
				bean.setManager(rs.getString("manager"));
				bean.setMarriage(rs.getString("marriage"));
				bean.setName(rs.getString("name"));
				bean.setPassword(rs.getString("password"));
				bean.setZipcode(rs.getString("zipcode"));
				
				lists.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if( rs != null ){ rs.close(); }
				if( pstmt != null ){ pstmt.close(); }
				super.closeConnection(); 
			} catch (Exception e2) {
				e2.printStackTrace(); 
			}
		}
		
		return lists;
	}
	public MemberDao() {
		
	}
	public Member SelectDataByPk( String id ){
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;				

		String sql = " select * from members " ; 
		sql += " where id = ? " ;

		Member bean = null ;
		
		List<Member> lists = new ArrayList<Member>();
		
		try {
			if( this.conn == null ){ this.conn = this.getConnection() ; }			
			pstmt = this.conn.prepareStatement(sql) ;			
			pstmt.setString( 1, id   ); 
			rs = pstmt.executeQuery() ;
			
			if ( rs.next() ) {
				bean = new Member();
				
				bean.setBirth(String.valueOf(rs.getDate("birth")));
				bean.setMpoint(rs.getInt("mpoint"));
				bean.setSalary(rs.getInt("salary"));
				
				bean.setAddress1(rs.getString("address1"));
				bean.setAddress2(rs.getString("address2"));
				bean.setGender(rs.getString("gender"));
				bean.setId(rs.getString("id"));
				bean.setManager(rs.getString("manager"));
				bean.setMarriage(rs.getString("marriage"));
				bean.setName(rs.getString("name"));
				bean.setPassword(rs.getString("password"));
				bean.setZipcode(rs.getString("zipcode"));
				
				lists.add(bean);
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
	
	public int SelectTotalCount() {
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;				
		
		String sql = " " ; 
		sql += " " ;
		sql += " " ;		

		
		int cnt = -99999 ;
		try {
			if( this.conn == null ){ this.conn = this.getConnection() ; }			
			pstmt = this.conn.prepareStatement(sql) ;			 
			rs = pstmt.executeQuery() ; 			
			
			
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
	
	public int UpdateMpoint(String id, int mpoint ) {
		String sql = " " ; 
		sql += " " ;
		sql += " " ;		

		PreparedStatement pstmt = null ;
		int cnt = -99999 ;
		try {
			if( conn == null ){ super.conn = super.getConnection() ; }
			conn.setAutoCommit( false );
			pstmt = super.conn.prepareStatement(sql) ;
		
			
			cnt = pstmt.executeUpdate() ; 
			conn.commit(); 
		} catch (Exception e) {
			SQLException err = (SQLException)e ;			
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
	public Member selectData(String id, String pwd) {
		Member bean = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = " select * from members where id = ? and password = ? ";
		
		try {
				if (conn == null) {
					super.conn = super.getConnection();
				}
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, id);
				pstmt.setString(2, pwd);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					bean = new Member();
					
					bean.setBirth(String.valueOf(rs.getDate("birth")));
					bean.setMpoint(rs.getInt("mpoint"));
					bean.setSalary(rs.getInt("salary"));
					
					bean.setAddress1(rs.getString("address1"));
					bean.setAddress2(rs.getString("address2"));
					bean.setGender(rs.getString("gender"));
					bean.setId(rs.getString("id"));
					bean.setManager(rs.getString("manager"));
					bean.setMarriage(rs.getString("marriage"));
					bean.setName(rs.getString("name"));
					bean.setPassword(rs.getString("password"));
					bean.setZipcode(rs.getString("zipcode"));
				}
				
		} catch (Exception e) {
			e.printStackTrace();
			bean = null;
			
		} finally {
			
			try {
					if(!rs.equals(null)) {rs.close();}
					if(!pstmt.equals(null)) {pstmt.close();}
					
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return bean;
	}
	
	public List<Member> GetManagerList() {
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;

		String sql = " select * from members " ; 
		sql += " where manager is null " ;
		sql += " order by name asc " ;

		
		List<Member> lists = new ArrayList<Member>();
		try {
			if( conn == null ){ super.conn = super.getConnection() ; }
			pstmt = super.conn.prepareStatement(sql) ;			
//			pstmt.setInt(1, beginRow);
//			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery() ;		
			
			while( rs.next() ){
				Member bean = new Member();
				
				bean.setBirth(String.valueOf(rs.getDate("birth")));
				
				bean.setMpoint(rs.getInt("mpoint"));
				bean.setSalary(rs.getInt("salary"));
				
				bean.setAddress1(rs.getString("address1"));
				bean.setAddress2(rs.getString("address2"));
				bean.setGender(rs.getString("gender"));
				bean.setId(rs.getString("id"));
				bean.setManager(rs.getString("manager"));
				bean.setMarriage(rs.getString("marriage"));
				bean.setName(rs.getString("name"));
				bean.setPassword(rs.getString("password"));
				bean.setZipcode(rs.getString("zipcode"));
				
				lists.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally{
			
			try {
				if( rs != null ){ rs.close(); }
				if( pstmt != null ){ pstmt.close(); }
				super.closeConnection();
				
			} catch (Exception e2) {
				
				e2.printStackTrace(); 
			}
		}
		
		return lists;
	}
	
} // Class 끝