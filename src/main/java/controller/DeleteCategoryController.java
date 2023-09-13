package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategoryDAO;
import model.Category;
@WebServlet(urlPatterns = {"/deleteCategory"})
public class DeleteCategoryController extends HttpServlet{
	private CategoryDAO categoryDAO = new CategoryDAO();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sCategory_id = req.getParameter("category_id");
		try {
			int category_id = Integer.parseInt(sCategory_id);
			Category category = categoryDAO.getById(category_id);
			if(category != null) {
				categoryDAO.deleteCategory(category_id);
			}
			resp.sendRedirect("listCategory");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
