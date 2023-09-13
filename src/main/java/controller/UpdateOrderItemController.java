package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mysql.cj.protocol.a.OffsetDateTimeValueEncoder;

import dao.CategoryDAO;
import dao.*;
import model.Category;
import model.*;

@WebServlet(urlPatterns = {"/updateorderstatus"})
public class UpdateOrderItemController extends HttpServlet{
private OrderItemDAO orderItemDAO = new OrderItemDAO();
private OrderDAO orderDAO = new OrderDAO();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sOrder_id = req.getParameter("id");
		try {
			int order_id = Integer.parseInt(sOrder_id);
			//CategoryDAO categoryDAO = new CategoryDAO();
			Order order = orderDAO.getOrderById(order_id);
			System.out.println(order.getOrderId());
			System.out.println(order.getAddress());
			System.out.println(order.getShippingCost());
			req.setAttribute("order", order);
			req.getRequestDispatcher("/views/admin/update_orderitem.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String sOrder_id = req.getParameter("orderId");
		String status = req.getParameter("status");	
		try {
			int order_id = Integer.parseInt(sOrder_id);
			Order order = orderDAO.getOrderById(order_id);			
			//CategoryDAO categoryDAO = new CategoryDAO();			
			order.setOrderStatus(status);
			orderDAO.updateOrder(order);	
			resp.sendRedirect("listOrder");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
