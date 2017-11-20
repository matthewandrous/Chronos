package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Database;
import objectFiles.Meeting;

/**
 * Servlet implementation class Result
 */
@WebServlet("/Result")
public class Result extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String meetingId = request.getParameter("meetingId");
		 Database db = new Database("MeetingInfo", "localhost", 3306);
	        try {
				db.getConnection();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
	        System.out.println(meetingId);
	    Meeting mt = db.getMeeting(Integer.parseInt(meetingId));
	    /*Meeting mt = new Meeting(3,4,5);
	    Date date = new Date(117, 0, 20);
	    mt.setStartDate(d);*/
	
		request.setAttribute("noOfDays", mt.getNumDays());
		Date date = mt.getStartDate();
		int day = date.getDate();
		int year = date.getYear();
		int month = date.getMonth();
		System.out.println(day + " " + month + " " + year);
		year = year % 2000;
		request.setAttribute("startDay", day);
		request.setAttribute("startMonth", month);
		request.setAttribute("startYear", year+1900);
		int hour = mt.getStartTime();
		if(hour == 12) {
			request.setAttribute("startHour", hour);
			request.setAttribute("startTimeOfDay", "pm");
		}
		else if (hour > 12) {
			hour = hour % 12;
			request.setAttribute("startHour", hour);
			request.setAttribute("startTimeOfDay", "pm");
		}
		else {
			request.setAttribute("startHour", hour);
			request.setAttribute("startTimeOfDay", "am");
		}
		request.setAttribute("noOfHours", mt.getNumHoursPerDay());
		request.setAttribute("responsesSoFar", db.getResponseNames(Integer.parseInt(meetingId)));
		request.setAttribute("responseTimes", db.getMeetingAvailabilities(Integer.parseInt(meetingId)));
		
		RequestDispatcher rs = request.getRequestDispatcher("results.jsp");
        rs.forward(request, response);
	
	}
}
