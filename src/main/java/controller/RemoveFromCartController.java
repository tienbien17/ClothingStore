package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cart;

/**
 * Servlet implementation class RemoveFromCartController
 */
@WebServlet("/remove-from-cart")
public class RemoveFromCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RemoveFromCartController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			int id = Integer.parseInt(request.getParameter("id"));
			ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("carts");
//			for (Cart c : cart_list) {			
//				if (c.getId() == id) {
//					cart_list.remove(cart_list.indexOf(c));
//					response.sendRedirect("cart");
//				}
//			}
			if (cart_list != null) {
				for (Cart c : cart_list) {
					if (c.getId() == (id)) {
						cart_list.remove(cart_list.indexOf(c));
						break;
					}
				}
			}
			response.sendRedirect("cart");
		}
	}
}
