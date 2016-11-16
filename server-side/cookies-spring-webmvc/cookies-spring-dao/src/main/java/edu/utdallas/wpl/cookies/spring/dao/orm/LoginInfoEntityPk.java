package edu.utdallas.wpl.cookies.spring.dao.orm;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

public class LoginInfoEntityPk implements Serializable {

	private static final long serialVersionUID = 1L;

	@OneToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "USER_ID")
	private UserInformationEntity user;
	
	@Column(name = "DEVICE_NAME")
	private String deviceName;

	public LoginInfoEntityPk() {}
	
	public LoginInfoEntityPk(String loginId, UserInformationEntity user, String deviceName) {
		super();
		this.user = user;
		this.deviceName = deviceName;
	}

}
