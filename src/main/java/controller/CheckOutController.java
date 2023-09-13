package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.x.protobuf.MysqlxCrud.Order;

import java.time.LocalDateTime;
import dao.ProductDAO;
import model.Account;
import model.*;

/**
 * Servlet implementation class CheckOutController
 */
@WebServlet("/check-out")
public class CheckOutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckOutController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    ProductDAO productDAO = new ProductDAO();
	    HttpSession session = req.getSession();
	    Account account = (Account) session.getAttribute("username");
	    ArrayList<Cart> cart_list = new ArrayList<Cart>();
	    int userId = -1;
	    if (account != null) {
	        userId = account.getId();
	        cart_list = (ArrayList<Cart>) productDAO.getCartByUserId(userId);
	        System.out.println("user id :" + userId);
	        session.setAttribute("carts", cart_list);
	    } else {
	        cart_list = (ArrayList<Cart>) session.getAttribute("carts");
	    }
	    String name = req.getParameter("name");
	    String phoneNumber = req.getParameter("phoneNumber");
	    String address = req.getParameter("address");
	    String description = req.getParameter("description");
	    int shippingCost = 50;
	    String orderStatus =  "Đơn Hàng đã được Khởi Tạo ";
	    
	    // Lấy đối tượng LocalDateTime từ hệ thống
	    LocalDateTime currentDateTime = LocalDateTime.now();
	    
	    // Chuyển đổi đối tượng LocalDateTime thành chuỗi dạng ngày giờ
	    String dateString = currentDateTime.toString();
	    System.out.println("Date: " + dateString);
	    
	    // Tạo đối tượng Order từ thông tin lấy được
	    model.Order order = new model.Order(userId, dateString, shippingCost, address, phoneNumber, description, cart_list,orderStatus);
	    
	    // Gọi phương thức addOrder để lưu đơn hàng vào cơ sở dữ liệu
	    try {
			productDAO.addOrder(order, cart_list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    cart_list = null;
	    session.setAttribute("carts", cart_list);
	    productDAO.deleteShoppingCartById(userId);
	    resp.sendRedirect("index");
	}

}
