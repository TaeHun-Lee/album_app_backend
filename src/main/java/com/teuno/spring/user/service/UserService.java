package com.teuno.spring.user.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.teuno.spring.user.dto.UserDTO;
import com.teuno.spring.user.dao.UserDAO;

@Service
public class UserService implements IUserService {
	
	@Autowired
	private UserDAO userdao;
	@Autowired
	private PasswordEncoder pwEncoder;
	
	@Override
	public boolean userSignUp(final UserDTO user) {
		boolean result = false;
		String enc = pwEncoder.encode(user.getUserPw());
		user.setUserPw(enc);
		result = userdao.insertUser(user);
		if(result) {
			UserDTO signingUser = userdao.selectUser(user);
			user.setUserId(signingUser.getUserId());
		}
		return result;
	}
	
	@Override
	public boolean userSignIn(final UserDTO user) {
		boolean result = false;
		UserDTO signingUser = userdao.selectUser(user);
		if (signingUser != null) {
			user.setUserId(signingUser.getUserId());
			user.setUserEmail(signingUser.getUserEmail());
			user.setUserSignedDate(signingUser.getUserSignedDate());
			String enc = signingUser.getUserPw();
			String raw = user.getUserPw();	
			if(pwEncoder.matches(raw, enc)) {
				user.setUserPw(enc);
				result = true;
			}
		}
		return result;
	}

	@Override
	public boolean userModify(final UserDTO user, boolean isSameName) {
		boolean result = false;
		String enc = pwEncoder.encode(user.getUserPw());
		user.setUserPw(enc);
		result = userdao.modifyUser(user, isSameName);
		return result;
	}

	@Override
	public boolean userDelete(final UserDTO user) {
		boolean result = false;
		result = userdao.deleteUser(user);
		if(result) {
			String dst = "/upload/" + user.getUserId();
			File userDir = new File(dst);
			while(userDir.exists() && userDir.isDirectory()) {
				File[] fList = userDir.listFiles();
				for(File f : fList)
					f.delete();
				if(fList.length == 0)
					userDir.delete();
			}
		}
		return result;
	}
	
	@Override
	public List<UserDTO> userList() {
		List<UserDTO> selecteds = userdao.selectUsersForAdmin();
		return selecteds;
	}
	
	
}
