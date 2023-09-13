package model;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class Product {
	private int id;
	private String size1;
	private String name;
	private String createDate;
	private String image;
	private String moreImage;
	private String type;
	private double price;
	private double priceMore;
	private double promotion;
	private String description;
	private int brandId;
	private String imageUrl;
	private Set<String> size;
	private String createdAt;
	private Set<String> colors;
	private Map<String, String> colorImages;
	private Category category; // Thay đổi kiểu dữ liệu của category từ String sang Category

	public Product(int brand_id, String product_name, String description, double price,double price_more, String image_url, String size, String created_at,Category categoryId) {
		super();
		this.brandId = brand_id;
		this.name = product_name;
		this.description = description;
		this.price = price;
		this.priceMore = price_more;
		this.imageUrl = image_url;
		this.addSize(size); 
		this.createdAt = created_at;
		this.category =category;
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

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
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

	public int getBrandId() {
		return brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	

	   public Set<String> getSize() {
	        if (size == null) {
	            size = new HashSet<>();
	        }
	        return size;
	    }
	   public String getsize1() {
	        return size.iterator().next();
	    }
	public void setSize(Set<String> size) {
		this.size = size;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public Set<String> getColors() {
		return colors;
	}

	public void setColors(Set<String> colors) {
		this.colors = colors;
	}

	public Map<String, String> getColorImages() {
		return colorImages;
	}

	public void setColorImages(Map<String, String> colorImages) {
		this.colorImages = colorImages;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void addColor(String color) {
        if (colors == null) {
        	colors = new HashSet<>()	;
        }
        colors.add(color);
//		this.colors.add(color);
	}

	public void addColorImage(String color, String image) {
		 if (colorImages == null) {
			colorImages = new HashMap<String, String>();
        }
		this.colorImages.put(color, image);
	}

	 public void addSize(String newSize) {
	        // Đảm bảo size đã được khởi tạo trước khi thêm phần tử
	        if (size == null) {
	            size = new HashSet<>()	;
	        }
	        size.add(newSize);
	    }

	public Product(int id, String name, String createDate, String image, String moreImage, String type, double price,
			double priceMore, double promotion, String description, int brandId, String imageUrl, Set<String> size,
			String createdAt, Set<String> colors, Map<String, String> colorImages, Category category) {
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
		this.brandId = brandId;
		this.imageUrl = imageUrl;
		this.size = size;
		this.createdAt = createdAt;
		this.colors = colors;
		this.colorImages = colorImages;
		this.category = category;
	}

	public Product() {
		super();
	}

}
