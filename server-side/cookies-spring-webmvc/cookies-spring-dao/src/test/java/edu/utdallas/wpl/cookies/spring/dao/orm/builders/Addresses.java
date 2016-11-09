package edu.utdallas.wpl.cookies.spring.dao.orm.builders;

import java.util.ArrayList;
import java.util.List;

import edu.utdallas.wpl.cookies.spring.dao.orm.Address;

public class Addresses {

	public static final Address ADDRESS_1 = new AddressBuilder()
			.withId(1)
			.withLine1("temp line 1")
			.withLine2("temp line 2")
			.withCountryCode("US")
			.withZipCode("75252")
			.build();

	
	@SuppressWarnings("serial")
	public static final List<Address> ADDRESSES = new ArrayList<Address>() {
		{
			add(ADDRESS_1);
		}
	};
	
}
