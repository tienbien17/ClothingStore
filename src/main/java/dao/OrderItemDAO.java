package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Category;
import model.Order;
import model.OrderItem;

public class OrderItemDAO {
	//getAll
		public List<OrderItem>getAll(){
			List<OrderItem> list = new ArrayList<OrderItem>();
			Connection conn = DBConnect.getConnection();
			String sql = "select * from OrderItems";
			
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					OrderItem orderItems = new OrderItem();
					orderItems.setOrder_id(rs.getInt("order_id"));
					orderItems.setId(rs.getInt("product_id"));
					orderItems.setColors_id(rs.getString("colors_id"));
					orderItems.setQuantity(rs.getInt("Quantity"));			
					orderItems.setStatus(rs.getString("status"));
					list.add(orderItems);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return list;
		}
		//Lấy thông tin theo ID
		public OrderItem getById(int id){
			return getProductById(id);
		}
		//Lấy thông tin theo ID
		public OrderItem getProductById(int id){
			OrderItem orderItems = null;
			Connection conn = DBConnect.getConnection();
			String sql = "select * from OrderItems where order_id=?";
			
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					orderItems = new OrderItem();
					orderItems.setOrder_id(rs.getInt("order_id"));
					orderItems.setId(rs.getInt("product_id"));
					orderItems.setColors_id(rs.getString("colors_id"));
					orderItems.setQuantity(rs.getInt("Quantity"));			
					orderItems.setStatus(rs.getString("status"));
					//Id, Name, Description trùng với tên ở trên SQL
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return orderItems;
		}
		//Cập nhập sản phẩm
		public boolean update(OrderItem orderItem) {
			boolean check = false;
			Connection conn = DBConnect.getConnection();
			String sql = "update OrderItems set product_id=?,colors_id=?,quantity=?,status=? where order_id=?";
			
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				
				ps.setInt(1, orderItem.getProduct_id());
				ps.setString(2, orderItem.getColors_id());
				ps.setInt(3, orderItem.getQuantity());
				ps.setString(4, orderItem.getStatus());
				ps.setInt(5, orderItem.getOrder_id());
				
				int row = ps.executeUpdate();
				if(row>0)
					check = true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return check;
		}
}
