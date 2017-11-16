<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String endpoint = "''"; %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Chronos</title>
		<script>
			function validateAndSend() {
				var error = false;
				var meetingName = document.getElementById("meetingName").value;
				var startDate = document.getElementById("startDate").value;
				var startTime = document.getElementById("startTime").value;
				var endDate = document.getElementById("endDate").value;
				var endTime = document.getElementById("endTime").value;
				var noOfParticipants = document.getElementById("noOfParticipants").value;
				var startDateTime = new Date(startDate + " " + startTime);
				var endDateTime = new Date(endDate + " " + endTime);
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
			        xhttp.open("GET", <%= endpoint %> + "?meetingName=" + meetingName + "&startDate=" + startDate + "&startTime=" + startTime + "&endDate=" + endDate + "&noOfParticipants=" + noOfParticipants, false);
			        xhttp.send();
			    	    if (xhttp.responseText.trim().toLowerCase() === "invalid") {
			    	 		alert("Invalid inputs");
						return;
			    	  	} else if (xhttp.responseText.trim().toLowerCase() === "valid") {
			    	  		request.getRequestDispatcher("inputTable.jsp").forward(request, response);
			    	  	}
				}
			}
		</script>
	</head>
	<body>
		<div id="outerContainer">
			<form name="newMeeting">
				<label>Meeting Name:</label><input name="meetingName" type="text" id="meetingName"><br>
				<label>Start Date:</label><input name="startDate" type="date" id="startDate"><br>
				<label>Start Time:</label><input name="startTime" type="time" id="startTime" step="3600"><br>
				<label>End Date:</label><input name="endDate" type="date" id="endDate"><br>
				<label>End Time:</label><input name="endTime" type="time" id="endTime" step="3600"><br>
				<label>Number of Participants:</label><input name="noOfParticipants" type="number" id="noOfParticipants" value="0"><br>
				<input type="button" onclick = "validateAndSend()" value="Submit">			
			</form>
		</div>
	</body>
</html>