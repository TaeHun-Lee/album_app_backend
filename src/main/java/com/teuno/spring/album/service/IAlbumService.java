package com.teuno.spring.album.service;

import java.io.IOException;
import java.util.List;

import com.teuno.spring.album.dto.AlbumDTO;

public interface IAlbumService {
	public boolean insertAlbum(final AlbumDTO album);
	public boolean modifyAlbum(final AlbumDTO album);
	public AlbumDTO selectAlbum(final AlbumDTO album);
	public List<AlbumDTO> selectAlbumForUser(final AlbumDTO album) throws IOException;
	public List<AlbumDTO> selectAlbumForAdmin();
	public boolean deleteAlbum(final AlbumDTO album);
}
