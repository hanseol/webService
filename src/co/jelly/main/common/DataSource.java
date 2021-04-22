package co.jelly.main.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
	private static DataSource dataSource = new DataSource();
	
	private String driver="oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "jelly";
	private String password = "jelly";
	
	public Connection conn;
	
	
	private DataSource() {} //외부에서 인스턴스를 생성하지 못하도록 함.
	
	
	public static DataSource getInstance() { //static:공유&참조
		return dataSource;
	}
	
	public Connection getConnection() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,user,password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
}
