package com.teuno.spring.mappers;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.teuno.spring.album.dto.AlbumDTO;

public interface IAlbumMapper {
	@Insert("INSERT INTO ALBUM(albumUserId, albumName, albumDesc, albumPictureSource, albumPictureName, albumDate) VALUES(#{albumUserId}, #{albumName}, #{albumDesc}, #{albumPictureSource}, #{albumPictureName}, #{albumDate})")
	Integer insertAlbum(@Param("albumUserId") Integer albumUserId, @Param("albumName") String albumName, @Param("albumDesc") String albumDesc, @Param("albumPictureSource") String albumPictureSource, @Param("albumPictureName") String albumPictureName, @Param("albumDate") Date albumDate);
	@Select("SELECT * FROM ALBUM WHERE albumId=#{albumId}")
	AlbumDTO selectAlbum(@Param("albumId") Integer albumId);
	@Select("SELECT * FROM ALBUM WHERE albumUserId=#{albumUserId}")
	List<AlbumDTO> selectAlbumForUser(@Param("albumUserId") Integer albumUserId);
	@Select("SELECT * FROM ALBUM")
	List<AlbumDTO> selectAlbumForAdmin();
	@Update("UPDATE ALBUM SET albumName=#{albumName}, albumDesc=#{albumDesc}, albumPictureSource=#{albumPictureSource}, albumPictureName=#{albumPictureName}, albumDate=#{albumDate} WHERE albumId=#{albumId}")
	Integer modifyAlbum(@Param("albumName") String albumName, @Param("albumDesc") String albumDesc, @Param("albumPictureSource") String albumPictureSource, @Param("albumPictureName") String albumPictureName, @Param("albumDate") Date albumDate, @Param("albumId") Integer albumId);
	@Delete("DELETE FROM ALBUM WHERE albumId=#{albumId}")
	Integer deleteAlbum(@Param("albumId") Integer albumId);
}
