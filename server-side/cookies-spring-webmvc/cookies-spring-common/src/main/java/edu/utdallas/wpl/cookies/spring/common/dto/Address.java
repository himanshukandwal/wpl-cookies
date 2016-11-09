package edu.utdallas.wpl.cookies.spring.common.dto;

/**
 *  @author Himanshu Kandwal, Anirudha KV, Srinivas
 */
public class Address {

	private Integer id;
    private String line1;
    private String line2;
    private String countryCode;
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
