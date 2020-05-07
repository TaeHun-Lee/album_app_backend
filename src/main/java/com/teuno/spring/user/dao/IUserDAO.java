package com.teuno.spring.user.dao;

import java.util.List;

import com.teuno.spring.user.dto.UserDTO;

public interface IUserDAO {
	boolean checkUserInstance(final UserDTO user);
	boolean insertUser(final UserDTO user);
	boolean modifyUser(final UserDTO user, boolean isSameName);
	boolean deleteUser(final UserDTO user);
	UserDTO selectUser(final UserDTO user);
	List<UserDTO> selectUsersForAdmin();
}
