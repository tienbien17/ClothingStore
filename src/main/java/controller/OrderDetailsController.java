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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class OrderDetailsController
 */
@WebServlet("/order-details")
public class OrderDetailsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderDetailsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String orderIdString = request.getParameter("id");
        if (orderIdString == null || orderIdString.isEmpty()) {
            response.sendRedirect("error.jsp");
            return;
        }

        try {
            int orderId = Integer.parseInt(orderIdString);
            OrderDAO orderDAO = new OrderDAO();
            Order order = orderDAO.getOrderById(orderId);
            List<OrderItem> orderItems = orderDAO.getOrderItemsByOrderId(orderId);

            if (order != null && orderItems != null) {
                request.setAttribute("order", order);
                request.setAttribute("orderItems", orderItems);
                request.getSession().setAttribute("order", order);
                request.getSession().setAttribute("orderItems", orderItems);
                request.getRequestDispatcher("/views/client/order-details.jsp").forward(request, response);
            } else {
                response.sendRedirect("/views/client/error.jsp");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("/views/client/error.jsp");
        }
    }

}
