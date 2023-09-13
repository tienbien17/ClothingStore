package controller;

import java.io.IOException;
import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategoryDAO;
import model.Category;

@WebServlet(urlPatterns = {"/listCategory"})
public class ListCategoryController extends HttpServlet{
	private CategoryDAO categoryDAO;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		categoryDAO = new CategoryDAO();
		List<Category> categories = new ArrayList<Category>();
		categories = categoryDAO.getAll();
		req.setAttribute("categories", categories);
		req.getRequestDispatcher("/views/admin/list_category.jsp").forward(req, resp);
	}
}
