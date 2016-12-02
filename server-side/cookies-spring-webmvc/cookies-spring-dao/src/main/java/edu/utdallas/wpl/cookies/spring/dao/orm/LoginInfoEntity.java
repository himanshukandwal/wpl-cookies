package edu.utdallas.wpl.cookies.spring.dao.orm;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity(name = "login_info")
@IdClass(LoginInfoEntityPk.class)
public class LoginInfoEntity implements Serializable{
	
	@Id
	private UserInformationEntity user;
	
	@Id
	private String deviceName;
	
	@Column(name = "LOGIN_TIME", length = 6)
	private Date loginTime;
	
	@Column(name = "SESSION_ID", length = 20)
	private String sessionId;
	
	@Column(name = "LOCATION_COORD", length = 20)
	private String locationCoordinates;

	public UserInformationEntity getUser() {
		return user;
	}

	public void setUser(UserInformationEntity user) {
		this.user = user;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getLocationCoordinates() {
		return locationCoordinates;
	}

	public void setLocationCoordinates(String locationCord) {
		this.locationCoordinates = locationCord;
	}

}
