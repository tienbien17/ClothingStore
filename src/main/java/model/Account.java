package model;

import java.sql.Date;

public class Account {
	private int id;
	private String fullName;
	private String userName;
	private String email;
	private String phoneNumber;
	private String address;
	private boolean active;
	private String password;
	private int userRoleCode;
	private String createAt;
	public Account() {
		super();
	}
	public Account(int user_id, String full_name, String username, String password, String address, String phone_number,
			String created_at, String email, boolean active, int role_code) {
		super();
		this.id = user_id;
		this.fullName = full_name;
		this.userName = username;
		this.password = password;
		this.address = address;
		this.phoneNumber = phone_number;
		this.createAt = created_at;
		this.email = email;
		this.active = active;
		this.userRoleCode = role_code;
	}
	
	
	
	

	public Account(String full_name, String username, String password, String address, String phone_number,
			String created_at, String email, int role_code) {
		super();
		this.fullName = full_name;
		this.userName = username;
		this.password = password;
		this.address = address;
		this.phoneNumber = phone_number;
		this.createAt = created_at;
		this.email = email;
		this.userRoleCode = role_code;
	}
	public Account(int id, String userName, boolean active, String password, String userRole) {
		super();
		this.id = id;
		this.userName = userName;
		this.active = active;
		this.password = password;
		this.userRoleCode = userRoleCode;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	
	public int getUserRoleCode() {
		return userRoleCode;
	}
	public void setUserRoleCode(int userRoleCode) {
		this.userRoleCode = userRoleCode;
	}
	public Account(String fullName, String userName, String email, String phoneNumber, String address, boolean active,
			String password, int userRoleCode, String createAt) {
		super();
		this.fullName = fullName;
		this.userName = userName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.active = active;
		this.password = password;
		this.userRoleCode = userRoleCode;
		this.createAt = createAt;
	}
	
	public String getCreateAt() {
		return createAt;
	}
	public void setCreateAt(String createAt) {
		this.createAt = createAt;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
