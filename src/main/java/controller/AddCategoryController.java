package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategoryDAO;
import model.Category;
@WebServlet(urlPatterns = {"/addCategory"})
public class AddCategoryController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("views/admin/add_category.jsp");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
//		String sCategory_id = req.getParameter("category_id");
		String category_name = req.getParameter("category_name");
		String sParentCategory = req.getParameter("parentCategory");
		int parentCategory = 0; // Giá trị mặc định
		if (sParentCategory != null && !sParentCategory.isEmpty()) {
		    parentCategory = Integer.parseInt(sParentCategory);
		}
//		req.getParameter("brand_id") != null ? Integer.parseInt(req.getParameter("brand_id")) : 0;
		Category prtCategory = new Category(parentCategory);
		try {
//			int category_id = Integer.parseInt(sCategory_id);
			Category category = new Category( category_name, prtCategory);
			CategoryDAO categoryDAO = new CategoryDAO();
			Category cateExists = null;
//					categoryDAO.getById(category_id);
			if(cateExists== null) {
				categoryDAO.insertCategory(category);
				resp.sendRedirect("listCategory");
			}else {
//				req.setAttribute("error", "Mã "+category_id + "đã tồn tại!");
//				req.getRequestDispatcher("/views/admin/add_category.jsp").forward(req, resp);
			}
//			categoryDAO.insertCategory(category);
//			resp.sendRedirect("listCategory");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
