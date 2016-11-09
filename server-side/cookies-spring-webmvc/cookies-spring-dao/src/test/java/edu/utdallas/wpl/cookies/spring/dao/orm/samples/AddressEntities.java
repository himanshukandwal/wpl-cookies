package edu.utdallas.wpl.cookies.spring.dao.orm.samples;

import java.util.ArrayList;
import java.util.List;

import edu.utdallas.wpl.cookies.spring.dao.orm.AddressEntity;
import edu.utdallas.wpl.cookies.spring.dao.orm.builders.AddressEntityBuilder;

public class AddressEntities {

	public static final AddressEntity ADDRESS_ENTITY_1 = new AddressEntityBuilder()
			.withId(1)
			.withLine1("temp line 1")
			.withLine2("temp line 2")
			.withCountryCode("US")
			.withZipCode("75252")
			.build();

	
	@SuppressWarnings("serial")
	public static final List<AddressEntity> ADDRESS_ENTITIES = new ArrayList<AddressEntity>() {
		{
			add (ADDRESS_ENTITY_1);
		}
	};
	
}
