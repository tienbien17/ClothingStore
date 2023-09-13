package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDAO;
import model.*;

/**
 * Servlet implementation class DetailProductController
 */
@WebServlet("/product")
public class DetailProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailProductController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	resp.setContentType("text/html;charset=UTF-8");
    	int id = Integer.parseInt(req.getParameter("id"));
		Product product = new Product();
		ProductDAO productDAO = new ProductDAO();
		product = productDAO.getProductById(id);
		req.setAttribute("product", product);
		req.getRequestDispatcher("/views/client/product-details.jsp").forward(req, resp);
	}

}
