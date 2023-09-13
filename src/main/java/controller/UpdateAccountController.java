package controller;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.*;
import dao.ProductDAO;
import model.*;
import model.Product;

@WebServlet(urlPatterns = {"/updateAccount"})
public class UpdateAccountController extends HttpServlet {
	private AccountDAO accountDAO = new AccountDAO();
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int user_id = req.getParameter("user_id") != null ? Integer.parseInt(req.getParameter("user_id")) : 0;
		try {			
			Account account = accountDAO.getUserById(user_id);
			req.setAttribute("account", account);
			req.getRequestDispatcher("/views/admin/update_account.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");                    
        int id = Integer.parseInt(req.getParameter("id"));
        String fullName = req.getParameter("fullName");
        String userName = req.getParameter("userName");
        String email = req.getParameter("email");
        String phoneNumber = req.getParameter("phoneNumber");
        String address = req.getParameter("address");
        String password = req.getParameter("password");
        boolean active = Boolean.parseBoolean(req.getParameter("active"));
        int userRole = Integer.parseInt(req.getParameter("userRole"));
        try {
        	Account account = new Account(); // Tạo đối tượng mới để đảm bảo không thay đổi dữ liệu trong CSDL trước khi cập nhật
            account.setId(id);
            account.setFullName(fullName);
            account.setUserName(userName);
            account.setEmail(email);
            account.setPhoneNumber(phoneNumber);
            account.setAddress(address);
            account.setPassword(password);
            account.setUserRoleCode(userRole);
            account.setActive(active);
            accountDAO.updateUserById(account);
            resp.sendRedirect("listAccount");
        } catch (Exception e) {
            e.printStackTrace();
        }   
        
	}
}
