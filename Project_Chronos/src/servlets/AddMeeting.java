package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Database;

/**
 * Servlet implementation class AddMeeting
 */
@WebServlet("/AddMeeting")
public class AddMeeting extends HttpServlet {
	private static final long serialVersionUID = 1L;
         
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		System.out.println("In servlet AddMeeting.java");
		String meetingName = request.getParameter("meetingName");
		String startDateString = request.getParameter("startDate");
		
		String startTime = request.getParameter("startTime");
		
		int numDays = Integer.valueOf(request.getParameter("numDays"));
		int numHoursPerDay = Integer.valueOf(request.getParameter("numHoursPerDay"));
		
		String hostIdString = (String)request.getParameter("hostId");
		if(hostIdString == null) {
			hostIdString = "1";
			System.out.println("hostIdString is null in AddMeeting.java");
		}
		
		request.setAttribute("userId",hostIdString);
		
		int noOfParticipants = Integer.valueOf(request.getParameter("noOfParticipants"));
		
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
		Date startDate = new Date();
		try {
			startDate = formatter.parse(startDateString);
			System.out.println(startDate.getDate() + " " + startDate.getMonth() + " " + startDate.getYear());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
        Database db_meeting = new Database("MeetingInfo", "localhost", 3306);
        try {
			db_meeting.getConnection();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}        
        System.out.println("Before add meeting: " + meetingName + " " + noOfParticipants + " " + numDays + " " + numHoursPerDay + " " + hostIdString + " " + startDate + " "+ startTime);
        int meetingId = db_meeting.addMeeting(meetingName, noOfParticipants, numDays, numHoursPerDay, Integer.valueOf(hostIdString), startDate, Integer.valueOf(startTime));
		System.out.println(meetingId);
		if(meetingId != -1) {
//			request.setAttribute("noOfDays", numDays);
//			request.setAttribute("startDay", startDate.getDate());
//			request.setAttribute("startMonth", 1+startDate.getMonth());
//			request.setAttribute("startYear", 1900+startDate.getYear());
//			
//			request.setAttribute("startHour", 8);
//			request.setAttribute("startTimeOfDay", "am");
//			request.setAttribute("meetingId", 1);
			
			
			System.out.println("I am directing back");
			PrintWriter out = response.getWriter();
			out.println(String.valueOf(meetingId));
			out.flush();
//            RequestDispatcher rs = request.getRequestDispatcher("selectTimes.jsp");
//            rs.forward(request, response);
		}
		
		//TODO No alert was printed at newMeetingPage.jsp
		else {
			System.out.println("failed");
			PrintWriter out = response.getWriter();
			out.println("invalid");
			out.flush();
		}
	}

}
