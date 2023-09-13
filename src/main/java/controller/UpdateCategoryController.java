package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategoryDAO;
import model.Category;

@WebServlet(urlPatterns = {"/updateCategory"})
public class UpdateCategoryController extends HttpServlet{
private CategoryDAO categoryDAO = new CategoryDAO();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sCategory_id = req.getParameter("category_id");
		try {
			int category_id = Integer.parseInt(sCategory_id);
			//CategoryDAO categoryDAO = new CategoryDAO();
			Category category = categoryDAO.getById(category_id);
			req.setAttribute("category", category);
			req.getRequestDispatcher("/views/admin/update_category.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String sCategory_id = req.getParameter("category_id");
		String category_name = req.getParameter("category_name");
		int parentCategory = Integer.parseInt( req.getParameter("parentCategory"));
		Category prtCategory = new Category(parentCategory);
		try {
			int category_id = Integer.parseInt(sCategory_id);
			//CategoryDAO categoryDAO = new CategoryDAO();
			//Category category = new Category(id, name, description);
			Category category = categoryDAO.getById(category_id);
			//CategoryDAO categoryDAO = new CategoryDAO();
			category.setCategoryName(category_name);
			category.setParentCategory(prtCategory);
			categoryDAO.updateCategory(category);
			resp.sendRedirect("listCategory");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
