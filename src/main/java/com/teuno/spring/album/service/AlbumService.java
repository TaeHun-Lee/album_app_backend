package com.teuno.spring.album.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
/*
Byte64 이미지용 패키지
import java.io.FileInputStream;
import java.util.Base64;
import org.apache.commons.io.IOUtils;
*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.teuno.spring.album.dao.AlbumDAO;
import com.teuno.spring.album.dto.AlbumDTO;

@Service
public class AlbumService implements IAlbumService {
	
	@Autowired
	AlbumDAO albumdao;
	private static final String SAVE_PATH = "/upload/";

	@Override
	public boolean insertAlbum(AlbumDTO album) {
		boolean res = false;
		
		MultipartFile file = album.getAlbumPictureFile();
		if(file != null) {
			String dirPath = SAVE_PATH + album.getAlbumUserId() + "/";
			String originFileName = file.getOriginalFilename();
			if (originFileName != null && !originFileName.equals("")) {
				String ext = originFileName.substring(originFileName.lastIndexOf("."), originFileName.length());
				String fileName = System.currentTimeMillis() + ext;
				try {
					File dest = new File(dirPath, fileName);
					if(!dest.exists()) dest.mkdirs();
					file.transferTo(dest);
					album.setAlbumPictureName(originFileName);
					album.setAlbumPictureSource(dirPath+fileName);
				} catch (Exception e) {
					System.err.println(e);
				}
			}
		}
		
		if(albumdao.insertAlbum(album))
			res = true;
		return res;
	}

	@Override
	public boolean modifyAlbum(AlbumDTO album) {
		boolean res = false;
		
		if(album.getAlbumPictureSource() != null) {
			File origin = new File(album.getAlbumPictureSource());
			if(origin.exists()) {
				origin.delete();
				album.setAlbumPictureSource(null);
			}
		}
		
		MultipartFile file = album.getAlbumPictureFile();
		if(file != null) {
			String dirPath = SAVE_PATH + album.getAlbumUserId() + "/";
			String originFileName = file.getOriginalFilename();
			if (originFileName != null && !originFileName.equals("")) {
				String ext = originFileName.substring(originFileName.lastIndexOf("."), originFileName.length());
				String fileName = System.currentTimeMillis() + ext;
				try {
					File dest = new File(dirPath, fileName);
					if(!dest.exists()) dest.mkdirs();
					file.transferTo(dest);
					album.setAlbumPictureName(originFileName);
					album.setAlbumPictureSource(dirPath+fileName);
				} catch (Exception e) {
					System.err.println(e);
				}
			}
		}
		if(albumdao.modifyAlbum(album))
			res = true;
		return res;
	}

	@Override
	public AlbumDTO selectAlbum(AlbumDTO album) {
		AlbumDTO selected = albumdao.selectAlbum(album);
		return selected;
	}
	
	@Override
	public List<AlbumDTO> selectAlbumForUser(AlbumDTO album) throws IOException {
		List<AlbumDTO> selecteds = albumdao.selectAlbumForUser(album);
		/*
		 이하 Base64 인코딩 방식으로 이미지 전송하는 방식
		 (번잡해서 대체함)
		 */
//		for(AlbumDTO var : selecteds) {
//			String source = var.getAlbumPictureSource();
//			System.out.println("유저 : " + var.getAlbumUserId());
//			System.out.println("소스 : " + var.getAlbumPictureSource());
//			File file = new File(source);
//			FileInputStream in = null;
//			byte[] data = null;
//			String byteData = null;
//			if(file.exists()) {
//				System.out.println("파일 존재");
//				try {
//					in = new FileInputStream(file);
//					data = IOUtils.toByteArray(in);
//					byteData = Base64.getEncoder().encodeToString(data);
//					var.setAlbumImageByteData(byteData);
//				} catch (Exception e) {
//					e.printStackTrace();
//				} finally {
//					in.close();
//				}
//			}
//		}
		return selecteds;
	}
	
	@Override
	public List<AlbumDTO> selectAlbumForAdmin(){
		List<AlbumDTO> selecteds = albumdao.selectAlbumForAdmin();
		return selecteds;
	}

	@Override
	public boolean deleteAlbum(AlbumDTO album) {
		boolean res = false;
		
		if(album.getAlbumPictureSource() != null) {
			File origin = new File(album.getAlbumPictureSource());
			if(origin.exists()) {
				origin.delete();
			}
		}
		
		if(albumdao.deleteAlbum(album))
			res = true;
		return res;
	}
}
