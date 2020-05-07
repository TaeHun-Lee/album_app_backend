package com.teuno.spring.user.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.teuno.spring.user.dto.UserDTO;
import com.teuno.spring.user.service.UserService;

@Controller
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	public UserDTO userSignedIn(HttpSession session) {
		UserDTO user = (UserDTO)session.getAttribute("signedUser");
		return user;
	}
	
	public boolean isAdmin(HttpSession session) {
		boolean isAdmin = false;
		UserDTO candi = (UserDTO)session.getAttribute("signedUser");
		if(candi.getUserName().equals("dogbook7")) {
			isAdmin = true;
		}
		return isAdmin;
	}
	
	@ResponseBody
	@RequestMapping(value="/checkUserSession")
	public HashMap<String, Object> checkUserSession(HttpSession session){
		HashMap<String, Object> map = new HashMap<String, Object>();
		UserDTO signedInUser = userSignedIn(session);
		if(signedInUser != null) {
			if(isAdmin(session)) {
				map.put("AUTH", "ADMIN");
			}
			else {
				UserDTO clone = null;
				try {
					clone = (UserDTO)signedInUser.clone();
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
				clone.setUserPw(null);
				map.put("AUTH", clone);
			}
		}
		else {
			map.put("AUTH", null);
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/userSignUp", method=RequestMethod.POST)
	public HashMap<String, Object> userSignUp(@RequestBody UserDTO user, HttpSession session) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		UserDTO signedInUser = userSignedIn(session);
		if(signedInUser != null) {
			map.put("AUTH", "Already Signed In");
			return map;
		}
		if(userService.userSignUp(user)) {
			UserDTO clone = null;
			try {
				clone = (UserDTO)user.clone();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
			session.setAttribute("signedUser", clone);
			if(isAdmin(session)) {
				map.put("AUTH", "ADMIN");
			}
			else {
				user.setUserPw(null);
				map.put("AUTH", user);	
			}
		}
		else {
			map.put("AUTH", null);
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/userSignIn", method=RequestMethod.POST)
	public HashMap<String, Object> userSignIn(@RequestBody UserDTO user, HttpSession session) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		UserDTO signedInUser = userSignedIn(session);
		if(signedInUser != null) {
			map.put("AUTH", "Already Signed In");
			return map;
		}
		if(userService.userSignIn(user)) {
			UserDTO clone = null;
			try {
				clone = (UserDTO)user.clone();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
			session.setAttribute("signedUser", clone);
			if(isAdmin(session)) {
				map.put("AUTH", "ADMIN");
			}
			else {
				user.setUserPw(null);
				map.put("AUTH", user);	
			}
		}
		else {
			map.put("AUTH", null);
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/userModify", method=RequestMethod.POST)
	public HashMap<String, Object> userModify(@RequestBody UserDTO user, HttpSession session) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		UserDTO signedInUser = userSignedIn(session);
		boolean result = false;
		if(signedInUser == null) {
			map.put("AUTH", "Not Signed In");
			return map;
		}
		user.setUserId(signedInUser.getUserId());
		if(signedInUser.getUserName().equals(user.getUserName())) {
			result = userService.userModify(user, true);
		}
		else {
			result = userService.userModify(user, false);
		}
		if(result == false) {
			map.put("AUTH", null);
		}
		else {
			UserDTO clone = null;
			try {
				clone = (UserDTO)user.clone();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
			session.setAttribute("signedUser", clone);
			user.setUserPw(null);
			map.put("AUTH", user);
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/userDelete", method=RequestMethod.GET)
	public HashMap<String, Object> userDelete(HttpSession session) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		UserDTO signedInUser = userSignedIn(session);
		if(signedInUser == null) {
			map.put("Message", "Not Signed In");
			return map;
		}
		boolean result = userService.userDelete(signedInUser);
		if(result == false) {
			map.put("Message", "Delete Error");
		}
		else {
			map.put("Message", "Delete Success");
			session.invalidate();
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/userDeleteForAdmin", method=RequestMethod.POST)
	public HashMap<String, Object> userDelete(@RequestBody UserDTO user, HttpSession session) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if(isAdmin(session)) {
			boolean result = userService.userDelete(user);
			if(result == false) {
				map.put("Message", "Delete Error");
			}
			else {
				map.put("Message", "Delete Success");
			}
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/userSignOut")
	public HashMap<String, Object> userSignOut(HttpSession session) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		UserDTO signedInUser = userSignedIn(session);
		if(signedInUser != null) {
			session.invalidate();
			map.put("Message", "SignOut Success");
		}
		else {
			map.put("Message", "Not Signed In");
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/userListForAdmin")
	public List<UserDTO> userList(HttpSession session) {
		List<UserDTO> userList = null;
		if(isAdmin(session)) {
			 userList = userService.userList();
		}
		return userList;
	}
}
