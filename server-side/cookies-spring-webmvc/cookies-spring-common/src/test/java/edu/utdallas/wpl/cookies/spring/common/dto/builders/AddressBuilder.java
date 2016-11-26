package edu.utdallas.wpl.cookies.spring.common.dto.builders;

import edu.utdallas.wpl.cookies.spring.common.dto.Address;

public class AddressBuilder {
	
	private Address address ;

    public AddressBuilder() {
		address = new Address();
	}
	
	public  AddressBuilder withLine(String line) {
		address.setLine(line);
		return this;
	}
	public  AddressBuilder withCountryCode(String countryCode) {
		address.setCountryCode(countryCode);
		return this;
	}
	
	public  AddressBuilder withZipCode(Integer zipCode) {
		address.setZipCode(zipCode);
		return this;
	}
	
	public Address build() {
		return address ;
	}
	
}
