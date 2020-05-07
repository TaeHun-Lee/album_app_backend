package com.teuno.spring.album.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.teuno.spring.album.dto.AlbumDTO;
import com.teuno.spring.album.service.AlbumService;
import com.teuno.spring.user.dto.UserDTO;

@Controller
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/album")
public class AlbumController {
	
	@Autowired
	AlbumService albumService;
	
	private UserDTO authCheck(HttpSession session) {
		UserDTO signedUser = (UserDTO)session.getAttribute("signedUser");
		return signedUser;
	}
	
	@ResponseBody
	@RequestMapping(value="/regist", method=RequestMethod.POST, consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public HashMap<String, Object> registAlbum(AlbumDTO album, HttpSession session) {
		System.out.println(album.getAlbumName());
		HashMap<String, Object> map = new HashMap<String, Object>();
		UserDTO signedUser = authCheck(session);
		if(signedUser == null)
			map.put("Message", "Not Signed In");
		else {
			Integer userId = signedUser.getUserId();
			album.setAlbumUserId(userId);
			if(albumService.insertAlbum(album)) {
				map.put("Message", "Album Registered");
			}
			else {
				map.put("Message", "Album Register Fail");
			}
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/regist", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public HashMap<String, Object> registAlbum(@RequestBody AlbumDTO album, HttpServletRequest req) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		UserDTO signedUser = authCheck(req.getSession());
		if(signedUser == null)
			map.put("Message", "Not Signed In");
		else {
			Integer userId = signedUser.getUserId();
			album.setAlbumUserId(userId);
			if(albumService.insertAlbum(album)) {
				map.put("Message", "Album Registered");
			}
			else {
				map.put("Message", "Album Register Fail");
			}
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/albumList", method=RequestMethod.GET)
	public HashMap<String, Object> getAlbumList(HttpSession session) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		UserDTO signedUser = authCheck(session);
		List<AlbumDTO> albums = null;
		if(signedUser == null) {
			map.put("Message", "Not Signed In");
		}
		else {
			AlbumDTO album = new AlbumDTO();
			album.setAlbumUserId(signedUser.getUserId());
			try {
				albums = albumService.selectAlbumForUser(album);
				map.put("AlbumList", albums);
			} catch (IOException e) {
				map.put("Message", "Error in Getting Album List");
				e.printStackTrace();
			}
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/albumListForAdmin", method=RequestMethod.GET)
	public List<AlbumDTO> getAlbumListForAdmin(HttpSession session) {
		List<AlbumDTO> albums = null;
		UserDTO candi = (UserDTO)session.getAttribute("signedUser");
		if(candi.getUserName().equals("dogbook7")) {
			albums = albumService.selectAlbumForAdmin();	
		}
		return albums;
	}
	
	@ResponseBody
	@RequestMapping(value="/modify", method=RequestMethod.POST, consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public HashMap<String, Object> modifyAlbum(AlbumDTO album, HttpSession session){
		HashMap<String, Object> map = new HashMap<String, Object>();
		UserDTO signedUser = authCheck(session);
		if(signedUser == null)
			map.put("Message", "Not Signed In");
		else {
			Integer userId = signedUser.getUserId();
			album.setAlbumUserId(userId);
			if(albumService.modifyAlbum(album)) {
				map.put("Message", "Album Modified");
			}
			else {
				map.put("Message", "Album Modify Fail");
			}
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/modify", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public HashMap<String, Object> modifyAlbum(@RequestBody AlbumDTO album, HttpServletRequest req){
		HashMap<String, Object> map = new HashMap<String, Object>();
		UserDTO signedUser = authCheck(req.getSession());
		if(signedUser == null)
			map.put("Message", "Not Signed In");
		else {
			Integer userId = signedUser.getUserId();
			album.setAlbumUserId(userId);
			if(albumService.modifyAlbum(album)) {
				map.put("Message", "Album Modified");
			}
			else {
				map.put("Message", "Album Modify Fail");
			}
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public HashMap<String, Object> deleteAlbum(@RequestBody AlbumDTO album, HttpSession session) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		UserDTO signedUser = authCheck(session);
		if(signedUser == null)
			map.put("Message", "Not Signed In");
		else {
			Integer userId = signedUser.getUserId();
			album.setAlbumUserId(userId);
			if(albumService.deleteAlbum(album)) {
				map.put("Message", "Album Deleted");
			}
			else {
				map.put("Message", "Album Delete Fail");
			}
		}
		return map;
	}
}
