package edu.utdallas.wpl.cookies.spring.dao.orm.builders;

import edu.utdallas.wpl.cookies.spring.dao.orm.AddressEntity;

public class AddressEntityBuilder {
	
	private AddressEntity address ;

    public AddressEntityBuilder() {
		address = new AddressEntity();
	}
	
    public  AddressEntityBuilder withId(Integer id) {
		address.setId(id);
		return this;
	}
    
	public  AddressEntityBuilder withLine1(String line1) {
		address.setLine1(line1);
		return this;
	}
	
	public  AddressEntityBuilder withLine2(String line2) {
		address.setLine2(line2);
		return this;
	}
	
	public  AddressEntityBuilder withCountryCode(String countryCode) {
		address.setCountryCode(countryCode);
		return this;
	}
	
	public  AddressEntityBuilder withZipCode(String zipCode) {
		address.setZipCode(zipCode);
		return this;
	}
	
	public AddressEntity build() {
		return address ;
	}
	
}
