var audio;

$(document).ready(function(){
  
    $("#center-pane").load("/resources/pages/browsePage.jsp");
    w3.includeHTML(playBack);
    $('#myCarousel').carousel({
	    interval: 10000
	})
    $(".next").click(function(){
        $("#myCarousel").carousel("prev");
    });
    $(".prev").click(function(){
        $("#myCarousel").carousel("next");
    });
    
    $('[data-toggle="tooltip"]').tooltip();
    
    function playBack(){
        audio = $("#audio")[0];
        audio.addEventListener("loadedmetadata",function(){
            var duration = audio.duration;
            $('#songDuration')[0].innerHTML= Math.floor(duration/60) + ":" + Math.floor(duration % 60);
        });
        audio.addEventListener("timeupdate",updateProgress,false);
        //audio.controls=false;
    } 
    //var activeToggle = $("#browseToggle"); //By default, the center pane shown is the browse overview
    
    $("#newPlaylistForm").submit(function(){
        var playlistName = $("#pName").val();
        var imagePath = $("#iPath").val();
        var description = $("#pDesc").val();
        $.ajax({
            url: "makePlaylist",
            type: "POST",
            //Sends the necessary form parameters to the servlet
            data:({
               playlistName: playlistName,
               imagePath: imagePath,
               description: description
            }),
            success: function(){
                console.log("Success creating playlist");
                $("#leftTool").load("/resources/toolbars/left.jsp",function(){
                    console.log("Reloaded playlist sidebar after add");
                });
            },
            error: function(){
                console.log("Failure creating playlist");
            }
        });
        $("#createPlaylistModal").modal('hide');
        return false;    
    });
    $("#searchForm").submit(function(){
      var keyword = $("#keyword").val();
      $.ajax({
        url: "search",
        type: "GET",
        data:({
               keyword: keyword
            }),
        success:function(){
            console.log("View success");
            $("#center-pane").load("resources/pages/search_results.jsp",function(){
                console.log("Loaded playlists into center pane!");
            });
        },
        error: function(){
            console.log("View error");
        }
    });
    return false; 
    });
    
});
function addArtistAdmin(){
     var artistName = $("#artistName").val();
           var popularity = $("#popularity").val();
           var imagePath = $("imagePath").val();
           $.ajax({
               url: "addArtistAdmin",
               type: "POST",
               data:({
                  artistName: artistName,
                  popularity: popularity,
                  imagePath: imagePath,
               }),
               
               success: function(){
                   console.log("Success");
               },
               error: function(){
                   console.log("Failure");
               }
           });
           return false;    
}
function upgradeToPremium(){
    console.log("trying to upgrade");
    var cardHold = $("#cardHold").val();
    var cardNum = $("#cardNum").val();
    var ccv = $("#ccv").val();
    var month = parseInt($("#month").val());
    var year = parseInt($("#year").val());
    var creditCompany = $("#creditCompany").val();
    var address = $("#address").val();
    console.log(typeof month);
    $.ajax({
       url: "upgrade",
       type: "POST",
       data:({
           cardNumber: cardNum,
           cardHolder: cardHold,
           ccv: ccv,
           expirationMonth: month,
           expirationYear: year,
           creditCompany: creditCompany,
           address: address
       }),
       success:function(){
           $("#center-pane").load("/resources/pages/profile.jsp",function(){
               console.log("success upgrading");
           });
           
       },
       error:function(){
           console.log("failure upgrading");
       }
    });
    return false;
}

function updateProgress() {
    var progress = $(".progress-bar")[0];
    var value = 0;
    if (audio.currentTime > 0)
        value = Math.floor((100 / audio.duration) * audio.currentTime);
    progress.style.width = value + "%";
    var currTime = $("#currentTime")[0];
    var t = audio.currentTime;  
    var less10="";
    var seconds = Math.floor(t%60);
    if (seconds < 10)
        less10 = "0";
    currTime.innerHTML= Math.floor(t/60) + ":" + less10 + seconds;
 }

function togglePlayPause() {
   var icon = $("#playPauseIcon")[0];
   if (audio.paused || audio.ended) { //if the audio is paused or not playing,
      $(icon).removeClass("fa-play");
      $(icon).addClass("fa-pause");
      audio.play();
   }
   else {
      $(icon).removeClass("fa-pause");
      $(icon).addClass("fa-play");
      audio.pause();
   }
}

function viewBrowse(){
    $("#center-pane").load("/resources/pages/browsePage.jsp");
}

function viewEditProfile(){
    $("#center-pane").load("/resources/pages/editProfile.jsp");
}

function viewUpgradePage(){
    $("#center-pane").load("/resources/pages/upgrade.jsp",function(){
     
    });
}

function viewProfile(){
  $("#center-pane").load("/resources/pages/profile.jsp");
}

function viewPlaylist(id){
    $.ajax({
        url: "viewPlaylist",
        type: "GET",
        data: ({
            playlistID: id
        }),
        success:function(){
            $("#center-pane").load("/resources/pages/playlist.jsp",function(){
               
            });
        },
        error: function(){
            console.log("Error viewing playlist");
        }
    });
    return false; // Makes sure that the link isn't followed
}

function viewArtist(id){
  $.ajax({
    url: "viewArtist",
    type: "GET",
    data: ({
      artistID: id
    }),
    success:function(){
      console.log("View success");
      $("#center-pane").load("/resources/pages/artist.jsp",function(){
        console.log("Loaded new artist info into center pane!");
      });
    },
    error: function(){
      console.log("View error");
    }
  });
  return false; // Makes sure that the link isn't followed
}

function viewAlbum(id){
    $.ajax({
        url: "viewAlbum",
        type: "GET",
        data: ({
            albumID: id
        }),
        success:function(){
            $("#center-pane").load("/resources/pages/album.jsp",function(){
                
            });
        },
        error: function(){
            console.log("Error viewing album");
        }
    });
    return false; // Makes sure that the link isn't followed
}

function viewFollowedAlbums(){
    $.ajax({
        url: "viewFollowedAlbums",
        type: "GET",
        success:function(){
            $("#center-pane").load("/resources/pages/followedAlbums.jsp",function(){
               
            });
        },
        error: function(){
            console.log("Error viewing followed albums");
        }
    });
    return false; // Makes sure that the link isn't followed
}

function viewAllPlaylists(){
    $.ajax({
        url: "viewAllPlaylists",
        type: "GET",
        success:function(){
            console.log("View success");
            $("#center-pane").load("resources/pages/follwedPlaylists.jsp",function(){
                console.log("Loaded playlists into center pane!");
            });
        },
        error: function(){
            console.log("View error");
        }
    });
    return false; // Makes sure that the link isn't followed
}

function viewFollowedSongs(){
    $.ajax({
        url: "viewSongs",
        type: "GET",
        success:function(){
            $("#center-pane").load("/resources/pages/song.jsp",function(){
              
            });
        },
        error: function(){
            console.log("Error viewing followed songs");
        }
    });
    return false; // Makes sure that the link isn't followed
}

function deletePlaylist(){
    $.ajax({
        url: "deletePlaylist",
        type: "POST",
        data: ({}),
        success: function(){
            console.log("Success deleting playlist");
            $("#leftTool").load("/resources/toolbars/left.jsp",function(){
                console.log("Reloaded playlist sidebar after delete");
                $("#center-pane").load("/resources/pages/browsePage.jsp");
            });
        },
        error: function(){
            console.log("Failure deleting playlist");
        }
    })
    return false;
};

function followPlaylist(playlist) {
  $.ajax({
    url: "followPlaylist",
    type: "POST",
    data: ({
      playlist: playlist,
    }),
    success:function(){
      $("#leftTool").load("/resources/toolbars/left.jsp",function(){
                console.log("Reloaded playlist sidebar after delete");
            });
    },
    error: function(){
            console.log("Failure following playlist");
    }
  });
  return false;
};

function unfollowPlaylist(playlist) {
  $.ajax({
    url: "unfollowPlaylist",
    type: "POST",
    data: ({
      playlist: playlist,
    }),
    success:function(){
      $("#leftTool").load("/resources/toolbars/left.jsp",function(){
                console.log("Reloaded playlist sidebar after delete");
            });
    },
    error: function(){
            console.log("Failure following playlist");
    }
  });
  return false;
};

function addToPlaylist(playlist, song) {
  $.ajax({
    url: "addToPlaylist",
    type: "POST",
    data: ({
      playlist: playlist,
      song: song
    }),
    success:function(){
      console.log("Success adding song");
    },
    error: function(){
      console.log("Failure adding song");
    }
  });
  return false;
}

function deleteFromPlaylist(playlistId, songId){
    $.ajax({
      url: "deleteFromPlaylist",
      type: "POST",
      data: ({
        playlistId: playlistId,
        songId: songId
      }),
      success: function(){
        console.log("Success deleting song");
          $("#center-pane").load("/resources/pages/playlist.jsp");
      },
      error: function(){
        console.log("Failure deleting song");
      }
    });
    return false;
};

function cancelPremium(){
    $.ajax({
       url: "cancel",
       type: "POST",
       data:({}),
       success:function(){
           $("#center-pane").load("/resources/pages/profile.jsp",function(){
               console.log("success cancelling");
           });
       },
       error:function(){
           console.log("failure cancelling");
       }
    });
    return false;    
}

function playSong(songId){
  $.ajax({
    url: "playSong",
    type: "GET",
    data: ({
      songId: songId
    }),
    success: function(){
      $("#bottomTool").load("/resources/toolbars/bottom.jsp");
    }
  })
}
function viewUsers(){
    $.ajax({
        url: "viewUsers",
        type: "GET",
        success:function(){
            console.log("View success");
            $("#center-pane").load("resources/pages/users.jsp",function(){
                console.log("Loaded playlists into center pane!");
            });
        },
        error: function(){
            console.log("View error");
        }
    });
    return false; // Makes sure that the link isn't followed
}
