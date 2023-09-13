package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategoryDAO;
import dao.ProductDAO;
import model.Category;
import model.Product;

@WebServlet(urlPatterns = {"/deleteProduct"})
public class DeleteProductController extends HttpServlet{
	private ProductDAO productDAO = new ProductDAO();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sProduct_id = req.getParameter("product_id");
		try {
			int product_id = Integer.parseInt(sProduct_id);
			Product product = productDAO.getProductById(product_id);
			if(product != null) {
				productDAO.deleteProduct(product_id);
			}
			resp.sendRedirect("listProduct");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
