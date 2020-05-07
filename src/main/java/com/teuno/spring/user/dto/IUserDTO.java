package com.teuno.spring.user.dto;

import java.sql.Date;

public interface IUserDTO {
	Integer getUserId();
	void setUserId(Integer userId);
	String getUserName();
	void setUserName(String userName);
	String getUserPw();
	void setUserPw(String userPw);
	String getUserEmail();
	void setUserEmail(String userEmail);
	Date getUserSignedDate();
	void setUserSignedDate(Date userSignedDate);
}
