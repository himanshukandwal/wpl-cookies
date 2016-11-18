package edu.utdallas.wpl.cookies.spring.dao.orm;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

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
	
	
	@OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn (name = "APARTMENT_ID")
	private ApartmentEntity apartmentEntity;
	
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
	
	public ApartmentEntity getApartmentId() {
		return apartmentEntity;
	}
	
	public void setApartmentId(ApartmentEntity apartmentId) {
		this.apartmentEntity = apartmentId;
	}
	
}
