/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team0n3.webspotify.model;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
/**
 *
 * @author JSCHA
 */
@Entity
@Table(name="users")
public class User implements Serializable{

    @Id
    @Column(name="username", nullable=false)
    private String username;
    @Column(name="email", nullable=false)
    private String email;
    @Column(name="password", nullable=false)
    private byte[] password;
    
    @OneToMany(cascade=CascadeType.ALL,mappedBy="creator")
    private Collection<Playlist> createdPlaylists;

    public Collection<Playlist> getCreatedPlaylists() {
        return createdPlaylists;
    }

    public void setCreatedPlaylists(Collection<Playlist> createdPlaylists) {
        this.createdPlaylists = createdPlaylists;
    }

    public Collection<Playlist> getFollowedPlaylists() {
        return followedPlaylists;
    }

    public void setFollowedPlaylists(Collection<Playlist> followedPlaylists) {
        this.followedPlaylists = followedPlaylists;
    }

    public Collection<Playlist> getCollabPlaylists() {
        return collabPlaylists;
    }

    public void setCollabPlaylists(Collection<Playlist> collabPlaylists) {
        this.collabPlaylists = collabPlaylists;
    }
    
    @ManyToMany(cascade ={CascadeType.ALL })
    @JoinTable(
            name="FollowPlaylist",
            joinColumns= {@JoinColumn(name="username")},
            inverseJoinColumns = {@JoinColumn(name="playlistID")}
    )
    private Collection<Playlist> followedPlaylists;
   
    @ManyToMany(cascade ={CascadeType.ALL })
    @JoinTable(
            name="CollabPlaylist",
            joinColumns= {@JoinColumn(name="username")},
            inverseJoinColumns = {@JoinColumn(name="playlistID")}
    )
    private Collection<Playlist> collabPlaylists;
    
    
    public User() {
    }

    public User(String username, String email, byte[] password) {
       this.username = username;
       this.email = email;
       this.password = password;
       createdPlaylists=null;
       followedPlaylists=null;
       collabPlaylists=null;
    }
    
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return this.email;
    } 
    public void setEmail(String email) {
        this.email = email;
    }
    public byte[] getPassword() {
        return this.password;
    }
    
    public void setHashedPassword(byte[] password) {
        this.password = password;
    }
    @Override
    public String toString(){
        return "username="+username+", email="+email;
    }
}