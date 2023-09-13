package model;

import java.util.Date;

public class Cart extends Product {
private int quantity;

public Cart( int quantity) {	
	this.quantity = quantity;
}

public Cart() {
}

public int getQuantity() {
	return quantity;
}

public void setQuantity(int quantity) {
	this.quantity = quantity;
}


}
