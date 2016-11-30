package edu.utdallas.wpl.cookies.spring.dao.orm;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity(name = "userinfo")
public class UserInformationEntity {
	
    @Id
    @Basic(optional = false)
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userinfo_seq")
	@SequenceGenerator(name = "userinfo_seq", sequenceName = "userinfo_sequence")
    private Integer id;

    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;
    
    @Column(name = "middle_name", length = 20)
    private String middleName;

    @Column(name = "last_name", length = 20)
    private String lastName;
    
    @Column(name = "email", nullable = false, length = 100)
    private String email;
    
    @Column(name = "password", length = 20)
    private String password;
   
	@OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn (name = "address_id")
    private AddressEntity address;
    
    @Column(name = "registration_date", length = 20)
    private Date registrationDate;
    
    @Column(name = "birth_date", length = 20)
    private Date birthDate;
    
    @Column(name = "sex", length = 20)
    private String sex;
    
    @Column(name = "mobile_number", length = 20)
    private String mobileNumber;
    @Column(name="role_id")
    
    private Integer roleId;
    
    @Column(name = "LAST_LOGIN")
	private Float lastLogin;
    
	@Column(name = "LOCATION_NAME")
	private String locationName;

	public Float getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Float lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
    
    
	
    public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AddressEntity getAddress() {
		return address;
	}

	public void setAddress(AddressEntity address) {
		this.address = address;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
 
}
