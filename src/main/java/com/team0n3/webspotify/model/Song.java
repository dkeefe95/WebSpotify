/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team0n3.webspotify.model;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 *
 * @author JSCHA
 */
@Entity
@Table(name="songs")
public class Song implements Serializable {
    @Id
    @Column(name="songid",nullable=false)
    @GeneratedValue
    private int songId;
    
    @Column(name="title",nullable=false)
    private String title;
    
   // @Column(name="artistId",nullable=false)
    //private int artistId;
    
   // @Column(name="albumId",nullable=false)
   // private int albumId;
    
    @ManyToOne
    @JoinColumn(name="albumId",referencedColumnName="aid",nullable=false)
    private Album albumId;
    
    @ManyToMany(cascade ={CascadeType.ALL })
    @JoinTable(
            name="songplaylist",
            joinColumns= {@JoinColumn(name="songid")},
            inverseJoinColumns = {@JoinColumn(name="playlistID")}
    ) 
    private Collection<Playlist> containedInPlaylists;
    
    public Song() {
    }
    
    public Song(String title) {
       this.title = title;
    }
    
    public Collection<Playlist> getContainedInPlaylists(){
        return containedInPlaylists;
    }
    
    public void setContainedInPlaylists( Collection<Playlist> containedInPlaylists){
        this.containedInPlaylists = containedInPlaylists;
    }
    
    public int getSongId() {
        return songId;
    }
    
    public void setSongId(int songId) {
        this.songId = songId;
    }
    
  /*  public int getArtistId() {
        return artistId;
    }
    
    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }*/
    
    public Album getAlbumId() {
        return albumId;
    }
    
    public void setAlbumId(Album albumId) {
        this.albumId = albumId;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    @Override
    public String toString(){
        return "id="+songId+", name="+title;
    }
}