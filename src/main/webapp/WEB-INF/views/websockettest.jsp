<%@page contentType="text/html" pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=ISO-8859-1">
    </head>
    <body>
        <meta charset="utf-8">
        <title>HelloWorld Web sockets</title>
        <script language="javascript" type="text/javascript">
            var wsUri = getRootUri() + "/bar/websocket";
            var mysocket;  
            
            // utils
            function getRootUri() {
                return "ws://" + (document.location.hostname == "" ? "localhost" : document.location.hostname) + ":" +
                        (document.location.port == "" ? "8080" : document.location.port);
            }
            function init() {
                output = document.getElementById("output");
                open_socket();
            }
            
            // socket init
            function open_socket(){
            	websocket = new WebSocket(wsUri);
                websocket.onopen = function(evt) {
                    onOpen(evt);
                };
                websocket.onmessage = function(evt) {
                    onMessage(evt);
                };
                websocket.onerror = function(evt) {
                    onError(evt);
                };
            }
            
            // event handlers
            function onOpen(evt) {
                writeToScreen("Connected to Endpoint!");
            }
            function onMessage(evt) {
                writeToScreen("Message Received: " + evt.data);
            }
            function onError(evt) {
                writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
            }
            
            // btn click
            function send_message(message) {
            	doSend(textID.value);
            }
            
            // action
            function doSend(message) {
                writeToScreen("Message Sent: " + message);
                websocket.send(message);
            }
            function writeToScreen(message) {
                var pre = document.createElement("p");
                pre.style.wordWrap = "break-word";
                pre.innerHTML = message;
                 
                output.appendChild(pre);
            }
            
            window.addEventListener("load", init, false);
        </script>
        <h2 style="text-align: center;">Hello World WebSocket Client</h2>
        <br>
        <div style="text-align: center;">
            <form action="">
                <input onclick="send_message()" value="Send" type="button">
                <input id="textID" name="message" value="Hello WebSocket!" type="text"><br>
            </form>
        </div>
        <div id="output"></div>
</body>
</html>
