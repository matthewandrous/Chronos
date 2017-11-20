<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String endpoint = "'AddMeeting'"; %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
		<link rel="stylesheet" href="https://www.w3schools.com/lib/w3-colors-signal.css">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Chronos</title>
		<script>
			function validateAndSend() {
				var error = false;
				var meetingName = document.getElementById("meetingName").value;
				var startDate = document.getElementById("startDate").value;
				var startTime = document.getElementById("startTime").value;
				var startTimeSplit = startTime.split(":");
				var startHour = startTimeSplit[0];
				var endDate = document.getElementById("endDate").value;
				var endTime = document.getElementById("endTime").value;
				var endTimeSplit = endTime.split(":");
				var endHour = endTimeSplit[0];
				var noOfParticipants = document.getElementById("noOfParticipants").value;
				var startDateTime = new Date(startDate + " " + startTime);
				var endDateTime = new Date(endDate + " " + endTime);
				var timeDiff = Math.abs(endDateTime.getTime() - startDateTime.getTime());
				var numDays = Math.ceil(timeDiff / (1000 * 3600 * 24)); 
				var numHoursPerDay = endHour - startHour;
				if (meetingName.length === 0) {
					error = true;
				}
				if (startDate.length === 0) {
					error = true;
				}
				if (startTime.length === 0) {
					error = true;
				}
				if (endDate.length === 0) {
					error = true;
				}
				if (endTime.length === 0) {
					error = true;
				}
				if (noOfParticipants === 0) {
					error = true;
				}
				if (startDate === endDate && startDateTime.getTime() - endDateTime.getTime() >= 0) {
					error = true;
				}
				if (startDateTime.getFullYear() - endDateTime.getFullYear() > 0) {
					error = true;
				}
				if (startDateTime.getFullYear() === endDateTime.getFullYear() && startDateTime.getMonth() - endDateTime.getMonth() > 0) {
					error = true;
				}
				if (startDateTime.getFullYear() === endDateTime.getFullYear() && startDateTime.getMonth() === endDateTime.getMonth() && startDateTime.getDate() - endDateTime.getDate() > 0) {
					error = true;
				}
				if (error) {
					alert("Invalid inputs");
					return;
				} else {
			        var xhttp = new XMLHttpRequest();
			        xhttp.open("GET", <%= endpoint %> + "?meetingName=" + meetingName + "&startDate=" + startDate + "&startTime=" + startTime + "&endDate=" + endDate + "&noOfParticipants=" + noOfParticipants + "&numDays=" + numDays + "&numHoursPerDay=" + numHoursPerDay, false);
			        xhttp.send();
			        var response = xhttp.responseText.trim().toLowerCase();
			    	   if (response === "invalid") {
			    	 		alert("Invalid inputs");
						return;
			    	  	} else {
			    	  		window.location.href = 'SelectTimes?meetingID=' + response;
			    	  		//request.getRequestDispatcher("selectTimes.jsp?hostId=" + response).forward(request, response);
						return;
			    	  	}
				}
			}
		</script>
	</head>
	<body>
		<img src="CF_Logo_OnWhite.png" alt="Logo" class="w3-image">
		<div id="outerContainer" class="w3-container w3-margin w3-animate-opacity">
			<form name="newMeeting">
			<% String hostIdString = request.getParameter("hostId");
			System.out.println(hostIdString);
			request.setAttribute("hostId", hostIdString);
			request.setAttribute("userId", hostIdString);%>
				<label>Meeting Name:</label><input name="meetingName" type="text" id="meetingName" class="w3-input w3-border w3-round" ><br>
				<label>Start Date:</label><input name="startDate" type="date" id="startDate" class="w3-input w3-border w3-round"><br>
				<label>End Date:</label><input name="endDate" type="date" id="endDate" class="w3-input w3-border w3-round"><br>
				<label>Start Time:</label><input name="startTime" type="time" id="startTime" step="3600" class="w3-input w3-border w3-round"><br>
				<label>End Time:</label><input name="endTime" type="time" id="endTime" step="3600" class="w3-input w3-border w3-round"><br>
				<label>Number of Participants:</label><input name="noOfParticipants" type="number" id="noOfParticipants" value="0" class="w3-input w3-border w3-round"><br>
				<input type="button" class="w3-button w3-signal-red w3-round-large" onclick = "validateAndSend()" value="Submit">			
			</form>
		</div>
	</body>
</html>