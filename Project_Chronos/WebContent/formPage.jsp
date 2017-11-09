<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Chronos</title>
		<style>
		td:hover {
			background-color:blue;
		}
		</style>
	</head>
	<body>
		<table id="dateTable">
		<%/* 	<tr> <!-- Header row -->
			
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
		</table>
	</body>
	<script>
		Date.prototype.addDays = function(days) {
			  var newDate = new Date(this.valueOf());
			  newDate.setDate(newDate.getDate() + days);
			  return newDate;
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
		var noOfDays = 7;
		var startDate = new Date();
		var endDate = new Date().addDays(noOfDays);
		for (var i = -1; i < noOfDays; i++) {
			var currDate = startDate.addDays(i);
			
			var th = document.createElement("th");
			if (i === -1) {
				th.appendChild(document.createElement("tr"));
				document.getElementById("dateTable").appendChild(th);
				continue;
			}
			
			var trDayOfWeek = document.createElement("tr");
			var tdDayOfWeek = document.createElement("td");
			var textDayOfWeek = document.createTextNode(convertDateIntToString(currDate.getDay()));
			tdDayOfWeek.appendChild(textDayOfWeek);
			trDayOfWeek.appendChild(tdDayOfWeek);
			
			var trDate = document.createElement("tr");
			var tdDate = document.createElement("td");
			var textDate = document.createTextNode(currDate.getDate());
			tdDate.appendChild(textDate);
			trDate.appendChild(tdDate);
			
			var trMonth = document.createElement("tr");
			var tdMonth = document.createElement("td");
			var textMonth = document.createTextNode(convertMonthIntToString(currDate.getMonth()+1));
			tdMonth.appendChild(textMonth);
			trMonth.appendChild(tdMonth);
			
			th.appendChild(trDayOfWeek);
			th.appendChild(trDate);
			th.appendChild(trMonth);
			
			document.getElementById("dateTable").appendChild(th);
		}
		var startHour = 8;
		var startTimeOfDay = "am";
		var noOfHours = 11;
		for (var k = 0; k < noOfHours; k++) {
			var trTime = document.createElement("tr");
			for (var j = 0; j < noOfDays + 1; j++) {
				console.log("k " + k + " j " + j);
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
				}
				trTime.appendChild(tdTime);
			}
			document.getElementById("dateTable").appendChild(trTime);
		}
		/* get difference between two dates
		var date1 = new Date("7/13/2010");
		var date2 = new Date("12/15/2010");
		var timeDiff = Math.abs(date2.getTime() - date1.getTime());
		var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24)); 
		alert(diffDays);*/
	</script>
</html>
