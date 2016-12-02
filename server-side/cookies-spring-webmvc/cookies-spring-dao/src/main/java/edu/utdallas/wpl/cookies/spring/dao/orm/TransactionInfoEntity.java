package edu.utdallas.wpl.cookies.spring.dao.orm;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity(name = "transaction_info")
public class TransactionInfoEntity implements Serializable{

	@Id
	@Column(name = "TRAN_ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRANSACTION_INFO_SEQUENCE")
	@SequenceGenerator(name = "TRANSACTION_INFO_SEQUENCE", sequenceName = "TRANSACTION_INFO_SEQUENCE")
	private Integer tranId;

	@OneToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "BID_ID")
	private PublishedBidsEntity bid;

	@OneToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "BIDRECEIVER_ID")
	private UserInformationEntity bidReceiver;

	@Column(name = "BID_PRICE", length = 20)
	private Float bidPrice;
	@Column(name = "BID_STATUS", length = 20)
	private String bidStatus;
	@Column(name = "comments", length = 20)
	private String comments;

	@Column(name = "quantity")
	private Integer quantity;

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Integer getTranId() {
		return tranId;
	}

	public void setTranId(Integer tranId) {
		this.tranId = tranId;
	}

	public String getBidStatus() {
		return bidStatus;
	}

	public void setBidStatus(String bidStatus) {
		this.bidStatus = bidStatus;
	}

	public PublishedBidsEntity getBid() {
		return bid;
	}

	public void setBid(PublishedBidsEntity bid) {
		this.bid = bid;
	}

	public Float getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(Float bidPrice) {
		this.bidPrice = bidPrice;
	}

	public UserInformationEntity getBidReceiver() {
		return bidReceiver;
	}

	public void setBidReceiver(UserInformationEntity bidReceiver) {
		this.bidReceiver = bidReceiver;
	}

}
