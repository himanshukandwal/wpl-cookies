package edu.utdallas.wpl.cookies.spring.dao.orm;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name = "login_info")
public class LoginInfoEntity {
	
	@EmbeddedId
	private LoginInfoEntityPk loginInfoEntityPk;
	
	@Column(name = "LOGIN_TIME", length = 6)
	private Date loginTime;
	
	@Column(name = "SESSION_ID", length = 20)
	private String sessionId;
	
	@Column(name = "LOCATION_COORD", length = 20)
	private String locationCoordinates;

	public LoginInfoEntityPk getLoginInfoEntityPk() {
		return loginInfoEntityPk;
	}
	
	public void setLoginInfoEntityPk(LoginInfoEntityPk loginInfoEntityPk) {
		this.loginInfoEntityPk = loginInfoEntityPk;
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

	public String getLocationCoordinates() {
		return locationCoordinates;
	}

	public void setLocationCoordinates(String locationCord) {
		this.locationCoordinates = locationCord;
	}

}
