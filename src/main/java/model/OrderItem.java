package model;

import java.util.Map;
import java.util.Set;

public class OrderItem extends Product {
	private int orderId;
	private int colorsId;
	private int quantity;
	public OrderItem(int id,  int orderId, int colorsId, int quantity) {
		this.orderId = orderId;
		this.setId(id);
		this.colorsId = colorsId;
		this.quantity = quantity;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getColorsId() {
		return colorsId;
	}
	public void setColorsId(int colorsId) {
		this.colorsId = colorsId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	

}
