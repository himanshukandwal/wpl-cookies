package edu.utdallas.wpl.cookies.spring.dao.orm;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
@Entity(name = "shoppingtable")
public class ShoppingInfoEntity {
	@Id
	@Column(name = "shopping_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SHOPPING_SEQUENCE")
	@SequenceGenerator(name = "SHOPPING_SEQUENCE", sequenceName = "SHOPPING_SEQUENCE")
	private Integer shoppingId;
	@OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn (name = "TRAN_ID")
	private TransactionInfoEntity transactionInfo;
	@Column(name = "QUANTITY", nullable = false)
	private Integer quantity;
	@Column(name = "PRICE", nullable = false)
	private Float price;
	@Column(name="bid_id")
	private Integer bidId;
	
	
	
	public Integer getBidId() {
		return bidId;
	}
	public void setBidId(Integer bidId) {
		this.bidId = bidId;
	}
	public Integer getShoppingId() {
		return shoppingId;
	}
	public void setShoppingId(Integer shoppingId) {
		this.shoppingId = shoppingId;
	}
	public TransactionInfoEntity getTransactionInfo() {
		return transactionInfo;
	}
	public void setTransactionInfo(TransactionInfoEntity transactionInfo) {
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
