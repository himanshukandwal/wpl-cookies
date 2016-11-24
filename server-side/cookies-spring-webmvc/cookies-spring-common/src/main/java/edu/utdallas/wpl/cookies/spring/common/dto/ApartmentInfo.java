package edu.utdallas.wpl.cookies.spring.common.dto;

import edu.utdallas.wpl.cookies.spring.common.enums.ApartmentType;

public class ApartmentInfo {

	private Integer apartmentId;
	private String apartmentName;
	private String apartmentCity;
	private String apartmentState;
	private String apartmentCountry;
	private String apartmentStreet;
	private String apartmentNumber;
	private String baseFare;
	private UserInformation owner;
	private String overallRating;
	private ApartmentType apartmentType;
	private String totNumPplRated;
	public Integer getApartmentId() {
		return apartmentId;
	}
	public void setApartmentId(Integer apartmentId) {
		this.apartmentId = apartmentId;
	}
	public String getApartmentName() {
		return apartmentName;
	}
	public void setApartmentName(String apartmentName) {
		this.apartmentName = apartmentName;
	}
	public String getApartmentCity() {
		return apartmentCity;
	}
	public void setApartmentCity(String apartmentCity) {
		this.apartmentCity = apartmentCity;
	}
	public String getApartmentState() {
		return apartmentState;
	}
	public void setApartmentState(String apartmentState) {
		this.apartmentState = apartmentState;
	}
	public String getApartmentCountry() {
		return apartmentCountry;
	}
	public void setApartmentCountry(String apartmentCountry) {
		this.apartmentCountry = apartmentCountry;
	}
	public String getApartmentStreet() {
		return apartmentStreet;
	}
	public void setApartmentStreet(String apartmentStreet) {
		this.apartmentStreet = apartmentStreet;
	}
	public String getApartmentNumber() {
		return apartmentNumber;
	}
	public void setApartmentNumber(String apartmentNumber) {
		this.apartmentNumber = apartmentNumber;
	}
	public String getBaseFare() {
		return baseFare;
	}
	public void setBaseFare(String baseFare) {
		this.baseFare = baseFare;
	}
	public UserInformation getOwner() {
		return owner;
	}
	public void setOwner(UserInformation owner) {
		this.owner = owner;
	}
	public String getOverallRating() {
		return overallRating;
	}
	public void setOverallRating(String overallRating) {
		this.overallRating = overallRating;
	}
	public ApartmentType getApartmentType() {
		return apartmentType;
	}
	public void setApartmentType(ApartmentType apartmentType) {
		this.apartmentType = apartmentType;
	}
	public String getTotNumPplRated() {
		return totNumPplRated;
	}
	public void setTotNumPplRated(String totNumPplRated) {
		this.totNumPplRated = totNumPplRated;
	}
	
}
