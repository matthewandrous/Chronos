<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
		<link rel="stylesheet" href="https://www.w3schools.com/lib/w3-colors-signal.css">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>User Classification Page</title>
	</head>
	<body class="w3-container w3-margin w3-animate-opacity">
		<img src="CF_Logo_OnWhite.png" alt="Logo" class="w3-image" style="width: 50%; max-width:400px">
		<p>I am...</p>
		<form name= "json" action="UploadServlet" method="POST" >
    			<label><input type="radio" name="userType" value="Host" checked="checked"> Hosting a Meeting</label><br>
    			<label><input type="radio" name="userType" value="Joiner"> Joining a Meeting</label><br>
    			<br>
    			<input type="submit" value ="GO" class="w3-button w3-signal-red w3-round-large"/>
		</form>
	</body>
	
</html>