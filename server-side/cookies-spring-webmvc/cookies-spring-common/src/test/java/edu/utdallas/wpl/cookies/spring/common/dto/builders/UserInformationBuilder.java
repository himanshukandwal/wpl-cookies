package edu.utdallas.wpl.cookies.spring.common.dto.builders;

import java.util.Date;

import edu.utdallas.wpl.cookies.spring.common.dto.Address;
import edu.utdallas.wpl.cookies.spring.common.dto.UserInformation;

public class UserInformationBuilder {
	
	private UserInformation userInfomationEntity;

    public UserInformationBuilder() {
    	userInfomationEntity = new UserInformation();
	}
	
    public  UserInformationBuilder withFirstName(String firstName) {
    	userInfomationEntity.setFirstName(firstName);
		return this;
	}
    
	public UserInformationBuilder withMiddleName(String middleName) {
		userInfomationEntity.setMiddleName(middleName);
		return this;
	}
	
	public UserInformationBuilder withLastName(String lastName) {
		userInfomationEntity.setLastName(lastName);
		return this;
	}
	
	public UserInformationBuilder withEmail(String email) {
		userInfomationEntity.setEmail(email);
		return this;
	}
	
	public UserInformationBuilder withPassword(String password) {
		userInfomationEntity.setPassword(password);
		return this;
	}
	
	public UserInformationBuilder withAddress(Address address) {
		userInfomationEntity.setAddress(address);
		return this;
	}

	public UserInformationBuilder withRegistrationDate(Date registrationDate) {
		userInfomationEntity.setRegistrationDate(registrationDate);
		return this;
	}

	public UserInformationBuilder withBirthDate(Date birthDate) {
		userInfomationEntity.setBirthDate(birthDate);
		return this;
	}

	public UserInformationBuilder withSex(String sex) {
		userInfomationEntity.setSex(sex);
		return this;
	}

	public UserInformationBuilder withMobileNumber(String mobileNumber) {
		userInfomationEntity.setMobileNumber(mobileNumber);
		return this;
	}
	
	public UserInformation build() {
		return userInfomationEntity;
	}
	
}
