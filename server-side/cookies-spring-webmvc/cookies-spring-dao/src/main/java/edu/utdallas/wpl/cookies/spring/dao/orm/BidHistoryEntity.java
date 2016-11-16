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
	/**
	 * 
	 */
	private static final long serialVersionUID = 708196945214833810L;
	@Id
	private Integer userId;
	@Id
	private Integer bidId;
	@Column(name="BID_STATUS", length = 20)
	private String bidStatus;
	@Column(name="BID_PRICE", length = 20)
	private String bidPrice;
	@Column(name="APARTMENT_ID", length = 20)
	private Integer apartmentId;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getBidId() {
		return bidId;
	}
	public void setBidId(Integer bidId) {
		this.bidId = bidId;
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
 class BidHistoryPk  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn (name = "USER_ID")
	private Integer userId;
	@OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn (name = "BID_ID")
	private Integer bidId;
	public BidHistoryPk(Integer userId, Integer bidId) {
		super();
		this.userId = userId;
		this.bidId = bidId;
	}
	
}
