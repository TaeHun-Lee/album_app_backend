package com.teuno.spring.album.dao;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.teuno.spring.album.dto.AlbumDTO;
import com.teuno.spring.mappers.IAlbumMapper;

@Repository
public class AlbumDAO implements IAlbumDAO {

	private IAlbumMapper mapper;
	
	public AlbumDAO(@Autowired SqlSession sqlSession) {
		mapper = sqlSession.getMapper(IAlbumMapper.class);
	}
	
	@Override
	public boolean insertAlbum(AlbumDTO album) {
		boolean res = true;
		Date date = new Date(new java.util.Date().getTime());
		Integer rn = mapper.insertAlbum(album.getAlbumUserId(), album.getAlbumName(), album.getAlbumDesc(), album.getAlbumPictureSource(), album.getAlbumPictureName(), date);
		if (rn != 1)
			res = false;
		return res;
	}

	@Override
	public AlbumDTO selectAlbum(AlbumDTO album) {
		AlbumDTO selected = mapper.selectAlbum(album.getAlbumId());
		return selected;
	}
	
	@Override
	public List<AlbumDTO> selectAlbumForUser(AlbumDTO album) {
		List<AlbumDTO> selecteds = mapper.selectAlbumForUser(album.getAlbumUserId());
		return selecteds;
	}
	
	@Override
	public List<AlbumDTO> selectAlbumForAdmin(){
		List<AlbumDTO> selecteds = mapper.selectAlbumForAdmin();
		return selecteds;
	}

	@Override
	public boolean modifyAlbum(AlbumDTO album) {
		boolean res = true;
		Date date = new Date(new java.util.Date().getTime());
		Integer rn = mapper.modifyAlbum(album.getAlbumName(), album.getAlbumDesc(), album.getAlbumPictureSource(), album.getAlbumPictureName(), date, album.getAlbumId());
		if (rn != 1) {
			res = false;
		}
		return res;
	}

	@Override
	public boolean deleteAlbum(AlbumDTO album) {
		boolean res = true;
		Integer rn = mapper.deleteAlbum(album.getAlbumId());
		if (rn != 1) {
			res = false;
		}
		return res;
	}
}
