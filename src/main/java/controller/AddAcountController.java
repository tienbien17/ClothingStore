package controller;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.AccountDAO;

import model.Account;

@WebServlet(urlPatterns = {"/addAccount"})
public class AddAcountController extends HttpServlet{
	AccountDAO accountDAO = new AccountDAO();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//List<Account> accounts = accountDAO.getAll();
		//req.setAttribute("accounts", accounts);
		req.getRequestDispatcher("/views/admin/add_account.jsp").forward(req, resp);

	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");

		String full_name = req.getParameter("full_name");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String address = req.getParameter("address");
		String phone_number = req.getParameter("phone_number");		
		String email = req.getParameter("email");	
		int role_code = req.getParameter("role_code") != null ? Integer.parseInt(req.getParameter("role_code")) : 0;		
		LocalDate startDate = LocalDate.now();
		
		try {
				
			Date created_date = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
			Account account = new Account(full_name, username, password, address, phone_number, null, email, role_code);
			accountDAO.insert(account);
			resp.sendRedirect("listAccount");
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
