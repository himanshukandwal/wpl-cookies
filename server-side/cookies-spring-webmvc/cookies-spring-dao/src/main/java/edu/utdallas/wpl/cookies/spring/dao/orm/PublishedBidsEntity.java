package edu.utdallas.wpl.cookies.spring.dao.orm;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity(name = "published_bids")
public class PublishedBidsEntity {
	
	@Id
	@Column(name = "BID_ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PUBLISHED_BIDS_SEQUENCE")
	@SequenceGenerator(name = "PUBLISHED_BIDS_SEQUENCE", sequenceName = "PUBLISHED_BIDS_SEQUENCE")
	private Integer bidId;
	
	@OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn (name = "OWNER_ID")
	private UserInformationEntity owner;
	
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
	
	@OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn (name = "APARTMENT_ID")
	private ApartmentEntity apartment;
	
	public ApartmentEntity getApartment() {
		return apartment;
	}
	
	public void setApartment(ApartmentEntity apartment) {
		this.apartment = apartment;
	}
	
	public Integer getBidId() {
		return bidId;
	}
	
	public void setBidId(Integer bidId) {
		this.bidId = bidId;
	}
	
	public UserInformationEntity getOwner() {
		return owner;
	}
	
	public void setUserId(UserInformationEntity userId) {
		this.owner = userId;
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
