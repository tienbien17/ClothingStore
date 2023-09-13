package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.sql.Date;

import model.*;

public class OrderDAO {
	 // Khai báo các câu lệnh truy vấn SQL cần thiết
    private static final String SELECT_ORDER_BY_ID = "SELECT * FROM Orders WHERE order_id = ?";
    private static final String SELECT_ORDER_ITEMS_BY_ORDER_ID = "SELECT * FROM OrderItems WHERE order_id = ?";
    public List<Order> getAllOrders() {
        List<Order> orderList = new ArrayList<>();

        try (Connection conn = DBConnect.getConnection()) {
            String query = "SELECT * FROM Orders";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        int orderId = rs.getInt("order_id");
                        int customerId = rs.getInt("user_id");
                        Date orderDate = rs.getDate("orderdate");
                        int shippingCost = rs.getInt("shipping_costs");
                        String address = rs.getString("address");
                        String phoneNumber = rs.getString("phone_number");
                        String description = rs.getString("description");
                        String orderStatus = rs.getString("order_status");

                        // Tạo đối tượng Order từ dữ liệu trong ResultSet
                        Order order = new Order();
                        order.setOrderId(orderId);
                        order.setUserId(customerId);
                        order.setOrderDate(orderDate.toString());
                        order.setShippingCost(shippingCost);
                        order.setAddress(address);
                        order.setPhoneNumber(phoneNumber);
                        order.setDescription(description);
                        order.setOrderStatus(orderStatus);

                        // Thêm đơn hàng vào danh sách
                        orderList.add(order);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderList;
    }

    // Hàm lấy đơn hàng theo order_id
    public Order getOrderById(int orderId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Order order = null;

        try {
            conn = DBConnect.getConnection();
            stmt = conn.prepareStatement(SELECT_ORDER_BY_ID);
            stmt.setInt(1, orderId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                // Lấy thông tin đơn hàng từ ResultSet
                int userId = rs.getInt("user_id");
                String orderDate = rs.getString("orderdate");
                int shippingCost = rs.getInt("shipping_costs");
                String address = rs.getString("address");
                String phoneNumber = rs.getString("phone_number");
                String description = rs.getString("description");
                String orderStatus = rs.getString("order_status");

                // Tạo đối tượng Order từ thông tin lấy được
                order = new Order(orderId, userId, orderDate, shippingCost, address, phoneNumber, description, orderStatus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeResources(conn, stmt, rs);
        }
        return order;
    }

    // Hàm lấy các mục đơn hàng (OrderItems) của một đơn hàng dựa trên order_id
    public List<OrderItem> getOrderItemsByOrderId(int orderId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<OrderItem> orderItems = new ArrayList<>();
        ProductDAO productDAO = new ProductDAO();
        try {
            conn = DBConnect.getConnection();
            stmt = conn.prepareStatement(SELECT_ORDER_ITEMS_BY_ORDER_ID);
            stmt.setInt(1, orderId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                // Lấy thông tin mục đơn hàng từ ResultSet
                int productId = rs.getInt("product_id");
                int colorsId = rs.getInt("colors_id");
                int quantity = rs.getInt("quantity");
                Product prd = productDAO.getProductById(productId);

                // Tạo đối tượng OrderItem và gán thông tin vào các thuộc tính
                OrderItem orderItem = new OrderItem(productId, orderId, colorsId, quantity);
                orderItem.setName(prd.getName());
                orderItem.setPrice(prd.getPrice());
                orderItem.setPriceMore(prd.getPriceMore());
                orderItem.setImageUrl(prd.getImageUrl());
                orderItems.add(orderItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeResources(conn, stmt, rs);
        }

        return orderItems;
    }
		public void saveIntoDatabase(Order order){
			List<Cart> carts = new ArrayList<Cart>();
			carts =order.getCarts();
			Connection conn = DBConnect.getConnection();
			String sql = "INSERT INTO Orders (user_id, orderdate, shipping_costs, address, phone_number, description) VALUES(?,?,?,?,?,?,?)";
			
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, order.getUserId());
				ps.setString(2, order.getOrderDate());
				ps.setInt(3, order.getShippingCost());
				ps.setString(4, order.getAddress());
				ps.setString(5, order.getPhoneNumber());
				ps.setString(6, order.getDescription());
				ps.setString(7, order.getOrderStatus());
				int row = ps.executeUpdate();
				
				sql = "INSERT INTO OrderItems (order_id, product_id, colors_id, quantity) VALUES(?,?,?,?)";
				for (Cart cart : carts) {
					ps.setInt(1, row);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void updateOrder(Order order){
			List<Cart> carts = new ArrayList<Cart>();
			carts =order.getCarts();
			Connection conn = DBConnect.getConnection();
			String sql = "UPDATE `clothingshop1`.`orders` SET `order_id` = ?, `user_id` = ?, `shipping_costs` = ?, `address` = ?, `phone_number` = ?, `description` = ?, `order_status` = ? WHERE (`order_id` = ?);";
			
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, order.getOrderId());
				ps.setInt(2, order.getUserId());
				ps.setInt(3, order.getShippingCost());
				ps.setString(4, order.getAddress());
				ps.setString(5, order.getPhoneNumber());
				ps.setString(6, order.getDescription());
				ps.setString(7, order.getOrderStatus());
				ps.setInt(8, order.getOrderId());
				int row = ps.executeUpdate();				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public List<Order> getOrdersByUserId(int userId) {
	        List<Order> orderList = new ArrayList<>();

	        try (Connection conn = DBConnect.getConnection()) {
	            String query = "SELECT * FROM Orders WHERE user_id = ?";
	            try (PreparedStatement stmt = conn.prepareStatement(query)) {
	                stmt.setInt(1, userId);

	                try (ResultSet rs = stmt.executeQuery()) {
	                    while (rs.next()) {
	                        int orderId = rs.getInt("order_id");
	                        int customerId = rs.getInt("user_id");
	                        Date orderDate = rs.getDate("orderdate");
	                        int shippingCost = rs.getInt("shipping_costs");
	                        String address = rs.getString("address");
	                        String phoneNumber = rs.getString("phone_number");
	                        String description = rs.getString("description");
	                        String orderStatus = rs.getString("order_status");

	                        // Tạo đối tượng Order từ dữ liệu trong ResultSet
	                        Order order = new Order();
	                        order.setOrderId(orderId);
	                        order.setUserId(customerId);
	                        order.setOrderDate(orderDate.toString());
	                        order.setShippingCost(shippingCost);
	                        order.setAddress(address);
	                        order.setPhoneNumber(phoneNumber);
	                        order.setDescription(description);
	                        order.setOrderStatus(orderStatus);

	                        // Thêm đơn hàng vào danh sách
	                        orderList.add(order);
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return orderList;
	    }
		public double calculateTotalPrice(List<OrderItem> orderItems) {
		    double totalPrice = 0.0;

		    for (OrderItem orderItem : orderItems) {
		        int productId = orderItem.getId(); // Lấy ID sản phẩm
		        int colorsId = orderItem.getColorsId(); // Lấy ID màu sắc
		        int quantity = orderItem.getQuantity(); // Lấy số lượng

		        // Tính tổng tiền cho mỗi mục đơn hàng dựa vào thông tin lấy được từ cơ sở dữ liệu
		        // (Giả sử ProductDAO có phương thức getProductPriceByIdAndColorId trả về giá tiền của sản phẩm dựa vào productId và colorsId)
		        double productPrice = orderItem.getPriceMore();
		        double itemTotalPrice = productPrice * quantity;
		        totalPrice += itemTotalPrice;
		    }

		    return totalPrice;
		}
		public static void main(String[] args) {
			OrderDAO orderDAO = new OrderDAO();
			List<Order> orderList = orderDAO.getAllOrders();
			for (Order order : orderList) {
				System.out.println(order.getOrderId());
			}
			
			
		}
}
