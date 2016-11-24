package edu.utdallas.wpl.cookies.spring.common.dto;

public class TransactionInfo {

	private Integer tranId;

	private PublishedBids bid;

	private UserInformation bidPoster;

	private UserInformation bidReceiver;

	private String bidPrice;

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

	public UserInformation getBidPoster() {
		return bidPoster;
	}

	public void setBidPoster(UserInformation bidPoster) {
		this.bidPoster = bidPoster;
	}

	public UserInformation getBidReceiver() {
		return bidReceiver;
	}

	public void setBidReceiver(UserInformation bidReceiver) {
		this.bidReceiver = bidReceiver;
	}

	public String getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(String bidPrice) {
		this.bidPrice = bidPrice;
	}

}
