package edu.utdallas.wpl.cookies.spring.common.dto;

public class ShoppingInfo {

	public Integer shoppingId;

	public TransactionInfo transactionInfo;

	public Integer quantity;

	public Float price;

	public Integer getShoppingId() {
		return shoppingId;
	}

	public void setShoppingId(Integer shoppingId) {
		this.shoppingId = shoppingId;
	}

	

	public TransactionInfo getTransactionInfo() {
		return transactionInfo;
	}

	public void setTransactionInfo(TransactionInfo transactionInfo) {
		this.transactionInfo = transactionInfo;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	


}
