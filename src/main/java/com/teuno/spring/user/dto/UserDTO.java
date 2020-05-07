package com.teuno.spring.user.dto;

import java.sql.Date;

public class UserDTO implements IUserDTO, Cloneable {
	
	private Integer userId;
	private String userName;
	private String userPw;
	private String userEmail;
	private Date userSignedDate;

	@Override
	public Integer getUserId() {
		return this.userId;
	}
	
	@Override
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String getUserName() {
		return this.userName;
	}
	
	@Override
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Override
	public String getUserPw() {
		return this.userPw;
	}

	@Override
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	@Override
	public String getUserEmail() {
		return this.userEmail;
	}

	@Override
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@Override
	public Date getUserSignedDate() {
		return this.userSignedDate;
	}

	@Override
	public void setUserSignedDate(Date userSignedDate) {
		this.userSignedDate = userSignedDate;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
