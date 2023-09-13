package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Category;

public class CategoryDAO {
	private static final String INSERT_CATEGORY = "INSERT INTO Categories (category_name, parentCategory) VALUES (?, ?)";
	private static final String INSERT_PARENT_CATEGORY = "INSERT INTO Categories (category_name) VALUES (?)";
	private static final String UPDATE_CATEGORY = "UPDATE Categories SET category_name = ?, parentCategory = ? WHERE category_id = ?";
	private static final String DELETE_CATEGORY = "DELETE FROM Categories WHERE category_id = ?";

	public List<Category> getAll() {
		List<Category> list = new ArrayList<Category>();
		Connection conn = DBConnect.getConnection();
		String sql =  "SELECT\r\n"
				+ "    c2.category_id AS 'category_id',\r\n"
				+ "    c2.category_name AS 'category',\r\n"
				+ "    parentCat.category_id as 'parentcategory_id',\r\n"
				+ "    parentCat.category_name AS 'parentcategory'\r\n"
				+ "FROM\r\n"
				+ "    Categories c2\r\n"
				+ "LEFT JOIN\r\n"
				+ "    Categories parentCat ON c2.parentCategory = parentCat.category_id;\r\n"
				+ "";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Category category = new Category();
				category.setCategoryId(rs.getInt("category_id"));
				;
				category.setCategoryName(rs.getString("category"));
				Category parentCategory = new Category();
				parentCategory.setCategoryName(rs.getString("parentcategory"));
				parentCategory.setCategoryId(rs.getInt("parentcategory_id"));
				category.setParentCategory(parentCategory);
				list.add(category);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	// Lấy thông tin theo ID
	public Category getById(int id) {
	    Category category = new Category();
	    Connection conn = DBConnect.getConnection();
	    String sql = "SELECT\r\n"
	            + "    c2.category_id AS 'categoryId',\r\n"
	            + "    c2.category_name AS 'categoryName',\r\n"
	            + "    parentCat.category_id as 'parentCategoryId',\r\n"
	            + "    parentCat.category_name AS 'parentCategoryName'\r\n"
	            + "FROM\r\n"
	            + "    Categories c2\r\n"
	            + "LEFT JOIN\r\n"
	            + "    Categories parentCat ON c2.parentCategory = parentCat.category_id;\r\n"
	            + "";

	    try {
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	             category = new Category();
	            category.setCategoryId(rs.getInt("categoryId"));
	            category.setCategoryName(rs.getString("categoryName"));
	            Category parentCategory = new Category();
	            parentCategory.setCategoryName(rs.getString("parentCategoryName"));
	            parentCategory.setCategoryId(rs.getInt("parentCategoryId"));
	            category.setParentCategory(parentCategory);
//	            list.add(category);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return category;
	}

	public Category getByID(int id) {
		Category category = null;
		Connection conn = DBConnect.getConnection();
		String sql = "SELECT \r\n" + "c2.category_id as 'category id',\r\n" + "c2.category_name AS 'category',\r\n"
				+ " parentCat.category_name AS 'parentcategory'\r\n" + " FROM \r\n" + " Categories c2\r\n"
				+ " LEFT JOIN\r\n" + " Categories parentCat ON c2.parentCategory = parentCat.category_id "
				+ "where c2.category_id = ?;";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				category = new Category();
				category.setCategoryId(rs.getInt("category id"));
				;
				category.setCategoryName(rs.getString("category"));
				Category parentCategory = new Category();
				parentCategory.setCategoryName(rs.getString("parentcategory"));
				category.setParentCategory(parentCategory);
				// Id, Name, Description trùng với tên ở trên SQL
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return category;
	}
	

	public Category getByName(String name) {
		Category category = null;
		Connection conn = DBConnect.getConnection();
		String sql = "SELECT \r\n" + "c2.category_id as 'category id',\r\n" + "c2.category_name AS 'category',\r\n"
				+ " parentCat.category_name AS 'parentcategory'\r\n" + " FROM \r\n" + " Categories c2\r\n"
				+ " LEFT JOIN\r\n" + " Categories parentCat ON c2.parentCategory = parentCat.category_id "
				+ "where c2.category_name = ?;";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				category = new Category();
				category.setCategoryId(rs.getInt("category id"));
				;
				category.setCategoryName(rs.getString("category"));
				Category parentCategory = new Category();
				parentCategory.setCategoryName(rs.getString("parentcategory"));
				category.setParentCategory(parentCategory);
				// Id, Name, Description trùng với tên ở trên SQL
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return category;
	}

	// Thêm mới sản phẩm
	public boolean insertCategory(Category category) {
		try (Connection conn = DBConnect.getConnection();
				PreparedStatement stmt = conn.prepareStatement(INSERT_CATEGORY)) {

			stmt.setString(1, category.getCategoryName());
			if (category.getParentCategory().getCategoryId() != 0) {
				stmt.setInt(2, category.getParentCategory().getCategoryId());
			} else {
				stmt.setNull(2, java.sql.Types.INTEGER);
			}
			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateCategory(Category category) {
		try (Connection conn = DBConnect.getConnection();
				PreparedStatement stmt = conn.prepareStatement(UPDATE_CATEGORY)) {

			stmt.setString(1, category.getCategoryName());
			if (category.getParentCategory() != null) {
				stmt.setInt(2, category.getParentCategory().getCategoryId());
			} else {
				stmt.setNull(2, java.sql.Types.INTEGER);
			}
			stmt.setInt(3, category.getCategoryId());

			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteCategory(int categoryId) {
		try (Connection conn = DBConnect.getConnection();
				PreparedStatement stmt = conn.prepareStatement(DELETE_CATEGORY)) {

			stmt.setInt(1, categoryId);

			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void main(String[] args) {
		CategoryDAO categoryDAO = new CategoryDAO();
		List<Category> list = new ArrayList<>();
		list = categoryDAO.getAll();
		for (Category category : list) {
			System.out.println(category.getCategoryId());
			System.out.println(category.getCategoryName());
			System.out.println(category.getParentCategory().getCategoryId());
			System.out.println(category.getParentCategory().getCategoryName());
		}
	}
}
