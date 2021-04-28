package shopping.common.model ;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SuperDao {
	protected Connection conn = null;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "proman";
	private String password = "oracle";
	
	protected Connection getConnection() {		
		try {			 
			return DriverManager.getConnection(url, id, password);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}	
	public void closeConnection(){
		conn = null ;
//		if (conn != null) {
//			try {
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}			
//		}
	}	
	public SuperDao() {
		try {
			Class.forName(driver);
			this.conn = getConnection();
			if (conn != null) {
				System.out.println("connection success");
			} else {
				System.out.println("connection failed");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("class not found");
			e.printStackTrace();
		}	
	}
}