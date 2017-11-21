
package com.team0n3.webspotify.dao.implementation;

import java.util.List;
import com.team0n3.webspotify.dao.PlaylistDAO;
import com.team0n3.webspotify.model.Playlist;
import com.team0n3.webspotify.model.Song;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class PlaylistDAOHibernateImpl implements PlaylistDAO{
    
    @Autowired
    private SessionFactory sessionFactory;
    public PlaylistDAOHibernateImpl(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }
    
    @Override
    public void addPlaylist(Playlist playlist){
        sessionFactory.getCurrentSession().persist(playlist);
    }
    
    @Override
    public Playlist getPlaylist(int playlistID){
        Playlist p = (Playlist)sessionFactory.getCurrentSession().get(Playlist.class,playlistID);
        return p;
    }
    
    @Override
    public List<Playlist> listPlaylists(){
        List<Playlist> playlistList = sessionFactory.getCurrentSession().createCriteria(Playlist.class).list();
        return playlistList;
    }
    
    @Override
    public void deletePlaylist(Playlist playlist){
        sessionFactory.getCurrentSession().delete(playlist);
    }
    
    @Override
    public void updatePlaylist(Playlist playlist){
        sessionFactory.getCurrentSession().update(playlist);
    }
}
