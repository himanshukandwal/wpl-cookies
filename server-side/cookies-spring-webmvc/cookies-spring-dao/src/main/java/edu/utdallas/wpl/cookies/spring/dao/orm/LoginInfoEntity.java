package edu.utdallas.wpl.cookies.spring.dao.orm;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity(name = "login_info")
@IdClass(LoginPk.class)
public class LoginInfoEntity {
	
	@Id
	private UserInformationEntity userInfo;
	
	@Column(name = "LOGIN_TIME", length = 6)
	private Date loginTime;
	
	@Column(name = "SESSION_ID", length = 20)
	private String sessionId;
	
	@Id
	private String deviceName;
	
	@Column(name = "LOCATION_COORD", length = 20)
	private String locationCord;

	

	public UserInformationEntity getUserId() {
		return userInfo;
	}

	public void setUserId(UserInformationEntity userId) {
		this.userInfo = userId;
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

	public String getLocationCord() {
		return locationCord;
	}

	public void setLocationCord(String locationCord) {
		this.locationCord = locationCord;
	}

}
