package com.teuno.spring.user.dao;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.teuno.spring.mappers.IUserMapper;
import com.teuno.spring.user.dto.UserDTO;

@Repository
public class UserDAO implements IUserDAO {

	private IUserMapper mapper;
	
	public UserDAO(@Autowired SqlSession sqlSession) {
		mapper = sqlSession.getMapper(IUserMapper.class);
	}

	@Override
	public boolean checkUserInstance(UserDTO user) {
		boolean res = false;
		Integer rn = mapper.checkUserInstance(user.getUserName());
		if (rn == 1)
			res = true;
		return res;
	}

	@Override
	public boolean insertUser(UserDTO user) {
		boolean res = true;
		Date date = new Date(new java.util.Date().getTime());
		if(checkUserInstance(user)) {
			res = false;
			return res;
		}
		else {
			user.setUserSignedDate(date);
			Integer rn = mapper.insertUser(user.getUserName(), user.getUserPw(), user.getUserEmail(), user.getUserSignedDate());
			if (rn != 1)
				res = false;
		}
		return res;
	}

	@Override
	public boolean modifyUser(UserDTO user, boolean isSameName) {
		boolean res = true;
		Date date = new Date(new java.util.Date().getTime());
		if(!isSameName) {
			if(checkUserInstance(user)) {
				res = false;
				return res;
			}
		}
		user.setUserSignedDate(date);
		Integer rn = mapper.modifyUser(user.getUserName(), user.getUserPw(), user.getUserEmail(), user.getUserSignedDate(), user.getUserId());
		if (rn != 1) {
			res = false;
		}
		return res;
	}

	@Override
	public boolean deleteUser(UserDTO user) {
		boolean res = true;
		Integer rn = mapper.deleteUser(user.getUserName(), user.getUserPw());
		if (rn != 1) {
			res = false;
		}
		return res;
	}

	@Override
	public UserDTO selectUser(UserDTO user) {
		UserDTO selected = mapper.selectUser(user.getUserName());
		return selected;
	}
	
	@Override
	public List<UserDTO> selectUsersForAdmin(){
		List<UserDTO> selecteds = mapper.selectUsersForAdmin();
		return selecteds;
	}
	
	
}
