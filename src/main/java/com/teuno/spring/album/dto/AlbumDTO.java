package com.teuno.spring.album.dto;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class AlbumDTO implements IAlbumDTO {
	
	private Integer albumId;
	private Integer albumUserId;
	private String albumName;
	private String albumDesc;
	private String albumPictureSource;
	private String albumPictureName;
	private MultipartFile albumPictureFile;
	private String albumImageByteData;
	private Date albumDate;
	
	@Override
	public Integer getAlbumId() {
		return this.albumId;
	}
	
	@Override
	public void setAlbumId(Integer albumId) {
		this.albumId = albumId;
	}
	
	@Override
	public Integer getAlbumUserId() {
		return this.albumUserId;
	}
	
	@Override
	public void setAlbumUserId(Integer albumUserId) {
		this.albumUserId = albumUserId;
	}
	
	@Override
	public String getAlbumName() {
		return this.albumName;
	}

	@Override
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	@Override
	public String getAlbumDesc() {
		return this.albumDesc;
	}

	@Override
	public void setAlbumDesc(String albumDesc) {
		this.albumDesc = albumDesc;
	}

	@Override
	public Date getAlbumDate() {
		return this.albumDate;
	}

	@Override
	public void setAlbumDate(Date albumDate) {
		this.albumDate = albumDate;
	}

	@Override
	public String getAlbumPictureSource() {
		return this.albumPictureSource;
	}

	@Override
	public void setAlbumPictureSource(String albumPictureSource) {
		this.albumPictureSource = albumPictureSource;
	}
	
	@Override
	public String getAlbumPictureName() {
		return this.albumPictureName;
	}
	
	@Override
	public void setAlbumPictureName(String albumPictureName) {
		this.albumPictureName = albumPictureName;
	}
	
	@Override
	public MultipartFile getAlbumPictureFile() {
		return this.albumPictureFile;
	}
	
	@Override
	public void setAlbumPictureFile(MultipartFile albumPictureFile) {
		this.albumPictureFile = albumPictureFile;
	}

	@Override
	public String getAlbumImageByteData() {
		return this.albumImageByteData;
	}

	@Override
	public void setAlbumImageByteData(String albumImageByteData) {
		this.albumImageByteData = albumImageByteData;
	}
	
}
