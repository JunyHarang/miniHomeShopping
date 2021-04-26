package shopping.mall.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import shopping.common.model.SuperDao;
import shopping.product.model.Product;

public class OrderDetailDao  extends SuperDao{

	public int UpdateRemark( Product bean ) {
		// ��ǰ ��ȣ pnum�� �ش��ϴ� ��� ���� �÷� remark�� ��ǰ �̸����� �����մϴ�.
		String sql = " update orderdetails set ";
		sql += " remark = ? " ;
		sql += " where pnum = ? " ; 
		PreparedStatement pstmt = null ;
		int cnt = -99999 ;
		try {
			if( conn == null ){ super.conn = super.getConnection() ; }
			conn.setAutoCommit( false );
			pstmt = super.conn.prepareStatement(sql) ;
			pstmt.setString(1, bean.getName());
			pstmt.setInt(2, bean.getNum());			
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
	
	public int InsertData(OrderDetail bean) {
		String sql = " insert into orderdetails(odid, oid, pnum, qty) " ; 
		sql += " values( seqodid.nextval, ?, ?, ? ) " ;

		PreparedStatement pstmt = null ;
		int cnt = -99999 ;
		try {
			if( conn == null ){ super.conn = super.getConnection() ; }
			conn.setAutoCommit( false );
			pstmt = super.conn.prepareStatement(sql) ;
			//�����ڰ� ������ �� 2 : ? ������ ��
			pstmt.setInt(1, bean.getOid() );
			pstmt.setInt(2, bean.getPnum() );			
			pstmt.setInt(3, bean.getQty() ); 
			
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

}
