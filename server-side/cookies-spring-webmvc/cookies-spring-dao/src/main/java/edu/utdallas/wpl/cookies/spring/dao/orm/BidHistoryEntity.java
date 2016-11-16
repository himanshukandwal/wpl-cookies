package edu.utdallas.wpl.cookies.spring.dao.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity(name = "bid_history")
@IdClass(BidHistoryPk.class)
public class BidHistoryEntity implements Serializable{
	
	private static final long serialVersionUID = 708196945214833810L;
	
	@Id
	private UserInformationEntity user;
	
	@Id
	private PublishedBidsEntity bid;
	
	@Column(name="BID_STATUS", length = 20)
	private String bidStatus;
	
	@Column(name="BID_PRICE", length = 20)
	private String bidPrice;
	
	@Column(name="APARTMENT_ID", length = 20)
	private Integer apartmentId;
	
	public UserInformationEntity getUser() {
		return user;
	}
	
	public void setUser(UserInformationEntity user) {
		this.user = user;
	}
	
	public PublishedBidsEntity getBid() {
		return bid;
	}
	
	public void setBid(PublishedBidsEntity bid) {
		this.bid = bid;
	}
	
	public String getBidStatus() {
		return bidStatus;
	}
	
	public void setBidStatus(String bidStatus) {
		this.bidStatus = bidStatus;
	}
	
	public String getBidPrice() {
		return bidPrice;
	}
	
	public void setBidPrice(String bidPrice) {
		this.bidPrice = bidPrice;
	}
	
	public Integer getApartmentId() {
		return apartmentId;
	}
	
	public void setApartmentId(Integer apartmentId) {
		this.apartmentId = apartmentId;
	}
	
}
