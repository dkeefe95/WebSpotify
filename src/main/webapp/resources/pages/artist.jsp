<div class="row" id="mediaPane">
    <div class="col-xs-12">
        <div class="col-xs-2">
            <img class="mediaPic" src="/resources/img/foo.jpg">
        </div>
        <div id="mediaInfo" class="col-xs-8">
            <div class="row">
                <span class="mediaType">Playlist</span> <!-- Album, Playlist, or Radio -->
            </div>
            <div class="row">
                <a href="#">
                    <h3 class="mediaName">${currentPlaylist.playlistName}</h3>    
                </a>
            </div>
            <div class="row">
                <a href="#">
                    <span class="mediaCreator">${currentPlaylist.creator.username}</span>    
                </a>
            </div>
            <div class="row" id="mediaSpecs">
                <span>
                    ${currentPlaylist.description}
                </span>
            </div>
        </div>
    </div>
</div>
<div class="row" id="tableContainer">
    <table class="table songTable">
        <tr>
            <th>Title</th>
            <th>Artist</th> 
            <th>Duration</th>
        </tr>
        <!--
        <c:forEach items="${Playlist.songs}" var="Song">
            <tr class="songEntry">
                <td>${Song.songName}</td>
                <td>${Song.artistName}</td>
                <td>${Song.duration}</td>
            </tr>
        </c:forEach>
        -->
    </table>
</div>