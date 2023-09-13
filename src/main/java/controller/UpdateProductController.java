package controller;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
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
@MultipartConfig
@WebServlet(urlPatterns = {"/updateProduct"})
public class UpdateProductController extends HttpServlet{
	private ProductDAO productDAO = new ProductDAO();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int product_id = req.getParameter("product_id") != null ? Integer.parseInt(req.getParameter("product_id")) : 0;
		try {			
			Product product = productDAO.getProductById(product_id);
			req.setAttribute("product", product);
			req.getRequestDispatcher("/views/admin/update_product.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");                    
        int product_id = Integer.parseInt(req.getParameter("product_id"));
        int brand_id = req.getParameter("brand_id") != null ? Integer.parseInt(req.getParameter("brand_id")) : 0;
        String product_name = req.getParameter("product_name");
        String description = req.getParameter("description");
        Double price = req.getParameter("price") != null ? Double.parseDouble(req.getParameter("price")) : 0;
        Double price_more = req.getParameter("price_more") != null ? Double.parseDouble(req.getParameter("price_more")) : 0;
        Double promotion = req.getParameter("promotion") != null ? Double.parseDouble(req.getParameter("promotion")) : 0;        
        String size = req.getParameter("size") ; 
        String filePart = req.getParameter("file"); // Retrieves <input type="file" name="file">
		String image_url = "/picture/"  + filePart; 
        try {
            Product product = new Product(); // Tạo đối tượng mới để đảm bảo không thay đổi dữ liệu trong CSDL trước khi cập nhật
            product.setId(product_id); // Đặt ID sản phẩm để xác định sản phẩm cần cập nhật
            product.setBrandId(brand_id);
            product.setName(product_name);
            product.setDescription(description);
            product.setPrice(price);
            product.setPriceMore(price_more);
            product.setPromotion(promotion);
            product.addSize(size);  
            product.setImageUrl(image_url);
            productDAO.updateInToDatabase(product);
            System.out.println(product_id);
            System.out.println(brand_id);
            System.out.println(product_name);
            System.out.println(description);
            System.out.println(price);
            System.out.println(image_url);
            resp.sendRedirect("listProduct");
        } catch (Exception e) {
            e.printStackTrace();
        }   
        
	}
}
