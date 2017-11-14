<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String meetingIds = "1,4,62,6,8";//(String)request.getAttribute("meetingIds");
   meetingIds = "'" + meetingIds + "'";
   String endpoint = "''";%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Chronos</title>
		<link rel="stylesheet" type="text/css" href="listOfMeetings.css">
	</head>
	<body>
		<div id="outerContainer">
			<div id="header">
				<p>All meetings:</p>
			</div>
			<div id="meetingsDiv">
			</div>
		</div>
	</body>
	<script>
		var meetingIds = <%= meetingIds %>
		var meetings = meetingIds.split(",");
		for (i in meetings) {
			var aMeeting = document.createElement("a");
			aMeeting.href = <%= endpoint %> + "?meetingId=" + meetings[i];
			var meetingDiv = document.createElement("div");
			meetingDiv.classList.add("meetingDiv");
			var pMeeting = document.createElement("p");
			var meetingText = document.createTextNode("Meeting ID: " + meetings[i]);
			pMeeting.appendChild(meetingText);
			meetingDiv.appendChild(pMeeting);
			aMeeting.appendChild(meetingDiv);
			document.getElementById("meetingsDiv").appendChild(aMeeting);
		}
	</script>
</html>
