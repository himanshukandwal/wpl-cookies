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

@Entity(name = "published_bids_info")
public class PublishedBidsEntity {

	@Id
	@Column(name = "BID_ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PUBLISHED_BIDS_SEQUENCE")
	@SequenceGenerator(name = "PUBLISHED_BIDS_SEQUENCE", sequenceName = "PUBLISHED_BIDS_SEQUENCE")
	private Integer bidId;

	@OneToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "OWNER_ID")
	private UserInformationEntity owner;

	@Column(name = "HOSTED_DATE")
	private Date hostedDate;

	@Column(name = "COMMENTS", length = 20)
	private String comments;

	@Column(name = "PRICE", length = 20)
	private Float price;

	@Column(name = "ACTIVE_IND", length = 1)
	private String activeInd;

	@Column(name = "BID_CLOSE_DATE")
	private Date bidCloseDate;

	@Column(name = "NUM_DAYS")
	private Integer numDays;

	@Column(name = "APARTMENT_TYPE")
	private String apartmentType;
	@Column(name = "FROM_DATE")
	private Date fromDate;
	@Column(name = "TO_DATE")
	private Date toDate;
	@OneToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "address_id")
	private AddressEntity addressEntity;
	@Column(name = "MODIFIED_DATE")

	private Float modifiedDate;

	public Float getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Float modifiedDate) {
		this.modifiedDate = modifiedDate;
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

	public void setOwner(UserInformationEntity owner) {
		this.owner = owner;
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

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
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

	public String getApartmentType() {
		return apartmentType;
	}

	public void setApartmentType(String apartmentType) {
		this.apartmentType = apartmentType;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public AddressEntity getAddressEntity() {
		return addressEntity;
	}

	public void setAddressEntity(AddressEntity addressEntity) {
		this.addressEntity = addressEntity;
	}

}
