<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<link rel="stylesheet" href="https://www.w3schools.com/lib/w3-colors-signal.css">
	<meta charset="UTF-8">
	<title>Joing User Login</title>
	<script>
		function validate(){
			var xhttp = new XMLHttpRequest(); //AJAX call with synchronous ajax we dont need a callback
			xhttp.open("GET", "AuthenticateJoiner.jsp?meetingID=" + document.loginForm.meetingID.value + "&username=" + document.loginForm.username.value, false);
			//submit button submits to action 
			//open gets ready to send AJAX CALL
			//the open parameters says send an AJAX call with username and password
			xhttp.send();
			if (xhttp.responseText.trim().length >0){
				document.getElementById("formerror").innerHTML = xhttp.responseText;
				return false;
			}
			return true;
		}
	</script>
</head>
<body class="w3-container w3-margin w3-animate-opacity">
	<img src="CF_Logo_OnWhite.png" alt="Logo" class="w3-image" style="width: 50%; max-width:400px">
	<div id="formerror">
		<!-- if there is an error we will have jsp send back an error -->
	</div>

	<form name = "loginForm" method="POST" action="SelectTimes?userType=guest" onsubmit="return validate();">
		Meeting ID (This is the id of the meeting you wish to join):
		<br>
		<input type="text" name="meetingID" /><br />
		Username: 
		<br>
		<input type="text" name="username"/><br />
		<br>
		<input type="submit" name="submit" value=" Join" class="w3-button w3-signal-red w3-round-large" />

	</form>
</body>
</html>