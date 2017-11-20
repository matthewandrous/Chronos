<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script>
			var socket;
			function connectToServer() {
				socket = new WebSocket("ws://localhost:8080/Project_Chronos/ws");
				socket.onopen = function(event) {
					document.getElementById("dummy").innerHTML += "Connected!";
				}
				socket.onmessage = function(event) {
					document.getElementById("dummy").innerHTML += event.data + "<br />";
				}
				socket.onclose = function(event) {
					document.getElementById("dummy").innerHTML += "Disconnected!";
				}
			}
			function sendMessage() {
				socket.send("a new response");
				return false;
			}
</script>
<html>
	<head>
		<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
		<link rel="stylesheet" href="https://www.w3schools.com/lib/w3-colors-signal.css">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>User Classification Page</title>
	</head>
	<body class="w3-container w3-margin w3-animate-opacity" onload="connectToServer()">
		<img src="CF_Logo_OnWhite.png" alt="Logo" class="w3-image">
		<p>I am...</p>
		<form name= "json" action="UploadServlet" method="POST" >
    			<input type="radio" name="userType" value="Host" checked="checked">Hosting a Meeting<br>
    			<input type="radio" name="userType" value="Joiner">Joining a Meeting<br>
    			<input type="submit" value ="GO" class="w3-button w3-signal-red w3-round-large"/>
		</form>
		<button onclick="sendMessage()">Click me</button>
		
		<div id = dummy>
		
		</div>
		
	</body>
	
</html>