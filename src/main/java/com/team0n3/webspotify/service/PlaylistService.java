/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team0n3.webspotify.service;

import com.team0n3.webspotify.model.Playlist;
import com.team0n3.webspotify.model.User;

/**
 *
 * @author spike
 */
public interface PlaylistService {
    public Playlist createPlaylist(String playlistName, String imagePath, String description, User currentUser);
}
