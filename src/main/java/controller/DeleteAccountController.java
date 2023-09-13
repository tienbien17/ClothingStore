package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AccountDAO;
import model.Account;

@WebServlet(urlPatterns = {"/deleteAccount"})
public class DeleteAccountController extends HttpServlet{
	private AccountDAO accountDAO = new AccountDAO();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String user_id = req.getParameter("user_id");
		try {
			int id = Integer.parseInt(user_id);
			
			Account account = accountDAO.getUserById(id);
			if(account != null) {
				accountDAO.deleteUserById(id);
			}
			resp.sendRedirect("listAccount");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
