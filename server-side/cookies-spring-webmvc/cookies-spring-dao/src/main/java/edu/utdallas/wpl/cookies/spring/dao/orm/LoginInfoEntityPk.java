package edu.utdallas.wpl.cookies.spring.dao.orm;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Embeddable
public class LoginInfoEntityPk implements Serializable {

	private static final long serialVersionUID = 1L;

	@OneToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "USER_ID")
	private UserInformationEntity user;
	
	@Column(name = "DEVICE_NAME")
	private String deviceName;

	public LoginInfoEntityPk() {}
	
	public LoginInfoEntityPk(UserInformationEntity user, String deviceName) {
		super();
		this.user = user;
		this.deviceName = deviceName;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof LoginInfoEntityPk 
				&& ((LoginInfoEntityPk) obj).deviceName.equals(deviceName) 
				&& ((LoginInfoEntityPk) obj).user == user)
			return true;
		return false;
	}

	@Override
	public int hashCode() {
		return user.hashCode() * 31 + deviceName.hashCode();
	}
	
	public UserInformationEntity getUser() {
		return user;
	}

	public void setUser(UserInformationEntity user) {
		this.user = user;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
}
