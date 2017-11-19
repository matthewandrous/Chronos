<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% int noOfDays = 4;//(int)request.getAttribute("noOfDays");
	int startDay = 12;//(int)request.getAttribute("startDay");
	int startMonth = 11;//(int)request.getAttribute("startMonth");
	int startYear = 17;//(int)request.getAttribute("startYear"); 
	int startHour = 8;//(int)request.getAttribute("startHour");
	String startTimeOfDay = "am";//(String)request.getAttribute("startTimeOfDay");
	int noOfHours = 8;//(int)request.getAttribute("noOfHours"); 
	String endpoint = "''"; 
	String meetingId = "'1'"; //(String)request.getAttribute("meetingId");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Chronos</title>
		<link rel="stylesheet" type="text/css" href="selectTimes.css">
	</head>
	<body>
	<p>This is selectTimes page</p>
		<div id="tableContainer"></div>
		<table id="dateTable"></table>
		<input type="button" value="Submit" onclick="send()">	
	</body>
	<script>
		Date.prototype.addDays = function(days) {
			  var newDate = new Date(this.valueOf());
			  newDate.setDate(newDate.getDate() + days);
			  return newDate;
		}
		var selectedIndexes = [];
		function send() {
			console.log("hello");
	        var xhttp = new XMLHttpRequest();
	        xhttp.open("GET", <%= endpoint %> + "?meetingId=" + <%= meetingId %> + "&freeTimes=" + selectedIndexes.join(","), false);
	        xhttp.send();
		}
		function convertDateIntToString(dateInt) {
			switch (dateInt) {
				case 1:
					return "Mon";
				case 2:
					return "Tue";
				case 3:
					return "Wed";
				case 4:
					return "Thu";
				case 5:
					return "Fri";
				case 6:
					return "Sat";
				case 0:
					return "Sun";
			}
		}
		function convertMonthIntToString(monthInt) {
			switch (monthInt) {
				case 1:
					return "Jan";
				case 2:
					return "Feb";
				case 3:
					return "Mar";
				case 4:
					return "Apr";
				case 5:
					return "May";
				case 6:
					return "Jun";
				case 7:
					return "Jul";
				case 8:
					return "Aug";
				case 9:
					return "Sep";
				case 10:
					return "Oct";
				case 11:
					return "Nov";
				case 0:
					return "Dec";
			}
		}
		var selectedBackgroundColour = "blue";
		var unselectedBackgroundColour = "white";
		var noOfDays = <%= noOfDays %>;
		var startDate = new Date(<%= startYear %>, <%= startMonth-1 %>, <%= startDay %>);
		for (var i = -1; i < noOfDays; i++) {
			var currDate = startDate.addDays(i);
			
			var th = document.createElement("th");
			if (i === -1) {
				th.appendChild(document.createElement("tr"));
				document.getElementById("dateTable").appendChild(th);
				continue;
			}
			
			var trDayOfWeek = document.createElement("tr");
			trDayOfWeek.classList.add("dateHeaders");
			var tdDayOfWeek = document.createElement("td");
			tdDayOfWeek.classList.add("dateHeaders");
			var textDayOfWeek = document.createTextNode(convertDateIntToString(currDate.getDay()));
			tdDayOfWeek.appendChild(textDayOfWeek);
			trDayOfWeek.appendChild(tdDayOfWeek);
			
			var trDate = document.createElement("tr");
			trDate.classList.add("dateHeaders");
			var tdDate = document.createElement("td");
			tdDate.classList.add("dateHeaders");
			var textDate = document.createTextNode(currDate.getDate());
			tdDate.appendChild(textDate);
			trDate.appendChild(tdDate);
			
			var trMonth = document.createElement("tr");
			trMonth.classList.add("dateHeaders");
			var tdMonth = document.createElement("td");
			tdMonth.classList.add("dateHeaders");
			var textMonth = document.createTextNode(convertMonthIntToString(currDate.getMonth()+1));
			tdMonth.appendChild(textMonth);
			trMonth.appendChild(tdMonth);
			
			th.appendChild(trDayOfWeek);
			th.appendChild(trDate);
			th.appendChild(trMonth);
			
			document.getElementById("dateTable").appendChild(th);
		}
		var startHour = <%= startHour %>;
		var startTimeOfDay = "<%= startTimeOfDay %>";
		var noOfHours = <%= noOfHours %>;
		var indexOfCells = 0;
		for (var k = 0; k < noOfHours; k++) {
			var trTime = document.createElement("tr");
			for (var j = 0; j < noOfDays + 1; j++) {
				var tdTime = document.createElement("td");
				if (j === 0) {
					tdTime.className = "doNotClick";
					var hourToOutput = startHour + k;
					if (hourToOutput > 12) {
						hourToOutput -= 12;
						if (startTimeOfDay === "am") {
							startTimeOfDay = "pm";
						} else {
							startTimeOfDay === "am";
						}
					}
					var tdText = document.createTextNode((hourToOutput).toString() + startTimeOfDay);
					tdTime.appendChild(tdText);
				} else {
					tdTime.id = indexOfCells;
				//	tdTime.style.backgroundColor = unselectedBackgroundColour;
					tdTime.className = "deselected";
					//tdTime.onclick = timeClicked(tdTime.id);
					indexOfCells += 1;
				}
				trTime.appendChild(tdTime);
			}
			document.getElementById("dateTable").appendChild(trTime);
		}
		var cells = document.querySelectorAll("td");
		for (var i = 0; i < cells.length; i++) {
		    cells[i].addEventListener("click", function() {
		    	   if (this.className != "doNotClick") {
		    		   if (this.className === "deselected") {
		    			   selectedIndexes.push(this.id);
		    		   } else {
		   			   var index = selectedIndexes.indexOf(this.id);
					   if (index > -1) {
					   	   selectedIndexes.splice(index, 1);
					   }
		    		   }
		    		   for (var j = 0; j < selectedIndexes.length; j++) {
		    			   console.log(selectedIndexes[j]);
		    		   }
		    		   this.className= this.className == "deselected" ? "selected" : "deselected";
		    	   }
		    });
		}
	/*	function timeClicked(id) {
			console.log(id);
			var selectedTd = document.getElementById(id);
			if (selectedTd.style.backgroundColour === unselectedBackgroundColour) {
				console.log("here1");
				selectedTd.style.backgroundColor = selectedBackgroundColour;
				selectedIndexes.push(id);
			} else if (selectedTd.style.backgroundColour === selectedBackgroundColour) {
				console.log("here2");
				selecetedTd.style.backgroundColor = unselectedBackgroundColour;
				var index = selectedIndex.indexOf(id);
				if (index > -1) {
				    array.splice(index, 1);
				}
			}
		}*/
	</script>
</html>