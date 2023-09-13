package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AccountDAO;
import model.Account;

@WebServlet(urlPatterns = {"/listAccount"})
public class ListAccountController extends HttpServlet{
	private AccountDAO accountDAO;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		accountDAO = new AccountDAO();
		List<Account> accounts = new ArrayList<Account>();
		accounts = accountDAO.getAll();
		req.setAttribute("accounts", accounts);
		req.getRequestDispatcher("/views/admin/list_account.jsp").forward(req, resp);
	}	
}
