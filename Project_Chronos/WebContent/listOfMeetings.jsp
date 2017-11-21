<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String meetingIds = (String)request.getAttribute("meetingIds");
   meetingIds = "'" + meetingIds + "'";
   String meetingNames = (String)request.getAttribute("meetingNames");
   meetingNames = "'" + meetingNames + "'";
   String endpoint = "'Result'";
   String username = (String)request.getAttribute("username");
   username = "'" + username + "'";
   int hostId = (int)request.getAttribute("hostId");
   String hostIdString = String.valueOf(hostId);
   hostIdString = "'" +hostIdString + "'";
   String meetingId = (String)request.getAttribute("meetingId");
   %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
		<link rel="stylesheet" href="https://www.w3schools.com/lib/w3-colors-signal.css">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Chronos</title>
		<link rel="stylesheet" type="text/css" href="listOfMeetings.css">
		<style>
			.buttonStyle {
				text-decoration: none;
			}
			
			.aStyle {
				margin-bottom: 20px;
				margin-right: 100%;
			}
		</style>
	</head>
	<body onload="connectToServer()" class="w3-container w3-margin w3-animate-opacity">
		<img src="CF_Logo_OnWhite.png" alt="Logo" class="w3-image" style="width: 50%; max-width:400px">
		<div id="outerContainer">
		<div id="Note"><%if(meetingId != null){%><font color="red">You just added a new meeting with ID <%=meetingId %>. Take a note!</font><br><%} %></div>
			<div id="header">
				<p>All meetings:</p>
			</div>
			<div id="meetingsDiv">
			</div>
			<br>
			<button class="w3-button w3-signal-red w3-round-large"><a id="addNewMeetingButton buttonStyle">Add New Meeting</a></button>
		</div>
		<div id = dummy ></div>
	</body>
	<script>
	var socket;
	function connectToServer() {
		socket = new WebSocket("ws://localhost:8080/Project_Chronos/ws");
		socket.onopen = function(event) {
			// document.getElementById("dummy").innerHTML += "Connected!";
		}
		socket.onmessage = function(event) {
			document.getElementById("dummy").innerHTML += event.data + "<br />";
		}
		socket.onclose = function(event) {
			// document.getElementById("dummy").innerHTML += "Disconnected!";
		}
	}
	
		var meetingIds = <%= meetingIds %>
		var meetingNames = <%= meetingNames %>
		var meetings = meetingIds.split(",");
		var meetingNs = meetingNames.split(",");
		if (meetingIds === null || meetingIds === " " || meetingIds === "" || meetingIds === "null") {
			document.getElementById("meetingsDiv").innerHTML = "<p>You have no meetings</p>";
		} else {
			for (i in meetings) {
				var aMeeting = document.createElement("a");
				aMeeting.href = <%= endpoint %> + "?meetingId=" + meetings[i];
				aMeeting.classList.add("w3-button");
				aMeeting.classList.add("w3-signal-red");
				aMeeting.classList.add("w3-round-large");
				aMeeting.classList.add("aStyle");
				var meetingDiv = document.createElement("div");
				meetingDiv.classList.add("meetingDiv");
				var pMeeting = document.createElement("p");
				var meetingText = document.createTextNode("Meeting ID: " + meetings[i] + " - " + meetingNs[i]);
				pMeeting.appendChild(meetingText);
				meetingDiv.appendChild(pMeeting);
				aMeeting.appendChild(meetingDiv);
				document.getElementById("meetingsDiv").appendChild(aMeeting);
			}
		}
		document.getElementById("addNewMeetingButton").href = "newMeetingPage.jsp?username=" + <%= username %> + "&hostId=" + <%= hostIdString %>; 
	</script>
</html>
