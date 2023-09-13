package model;

public class Category {
    private int categoryId;
    private String categoryName;
    private Category parentCategory;

    public Category(int categoryId, String categoryName, Category parentCategory) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.parentCategory = parentCategory;
    }

	public Category(String categoryName, Category parentCategory) {
		super();
		this.categoryName = categoryName;
		this.parentCategory = parentCategory;
	}

	public Category() {
		super();
	}

	public Category(int id) {
		this.categoryId = id;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Category getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}

    // Getters and setters

    // ...
}
