package com.kp.admin.bean;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("NativeUsers")
public class NativeUsers {

	private String id;
	private String tenantId;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String displayName;
    private String password;
    private List<String> adminRoles;
    
    
    
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
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
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public List<String> getAdminRoles() {
		return adminRoles;
	}
	public void setAdminRoles(List<String> adminRoles) {
		this.adminRoles = adminRoles;
	}

}
