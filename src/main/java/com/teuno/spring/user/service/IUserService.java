package com.teuno.spring.user.service;

import java.util.List;

import com.teuno.spring.user.dto.UserDTO;

public interface IUserService {
	public boolean userSignUp(final UserDTO user);
	public boolean userSignIn(final UserDTO user);
	public boolean userModify(final UserDTO user, boolean isSameName);
	public boolean userDelete(final UserDTO user);
	public List<UserDTO> userList();
}
