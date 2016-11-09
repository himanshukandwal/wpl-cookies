package edu.utdallas.wpl.cookies.spring.common.dto.samples;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.utdallas.wpl.cookies.spring.common.dto.UserInformation;
import edu.utdallas.wpl.cookies.spring.common.dto.builders.UserInformationBuilder;

public class UserInformations {

	public static final UserInformation USERINFORMATION_1 = new UserInformationBuilder()
			.withBirthDate(new Date())
			.withEmail("hello@abc.com")
			.build();

	
	@SuppressWarnings("serial")
	public static final List<UserInformation> USERINFORMATIONS = new ArrayList<UserInformation>() {
		{
			add (USERINFORMATION_1);
		}
	};
	
}
