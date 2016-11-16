package edu.utdallas.wpl.cookies.spring.dao.orm;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import edu.utdallas.wpl.cookies.spring.dao.orm.enums.ApartmentType;

@Entity(name = "apartment_info")
public class ApartmentEntity {

	@Id
    @Basic(optional = false)
	@Column(name = "APARTMENT_ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "APARTMENT_SEQUENCE")
	@SequenceGenerator(name = "APARTMENT_SEQUENCE", sequenceName = "APARTMENT_SEQUENCE")
	private Integer apartmentId;
	
	@Column(name="APARTMENT_NAME", length = 20)
	private String apartmentName;
	
	@Column(name="APARTMENT_CITY", length = 20)
	private String apartmentCity;
	
	@Column(name="APARTMENT_STATE", length = 20)
	private String apartmentState;
	
	@Column(name="APARTMENT_COUNTRY", length = 20)
	private String apartmentCountry;
	
	@Column(name="APARTMENT_STREET", length = 20)
	private String apartmentStreet;
	
	@Column(name="APARTMENT_NUMBER", length = 20)
	private String apartmentNumber;
	
	@Column(name="BASE_FARE", length = 20)
	private String baseFare;
	
	@OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn (name = "OWNER_ID")
	private UserInformationEntity owner;
	
	@Column(name="OVERALL_RATING", length = 20)
	private String overallRating;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="APARTMENT_TYPE", length = 20)
	private ApartmentType apartmentType;
	
	@Column(name="TOT_NUM_PPL_RATED", length = 20)
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
	
	public UserInformationEntity getOwner() {
		return owner;
	}
	
	public void setOwner(UserInformationEntity owner) {
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
