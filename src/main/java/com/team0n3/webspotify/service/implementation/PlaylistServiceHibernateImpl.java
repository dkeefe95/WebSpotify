/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team0n3.webspotify.service.implementation;

import com.team0n3.webspotify.dao.PlaylistDAO;
import com.team0n3.webspotify.model.Playlist;
import com.team0n3.webspotify.model.User;
import com.team0n3.webspotify.service.PlaylistService;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author spike
 */
@Service
@Transactional(readOnly = true)
public class PlaylistServiceHibernateImpl implements PlaylistService{
    @Autowired
    private PlaylistDAO playlistDao;
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    @Transactional(readOnly=false)
    public Playlist createPlaylist(String playlistName, String imagePath, String description, User currentUser) {
        Playlist playlist = new Playlist(playlistName,imagePath,description,currentUser);
        playlistDao.addPlaylist(playlist);
        return playlist;
    }
    
    @Override
    @Transactional(readOnly=true)
    public List<Playlist> listAllPlaylists(){
        return playlistDao.listPlaylists();
    }
    
    @Override
    @Transactional(readOnly=true)
    public Playlist getPlaylistByID(int playlistID){
        return playlistDao.getPlaylist(playlistID);
    }
    
    @Override
    @Transactional(readOnly=false)
    public void deletePlaylist(Playlist p){
        playlistDao.deletePlaylist(p);
    }
}
