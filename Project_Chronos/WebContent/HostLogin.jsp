<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Host Login</title>
<script>
	/* function validate(){
		var xhttp = new XMLHttpRequest(); //AJAX call with synchronous ajax we dont need a callback
		xhttp.open("GET", "AuthenticateHost.jsp?username=" + document.myform.username.value + "&password=" + document.myform.pw.value, false);
		//submit button submits to action 
		//open gets ready to send AJAX CALL
		//the open parameters says send an AJAX call with username and password
		xhttp.send();
		if (xhttp.responseText.trim().length >0){
			document.getElementById("formerror").innerHTML = xhttp.responseText;
			return false;
		}
	} */
</script>
</head>
<body>
	<div id="formerror">
		<!-- if there is an error we will have jsp send back an error -->
	</div>
	<form name = "loginForm" method="POST" action="Login">
		Username <input type="text" name ="username" /><br />
		Password <input type="text" name="password" /><br />
		<input type="submit" name="submit" value=" login" />
	</form>
</body>
</html>