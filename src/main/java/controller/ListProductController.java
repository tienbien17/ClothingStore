package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategoryDAO;
import dao.ProductDAO;
import model.Category;
import model.Product;

@WebServlet(urlPatterns = {"/listProduct"})
public class ListProductController extends HttpServlet{
	private ProductDAO productDAO;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		productDAO = new ProductDAO();
		List<Product> products = new ArrayList<Product>();
		products = productDAO.getAllProducts();
		req.setAttribute("products", products);
		req.getRequestDispatcher("/views/admin/list_product.jsp").forward(req, resp);
	}
}
