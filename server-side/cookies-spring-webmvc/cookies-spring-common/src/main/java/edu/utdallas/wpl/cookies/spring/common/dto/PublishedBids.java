package edu.utdallas.wpl.cookies.spring.common.dto;

import java.util.Date;

public class PublishedBids {
	
	private Integer bidId;
    private UserInformation owner;
	
	private Date hostedDate;
	
	
	private String comments;
	
	
	private String price;
	
	
	private String activeInd;
	

	private Date bidCloseDate;
	

	private Integer numDays;
	

	private ApartmentInfo apartment;
	
	
	public Integer getBidId() {
		return bidId;
	}


	public void setBidId(Integer bidId) {
		this.bidId = bidId;
	}


	public UserInformation getOwner() {
		return owner;
	}


	public void setOwner(UserInformation owner) {
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


	public ApartmentInfo getApartment() {
		return apartment;
	}


	public void setApartment(ApartmentInfo apartment) {
		this.apartment = apartment;
	}


	
	

}
