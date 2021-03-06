
package com.team0n3.webspotify.dao;

import java.util.List;
import com.team0n3.webspotify.model.Playlist;

public interface PlaylistDAO {
  
  public void addPlaylist(Playlist playlist);

  public Playlist getPlaylist(int playlistID);
  public List<Playlist> listPlaylists();
  public void deletePlaylist(Playlist playlist);
  public void updatePlaylist(Playlist playlist);
  public List<Playlist> search(String keyword, boolean limit);
  public List<Playlist> getTopPlaylists();
}
