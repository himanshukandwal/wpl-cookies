package edu.utdallas.wpl.cookies.spring.dao.orm;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
@Entity(name = "published_bids")
public class PublishedBidsEntity {
	@Id
    @Basic(optional = false)
	@Column(name = "BID_ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PUBLISHED_BIDS_SEQUENCE")
	@SequenceGenerator(name = "PUBLISHED_BIDS_SEQUENCE", sequenceName = "PUBLISHED_BIDS_SEQUENCE")
	private Integer bidId;
	@Column(name="USER_ID", length = 38)
	private Integer userId;
	@Column(name="HOSTED_DATE")
	private Date hostedDate;
	@Column(name="COMMENTS", length = 20)
	private String comments;
	@Column(name="PRICE", length = 20)
	private String price;
	@Column(name="ACTIVE_IND", length = 1)
	private String activeInd;
	@Column(name="BID_CLOSE_DATE")
	private Date bidCloseDate;
	@Column(name="NUM_DAYS")
	private Integer numDays;
	@Column(name="APARTMENT_ID", length = 38)
	private Integer apartmentId;
	public Integer getApartmentId() {
		return apartmentId;
	}
	public void setApartmentId(Integer apartmentId) {
		this.apartmentId = apartmentId;
	}
	public Integer getBidId() {
		return bidId;
	}
	public void setBidId(Integer bidId) {
		this.bidId = bidId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Date getHostedDate() {
		return hostedDate;
	}
	public void setHostedDate(Date hostedDate) {
		this.hostedDate = hostedDate;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getActiveInd() {
		return activeInd;
	}
	public void setActiveInd(String activeInd) {
		this.activeInd = activeInd;
	}
	public Date getBidCloseDate() {
		return bidCloseDate;
	}
	public void setBidCloseDate(Date bidCloseDate) {
		this.bidCloseDate = bidCloseDate;
	}
	public Integer getNumDays() {
		return numDays;
	}
	public void setNumDays(Integer numDays) {
		this.numDays = numDays;
	}
	

}
