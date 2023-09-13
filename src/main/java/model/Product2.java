package model;

import java.util.Date;

public class Product2 {
	private int id;
	private String name;
	private Date createDate;
	private String image;
	private String moreImage;
	private String type;
	private double price;
	private double priceMore;
	private double promotion;
	private String description;
	private int categoryId;
	private String categoryName;
	private String categoryDescription;
	public Product2(int id, String name, Date createDate, String image, String moreImage, String type, double price,
			double priceMore, double promotion, String description, int categoryId, String categoryName,
			String categoryDescription) {
		super();
		this.id = id;
		this.name = name;
		this.createDate = createDate;
		this.image = image;
		this.moreImage = moreImage;
		this.type = type;
		this.price = price;
		this.priceMore = priceMore;
		this.promotion = promotion;
		this.description = description;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.categoryDescription = categoryDescription;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryDescription() {
		return categoryDescription;
	}
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}
	public Product2() {
		super();
	}
	public Product2(int id, String name, Date createDate, String image, String moreImage, String type, double price,
			double priceMore, double promotion, String description, int categoryId) {
		super();
		this.id = id;
		this.name = name;
		this.createDate = createDate;
		this.image = image;
		this.moreImage = moreImage;
		this.type = type;
		this.price = price;
		this.priceMore = priceMore;
		this.promotion = promotion;
		this.description = description;
		this.categoryId = categoryId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getMoreImage() {
		return moreImage;
	}
	public void setMoreImage(String moreImage) {
		this.moreImage = moreImage;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getPriceMore() {
		return priceMore;
	}
	public void setPriceMore(double priceMore) {
		this.priceMore = priceMore;
	}
	public double getPromotion() {
		return promotion;
	}
	public void setPromotion(double promotion) {
		this.promotion = promotion;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	
}
