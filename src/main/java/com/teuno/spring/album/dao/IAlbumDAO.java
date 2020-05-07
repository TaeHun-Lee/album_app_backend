package com.teuno.spring.album.dao;

import java.util.List;

import com.teuno.spring.album.dto.AlbumDTO;
public interface IAlbumDAO {
	public boolean insertAlbum(final AlbumDTO album);
	public AlbumDTO selectAlbum(final AlbumDTO album);
	public List<AlbumDTO> selectAlbumForUser(final AlbumDTO album);
	public List<AlbumDTO> selectAlbumForAdmin();
	public boolean modifyAlbum(final AlbumDTO album);
	public boolean deleteAlbum(final AlbumDTO album);
}
