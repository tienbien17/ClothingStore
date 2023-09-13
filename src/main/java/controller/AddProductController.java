package controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpClient;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


import dao.CategoryDAO;
import dao.ProductDAO;
import model.Category;
import model.Product;

@WebServlet(urlPatterns = { "/addProduct" })
@MultipartConfig
public class AddProductController extends HttpServlet {
	ProductDAO productDAO = new ProductDAO();
	CategoryDAO categoryDAO = new CategoryDAO();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Product> products = productDAO.getAllProducts();
		List<Category> categories = categoryDAO.getAll();
		req.setAttribute("products", products);
		req.setAttribute("categories", categories);
		req.getRequestDispatcher("/views/admin/add_product.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");

		int brand_id = req.getParameter("brand_id") != null ? Integer.parseInt(req.getParameter("brand_id")) : 0;
		int categoryId = req.getParameter("category") != null ? Integer.parseInt(req.getParameter("category")) : 0;
		String product_name = req.getParameter("product_name");
		String description = req.getParameter("description");
		Double price = req.getParameter("price") != null ? Double.parseDouble(req.getParameter("price")) : 0;
		Double price_more = req.getParameter("price_more") != null ? Double.parseDouble(req.getParameter("price_more")) : 0;
		String size = req.getParameter("size") ;
		LocalDate startDate = LocalDate.now();
		Category category = new  Category(categoryId);
		int color_id = req.getParameter("color") != null ? Integer.parseInt(req.getParameter("color")) : 1;
		try {
			Part filePart = req.getPart("file"); // Retrieves <input type="file" name="file">
			String image_url = "/picture/"  + Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
			//InputStream fileContent = filePart.getInputStream();
			
			Date created_date = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String dateString = dateFormat.format(created_date);
			
			Product product = new Product(brand_id, product_name, description, price, price_more, image_url, size, dateString,category);
			
			productDAO.insertProductInToDatabase(product,categoryId,color_id);
			resp.sendRedirect("listProduct");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
