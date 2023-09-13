package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountDAO;
import dao.ProductDAO;
import model.Account;
import model.Cart;

/**
 * Servlet implementation class LogInController
 */
@WebServlet("/log-in")
public class LogInController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		req.getRequestDispatcher("/views/client/log-in.jsp").forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		ProductDAO productDAO = new ProductDAO();
		AccountDAO accountDAO = new AccountDAO();
		Account account = accountDAO.userLogin(username, password);
		ArrayList<Cart> cart_list = new ArrayList<Cart>();
		int userId = -1;
		if (account != null) {
			HttpSession session = req.getSession();
			session.setAttribute("username", account);
			resp.sendRedirect("index");
			userId = account.getId();
			cart_list = (ArrayList<Cart>) productDAO.getCartByUserId(userId);
			System.out.println("user id :" + userId);
			session.setAttribute("carts", cart_list);
		} else {
			req.setAttribute("error", "Sai tên đăng nhập hoặc mật khẩu");
			req.getRequestDispatcher("/views/client/log-in.jsp").forward(req, resp);
		}
	}

}
