package shopping.board.model ;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import shopping.common.model.SuperDao;

public class BoardDao extends SuperDao {
	public int ReplyData( Board bean ){
		
		String sql = " update boards set orderno = orderno + 1  where groupno = ? and orderno > ? ";
		
		PreparedStatement pstmt = null ;
		int cnt = - 1;
		
		try {
			
			if( conn == null ){ super.conn = super.getConnection() ; }
			conn.setAutoCommit( false );
			pstmt = super.conn.prepareStatement(sql) ;
			
			// 치환
			pstmt.setInt(1, bean.getGroupno());
			pstmt.setInt(2, bean.getOrderno());

			
			cnt = pstmt.executeUpdate() ; 
			
			sql = " insert into boards ( no, subject, writer, password, content, groupno, orderno, depth ) values ( myboard.nextval, ?, ?, ?, ?, ?, ?, ? ) " ;
			
			pstmt = null;
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bean.getSubject());
			pstmt.setString(2, bean.getWriter());
			pstmt.setString(3, bean.getPassword());
			pstmt.setString(4, bean.getContent());
			pstmt.setInt		 (5, bean.getGroupno());
			pstmt.setInt		 (6, bean.getOrderno() +1 );
			pstmt.setInt		 (7, bean.getDepth() +1 );
			
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
		
	} // ReplyData 끝
	
	public int InsertData( Board bean ){
		String sql = " insert into boards( no, subject, writer, password, content, groupno ) " ;
						sql += " values(myboard.nextval, ?, ? ,? , ?, myboard.currval) " ;

		PreparedStatement pstmt = null ;
		int cnt = -1 ;
		
		try {
			
						if( conn == null ){ 
							super.conn = super.getConnection() ; 
						}
					
						conn.setAutoCommit( false );
						pstmt = super.conn.prepareStatement(sql) ;
						
						pstmt.setString( 1, bean.getSubject() );
						pstmt.setString( 2, bean.getWriter() );
						pstmt.setString( 3, bean.getPassword() );
						pstmt.setString( 4, bean.getContent() );
					
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
				if( pstmt != null ){
					pstmt.close(); 
				}
				super.closeConnection();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return cnt ;
	}
	
	public int UpdateData( Board bean ){
		String sql = " update boards set content=?, password=?, subject=?, writer=?, readhit=? where no = ? " ;
		
		PreparedStatement pstmt = null ;
		int cnt = - 1 ;
		
		try {
			if( conn == null ){ super.conn = super.getConnection() ; }
			conn.setAutoCommit( false );
			pstmt = super.conn.prepareStatement(sql) ;

			pstmt.setString(1, bean.getContent());
			pstmt.setString(2, bean.getPassword());
			pstmt.setString(3, bean.getSubject());
			pstmt.setString(4, bean.getWriter());
			pstmt.setInt		 (5, bean.getReadhit());
			pstmt.setInt		 (6, bean.getNo());
			pstmt.setString(7, bean.getRegdate());
			
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
	public int DeleteData( int no ){
		String sql = " delete from boards " ;
		sql += " where no = ? " ;
		sql += " " ;
		
		PreparedStatement pstmt = null ;
		int cnt = - 1 ;
		
		try {
			
			if( conn == null ){ super.conn = super.getConnection() ; }
			conn.setAutoCommit( false );
			pstmt = super.conn.prepareStatement(sql) ;
			
			pstmt.setInt(1, no);
			
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
	
	public BoardDao() {
	
	}
	public List<Board> SelectDataList(int beginRow, int endRow, String mode, String keyword) {
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		
		 String sql = " select ranking, no, subject, writer, password, content, readhit, regdate, groupno, orderno, depth, remark ";
		 		  		 sql += " from ( select no, subject, writer, password, content, readhit, regdate, groupno, orderno, depth, remark, rank() over(order by groupno desc, orderno asc, depth asc) as ranking from boards ";
		 				 
		 if (mode.equalsIgnoreCase("all") == false) {
		 		 System.out.println("Not all Search Mode");
		 		 sql += " where " + mode + " like '%" + keyword + "%' " ;
		 }
		 		  		 
		 		  		sql += " ) ";
		 		  		sql += " where ranking between ? and ? ";
		 				 
		 
		
		List<Board> lists = new ArrayList<Board>();
		
		try {
			
			if( conn == null ){ super.conn = super.getConnection() ; }
			pstmt = super.conn.prepareStatement(sql) ;	
			
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, endRow);
			
			rs = pstmt.executeQuery() ;			
			
			while( rs.next() ){
				Board bean = new Board();
				
				bean.setNo(rs.getInt("no"));
				bean.setReadhit(rs.getInt("readhit"));
				bean.setGroupno(rs.getInt("groupno"));
				bean.setOrderno(rs.getInt("orderno"));
				bean.setDepth(rs.getInt("depth"));
				
				bean.setSubject(rs.getString("subject"));
				bean.setWriter(rs.getString("writer"));
				bean.setPassword(rs.getString("password"));
				bean.setContent(rs.getString("content"));
				bean.setRegdate(rs.getString("regdate"));
				bean.setRemark(rs.getString("remark"));
				
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
		
		return lists ;
	}

	public Board SelectDataByPk( int no ){
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;				
		
		String sql = " select * from boards where no = ? " ;
		
		Board bean = null ;
		
		List<Board> lists = new ArrayList<Board>();
		
		try {
			if( this.conn == null ){ 
				this.conn = this.getConnection() ; 
			}
			
			pstmt = this.conn.prepareStatement(sql) ;			
			pstmt.setInt( 1, no ); 
			rs = pstmt.executeQuery() ; 
			
				if ( rs.next() ) { 
					
					bean = new Board();
					
					bean.setNo(rs.getInt("no"));
					bean.setReadhit(rs.getInt("readhit"));
					bean.setGroupno(rs.getInt("groupno"));
					bean.setOrderno(rs.getInt("orderno"));
					bean.setDepth(rs.getInt("depth"));
					
					bean.setSubject(rs.getString("subject"));
					bean.setWriter(rs.getString("writer"));
					bean.setPassword(rs.getString("password"));
					bean.setContent(rs.getString("content"));
					bean.setRegdate(rs.getString("regdate"));
					bean.setRemark(rs.getString("remark"));
					
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
		
	} // SelectDataList 끝
	
	public List<Board> SelectDataList() {
		
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		
		String sql = " " ;
		sql += " " ;
		sql += " " ; 

		List<Board> lists = new ArrayList<Board>() ;
		
		try {
			
			if( this.conn == null ){ this.conn = this.getConnection() ; }			
			pstmt = this.conn.prepareStatement(sql) ;			

			
			rs = pstmt.executeQuery() ; 
			while ( rs.next() ) {
				Board bean = new Board() ; 
						
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
	
	public List<Board> SelectDataList(int beginRow, int endRow) {
		
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		
		String sql = " " ;
		sql += " " ;
		sql += " " ; 

		List<Board> lists = new ArrayList<Board>() ;
		try {
			if( this.conn == null ){ this.conn = this.getConnection() ; }			
			pstmt = this.conn.prepareStatement(sql) ;
			pstmt.setInt(1, beginRow);
			pstmt.setInt(2, endRow); 
			
			rs = pstmt.executeQuery() ; 
			while ( rs.next() ) {
				Board bean = new Board() ; 
			 		
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
	public int UpdateReadhit(int no) {
		PreparedStatement pstmt = null ;
		
		String sql = " update boards set readhit = readhit + 1 where no = ? " ;
		
		int cnt = - 1 ; 
		try {
			if( this.conn == null ){ this.conn = this.getConnection() ; }
			conn.setAutoCommit( false ); 
			pstmt = this.conn.prepareStatement( sql ) ;
			
			pstmt.setInt(1, no);
			
			cnt = pstmt.executeUpdate() ;
			conn.commit(); 
		} catch (Exception e) {
			e.printStackTrace();
			cnt = - 1 ;
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally{
			try {
				if( pstmt != null ){ pstmt.close(); }
				this.closeConnection();
			} catch (Exception e2) {
				e2.printStackTrace(); 
			}
		}
		return cnt ; 
	}
	
	public int SelectTotalCount(String mode, String keyword) {
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;				

		String sql = " select count(*) as cnt from boards " ;
		
		if (mode.equalsIgnoreCase("all") == false) {
			System.out.println("Not all search Mode ");
			sql += " where " + mode + " like '%" + keyword + "%' "  ;
		}
		
		sql += " " ;
		
		int cnt = 0 ; 
		
		try {
			
			if( this.conn == null ) { 
				this.conn = this.getConnection() ; 
			}			
			
			pstmt = this.conn.prepareStatement(sql) ;			 
			rs = pstmt.executeQuery() ; 
			
			if (rs.next()) {
				cnt = rs.getInt("cnt");
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

	public int UpdateReply(int groupno, int orderno) {
		PreparedStatement pstmt = null ;
		String sql = " " ;
		sql += " " ;
		sql += " " ;
		
		int cnt = -99999 ; 
		try {
			if( this.conn == null ){ this.conn = this.getConnection() ; }
			conn.setAutoCommit( false ); 
			pstmt = this.conn.prepareStatement( sql ) ;
			
			cnt = pstmt.executeUpdate() ;
			conn.commit(); 
		} catch (Exception e) {
			e.printStackTrace();
			cnt = -99999 ;
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally{
			try {
				if( pstmt != null ){ pstmt.close(); }
				this.closeConnection();
			} catch (Exception e2) {
				e2.printStackTrace(); 
			}
		}
		return cnt ; 
	}
	

	public int GetGroupnoCount(int groupno) {

		PreparedStatement pstmt = null ;
		ResultSet rs = null ;				

		String sql = " select count(*) as cnt from boards where groupno = ? " ;
		
		int cnt = 0 ; 
		
		try {
			
			if( this.conn == null ) { 
				this.conn = this.getConnection() ; 
			}			
			
			pstmt = this.conn.prepareStatement(sql) ;
			
			pstmt.setInt(1, groupno);
			
			rs = pstmt.executeQuery() ; 
			
			if (rs.next()) {
				cnt = rs.getInt("cnt");
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
}