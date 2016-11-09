package edu.utdallas.wpl.cookies.spring.dao.orm;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "address")
public class AddressEntity {

    @Id
    @Basic(optional = false)
	@Column(name = "address_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_seq")
	@SequenceGenerator(name = "address_seq", sequenceName = "address_sequence")
    private Integer id;

    @Column(name="address_line1", length = 20)
    private String line1;
    
    @Column(name="address_line2", length = 20)
    private String line2;
    
    @Column(name="country_code", length = 20)
    private String countryCode;
    
    @Column(name="zip_code", length = 20)
    private String zipCode;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public String getLine2() {
		return line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
   
}
