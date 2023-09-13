package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class ProductDAO {
	// getAll
	// getById
	private static final String INSERT_PRODUCT = "INSERT INTO Products (brand_id, product_name, description, price, price_more, promotion, image_url, size, created_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_PRODUCT = "UPDATE Products SET brand_id = ?, product_name = ?, description = ?, price = ?, price_more = ?, promotion = ?, image_url = ?, size = ?, created_at = ? WHERE product_id = ?";

	public boolean insertProduct(Product product) {
		try (Connection conn = DBConnect.getConnection();
				PreparedStatement stmt = conn.prepareStatement(INSERT_PRODUCT)) {

			stmt.setInt(1, product.getBrandId());
			stmt.setString(2, product.getName());
			stmt.setString(3, product.getDescription());
			stmt.setDouble(4, product.getPrice());
			stmt.setDouble(5, product.getPriceMore());
			stmt.setDouble(6, product.getPromotion());
			stmt.setString(7, product.getImageUrl());
			// Convert Set<String> size to a comma-separated string
			stmt.setString(8, String.join(",", product.getSize()));
			stmt.setString(9, product.getCreatedAt());

			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateProduct(Product product) {
		try (Connection conn = DBConnect.getConnection();
				PreparedStatement stmt = conn.prepareStatement(UPDATE_PRODUCT)) {

			stmt.setInt(1, product.getBrandId());
			stmt.setString(2, product.getName());
			stmt.setString(3, product.getDescription());
			stmt.setDouble(4, product.getPrice());
			stmt.setDouble(5, product.getPriceMore());
			stmt.setDouble(6, product.getPromotion());
			stmt.setString(7, product.getImageUrl());
			stmt.setString(8, String.join(",", product.getSize()));
			stmt.setString(9, product.getCreatedAt());
			stmt.setInt(10, product.getId());

			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// insert
	public boolean insertProductInToDatabase1(Product product,int ctId) {
		boolean check = false;
		Connection conn = DBConnect.getConnection();
		String sql = "INSERT INTO Products (brand_id, product_name, description, price, price_more, image_url, size, created_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		String sqlProductId = "SELECT * FROM Products WHERE brand_id = ? AND product_name = ? AND description = ? AND price = ? AND price_more = ? AND image_url = ? AND size = ? AND created_at = ?;";
		String sqlCategory = "INSERT INTO ProductCategories (product_id, category_id) VALUES (?, ?);";


		int id = 0 ;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, product.getBrandId());
			ps.setString(2, product.getName());
			ps.setString(3, product.getDescription());
			ps.setDouble(4, product.getPrice());
			ps.setDouble(5, product.getPriceMore());
			ps.setString(6, product.getImage());
			ps.setString(7, product.getSize().iterator().next());
			ps.setString(8, (product.getCreatedAt()));

			int row = ps.executeUpdate();
			if (row > 0)
				check = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			PreparedStatement ps = conn.prepareStatement(sqlProductId);
			ps.setInt(1, product.getBrandId());
			ps.setString(2, product.getName());
			ps.setString(3, product.getDescription());
			ps.setDouble(4, product.getPrice());
			ps.setDouble(5, product.getPriceMore());
			ps.setString(6, product.getImage());
			ps.setString(7, product.getSize().iterator().next());
			ps.setString(8, (product.getCreatedAt()));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				id=rs.getInt("product_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			PreparedStatement ps = conn.prepareStatement(sqlCategory);
			ps.setInt(1, id);
			ps.setInt(2, ctId);
			

			int row = ps.executeUpdate();
			if (row > 0)
				check = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return check;
	}
	public void insertProductInToDatabase(Product product, int categoryId,int colorId) {
	    Connection conn = DBConnect.getConnection();
	    String sql = "INSERT INTO Products (brand_id, product_name, description, price, price_more, image_url, size, created_at) "
	            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	    try {
	        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        ps.setInt(1, product.getBrandId());
	        ps.setString(2, product.getName());
	        ps.setString(3, product.getDescription());
	        ps.setDouble(4, product.getPrice());
	        ps.setDouble(5, product.getPriceMore());
	        ps.setString(6, product.getImageUrl());
	        ps.setString(7, product.getsize1());
	        ps.setString(8, product.getCreatedAt());
	        ps.executeUpdate();

	        // Get the auto-generated product_id
	        ResultSet generatedKeys = ps.getGeneratedKeys();
	        if (generatedKeys.next()) {
	            int productId = generatedKeys.getInt(1);

	            // Insert into ProductCategories table
	            String insertCategorySql = "INSERT INTO ProductCategories (product_id, category_id) VALUES (?, ?)";
	            PreparedStatement psCategory = conn.prepareStatement(insertCategorySql);
	            psCategory.setInt(1, productId);
	            psCategory.setInt(2, categoryId);
	            psCategory.executeUpdate();
	            String insertColorSql = "INSERT INTO productcolors (product_id, colors_id) VALUES (?, ?)";
	            psCategory = conn.prepareStatement(insertColorSql);
	            psCategory.setInt(1, productId);
	            psCategory.setInt(2, colorId);
	            psCategory.executeUpdate();
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } 
	}

	// update
	public boolean updateInToDatabase(Product product) {
		boolean check = false;
		Connection conn = DBConnect.getConnection();
		String sql = "UPDATE `clothingshop1`.`products` SET `brand_id` = ?, `product_name` = ?, `description` = ?, `price` = ?, `price_more` = ?, `promotion` = ?, `image_url` = ?, `size` = ? WHERE (`product_id` = ?)";


		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			System.out.println("test:"+ product.getName());
			System.out.println("test:"+product.getBrandId());
			ps.setInt(1, product.getBrandId());
			ps.setString(2, product.getName());
			ps.setString(3, product.getDescription());
			ps.setDouble(4, product.getPrice());
			ps.setDouble(5, product.getPriceMore());
			ps.setDouble(6, product.getPromotion());
			 ps.setString(7, product.getImageUrl());
			ps.setString(8, product.getSize().iterator().next());
			System.out.println("debug ham update "+product.getImageUrl());
			// ps.setDate(9, new Date(product.getCreated_at().getTime()));
			ps.setInt(9, product.getId());
			int row = ps.executeUpdate();
			if (row > 0)
				check = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return check;
	}
	public boolean updateInToDatabase1(Product product) {
	    boolean check = false;
	    Connection conn = DBConnect.getConnection();
	    String sql = "UPDATE `clothingshop1`.`products` SET `brand_id` = ?, `product_name` = ?, `description` = ?, `price` = ?, `price_more` = ?, `promotion` = ?, `image_url` = ?, `size` = ? WHERE (`product_id` = ?)";

	    try {
	        PreparedStatement ps = conn.prepareStatement(sql);
	        System.out.println("test:" + product.getName());
	        System.out.println("test:" + product.getBrandId());

	        ps.setInt(1, product.getBrandId());
	        ps.setString(2, product.getName());
	        ps.setString(3, product.getDescription());
	        ps.setDouble(4, product.getPrice());
	        ps.setDouble(5, product.getPriceMore());
	        ps.setDouble(6, product.getPromotion());
	        ps.setString(7, product.getImageUrl());

	        // Giả sử product.getSize() trả về giá trị hợp lệ
	        ps.setString(8, product.getsize1());

	        ps.setInt(9, product.getId());

	        int row = ps.executeUpdate();
	        if (row > 0)
	            check = true;
	    } catch (SQLException e) {
	        e.printStackTrace(); // Xử lý lỗi này một cách tinh vi hơn trong mã thực tế của bạn
	    } finally {
	        // Đóng kết nối trong khối finally để đảm bảo nó được đóng
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace(); // Xử lý lỗi này một cách tinh vi hơn trong mã thực tế của bạn
	            }
	        }
	    }
	    return check;
	}


	// delete
	public void deleteProduct(int product_id) {
	    Connection conn = DBConnect.getConnection();
	    String deleteProductCategoriesSQL = "DELETE FROM ProductCategories WHERE product_id=?";
	    String deleteProductColorsSQL = "DELETE FROM ProductColors WHERE product_id=?";
	    String deleteProductImagesSQL = "DELETE FROM ProductsImages WHERE product_id=?";
	    String deleteProductSQL = "DELETE FROM Products WHERE product_id=?";
	    
	    try {
	        // Delete from dependent tables first
	        PreparedStatement deleteProductCategoriesStatement = conn.prepareStatement(deleteProductCategoriesSQL);
	        deleteProductCategoriesStatement.setInt(1, product_id);
	        deleteProductCategoriesStatement.executeUpdate();

	        PreparedStatement deleteProductColorsStatement = conn.prepareStatement(deleteProductColorsSQL);
	        deleteProductColorsStatement.setInt(1, product_id);
	        deleteProductColorsStatement.executeUpdate();

	        PreparedStatement deleteProductImagesStatement = conn.prepareStatement(deleteProductImagesSQL);
	        deleteProductImagesStatement.setInt(1, product_id);
	        deleteProductImagesStatement.executeUpdate();

	        // Then delete from the main table
	        PreparedStatement deleteProductStatement = conn.prepareStatement(deleteProductSQL);
	        deleteProductStatement.setInt(1, product_id);
	        deleteProductStatement.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


	// add Product

	public List<Product> getNewProducts() {
		List<Product> newProducts = new ArrayList<Product>();
		Connection conn = DBConnect.getConnection();
		String sql = "select * from product order by createdate desc limit 3";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt(1));
				product.setName(rs.getString(2));
				product.setCreateDate(rs.getString(3));
				product.setImage(rs.getString(4));
				product.setMoreImage(rs.getString(5));
				product.setType(rs.getString(6));
				product.setPrice(rs.getDouble(7));
				product.setPriceMore(rs.getDouble(8));
				product.setPromotion(rs.getDouble(9));
				product.setDescription(rs.getString(10));
				Category ct = new Category();
				Category prct = new Category();
				ct.setCategoryId(rs.getInt("category_id"));
				ct.setCategoryName(rs.getString("category"));
				prct.setCategoryName(rs.getString("parentcategory"));
				ct.setParentCategory(prct);
				product.setCategory(ct);
				newProducts.add(product);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newProducts;
	}

	public List<Product> getAllProducts() {
		List<Product> allProducts = new ArrayList<Product>();
		Connection conn = DBConnect.getConnection();
//			String sql = "SELECT p.*, c.name, c.description AS categoryDescription \r\n"
//					+ "FROM product p\r\n"
//					+ "INNER JOIN category c ON p.categoryId = c.id";
		String sql = "SELECT p.product_id AS 'product id', p.brand_id AS 'brand id', p.product_name AS 'product', "
				+ "p.description AS 'description', p.price AS 'price', p.price_more AS 'price more', p.promotion AS 'promotion', "
				+ "p.image_url AS 'image url',c2.category_id AS 'category_id', p.size AS 'size', p.created_at AS 'created at', c.colors_name AS 'color name', "
				+ "pi.image_url AS 'color image', c2.category_name AS 'category', parentCat.category_name AS 'parentcategory' "
				+ "FROM Products p " + "LEFT JOIN Brands b ON p.brand_id = b.brand_id "
				+ "LEFT JOIN ProductColors pc ON p.product_id = pc.product_id "
				+ "LEFT JOIN Colors c ON pc.colors_id = c.colors_id "
				+ "LEFT JOIN ProductsImages pi ON p.product_id = pi.product_id AND pc.colors_id = pi.colors_id "
				+ "LEFT JOIN ProductCategories pc2 ON p.product_id = pc2.product_id "
				+ "LEFT JOIN Categories c2 ON pc2.category_id = c2.category_id "
				+ "LEFT JOIN Categories parentCat ON c2.parentCategory = parentCat.category_id";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			Map<Integer, Product> productMap = new HashMap<>();
			while (rs.next()) {
				int productId = rs.getInt("product id");

				// Nếu sản phẩm chưa tồn tại trong productMap, tạo mới và thêm vào map
				if (!productMap.containsKey(productId)) {
					Product product = new Product();
					product.setId(rs.getInt("product id"));
					product.setName(rs.getString("product"));
					product.setBrandId(rs.getInt("brand id"));
					product.setDescription(rs.getString("description"));
					product.setPrice(rs.getDouble("price"));
					product.setPriceMore(rs.getDouble("price more"));
					product.setPromotion(rs.getDouble("promotion"));
					product.setImageUrl(rs.getString("image url"));
					product.setCreatedAt(rs.getString("created at"));
					productMap.put(productId, product);
				}
				;
				Product product = productMap.get(productId);
				product.addSize(rs.getString("size"));
				product.addColor(rs.getString("color name"));
				product.addColorImage(rs.getString("color name"), rs.getString("color image"));
				Category ct = new Category();
				Category prct = new Category();
				ct.setCategoryId(rs.getInt("category_id"));
				ct.setCategoryName(rs.getString("category"));
				prct.setCategoryName(rs.getString("parentcategory"));
				ct.setParentCategory(prct);
				product.setCategory(ct);
			}
			allProducts.addAll(productMap.values());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allProducts;
	}

	public List<Product> getProductByCategory(int id) {
		List<Product> allProducts = new ArrayList<Product>();
		Connection conn = DBConnect.getConnection();
		String sql = "";
		String sql0 = "SELECT * FROM clothingshop1.categories where category_id = ? ; ";
		int prid = 0;
		try {
			PreparedStatement ps1 = conn.prepareStatement(sql0);
			ps1.setInt(1, id);
			ResultSet rs = ps1.executeQuery();

			while (rs.next()) {
				prid = rs.getInt("parentCategory");
			}
			if (prid != 0) {
				sql = "SELECT p.product_id AS 'product id', p.brand_id AS 'brand id', p.product_name AS 'product', "
						+ "p.description AS 'description', p.price AS 'price', p.price_more AS 'price more', p.promotion AS 'promotion', "
						+ "p.image_url AS 'image url', p.size AS 'size', p.created_at AS 'created at', c.colors_name AS 'color name', "
						+ "pi.image_url AS 'color image', c2.category_name AS 'category', parentCat.category_name AS 'parentcategory',c2.category_id AS 'category_id' "
						+ "FROM Products p " + "LEFT JOIN Brands b ON p.brand_id = b.brand_id "
						+ "LEFT JOIN ProductColors pc ON p.product_id = pc.product_id "
						+ "LEFT JOIN Colors c ON pc.colors_id = c.colors_id "
						+ "LEFT JOIN ProductsImages pi ON p.product_id = pi.product_id AND pc.colors_id = pi.colors_id "
						+ "LEFT JOIN ProductCategories pc2 ON p.product_id = pc2.product_id "
						+ "LEFT JOIN Categories c2 ON pc2.category_id = c2.category_id "
						+ "LEFT JOIN Categories parentCat ON c2.parentCategory = parentCat.category_id "
						+ "where  c2.category_id = ? ;";
			} else {
				sql = "SELECT\r\n" + "    p.product_id AS 'product id',\r\n" + "    p.brand_id AS 'brand id',\r\n"
						+ "    p.product_name AS 'product',\r\n" + "    p.description AS 'description',\r\n"
						+ "    p.price AS 'price',\r\n" + "    p.price_more AS 'price more',\r\n"
						+ "    p.promotion AS 'promotion',\r\n" + "    p.image_url AS 'image url',\r\n"
						+ "    p.size AS 'size',\r\n" + "    p.created_at AS 'created at',\r\n"
						+ "    c.colors_name AS 'color name',\r\n" + "    pi.image_url AS 'color image',\r\n"
						+ "    c2.category_name AS 'category',\r\n" + "    c2.category_id AS 'category_id',\r\n"
						+ "    parentCat.category_name AS 'parentcategory'\r\n" + "FROM\r\n" + "    Products p\r\n"
						+ "    LEFT JOIN Brands b ON p.brand_id = b.brand_id\r\n"
						+ "    LEFT JOIN ProductColors pc ON p.product_id = pc.product_id\r\n"
						+ "    LEFT JOIN Colors c ON pc.colors_id = c.colors_id\r\n"
						+ "    LEFT JOIN ProductsImages pi ON p.product_id = pi.product_id AND pc.colors_id = pi.colors_id\r\n"
						+ "    LEFT JOIN ProductCategories pc2 ON p.product_id = pc2.product_id\r\n"
						+ "    LEFT JOIN Categories c2 ON pc2.category_id = c2.category_id\r\n"
						+ "    LEFT JOIN Categories parentCat ON c2.parentCategory = parentCat.category_id\r\n"
						+ "WHERE\r\n" + "    parentCat.category_id = ?;";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			Map<Integer, Product> productMap = new HashMap<>();
			while (rs.next()) {
				int productId = rs.getInt("product id");

				// Nếu sản phẩm chưa tồn tại trong productMap, tạo mới và thêm vào map
				if (!productMap.containsKey(productId)) {
					Product product = new Product();
					product.setId(rs.getInt("product id"));
					product.setName(rs.getString("product"));
					product.setBrandId(rs.getInt("brand id"));
					product.setDescription(rs.getString("description"));
					product.setPrice(rs.getDouble("price"));
					product.setPriceMore(rs.getDouble("price more"));
					product.setPromotion(rs.getDouble("promotion"));
					product.setImageUrl(rs.getString("image url"));
					product.setCreatedAt(rs.getString("created at"));

					productMap.put(productId, product);

				}
				Product product = productMap.get(productId);
				product.addSize(rs.getString("size"));
				product.addColor(rs.getString("color name"));
				product.addColorImage(rs.getString("color name"), rs.getString("color image"));
				Category ct = new Category();
				Category prct = new Category();
				ct.setCategoryName(rs.getString("category"));
				prct.setCategoryName(rs.getString("parentcategory"));
				ct.setParentCategory(prct);
				product.setCategory(ct);
			}
			allProducts.addAll(productMap.values());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allProducts;
	}

	public List<Cart> getCartProducts(ArrayList<Cart> cartlist) {
		List<Cart> products = new ArrayList<Cart>();
		Set<Integer> addedProductIds = new HashSet<>(); // Set để kiểm tra sản phẩm đã được thêm vào danh sách hay chưa
		Connection conn = DBConnect.getConnection();
		try {
			for (Cart item : cartlist) {
				String sql = "SELECT p.product_id AS 'product id', p.brand_id AS 'brand id', p.product_name AS 'product', "
						+ "p.description AS 'description', p.price AS 'price', p.price_more AS 'price more', p.promotion AS 'promotion', "
						+ "p.image_url AS 'image url', p.size AS 'size', p.created_at AS 'created at', c.colors_name AS 'color name', "
						+ "pi.image_url AS 'color image', c2.category_name AS 'category', parentCat.category_name AS 'parentcategory' "
						+ "FROM Products p " + "LEFT JOIN Brands b ON p.brand_id = b.brand_id "
						+ "LEFT JOIN ProductColors pc ON p.product_id = pc.product_id "
						+ "LEFT JOIN Colors c ON pc.colors_id = c.colors_id "
						+ "LEFT JOIN ProductsImages pi ON p.product_id = pi.product_id AND pc.colors_id = pi.colors_id "
						+ "LEFT JOIN ProductCategories pc2 ON p.product_id = pc2.product_id "
						+ "LEFT JOIN Categories c2 ON pc2.category_id = c2.category_id "
						+ "LEFT JOIN Categories parentCat ON c2.parentCategory = parentCat.category_id "
						+ "WHERE p.product_id = ? && c.colors_name = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, item.getId());
				String color = item.getColors().iterator().next();
				ps.setString(2, color);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					int productId = rs.getInt("product id");
					if (!addedProductIds.contains(productId)) {
						Cart cart = new Cart();
						cart.setId(productId);
						cart.setName(rs.getString("product"));
						cart.setCreatedAt(rs.getString("created at"));
						cart.setImageUrl(rs.getString("image url"));
						cart.setPrice(rs.getDouble("price more") * item.getQuantity());
						cart.setPriceMore(rs.getDouble("price more"));
						cart.setPromotion(rs.getDouble("promotion"));
						cart.setDescription(rs.getString("description"));
						cart.addSize(rs.getString("size"));
						cart.addColor(rs.getString("color name"));
						cart.addColorImage(rs.getString("color name"), rs.getString("color image"));
						cart.setQuantity(item.getQuantity());
						String category = rs.getString("category");
						String parentCategory = rs.getString("parentcategory");
						Category ct = new Category();
						ct.setCategoryName(category);
						Category prct = new Category();
						prct.setCategoryName(parentCategory);
						ct.setParentCategory(prct);
						cart.setCategory(ct);
						products.add(cart);
						addedProductIds.add(productId); // Đánh dấu sản phẩm đã được thêm vào danh sách
					} else {
						// Sản phẩm đã tồn tại trong danh sách, chỉ cập nhật số lượng
						for (Cart existingCart : products) {
							if (existingCart.getId() == productId) {
								existingCart.setQuantity(existingCart.getQuantity() + item.getQuantity());
								break;
							}
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return products;
	}

	public List<Cart> getCartByUserId(int id) {
		List<Cart> carts = new ArrayList<Cart>();
		Connection conn = DBConnect.getConnection();
		String sql = "SELECT \r\n" + "    u.user_id AS 'user id',\r\n"
				+ "    sc.shopping_cart_id AS 'shopping cart id',\r\n" + "    scp.product_id AS 'product id',\r\n"
				+ "    p.product_name AS 'product name',\r\n" + "    p.price AS 'price',\r\n"
				+ "    c.colors_name AS 'color name',\r\n" + "    scp.quantity AS 'quantity product'\r\n" + "FROM\r\n"
				+ "    Users u\r\n" + "    JOIN ShoppingCart sc ON u.user_id = sc.user_id\r\n"
				+ "    JOIN ShoppingCartProduct scp ON sc.shopping_cart_id = scp.shopping_cart_id\r\n"
				+ "    JOIN Products p ON scp.product_id = p.product_id\r\n"
				+ "    JOIN ProductColors pc ON p.product_id = pc.product_id\r\n"
				+ "    JOIN Colors c ON pc.colors_id = c.colors_id\r\n" + "WHERE\r\n" + "    u.user_id = ?;";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Cart cart = new Cart();
				int pId = rs.getInt("product id");
				cart.setId(pId);
				String color = rs.getString("color name");
				cart.addColor(color);
				int quantity = rs.getInt("quantity product");
				cart.setQuantity(quantity);
				carts.add(cart);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return getCartProducts((ArrayList<Cart>) carts);
	}

	public Product getProductById(int id) {
		Product prd = new Product();
		Connection conn = DBConnect.getConnection();
		String sql = "SELECT p.product_id AS 'product id', p.brand_id AS 'brand id', p.product_name AS 'product', "
				+ "p.description AS 'description', p.price AS 'price', p.price_more AS 'price more', p.promotion AS 'promotion', "
				+ "p.image_url AS 'image url', p.size AS 'size', p.created_at AS 'created at', c.colors_name AS 'color name', "
				+ "pi.image_url AS 'color image', c2.category_id AS 'category_id', c2.category_name AS 'category', parentCat.category_name AS 'parentcategory' "
				+ "FROM Products p " + "LEFT JOIN Brands b ON p.brand_id = b.brand_id "
				+ "LEFT JOIN ProductColors pc ON p.product_id = pc.product_id "
				+ "LEFT JOIN Colors c ON pc.colors_id = c.colors_id "
				+ "LEFT JOIN ProductsImages pi ON p.product_id = pi.product_id AND pc.colors_id = pi.colors_id "
				+ "LEFT JOIN ProductCategories pc2 ON p.product_id = pc2.product_id "
				+ "LEFT JOIN Categories c2 ON pc2.category_id = c2.category_id "
				+ "LEFT JOIN Categories parentCat ON c2.parentCategory = parentCat.category_id "
				+ "where  p.product_id =? ;";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			Map<Integer, Product> productMap = new HashMap<>();
			while (rs.next()) {
				int productId = rs.getInt("product id");

				// Nếu sản phẩm chưa tồn tại trong productMap, tạo mới và thêm vào map
				if (!productMap.containsKey(productId)) {
					Product product = new Product();
					product.setId(rs.getInt("product id"));
					product.setName(rs.getString("product"));
					product.setBrandId(rs.getInt("brand id"));
					product.setDescription(rs.getString("description"));
					product.setPrice(rs.getDouble("price"));
					product.setPriceMore(rs.getDouble("price more"));
					product.setPromotion(rs.getDouble("promotion"));
					product.setImageUrl(rs.getString("image url"));
					product.setCreatedAt(rs.getString("created at"));
					productMap.put(productId, product);
				}
				;
				Product product = productMap.get(productId);
				product.addSize(rs.getString("size"));
				product.addColor(rs.getString("color name"));
				product.addColorImage(rs.getString("color name"), rs.getString("color image"));
				Category ct = new Category();
				Category prct = new Category();
				ct.setCategoryId(rs.getInt("category_id"));
				ct.setCategoryName(rs.getString("category"));
				prct.setCategoryName(rs.getString("parentcategory"));
				ct.setParentCategory(prct);
				product.setCategory(ct);
				prd = product;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prd;
	}

	public double getTotalPrice(ArrayList<Cart> cartlist) {
		System.out.println("---------------------");
		System.out.println("cart list zise:" + cartlist.size());
		double sum = 0;
		Connection conn = DBConnect.getConnection();
		try {
			if (cartlist.size() > 0) {
				for (Cart cart : cartlist) {
					System.out.println("report");
					System.out.println("Id gio hang :" + cart.getId());
					String sql = "SELECT p.product_id AS 'product id', p.brand_id AS 'brand id', p.product_name AS 'product', "
							+ "p.description AS 'description', p.price AS 'price', p.price_more AS 'price more', p.promotion AS 'promotion', "
							+ "p.image_url AS 'image url', p.size AS 'size', p.created_at AS 'created at', c.colors_name AS 'color name', "
							+ "pi.image_url AS 'color image', c2.category_name AS 'category', parentCat.category_name AS 'parentcategory' "
							+ "FROM Products p " + "LEFT JOIN Brands b ON p.brand_id = b.brand_id "
							+ "LEFT JOIN ProductColors pc ON p.product_id = pc.product_id "
							+ "LEFT JOIN Colors c ON pc.colors_id = c.colors_id "
							+ "LEFT JOIN ProductsImages pi ON p.product_id = pi.product_id AND pc.colors_id = pi.colors_id "
							+ "LEFT JOIN ProductCategories pc2 ON p.product_id = pc2.product_id "
							+ "LEFT JOIN Categories c2 ON pc2.category_id = c2.category_id "
							+ "LEFT JOIN Categories parentCat ON c2.parentCategory = parentCat.category_id "
							+ "where  p.product_id =? &&  c.colors_name= ?;";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setInt(1, cart.getId());
					String color = cart.getColors().iterator().next();
					ps.setString(2, color);
//						System.out.println("get id thanh cong");
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
//						    System.out.println("gia cua san pham: " + rs.getDouble("price"));
						sum += rs.getDouble("price more") * cart.getQuantity();
//						    System.out.println(sum);
					}
					System.out.println(sum);
				}

			} else
				return 0;
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("sum = " + sum);
		return sum;
	}

//	public void updateCartToDatabase(int userId, List<Cart> cartList) {
//		Connection conn = null;
//		PreparedStatement ps = null;
//		try {
//			conn = DBConnect.getConnection();
//			String cartId = "SELECT shopping_cart_id FROM clothingshop2.shoppingcart where user_id=?;";
//			ps = conn.prepareStatement(cartId);
//			ps.setInt(1, userId);
//			ResultSet rs = ps.executeQuery();
//			int shoppingCartId = -1;
//			while (rs.next()) {
//				shoppingCartId = rs.getInt("shopping_cart_id");
//			}
//			String deleteCartSql = "DELETE FROM ShoppingCart WHERE user_id = ?";
//			String insertCartSql = "INSERT INTO ShoppingCartProduct (shopping_cart_id, product_id, colors_id, quantity) VALUES (?, ?, ?, ?)";
//
//			// Xóa toàn bộ giỏ hàng của người dùng trước khi cập nhật lại
//			ps = conn.prepareStatement(deleteCartSql);
//			ps.setInt(1, userId);
//			ps.executeUpdate();
//			ps.close();
//
//			// Thêm mới các mục trong giỏ hàng vào cơ sở dữ liệu
//
//			for (Cart cart : cartList) {
//				String colorIdsql = "SELECT colors_id FROM clothingshop2.colors where colors_name=?;";
//				ps = conn.prepareStatement(colorIdsql);
//				ps.setString(1, cart.getColors().iterator().next());
//				rs = ps.executeQuery();
//				int colorId=-1;
//				while (rs.next()) {
//					 colorId = rs.getInt("colors_id");
//				}
//				ps = conn.prepareStatement(insertCartSql);
//				ps.setInt(1, shoppingCartId);
//				ps.setInt(2, cart.getId());
//				ps.setInt(3, colorId); // Lấy màu đầu tiên trong danh sách màu
//				ps.setInt(4, cart.getQuantity());
//				ps.executeUpdate();
//				System.out.println("update thanh cong");
//			}
//			ps.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (ps != null) {
//					ps.close();
//				}
//				if (conn != null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//
//	}
	public void updateCartToDatabase1(int userId, List<Cart> cartList) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBConnect.getConnection();

			// Kiểm tra xem giỏ hàng của người dùng đã tồn tại hay chưa
			String cartIdQuery = "SELECT shopping_cart_id FROM clothingshop1.shoppingcart WHERE user_id = ?";
			ps = conn.prepareStatement(cartIdQuery);
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			int shoppingCartId = -1;
			if (rs.next()) {
				// Giỏ hàng của người dùng đã tồn tại
				shoppingCartId = rs.getInt("shopping_cart_id");
			} else {
				// Giỏ hàng của người dùng chưa tồn tại, tạo mới giỏ hàng
				String createCartQuery = "INSERT INTO ShoppingCart (user_id) VALUES (?)";
				ps = conn.prepareStatement(createCartQuery, Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, userId);
				ps.executeUpdate();
				ResultSet generatedKeys = ps.getGeneratedKeys();
				if (generatedKeys.next()) {
					shoppingCartId = generatedKeys.getInt(1);
				}
			}

			// Xóa toàn bộ giỏ hàng của người dùng trước khi cập nhật lại
			String deleteCartProductSql = "DELETE FROM shoppingcartproduct WHERE shopping_cart_id = ?";
			String deleteCartSql = "DELETE FROM shoppingcart WHERE shopping_cart_id = ?";
			ps = conn.prepareStatement(deleteCartProductSql);
			ps.setInt(1, shoppingCartId);
			ps.executeUpdate();

			ps = conn.prepareStatement(deleteCartSql);
			ps.setInt(1, shoppingCartId);
			ps.executeUpdate();
			String createCartQuery = "INSERT INTO ShoppingCart (user_id) VALUES (?)";
			ps = conn.prepareStatement(createCartQuery, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, userId);
			ps.executeUpdate();
			ResultSet generatedKeys = ps.getGeneratedKeys();
			if (generatedKeys.next()) {
				shoppingCartId = generatedKeys.getInt(1);
			}
			// Thêm mới các mục trong giỏ hàng vào cơ sở dữ liệu
			String insertCartSql = "INSERT INTO ShoppingCartProduct (shopping_cart_id, product_id, colors_id, quantity) VALUES (?, ?, ?, 1)";
//	        Cart cart = cartList.get(cartList.size()-1);
			for (Cart cart : cartList) {
				String colorIdsql = "SELECT colors_id FROM clothingshop1.colors WHERE colors_name = ?";
				ps = conn.prepareStatement(colorIdsql);
				ps.setString(1, cart.getColors().iterator().next());
				rs = ps.executeQuery();
				int colorId = -1;
				if (rs.next()) {
					colorId = rs.getInt("colors_id");
				}
				ps = conn.prepareStatement(insertCartSql);
				ps.setInt(1, shoppingCartId);
				ps.setInt(2, cart.getId());
				ps.setInt(3, colorId); // Lấy màu đầu tiên trong danh sách màu
				ps.setInt(4, cart.getQuantity());
				ps.executeUpdate();
			}

			System.out.println("Update giỏ hàng thành công");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void updateCartToDatabase2(int userId, List<Cart> cartList) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBConnect.getConnection();

			// Kiểm tra xem giỏ hàng của người dùng đã tồn tại hay chưa
			String cartIdQuery = "SELECT shopping_cart_id FROM clothingshop1.shoppingcart WHERE user_id = ?";
			ps = conn.prepareStatement(cartIdQuery);
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			int shoppingCartId = -1;
			if (rs.next()) {
				// Giỏ hàng của người dùng đã tồn tại
				shoppingCartId = rs.getInt("shopping_cart_id");
			} else {
				// Giỏ hàng của người dùng chưa tồn tại, tạo mới giỏ hàng
				String createCartQuery = "INSERT INTO ShoppingCart (user_id) VALUES (?)";
				ps = conn.prepareStatement(createCartQuery, Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, userId);
				ps.executeUpdate();
				ResultSet generatedKeys = ps.getGeneratedKeys();
				if (generatedKeys.next()) {
					shoppingCartId = generatedKeys.getInt(1);
				}
			}

			// Xóa toàn bộ giỏ hàng của người dùng trước khi cập nhật lại
			String deleteCartProductSql = "DELETE FROM shoppingcartproduct WHERE shopping_cart_id = ?";
			ps = conn.prepareStatement(deleteCartProductSql);
			ps.setInt(1, shoppingCartId);
			ps.executeUpdate();

			// Thêm mới các mục trong giỏ hàng vào cơ sở dữ liệu
			String insertCartSql = "INSERT INTO ShoppingCartProduct (shopping_cart_id, product_id, colors_id, quantity) VALUES (?, ?, ?, 1)";
			for (Cart cart : cartList) {
				String colorIdsql = "SELECT colors_id FROM clothingshop1.colors WHERE colors_name = ?";
				ps = conn.prepareStatement(colorIdsql);
				ps.setString(1, cart.getColors().iterator().next());
				rs = ps.executeQuery();
				int colorId = -1;
				if (rs.next()) {
					colorId = rs.getInt("colors_id");
				}
				ps = conn.prepareStatement(insertCartSql);
				ps.setInt(1, shoppingCartId);
				ps.setInt(2, cart.getId());
				ps.setInt(3, colorId); // Lấy màu đầu tiên trong danh sách màu
//            ps.setInt(4, cart.getQuantity());
				ps.executeUpdate();
			}

			System.out.println("Update giỏ hàng thành công");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void updateCartToDatabase3(int userId, List<Cart> cartList) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBConnect.getConnection();

			// Kiểm tra xem giỏ hàng của người dùng đã tồn tại hay chưa
			String cartIdQuery = "SELECT shopping_cart_id FROM clothingshop1.shoppingcart WHERE user_id = ?";
			ps = conn.prepareStatement(cartIdQuery);
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			int shoppingCartId = -1;
			if (rs.next()) {
				// Giỏ hàng của người dùng đã tồn tại
				shoppingCartId = rs.getInt("shopping_cart_id");
			} else {
				// Giỏ hàng của người dùng chưa tồn tại, tạo mới giỏ hàng
				String createCartQuery = "INSERT INTO ShoppingCart (user_id) VALUES (?)";
				ps = conn.prepareStatement(createCartQuery, Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, userId);
				ps.executeUpdate();
				ResultSet generatedKeys = ps.getGeneratedKeys();
				if (generatedKeys.next()) {
					shoppingCartId = generatedKeys.getInt(1);
				}
			}

			// Thêm mới các mục trong giỏ hàng vào cơ sở dữ liệu
			String insertCartSql = "INSERT INTO ShoppingCartProduct (shopping_cart_id, product_id, colors_id, quantity) VALUES (?, ?, ?, ?) "
					+ "ON DUPLICATE KEY UPDATE quantity = quantity + VALUES(quantity)";
			for (Cart cart : cartList) {
				String colorIdsql = "SELECT colors_id FROM clothingshop1.colors WHERE colors_name = ?";
				ps = conn.prepareStatement(colorIdsql);
				ps.setString(1, cart.getColors().iterator().next());
				rs = ps.executeQuery();
				int colorId = -1;
				if (rs.next()) {
					colorId = rs.getInt("colors_id");
				}
				ps = conn.prepareStatement(insertCartSql);
				ps.setInt(1, shoppingCartId);
				ps.setInt(2, cart.getId());
				ps.setInt(3, colorId); // Lấy màu đầu tiên trong danh sách màu
				ps.setInt(4, cart.getQuantity());
				ps.executeUpdate();
			}

			System.out.println("Update giỏ hàng thành công");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void updateCartToDatabase4(int userId, List<Cart> cartList) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBConnect.getConnection();

			// Kiểm tra xem giỏ hàng của người dùng đã tồn tại hay chưa
			String cartIdQuery = "SELECT shopping_cart_id FROM clothingshop1.shoppingcart WHERE user_id = ?";
			ps = conn.prepareStatement(cartIdQuery);
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			int shoppingCartId = -1;
			if (rs.next()) {
				// Giỏ hàng của người dùng đã tồn tại
				shoppingCartId = rs.getInt("shopping_cart_id");
			} else {
				// Giỏ hàng của người dùng chưa tồn tại, tạo mới giỏ hàng
				String createCartQuery = "INSERT INTO ShoppingCart (user_id) VALUES (?)";
				ps = conn.prepareStatement(createCartQuery, Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, userId);
				ps.executeUpdate();
				ResultSet generatedKeys = ps.getGeneratedKeys();
				if (generatedKeys.next()) {
					shoppingCartId = generatedKeys.getInt(1);
				}
			}

			// Thêm mới các mục trong giỏ hàng vào cơ sở dữ liệu
			String insertCartSql = "INSERT INTO ShoppingCartProduct (shopping_cart_id, product_id, colors_id, quantity) VALUES (?, ?, ?, ?) "
					+ "ON DUPLICATE KEY UPDATE quantity = quantity + VALUES(quantity)";
			for (Cart cart : cartList) {
				String colorIdsql = "SELECT colors_id FROM clothingshop1.colors WHERE colors_name = ?";
				ps = conn.prepareStatement(colorIdsql);
				ps.setString(1, cart.getColors().iterator().next());
				rs = ps.executeQuery();
				int colorId = -1;
				if (rs.next()) {
					colorId = rs.getInt("colors_id");
				}
				ps = conn.prepareStatement(insertCartSql);
				ps.setInt(1, shoppingCartId);
				ps.setInt(2, cart.getId());
				ps.setInt(3, colorId); // Lấy màu đầu tiên trong danh sách màu
				ps.setInt(4, cart.getQuantity());
				ps.executeUpdate();
			}

			System.out.println("Update giỏ hàng thành công");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void updateCartToDatabase5(int userId, List<Cart> cartList) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBConnect.getConnection();

			// Kiểm tra xem giỏ hàng của người dùng đã tồn tại hay chưa
			String cartIdQuery = "SELECT shopping_cart_id FROM clothingshop1.shoppingcart WHERE user_id = ?";
			ps = conn.prepareStatement(cartIdQuery);
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			int shoppingCartId = -1;
			if (rs.next()) {
				// Giỏ hàng của người dùng đã tồn tại
				shoppingCartId = rs.getInt("shopping_cart_id");
			} else {
				// Giỏ hàng của người dùng chưa tồn tại, tạo mới giỏ hàng
				String createCartQuery = "INSERT INTO ShoppingCart (user_id) VALUES (?)";
				ps = conn.prepareStatement(createCartQuery, Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, userId);
				ps.executeUpdate();
				ResultSet generatedKeys = ps.getGeneratedKeys();
				if (generatedKeys.next()) {
					shoppingCartId = generatedKeys.getInt(1);
				}
			}

			// Thêm mới các mục trong giỏ hàng vào cơ sở dữ liệu hoặc cập nhật số lượng nếu
			// sản phẩm đã tồn tại
			String insertCartSql = "INSERT INTO ShoppingCartProduct (shopping_cart_id, product_id, colors_id, quantity) VALUES (?, ?, ?, 1) "
					+ "ON DUPLICATE KEY UPDATE quantity = 1";
			for (Cart cart : cartList) {
				String colorIdsql = "SELECT colors_id FROM clothingshop1.colors WHERE colors_name = ?";
				ps = conn.prepareStatement(colorIdsql);
				ps.setString(1, cart.getColors().iterator().next());
				rs = ps.executeQuery();
				int colorId = -1;
				if (rs.next()) {
					colorId = rs.getInt("colors_id");
				}
				ps = conn.prepareStatement(insertCartSql);
				ps.setInt(1, shoppingCartId);
				ps.setInt(2, cart.getId());
				ps.setInt(3, colorId); // Lấy màu đầu tiên trong danh sách màu
				ps.setInt(4, cart.getQuantity());
				ps.setInt(5, cart.getQuantity()); // Cập nhật số lượng nếu sản phẩm đã tồn tại
				ps.executeUpdate();
			}

			System.out.println("Update giỏ hàng thành công");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void updateCartToDatabase6(int userId, List<Cart> cartList) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBConnect.getConnection();

			// Kiểm tra xem giỏ hàng của người dùng đã tồn tại hay chưa
			String cartIdQuery = "SELECT shopping_cart_id FROM clothingshop1.shoppingcart WHERE user_id = ?";
			ps = conn.prepareStatement(cartIdQuery);
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			int shoppingCartId = -1;
			if (rs.next()) {
				// Giỏ hàng của người dùng đã tồn tại
				shoppingCartId = rs.getInt("shopping_cart_id");
			} else {
				// Giỏ hàng của người dùng chưa tồn tại, tạo mới giỏ hàng
				String createCartQuery = "INSERT INTO ShoppingCart (user_id) VALUES (?)";
				ps = conn.prepareStatement(createCartQuery, Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, userId);
				ps.executeUpdate();
				ResultSet generatedKeys = ps.getGeneratedKeys();
				if (generatedKeys.next()) {
					shoppingCartId = generatedKeys.getInt(1);
				}
			}

			// Cập nhật giỏ hàng trong cơ sở dữ liệu
			String updateCartSql = "UPDATE ShoppingCartProduct SET quantity = ? WHERE shopping_cart_id = ? AND product_id = ? AND colors_id = ?";
			for (Cart cart : cartList) {
				int quantity = cart.getQuantity();
				String color = cart.getColors().iterator().next();

				// Lấy colorId từ bảng Colors
				String colorIdSql = "SELECT colors_id FROM clothingshop1.colors WHERE colors_name = ?";
				ps = conn.prepareStatement(colorIdSql);
				ps.setString(1, color);
				rs = ps.executeQuery();
				int colorId = -1;
				if (rs.next()) {
					colorId = rs.getInt("colors_id");
				}

				// Kiểm tra xem sản phẩm đã tồn tại trong giỏ hàng chưa
				String checkExistSql = "SELECT COUNT(*) AS count FROM ShoppingCartProduct WHERE shopping_cart_id = ? AND product_id = ? AND colors_id = ?";
				ps = conn.prepareStatement(checkExistSql);
				ps.setInt(1, shoppingCartId);
				ps.setInt(2, cart.getId());
				ps.setInt(3, colorId);
				rs = ps.executeQuery();
				rs.next();
				int count = rs.getInt("count");

				// Nếu sản phẩm đã tồn tại, cập nhật số lượng
				if (count > 0) {
					ps = conn.prepareStatement(updateCartSql);
					ps.setInt(1, quantity);
					ps.setInt(2, shoppingCartId);
					ps.setInt(3, cart.getId());
					ps.setInt(4, colorId);
					ps.executeUpdate();
				} else {
					// Nếu sản phẩm chưa tồn tại, thêm mới vào giỏ hàng
					String insertCartSql = "INSERT INTO ShoppingCartProduct (shopping_cart_id, product_id, colors_id, quantity) VALUES (?, ?, ?, ?)";
					ps = conn.prepareStatement(insertCartSql);
					ps.setInt(1, shoppingCartId);
					ps.setInt(2, cart.getId());
					ps.setInt(3, colorId);
					ps.setInt(4, quantity);
					ps.executeUpdate();
				}
			}

			System.out.println("Update giỏ hàng thành công");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void updateCartToDatabase(int userId, List<Cart> cartList) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBConnect.getConnection();

			// Kiểm tra xem giỏ hàng của người dùng đã tồn tại hay chưa
			String cartIdQuery = "SELECT shopping_cart_id FROM clothingshop1.shoppingcart WHERE user_id = ?";
			ps = conn.prepareStatement(cartIdQuery);
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			int shoppingCartId = -1;
			if (rs.next()) {
				// Giỏ hàng của người dùng đã tồn tại
				shoppingCartId = rs.getInt("shopping_cart_id");
			} else {
				// Giỏ hàng của người dùng chưa tồn tại, tạo mới giỏ hàng
				String createCartQuery = "INSERT INTO ShoppingCart (user_id) VALUES (?)";
				ps = conn.prepareStatement(createCartQuery, Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, userId);
				ps.executeUpdate();
				ResultSet generatedKeys = ps.getGeneratedKeys();
				if (generatedKeys.next()) {
					shoppingCartId = generatedKeys.getInt(1);
				}
			}

			// Xóa toàn bộ giỏ hàng của người dùng trước khi cập nhật lại
			String deleteCartProductSql = "DELETE FROM shoppingcartproduct WHERE shopping_cart_id = ?";
			ps = conn.prepareStatement(deleteCartProductSql);
			ps.setInt(1, shoppingCartId);
			ps.executeUpdate();

			// Thêm mới các mục trong giỏ hàng vào cơ sở dữ liệu
			String insertCartSql = "INSERT INTO ShoppingCartProduct (shopping_cart_id, product_id, colors_id, quantity) VALUES (?, ?, ?, ?)";
			for (Cart cart : cartList) {
				String colorIdsql = "SELECT colors_id FROM clothingshop1.colors WHERE colors_name = ?";
				ps = conn.prepareStatement(colorIdsql);
				ps.setString(1, cart.getColors().iterator().next());
				rs = ps.executeQuery();
				int colorId = -1;
				if (rs.next()) {
					colorId = rs.getInt("colors_id");
				}
				ps = conn.prepareStatement(insertCartSql);
				ps.setInt(1, shoppingCartId);
				ps.setInt(2, cart.getId());
				ps.setInt(3, colorId); // Lấy màu đầu tiên trong danh sách màu
				ps.setInt(4, cart.getQuantity());
				ps.executeUpdate();
			}

			System.out.println("Update giỏ hàng thành công");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void deleteShoppingCartById(int userId) {
		try (Connection conn = DBConnect.getConnection()) {
			// Xóa các mục giỏ hàng trong bảng "shoppingcartproduct"
			String deleteShoppingCartProductQuery = "DELETE FROM shoppingcartproduct WHERE shopping_cart_id IN (SELECT shopping_cart_id FROM shoppingcart WHERE user_id = ?)";
			try (PreparedStatement stmtShoppingCartProduct = conn.prepareStatement(deleteShoppingCartProductQuery)) {
				stmtShoppingCartProduct.setInt(1, userId);
				stmtShoppingCartProduct.executeUpdate();
			}

			// Xóa giỏ hàng trong bảng "shoppingcart"
			String deleteShoppingCartQuery = "DELETE FROM shoppingcart WHERE user_id = ?";
			try (PreparedStatement stmtShoppingCart = conn.prepareStatement(deleteShoppingCartQuery)) {
				stmtShoppingCart.setInt(1, userId);
				stmtShoppingCart.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addOrder(Order order, List<Cart> cartItems) throws SQLException {
		Connection conn = DBConnect.getConnection();
		// Thêm đơn hàng vào bảng "Orders"
		String insertOrderQuery = "INSERT INTO Orders (user_id, orderdate, shipping_costs, address, phone_number, description,order_status) VALUES (?, ?, ?, ?, ?, ?,?)";
		try (PreparedStatement stmt = conn.prepareStatement(insertOrderQuery, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setInt(1, order.getUserId());
			stmt.setString(2, order.getOrderDate());
			stmt.setInt(3, order.getShippingCost());
			stmt.setString(4, order.getAddress());
			stmt.setString(5, order.getPhoneNumber());
			stmt.setString(6, order.getDescription());
			stmt.setString(7, order.getOrderStatus());

			int affectedRows = stmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("Creating order failed, no rows affected.");
			}

			// Lấy khóa chính của đơn hàng vừa thêm vào bảng "Orders"
			try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					int orderId = generatedKeys.getInt(1);

					// Thêm các mục đơn hàng vào bảng "OrderItems"
					String insertOrderItemQuery = "INSERT INTO OrderItems (order_id, product_id, colors_id, quantity) VALUES (?, ?, ?, ?)";
					try (PreparedStatement stmtOrderItem = conn.prepareStatement(insertOrderItemQuery)) {
						for (Cart cart : cartItems) {
							stmtOrderItem.setInt(1, orderId);
							stmtOrderItem.setInt(2, cart.getId());
							String findId = "select colors_id from colors where colors_name = ?";
							PreparedStatement ps = conn.prepareStatement(findId);
							ps.setString(1, cart.getColors().iterator().next());
							ResultSet rs = ps.executeQuery();
							int colorId = -1;
							while (rs.next()) {
								colorId = rs.getInt("colors_id");
							}
							stmtOrderItem.setInt(3, colorId);
							stmtOrderItem.setInt(4, cart.getQuantity());
							stmtOrderItem.addBatch();
						}
						stmtOrderItem.executeBatch();
					}
				} else {
					throw new SQLException("Creating order failed, no ID obtained.");
				}
			}
		}

	}

	public static void main(String[] args) {
		ProductDAO productDAO = new ProductDAO();
		List<Product> productList1 = productDAO.getProductByCategory(6);
		List<Cart> productList = productDAO.getCartByUserId(1);
		for (Product product : productList1) {
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
			Category category = product.getCategory();
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

//		System.out.println("find id 1");
//		Product product = productDAO.getProductById(1);
//		System.out.println("Product ID: " + product.getId());
//		System.out.println("Product Name: " + product.getName());
//		System.out.println("Product Brand ID: " + product.getBrandId());
//		System.out.println("Product Description: " + product.getDescription());
//		System.out.println("Product Price: " + product.getPrice());
//		System.out.println("Product Price More: " + product.getPriceMore());
//		System.out.println("Product Promotion: " + product.getPromotion());
//		System.out.println("Product Image URL: " + product.getImageUrl());
//		System.out.println("Product Created At: " + product.getCreatedAt());
//
//		// Xuất danh sách kích thước (size) của sản phẩm
//		System.out.println("Product Sizes:");
//		for (String size : product.getSize()) {
//			System.out.println("- " + size);
//		}

		// Xuất danh sách màu sắc (color name) của sản phẩm
//		System.out.println("Product Colors:");
//		for (String color : product.getColors()) {
//			System.out.println("- " + color);
//		}
//
//		// Xuất danh sách hình ảnh (color image) theo từng màu sắc của sản phẩm
//		System.out.println("Product Color Images:");
//		Map<String, String> colorImages = product.getColorImages();
//		for (Map.Entry<String, String> entry : colorImages.entrySet()) {
//			System.out.println("- Color: " + entry.getKey() + ", Image URL: " + entry.getValue());
//		}
//
//		// Xuất thông tin của danh mục (category) của sản phẩm
//		Category category = product.getCategory();
//		System.out.println("Product Category:");
//		System.out.println("- Category Name: " + category.getCategoryName());
//
//		Category parentCategory = category.getParentCategory();
//		if (parentCategory != null) {
//			System.out.println("- Parent Category Name: " + parentCategory.getCategoryName());
//		} else {
//			System.out.println("- Parent Category Name: No parent category");
//		}
//
//		System.out.println("----------------------------------------");
//
//		ArrayList<Cart> carts = new ArrayList<Cart>();
//		Cart c1 = new Cart();
//		c1.setId(1);
//		c1.setQuantity(2);
//		carts.add(c1);
//		System.out.println(productDAO.getTotalPrice(carts));
	}
}
