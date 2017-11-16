<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% int noOfDays = 3;//(int)request.getAttribute("noOfDays");
   int startDay = 12;//(int)request.getAttribute("startDay");
   int startMonth = 11;//(int)request.getAttribute("startMonth");
   int startYear = 17;//(int)request.getAttribute("startYear"); 
   int startHour = 8;//(int)request.getAttribute("startHour");
   String startTimeOfDay = "am";//(String)request.getAttribute("startTimeOfDay");
   int noOfHours = 3;//(int)request.getAttribute("noOfHours");
   String responsesSoFar =  "Gautam,Byron,MuYao,PeiXuan,Matthew";//(String)request.getAttribute("responsesSoFar");
   String responseTimes = "1,2,3,2,2,2,0,1,4";//(String)request.getAttribute("responseTimes"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Chronos</title>
		<!-- <link rel="stylesheet" type="text/css" href="results.css"> -->
		<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	</head>
	<body>
		<div id="outerContainer">
		<img src="CF_Logo_OnWhite.png" alt="Logo_OnWhite" style="width:300px;height:111px;">
			<div>
			</div>
			<div id="responsesLegendContainer">
				<div id="responsesContainer">
					<p>Responses so far:</p>
				</div>
				<div id="legendContainer">
					<p>Legend:</p>
					<table id="legendTable">
						<tr>
							<td>0 can't: </td>
							<td style="width:50%; background-color: white;"></td>
						</tr>
					</table>
				</div>
			</div>
			<div id="dateTableContainer">
				<table id="dateTable"></table>
			</div>
		</div>
	</body>
	<script>
		Date.prototype.addDays = function(days) {
			  var newDate = new Date(this.valueOf());
			  newDate.setDate(newDate.getDate() + days);
			  return newDate;
		}
	/*	function generateColours() {
			var colours = [];
			while (colours.length < 100) {
			    do {
			        var colour = Math.floor((Math.random()*1000000)+1);
			    } while (colours.indexOf(colour) >= 0);
			    colours.push("#" + ("000000" + colour.toString(16)).slice(-6));
			}
			return colours;
		}*/
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
					indexOfCells += 1;
				}
				trTime.appendChild(tdTime);
			}
			document.getElementById("dateTable").appendChild(trTime);
		}
		
		var responsesSoFarToSplit = "<%= responsesSoFar %>"
		var responsesSoFar = responsesSoFarToSplit.split(",");
		for (response in responsesSoFar) {
			var pResponsesSoFar = document.createElement("p");
			var textResponsesSoFar = document.createTextNode("-" + responsesSoFar[response]);
			pResponsesSoFar.appendChild(textResponsesSoFar);
			document.getElementById("responsesContainer").appendChild(pResponsesSoFar);
		}
		
		var responseTimesToSplit = "<%= responseTimes %>";
		var responseTimes = responseTimesToSplit.split(",");
		var baseR = 0;
		var baseG = 0;
		var baseB = 0;
		var table = document.getElementById("dateTable");
		var colours = ["Gold","GoldenRod","Orange","DarkOrange","Red","DarkRed"];
		for (i in responseTimes) {
			var tableCell = document.getElementById(i.toString());
			if (responseTimes[i] === "0") {
				tableCell.style.backgroundColor = "rgb(255,255,255)";
			} else {
				var bValue = baseB + (responseTimes[i] * 70);
				//tableCell.style.backgroundColor = "rgb(" + baseR.toString() + "," + baseG.toString() + "," + bValue.toString() + ")";	
				tableCell.style.backgroundColor = colours[responseTimes[i]];
			}
		}
		
		var legend = document.getElementById("legendTable");
		for (i in responsesSoFar) {
			var trLegend = document.createElement("tr");
			var tdText = document.createElement("td");
			var plusOne = parseInt(i)+1;
			var noOfPeopleText = document.createTextNode(plusOne.toString() + " can't: ");
			tdText.appendChild(noOfPeopleText);
			trLegend.appendChild(tdText);
			var tdColour = document.createElement("td");
			if (plusOne === 6) {
				plusOne = 0;
			}
			tdColour.style.backgroundColor = colours[plusOne];
			tdColour.style.width = "50%";
			trLegend.appendChild(tdColour);
			legend.appendChild(trLegend);
		}
	
		/* get difference between two dates
		var date1 = new Date("7/13/2010");
		var date2 = new Date("12/15/2010");
		var timeDiff = Math.abs(date2.getTime() - date1.getTime());
		var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24)); 
		alert(diffDays);*/
	</script>
</html>

<%/* table structure 	
<tr> <!-- Header row -->
			
				<th> <!-- Individual header box -->
					<tr> <!-- Row which has day/month etc-->
						<td>
							Mon
						</td>
					</tr>
					<tr>
						<td>
							2
						</td>
					</tr>
					<tr>
						<td>
							Oct
						</td>
					</tr>
				</th>
				
				
			</tr> */%>
