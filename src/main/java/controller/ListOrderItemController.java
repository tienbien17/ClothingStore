package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderDAO;
import dao.OrderItemDAO;
import model.Order;
import model.OrderItem;
@WebServlet(urlPatterns = {"/listOrderItem"})
public class ListOrderItemController extends HttpServlet{
	private OrderItemDAO orderItemDAO;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		orderItemDAO = new OrderItemDAO();
		List<OrderItem> orderItem = new ArrayList<OrderItem>();
		orderItem = orderItemDAO.getAll();
		req.setAttribute("orderItem", orderItem);
		req.getRequestDispatcher("/views/admin/list_orderitem.jsp").forward(req, resp);
	}

}
