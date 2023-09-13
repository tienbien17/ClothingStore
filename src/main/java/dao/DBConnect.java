package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnect {
	public static Connection getConnection() {
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/clothingshop1";
		String user = "root";
		String pass = "123456";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			//kết nối
			conn = DriverManager.getConnection(url, user, pass);
			System.out.println("connect success!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	public static void main(String[] args) {
		getConnection();
	}
	public static void closeResources(Connection conn, PreparedStatement stmt, ResultSet rs) {
		// TODO Auto-generated method stub
		
	}
}
