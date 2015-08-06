package com.skyline.trumpet.backend.model;

public class User {
	private long id;
	private String userName;
	private String password;
	private String email;
	private String phone;
	private String avaterUrl;

	public String getAvaterUrl() {
	    return avaterUrl;
	}

	public void setAvaterUrl(String avaterUrl) {
	    this.avaterUrl = avaterUrl;
	}
	
	public User(){}
	
	public User(String userName, String password)
	{
		this.userName = userName;
		this.password = password;
	}
	
	public User(String userName, String password, String email, String phone){
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }
	
	public User(String userName, String password, String email, String phone, String avaterUrl){
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.avaterUrl = avaterUrl;
    }
	
	public User(long id, String userName, String password, String email, String phone){
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.phone = phone;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
