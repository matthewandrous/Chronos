<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- 
This page is used for the host to login/register
 -->
<html>
<head>
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<link rel="stylesheet" href="https://www.w3schools.com/lib/w3-colors-signal.css">
	<meta charset="UTF-8">
	<title>Host Login</title>
	<style>
		.form {
			padding-bottom: 20px;
		}
	</style>
	<script>
		  function validate(){
			var xhttp = new XMLHttpRequest(); //AJAX call with synchronous ajax we dont need a callback
			xhttp.open("GET", "AuthenticateHost.jsp?username=" + document.myform.username.value + "&password=" + document.myform.pw.value, false);
			//document.myform.pw.value
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
		
		 /* function register(){
			 var xhttp = new XMLHttpRequest(); //AJAX call with synchronous ajax we dont need a callback
				xhttp.open("GET", "makeAccount.jsp?username=" + document.register.username.value + "&password=" + document.register.password.value + "&email=" + document.register.email.value, false);
				
		 } */
		 
		 
		 function openCity(cityName) {
		    var i;
		    var x = document.getElementsByClassName("city");
		    for (i = 0; i < x.length; i++) {
		        x[i].style.display = "none";
		    }
		    document.getElementById(cityName).style.display = "block";
		}
		 
		 
	</script>
</head>

	<body class="w3-container w3-margin w3-animate-opacity">
		
		<img src="CF_Logo_OnWhite.png" alt="Logo" class="w3-image" style="width: 50%; max-width:400px">
		<br>
	
		 <div class="w3-bar">
		  <button class="w3-bar-item w3-button w3-signal-red" onclick="openCity('London')">Log In</button>
		  <button class="w3-bar-item w3-button w3-light-grey" onclick="openCity('Tokyo')">Register</button>
		</div> 
	
		<div id="formerror">
			<!-- if there is an error we will have jsp send back an error -->
			<%String err = (String)request.getAttribute("erromsg");
			if(err != null){%>
				<font color="red"> <%=err %> </font><br /> 
			<%}%>
		</div>
		
		<div id="London" class="city">
			<form name = "myform" method="POST" action="Login" class="w3-container w3-signal-red form">
				<p>Please log in below:<p>
				<div><label>Username</label></div> <input type="text" name ="username" /><br><br>
				<div><label>Password</label></div> <input type="password" name="password" /><br> <br>
				<input type="submit" name="submit" value=" login" class="w3-button w3-white w3-round-large"/>
			</form>
		</div>
		
		<div id="Tokyo" class="city" style="display:none">
			<form name="register" method = "POST" action="MakeAccount" class="w3-container w3-light-grey form">
				<p>Don't have an account? Create one below!<p>
				<div><label>Username</label></div> <input type="text" name ="username" /><br><br>
				<div><label>Password</label></div> <input type="password" name="password" /><br><br>
				<div><label>Email</label></div> <input type="text" name="email"> <br> <br>
				<input type="submit" name="submit2" value=" sign up" class="w3-button w3-signal-red w3-round-large"/>
			</form> 
		</div>
	</body>

</html>