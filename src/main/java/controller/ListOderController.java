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
import model.Order;

@WebServlet(urlPatterns = {"/listOrder"})
public class ListOderController extends HttpServlet{
	private OrderDAO orderDAO;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		orderDAO = new OrderDAO();
		List<Order> orders = new ArrayList<Order>();
		orders = orderDAO.getAllOrders();
		req.setAttribute("orderList", orders);
		req.getRequestDispatcher("/views/admin/list_order.jsp").forward(req, resp);
	}

}
