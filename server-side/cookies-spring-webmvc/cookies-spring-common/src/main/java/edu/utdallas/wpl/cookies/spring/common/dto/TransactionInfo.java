package edu.utdallas.wpl.cookies.spring.common.dto;



public class TransactionInfo {
	
	
	private Integer tranId;


	private PublishedBids bid;


	private UserInformation bidReceiver;

	
	private Float bidPrice;
	
	private String bidStatus;
	
	private String comments;
	
	private Integer quantity;

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getTranId() {
		return tranId;
	}

	public void setTranId(Integer tranId) {
		this.tranId = tranId;
	}

	public PublishedBids getBid() {
		return bid;
	}

	public void setBid(PublishedBids bid) {
		this.bid = bid;
	}

	public UserInformation getBidReceiver() {
		return bidReceiver;
	}

	public void setBidReceiver(UserInformation bidReceiver) {
		this.bidReceiver = bidReceiver;
	}

	public Float getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(Float bidPrice) {
		this.bidPrice = bidPrice;
	}

	public String getBidStatus() {
		return bidStatus;
	}

	public void setBidStatus(String bidStatus) {
		this.bidStatus = bidStatus;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	


}
