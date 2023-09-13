package controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AccountDAO;
import model.Account;

/**
 * Servlet implementation class RegisterController
 */
@WebServlet("/register")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static boolean isValidEmail(String email) {
        // Biểu thức chính quy để kiểm tra email
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher("/views/client/register.jsp").forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AccountDAO accountDAO = new AccountDAO();
		String fullName= req.getParameter("fullName");
		String username = req.getParameter("userName");
		String email= req.getParameter("email");
		String phoneNumber = req.getParameter("phoneNumber");
		String address= req.getParameter("address");
		String password = req.getParameter("password");
		String repassword= req.getParameter("repassword");
		// Lấy đối tượng LocalDateTime từ hệ thống
	    LocalDateTime currentDateTime = LocalDateTime.now();
	    
	    // Chuyển đổi đối tượng LocalDateTime thành chuỗi dạng ngày giờ
	    String dateString = currentDateTime.toString();    
		if(!password.contains(repassword)) {
			req.setAttribute("error", "Nhập Lại Mật Khẩu Không đúng");
			req.getRequestDispatcher("/views/client/register.jsp").forward(req, resp);
		}else if(!isValidEmail(email)){
			req.setAttribute("error", "Mail Không đúng định dạng");
			req.getRequestDispatcher("/views/client/register.jsp").forward(req, resp);
		}else if(username.length()<6||password.length()<6){
			req.setAttribute("error", "tài khoản hoặc mật khẩu phải lớn hơn hoặc bằng 6 ký tự ");
			req.getRequestDispatcher("/views/client/register.jsp").forward(req, resp);
		}else if(!accountDAO.checkValidUserName(username)) {
			req.setAttribute("error", "Tài khoản đã tồn tại ");
			req.getRequestDispatcher("/views/client/register.jsp").forward(req, resp);
		}else {
			Account account = new Account(fullName, username, email, phoneNumber, address, false, password, 1, dateString);
			accountDAO.saveAccount(account);
			resp.sendRedirect("log-in");
		}
	}

}
