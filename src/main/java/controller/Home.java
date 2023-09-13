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
import model.*;

@WebServlet(urlPatterns = {"/index"})
public class Home extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Product> products = new ArrayList<Product>();
		List<Category> categories = new ArrayList<Category>();
		ProductDAO productDAO = new ProductDAO();
		CategoryDAO categoryDAO = new CategoryDAO();
		categories =categoryDAO.getAll();
		products = productDAO.getAllProducts();
//		System.out.println("size cua products :"+products.size());
		req.setAttribute("categories", categories);
		req.setAttribute("products", products);		
		req.getRequestDispatcher("/views/client/index.jsp").forward(req, resp);
		
	}

}
