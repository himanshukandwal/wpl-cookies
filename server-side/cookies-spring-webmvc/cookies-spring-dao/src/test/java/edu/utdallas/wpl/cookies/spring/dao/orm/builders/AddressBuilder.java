package edu.utdallas.wpl.cookies.spring.dao.orm.builders;

import edu.utdallas.wpl.cookies.spring.dao.orm.Address;

public class AddressBuilder {
	
	private Address address ;

    public AddressBuilder() {
		address = new Address();
	}
	
    public  AddressBuilder withId(Integer id) {
		address.setId(id);
		return this;
	}
    
	public  AddressBuilder withLine1(String line1) {
		address.setLine1(line1);
		return this;
	}
	
	public  AddressBuilder withLine2(String line2) {
		address.setLine2(line2);
		return this;
	}
	
	public  AddressBuilder withCountryCode(String countryCode) {
		address.setCountryCode(countryCode);
		return this;
	}
	
	public  AddressBuilder withZipCode(String zipCode) {
		address.setZipCode(zipCode);
		return this;
	}
	
	public Address build() {
		return address ;
	}
	
}
