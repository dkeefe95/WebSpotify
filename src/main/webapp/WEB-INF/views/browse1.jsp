<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Browse Overview</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <!-- STYLESHEETS -->
        
        <!-- Bootstrap Core CSS-->
        <link href="${pageContext.servletContext.contextPath}/resources/css/bootstrap.css" rel="stylesheet">
        
        <!-- Fonts CSS -->
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
        
        <!-- Custom Theme CSS -->
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/stylesheet.css">
        
    </head>
    <body>
        <div>
            <%@include file="/resources/toolbars/top.jsp" %>
            
            <div id="main-section">
                <%@include file="/resources/toolbars/left.jsp" %>
                
                <div class="col-xs-10 col-xs-offset-2" id="center-pane">
                    
                    <div id="createPlaylistModal" class="modal fade" role="dialog">
                        <div class="modal-dialog">
                          <!-- Modal content-->
                            <div class="modal-content" id="modalBackground">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <h4 class="modal-title">Create Playlist</h4>
                                </div>
                                <form id="newPlaylistForm">
                                    <div class="modal-body">
                                        <div class="row">
                                            <input id='pName' type="text" name="playlistName" placeholder="New Playlist">
                                        </div>
                                        <div class="row">
                                            <div class="col-xs-6">
                                                <img height="250" width="250" id="playlist-image" src="http://placehold.it/250x250" alt="Image" class="row img-responsive">
                                                <input id='iPath' name="imagePath" class="row" type="file" accept="image/*">
                                            </div>
                                            <div class="col-xs-6">
                                                <input id='pDesc' type="text" name="description" placeholder="Enter a description for your playlist here.">
                                            </div>
                                            <input type="submit" value="Submit">
                                        </div>
                                    </div>
                                </form>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default">Create Playlist</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <!-- INSERT EVERY POSSIBLE PAGE FOR CENTER PANE -->
                 
                </div>
            </div>
            
            <%@include file="/resources/toolbars/bottom.jsp" %>
        </div>
        
        <!-- JAVASCRIPT -->
        
        <!-- jQuery script -->
        <script src="${pageContext.servletContext.contextPath}/resources/js/jquery.min.js"></script>
        
        <!-- Bootstrap Javascript -->
        <script src="${pageContext.servletContext.contextPath}/resources/js/bootstrap.min.js"></script>
        
        <!-- For including HTML Snippets -->
        <script src="${pageContext.servletContext.contextPath}/resources/js/w3.js"></script>
        
        <!-- Main Page Script -->
        <script src="${pageContext.servletContext.contextPath}/resources/js/script.js"></script>

        <!-- Script to display images-->
        <script>
          $(function () {
            $(":file").change(function () {
                if (this.files && this.files[0]) {
                    var reader = new FileReader();
                    reader.onload = imageIsLoaded;
                    reader.readAsDataURL(this.files[0]);
                }
            });
          });
          function imageIsLoaded(e) {
            $('#playlist-image').attr('src', e.target.result);
          };
        /** got from http://jsfiddle.net/vacidesign/ja0tyj0f/**/
        </script>
        
    </body>
</html>