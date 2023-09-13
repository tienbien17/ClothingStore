package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.Session;

import dao.OrderDAO;
import model.*;

/**
 * Servlet implementation class OrderController
 */
@WebServlet("/orders")
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Account auth = (Account) req.getSession().getAttribute("username");
		int userId = auth.getId() ; 
		OrderDAO orderDAO = new  OrderDAO();
        // Gọi phương thức trong DAO để lấy danh sách các đơn hàng theo user ID
        List<Order> orderList = orderDAO.getOrdersByUserId(userId);

        // Gửi danh sách đơn hàng vào request attribute
        req.setAttribute("orderList", orderList);
		
		req.getRequestDispatcher("/views/client/orders.jsp").forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
