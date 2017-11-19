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
 * Servlet implementation class JoinMeeting
 */
@WebServlet("/JoinMeeting")
public class JoinMeeting extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String meetingID = request.getParameter("meetingID");
        
       
        Database db_mt = new Database("MeetingInfo", "localhost", 3306);
        try {
			db_mt.getConnection();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
        
        Meeting mt  = db_mt.getMeeting(Integer.parseInt(meetingID));
        request.setAttribute("noOfDays", mt.getNumDays());
		Date date = mt.getStartDate();
		int day = date.getDate();
		int year = date.getYear();
		int month = date.getMonth();
		System.out.println(day + " " + month + " " + year);
		year = year % 2000;
		request.setAttribute("startDay", day);
		request.setAttribute("startMonth", month+1);
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
		request.setAttribute("type", "guest");
		
		RequestDispatcher rs = request.getRequestDispatcher("SelectTimes");
       rs.forward(request, response);
	

    }  

}
