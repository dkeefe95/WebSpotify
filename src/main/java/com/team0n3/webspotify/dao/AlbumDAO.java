
package com.team0n3.webspotify.dao;

import java.util.List;
import com.team0n3.webspotify.model.Album;

public interface AlbumDAO {
  
  public void addAlbum(Album album);

  public Album getAlbum(int albumId);

  public List<Album> listAlbums();

  public void deleteAlbum(Album album);

  public void updateAlbum(Album album);
  
  public List<Album> search(String keyword, boolean limit);
  
  public List<Album> getTopAlbums();
  
  public List<Album> getNewAlbums();
  
  public List<Album> getNotFollowedAlbums(String username);
}
