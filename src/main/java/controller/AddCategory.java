package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategoryDAO;
import model.Category;

@WebServlet(urlPatterns = {"/addCategory1"})
public class AddCategory extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("views/admin/add_category.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		String sID = req.getParameter("id");
		String name = req.getParameter("name");
//		String description = req.getParameter("description");
		try {
			int id = Integer.parseInt(sID);
			
			Category category = new Category();
			category.setCategoryId(id);
			category.setCategoryName(name);
			CategoryDAO categoryDAO = new CategoryDAO();
			Category cateExists = categoryDAO.getById(id);
			if(cateExists== null) {
				categoryDAO.insertCategory(category);
				resp.sendRedirect("listCategory");
			}else {
				req.setAttribute("error", "Mã "+id + "đã tồn tại!");
				req.getRequestDispatcher("/views/admin/add_category.jsp").forward(req, resp);
			}
			categoryDAO.insertCategory(category);
			resp.sendRedirect("listCategory");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
