package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Database;

/**
 * Servlet implementation class UpdateAvailability
 */
@WebServlet("/UpdateAvailability")
public class UpdateAvailability extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("In UpdateAvailability.java");
		String meetingId = request.getParameter("meetingId");
		String freeTimes = request.getParameter("freeTimes");
		String type = request.getParameter("userType");
		String username = request.getParameter("username");
		
		//freeTimes = "1,0,1,1,0,1";
		// TODO NEED USERID
		Database db_user = new Database("UserInfo", "localhost", 3306);
		int userId = -1;
		try {
			db_user.getConnection();
			userId = db_user.getUserId(username);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	    
		db_user.close();
		System.out.println("In UpdateAvailability.java " + meetingId + " " + freeTimes + " " + userId + " " + username + " " + type);
		
       Database db = new Database("AvailabilityInfo", "localhost", 3306);
       try {
		db.getConnection();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       db.setAvailFromString(freeTimes, Integer.parseInt(meetingId), userId);
      
		System.out.println(type + " in UpdateAvai" );
       if(type.equals("host")) {
    	   System.out.println("I am a host in UpdateAvai" );
    	   Database db_meeting = new Database("MeetingInfo", "localhost", 3306);
    	   String meetingIds = "";
    	   String meetingNames = "";
   		   try {
   			   db_meeting.getConnection();
   			   meetingIds = db_meeting.getHostMeetings(userId);
   			   meetingNames = db_meeting.getHostMeetingNames(userId);
   		   } catch (SQLException e) {
   			   System.out.println(e.getMessage());
   		   }
   		   request.setAttribute("username", username);
   		   request.setAttribute("meetingIds", meetingIds);
   		   request.setAttribute("meetingNames", meetingNames);
   		   request.setAttribute("hostId", userId);
   		   request.setAttribute("userType", "host");
   		   request.setAttribute("meetingId", meetingId);
   		
    	   RequestDispatcher rs = request.getRequestDispatcher("listOfMeetings.jsp");
           rs.forward(request, response);
           db.close();
           db_meeting.close();
//           PrintWriter out = response.getWriter();
//           out.println("crazyhost");
//           out.flush();
       }
       else {
    	   System.out.println("I am a guest in UpdateAvai" );
    	   RequestDispatcher rs = request.getRequestDispatcher("GuestEnd.jsp");
           rs.forward(request, response);
//    	   PrintWriter out = response.getWriter();
//           out.println("guest");
//           out.flush();
       }
    }  

}
