package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.*;
import model.*;

/**
 * Servlet implementation class ViewByCategoryController
 */
@WebServlet("/category")
public class ViewByCategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewByCategoryController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	resp.setContentType("text/html;charset=UTF-8");
    	int id = Integer.parseInt(req.getParameter("id"));
    	String name = (req.getParameter("name"));
		List<Product> products = new ArrayList<Product>();
		List<Category> categories = new ArrayList<>();
		Category category = new Category();
		ProductDAO productDAO = new ProductDAO();
		CategoryDAO categoryDAO = new CategoryDAO();
//		category = categoryDAO.getById(id);
		categories=categoryDAO.getAll();
		products = productDAO.getProductByCategory(id);
//		System.out.println("size cua products :"+products.size());
		for (Product product : products) {
			System.out.println("Product 11 ID: " + product.getId());
			System.out.println("Product  11Name: " + product.getName());
			System.out.println("Product11 Brand ID: " + product.getBrandId());
			System.out.println("Product 11Description: " + product.getDescription());
			System.out.println("Product11 Price: " + product.getPrice());
			System.out.println("Product 11Price More: " + product.getPriceMore());
			System.out.println("Product Promotion: " + product.getPromotion());
			System.out.println("Product Image URL: " + product.getImageUrl());
			System.out.println("Product Created At: " + product.getCreatedAt());
			System.out.println("Product Category: " + product.getCategory().getCategoryName());

			System.out
					.println("Product parent Category: " + product.getCategory().getParentCategory().getCategoryName());

			// Xuất danh sách kích thước (size) của sản phẩm
			System.out.println("Product Sizes:");
			for (String size : product.getSize()) {
				System.out.println("- " + size);
			}

			// Xuất danh sách màu sắc (color name) của sản phẩm
			System.out.println("Product11 Colors:");
			for (String color : product.getColors()) {
				System.out.println("- " + color);
			}

			// Xuất danh sách hình ảnh (color image) theo từng màu sắc của sản phẩm
			System.out.println("Product Color Images:");
			Map<String, String> colorImages = product.getColorImages();
			for (Map.Entry<String, String> entry : colorImages.entrySet()) {
				System.out.println("- Color: " + entry.getKey() + ", Image URL: " + entry.getValue());
			}

			// Xuất thông tin của danh mục (category) của sản phẩm
			Category category1 = product.getCategory();
			System.out.println("Product Category:");
			System.out.println("- Category Name: " + category.getCategoryName());

			Category parentCategory = category.getParentCategory();
			if (parentCategory != null) {
				System.out.println("- Parent Category Name: " + parentCategory.getCategoryName());
			} else {
				System.out.println("- Parent Category Name: No parent category");
			}

			System.out.println("----------------------------------------");
		}
		req.setAttribute("categories", categories);
		req.setAttribute("products", products);
		req.getRequestDispatcher("/views/client/index.jsp").forward(req, resp);
	}


}
