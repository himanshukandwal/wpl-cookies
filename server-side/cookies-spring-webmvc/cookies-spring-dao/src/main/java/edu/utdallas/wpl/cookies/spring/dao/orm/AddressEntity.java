package edu.utdallas.wpl.cookies.spring.dao.orm;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "address")
public class AddressEntity implements Serializable{

    @Id
    @Basic(optional = false)
	@Column(name = "address_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_seq")
	@SequenceGenerator(name = "address_seq", sequenceName = "address_sequence")
    private Integer id;

    @Column(name="address_line", length = 20)
    private String line;
    
    @Column(name="country", length = 20)
    private String countryCode;
    
    @Column(name="zip_code", length = 20)
    private Integer zipCode;

    @Column(name="city", length = 20)
    private String city;
    @Column(name="state", length = 20)
    private String state;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLine() {
		return line;
	}
	public void setLine(String line) {
		this.line = line;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public Integer getZipCode() {
		return zipCode;
	}
	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
   
}
