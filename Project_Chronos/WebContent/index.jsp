<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>User Classification Page</title>
	</head>
	<body>
		<p>I am...</p>
		<form name= "json" action="UploadServlet" method="POST" >
    			<input type="radio" name="userType" value="Host" checked="checked">Hosting a Meeting<br>
    			<input type="radio" name="userType" value="Joiner">Joining a Meeting<br>
    			<input type="submit" value ="GO"/>
		</form>
		
	</body>
	
</html>