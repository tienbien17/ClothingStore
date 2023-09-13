package model;

import java.util.List;

public class Order {
	private int orderId;
	private int userId;
	private String orderDate;
	private int shippingCost;
	private String address;
	private String phoneNumber;
	private String description;
	private String orderStatus;
	
	
	public Order(int orderId, int userId, String orderDate, int shippingCost, String address, String phoneNumber,
			String description, String orderStatus) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.orderDate = orderDate;
		this.shippingCost = shippingCost;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.description = description;
		this.orderStatus = orderStatus;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	private List<Cart> carts;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public int getShippingCost() {
		return shippingCost;
	}
	public void setShippingCost(int shippingCost) {
		this.shippingCost = shippingCost;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Cart> getCarts() {
		return carts;
	}
	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}
	public Order( int userId, String orderDate, int shippingCost, String address, String phoneNumber,
			String description, List<Cart> carts,String orderStatus) {
		super();
		this.userId = userId;
		this.orderDate = orderDate;
		this.shippingCost = shippingCost;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.description = description;
		this.carts = carts;
		this.orderStatus=orderStatus;
	}
	public Order() {
		super();
	}
	
	
	
}
