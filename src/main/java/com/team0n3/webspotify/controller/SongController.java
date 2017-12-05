/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team0n3.webspotify.controller;

import com.team0n3.webspotify.enums.AccountType;
import com.team0n3.webspotify.model.Song;
import com.team0n3.webspotify.model.User;
import com.team0n3.webspotify.service.SongService;
import com.team0n3.webspotify.service.UserService;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author spike
 */
@Controller
@RequestMapping("song")
public class SongController {
  
  @Autowired
  private UserService userService;
  
  @Autowired
  private SongService songService;
  
  @RequestMapping(value="/followSong", method=RequestMethod.POST)
  @ResponseBody
  public void followSong(@RequestParam int songId, HttpSession session){
    User currentUser = (User)session.getAttribute("currentUser");
    (currentUser.getFollowedSongs()).add(songService.getSong(songId));
    userService.followSong(currentUser.getUsername(), songId);
  }
  
  @RequestMapping(value="/unfollowSong", method=RequestMethod.POST)
  @ResponseBody
  public void unfollowSong(@RequestParam int songId, HttpSession session){
    boolean found = false;
    User currentUser = (User)session.getAttribute("currentUser");
    Collection<Song> followedSongs = currentUser.getFollowedSongs();
    for(Song s:followedSongs){
      if(s.getSongId() == songId){
        followedSongs.remove(s);
        found = true;
        break;
      }
    }
    if(found)
      userService.unfollowSong(currentUser.getUsername(), songId);
  }
  
  @RequestMapping(value = "/viewAllSongs", method= RequestMethod.GET)
  @ResponseBody
  public void viewAllSongs(HttpSession session){
    /** CURRENTLY VIEWS ALL SONGS **/
    List<Song> followSongs = songService.listAllSongs();
    session.setAttribute("songList",followSongs);
  }
  
  @RequestMapping( value = "/adminAddSong", method = RequestMethod.POST)
  @ResponseBody
  public void adminAddSong(@RequestParam String title, HttpSession session)
  {
    User user = (User)session.getAttribute("currentUser");
    System.out.println(user.toString());
    if(user.getAccountType() == AccountType.Admin)
    {
        userService.adminAddSong(user.getUsername(), title);    
    }
  }
  
  @RequestMapping( value = "/adminRemoveSong", method = RequestMethod.POST)
  @ResponseBody
  public void adminRemoveSong(@RequestParam int songId, HttpSession session){
    List<Song> allSongs = songService.listAllSongs();
    boolean found = false;
    for(Song s : allSongs){
      if(s.getSongId() == songId){
        allSongs.remove(s);
        found = true;
        break;
      }
    }
    if(found){
      User currentUser = (User)session.getAttribute("currentUser");
      userService.adminRemoveSong(currentUser.getUsername(), songId);
      session.setAttribute("allSongs",allSongs);
    }
  }
}