
package com.team0n3.webspotify.dao.implementation;

import com.team0n3.webspotify.dao.AlbumDAO;
import com.team0n3.webspotify.model.Album;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class AlbumDAOHibernateImpl implements AlbumDAO{
    
    @Autowired
    private SessionFactory sessionFactory;
    public AlbumDAOHibernateImpl(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }
    
    @Override
    public void addAlbum(Album album){
        sessionFactory.getCurrentSession().persist(album);
    }
    
    @Override
    public Album getAlbum(int albumId){
        Album a = (Album)sessionFactory.getCurrentSession().get(Album.class,albumId);
        return a;
    }
    
    @Override
    public List<Album> listAlbums(){
        List<Album> albumList = sessionFactory.getCurrentSession().createCriteria(Album.class).list();
        return albumList;
    }
    
    @Override
    public void deleteAlbum(Album album){
        sessionFactory.getCurrentSession().delete(album);
    }
    
    @Override
    public void updateAlbum(Album album){
        sessionFactory.getCurrentSession().update(album);
    }
    
}
