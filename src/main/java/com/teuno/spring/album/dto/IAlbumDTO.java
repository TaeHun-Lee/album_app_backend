package com.teuno.spring.album.dto;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public interface IAlbumDTO {
	public Integer getAlbumId();
	public void setAlbumId(Integer albumId);
	public Integer getAlbumUserId();
	public void setAlbumUserId(Integer albumUserId);
	public String getAlbumName();
	public void setAlbumName(String albumName);
	public String getAlbumDesc();
	public void setAlbumDesc(String albumDesc);
	public Date getAlbumDate();
	public void setAlbumDate(Date albumDate);
	public String getAlbumPictureSource();
	public void setAlbumPictureSource(String albumPictureSource);
	public String getAlbumPictureName();
	public void setAlbumPictureName(String albumPictureName);
	public MultipartFile getAlbumPictureFile();
	public void setAlbumPictureFile(MultipartFile albumPictureFile);
	public String getAlbumImageByteData();
	public void setAlbumImageByteData(String albumImageByteData);
}
