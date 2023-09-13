package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import model.Account;

public class AccountDAO {
	// các phương thức
	// getAll
	public List<Account>getAll(){
		List<Account> list = new ArrayList<Account>();
		Connection conn = DBConnect.getConnection();
		String sql = "SELECT * FROM clothingshop1.users;";
		try {
			PreparedStatement ps =conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
			Account account = new Account();
			
			account.setId(rs.getInt("user_id"));
			account.setFullName(rs.getNString("full_name"));	
			account.setUserName(rs.getNString("username"));
			account.setPassword(rs.getNString("password"));
			account.setAddress(rs.getNString("address"));
			account.setPhoneNumber(rs.getNString("phone_number"));
			account.setCreateAt(rs.getString("created_at"));
			account.setEmail(rs.getNString("email"));
			account.setActive(rs.getBoolean("active"));
			account.setUserRoleCode(rs.getInt("role_code"));				
			list.add(account);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;						
	}
	// getByID
	public Account getUserById(int user_id) {
		Account account = null;
		Connection conn = DBConnect.getConnection();
		String sql = "SELECT * FROM users where user_id=? ";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, user_id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				account = new Account();
				account.setId(rs.getInt("user_id"));
				account.setFullName(rs.getNString("full_name"));	
				account.setUserName(rs.getNString("username"));
				account.setPassword(rs.getNString("password"));
				account.setAddress(rs.getNString("address"));
				account.setPhoneNumber(rs.getNString("phone_number"));
				account.setCreateAt(rs.getString("created_at"));
				account.setEmail(rs.getNString("email"));
				account.setActive(rs.getBoolean("active"));
				account.setUserRoleCode(rs.getInt("role_code"));	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return account;
		
	}
	// insert
	public boolean insert(Account users) {
		boolean check = false;
		Connection conn = DBConnect.getConnection();
		String sql = "insert into Users (full_name, username, password, address, phone_number, email, role_code) "
				+ "values(?,?,?,?,?,?,?) ";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, users.getFullName());
			ps.setString(2, users.getUserName());
			ps.setString(3, users.getPassword());
			ps.setString(4, users.getAddress());
			ps.setString(5, users.getPhoneNumber());					
			ps.setString(6, (users.getCreateAt()));

			ps.setString(6, users.getEmail());					

			ps.setInt(7, users.getUserRoleCode());

			
			int row = ps.executeUpdate();
			if(row>0)
				check = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return check;
	}
	// update
	
	// dalete

	// Phương thức đăng nhập hệ thống

	public Account checkLogin(String username, String password) {
		Account account = null;
		Connection conn = DBConnect.getConnection();
		String sql = "select * from users where username=? and password=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				account = new Account();
				account.setId(rs.getInt(1));
				account.setFullName(rs.getNString(2));	
				account.setUserName(rs.getNString(3));
				account.setPassword(rs.getNString(4));
				account.setAddress(rs.getNString(5));
				account.setPhoneNumber(rs.getNString(6));
				account.setCreateAt(rs.getString(7));
				account.setEmail(rs.getNString(8));
				account.setActive(rs.getBoolean(9));
				account.setUserRoleCode(rs.getInt(10));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return account;		
	}

	public void deleteUserById(int user_id) {
		Connection conn = DBConnect.getConnection();
		String sql = "delete from users where user_id=?";
		try {
			   // Xóa dòng từ bảng "orderitems" trước
	        String deleteOrderItemsSQL = "DELETE FROM orderitems WHERE order_id IN (SELECT order_id FROM orders WHERE user_id=?)";
	        PreparedStatement deleteOrderItemsStatement = conn.prepareStatement(deleteOrderItemsSQL);
	        deleteOrderItemsStatement.setInt(1, user_id);
	        deleteOrderItemsStatement.executeUpdate();
		       // Xóa dòng từ bảng "orders" trước
	        String deleteOrdersSQL = "DELETE FROM orders WHERE user_id=?";
	        PreparedStatement deleteOrdersStatement = conn.prepareStatement(deleteOrdersSQL);
	        deleteOrdersStatement.setInt(1, user_id);
	        deleteOrdersStatement.executeUpdate();

	        // Sau đó, xóa dòng từ bảng "users"
	        String deleteUserSQL = "DELETE FROM users WHERE user_id=?";
	        PreparedStatement deleteUserStatement = conn.prepareStatement(deleteUserSQL);
	        deleteUserStatement.setInt(1, user_id);
	        deleteUserStatement.executeUpdate();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, user_id);
			ps.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public boolean updateUserById(Account users) {
		boolean check = false;
		Connection conn = DBConnect.getConnection();
		String sql ="update users set full_name=?,username=?,password=?,address=?,"
				+ "phone_number=?,email=?,active=?,role_code=? where user_id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, users.getFullName());
			ps.setString(2, users.getUserName());
			ps.setString(3, users.getPassword());
			ps.setString(4, users.getAddress());
			ps.setString(5, users.getPhoneNumber());
			ps.setString(6, users.getEmail());
			ps.setBoolean(7, users.isActive());
			ps.setInt(8, users.getUserRoleCode());
			ps.setInt(9, users.getId());
			int row = ps.executeUpdate();
			if(row>0)
				check=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return check;
	}
	
	public Account checkLoginUser(String userName, String password) {
		Account account = null;
		Connection conn = DBConnect.getConnection();
		String sql = "select * from users where username='?' && password= '?'";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				account = new Account();
				account.setId(rs.getInt(1));
				account.setUserName(rs.getNString(2));
				account.setActive(rs.getBoolean(3));
				account.setPassword(rs.getNString(4));
				account.setUserRoleCode(rs.getInt(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (account != null)
			System.out.println("login thanh cong");
		else {
			System.out.println("login that bai");
		}
		return account;

	}

	public Account userLogin(String email, String password) {
		Account user = null;
		Connection conn = DBConnect.getConnection();
		try {
			String query = "select * from users where username = ? and password=?";
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, email);
			pst.setString(2, password);
			ResultSet rs = pst.executeQuery();
			rs = pst.executeQuery();
			if (rs.next()) {
				user = new Account();
				user.setId(rs.getInt("user_id"));
				user.setUserName(rs.getString("full_name"));
				user.setPassword(rs.getString("password"));
			}
		} catch (SQLException e) {
			System.out.print(e.getMessage());
		}
		if (user != null)
			System.out.println("login thanh cong");
		else {
			System.out.println("login that bai");
		}
		return user;
	}

	public boolean checkValidUserName(String username) {
			boolean check = true;
			Connection conn = DBConnect.getConnection();
			String query = "select * from users where username = ? ";
        	PreparedStatement pst;
			try {
				pst = conn.prepareStatement(query);
				pst.setString(1, username);
				ResultSet rs = pst.executeQuery();
				rs = pst.executeQuery();
				if (rs.next()) {
					check = false;		
					}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return check;	
		}
	public void saveAccount(Account account) {
        try (Connection conn = DBConnect.getConnection()) {
            // Thêm tài khoản vào bảng "Accounts"
            String insertAccountQuery = "INSERT INTO users (full_name, username, email, phone_number, address, active, password, role_code,created_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";
            try (PreparedStatement stmt = conn.prepareStatement(insertAccountQuery)) {
                stmt.setString(1, account.getFullName());
                stmt.setString(2, account.getUserName());
                stmt.setString(3, account.getEmail());
                stmt.setString(4, account.getPhoneNumber());
                stmt.setString(5, account.getAddress());
                stmt.setBoolean(6, account.isActive());
                stmt.setString(7, account.getPassword());
                stmt.setInt(8, account.getUserRoleCode());
                stmt.setString(9, account.getCreateAt());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
