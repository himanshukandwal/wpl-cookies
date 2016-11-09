package edu.utdallas.wpl.cookies.spring.dao.orm.samples;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.utdallas.wpl.cookies.spring.dao.orm.UserInformationEntity;
import edu.utdallas.wpl.cookies.spring.dao.orm.builders.UserInformationEntityBuilder;

public class UserInformationEntities {

	public static final UserInformationEntity USERINFORMATION_ENTITY_1 = new UserInformationEntityBuilder()
			.withId(1)
			.withBirthDate(new Date())
			.withEmail("hello@abc.com")
			.build();

	
	@SuppressWarnings("serial")
	public static final List<UserInformationEntity> USERINFORMATION_ENTITIES = new ArrayList<UserInformationEntity>() {
		{
			add (USERINFORMATION_ENTITY_1);
		}
	};
	
}
