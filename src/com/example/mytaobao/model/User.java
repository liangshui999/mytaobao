package com.example.mytaobao.model;

public class User {
	private String userId;//用户账号
	private String passWord;//用户密码
	public User(String userId, String passWord) {
		super();
		this.userId = userId;
		this.passWord = passWord;
	}
	public User() {
		
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	

}
