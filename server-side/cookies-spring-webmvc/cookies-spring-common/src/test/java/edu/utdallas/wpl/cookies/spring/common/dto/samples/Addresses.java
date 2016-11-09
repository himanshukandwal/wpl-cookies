package edu.utdallas.wpl.cookies.spring.common.dto.samples;

import java.util.ArrayList;
import java.util.List;

import edu.utdallas.wpl.cookies.spring.common.dto.Address;
import edu.utdallas.wpl.cookies.spring.common.dto.builders.AddressBuilder;

public class Addresses {

	public static final Address ADDRESS_1 = new AddressBuilder()
			.withLine1("temp line 1")
			.withLine2("temp line 2")
			.withCountryCode("US")
			.withZipCode("75252")
			.build();

	public static Address copy(Address srcEvent) {
		return new AddressBuilder()
				.withLine1(srcEvent.getLine1())
				.withLine2(srcEvent.getLine2())
				.withZipCode(srcEvent.getZipCode())
				.withCountryCode(srcEvent.getCountryCode())
				.build();
	}
	
	@SuppressWarnings("serial")
	public static final List<Address> ADDRESSES = new ArrayList<Address>() {
		{
			add (ADDRESS_1);
		}
	};
	
}
