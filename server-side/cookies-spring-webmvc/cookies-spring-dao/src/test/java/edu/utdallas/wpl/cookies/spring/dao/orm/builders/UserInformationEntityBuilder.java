package edu.utdallas.wpl.cookies.spring.dao.orm.builders;

import java.util.Date;

import edu.utdallas.wpl.cookies.spring.dao.orm.AddressEntity;
import edu.utdallas.wpl.cookies.spring.dao.orm.UserInformationEntity;

public class UserInformationEntityBuilder {
	
	private UserInformationEntity userInfomationEntity;

    public UserInformationEntityBuilder() {
    	userInfomationEntity = new UserInformationEntity();
	}
	
    public  UserInformationEntityBuilder withId(Integer id) {
    	userInfomationEntity.setId(id);
		return this;
	}
    
    public  UserInformationEntityBuilder withFirstName(String firstName) {
    	userInfomationEntity.setFirstName(firstName);
		return this;
	}
    
	public UserInformationEntityBuilder withMiddleName(String middleName) {
		userInfomationEntity.setMiddleName(middleName);
		return this;
	}
	
	public UserInformationEntityBuilder withLastName(String lastName) {
		userInfomationEntity.setLastName(lastName);
		return this;
	}
	
	public UserInformationEntityBuilder withEmail(String email) {
		userInfomationEntity.setEmail(email);
		return this;
	}
	
	public UserInformationEntityBuilder withPassword(String password) {
		userInfomationEntity.setPassword(password);
		return this;
	}
	
	public UserInformationEntityBuilder withAddress(AddressEntity address) {
		userInfomationEntity.setAddress(address);
		return this;
	}

	public UserInformationEntityBuilder withRegistrationDate(Date registrationDate) {
		userInfomationEntity.setRegistrationDate(registrationDate);
		return this;
	}

	public UserInformationEntityBuilder withBirthDate(Date birthDate) {
		userInfomationEntity.setBirthDate(birthDate);
		return this;
	}

	public UserInformationEntityBuilder withSex(String sex) {
		userInfomationEntity.setSex(sex);
		return this;
	}

	public UserInformationEntityBuilder withMobileNumber(String mobileNumber) {
		userInfomationEntity.setMobileNumber(mobileNumber);
		return this;
	}
	
	public UserInformationEntity build() {
		return userInfomationEntity;
	}
	
}
