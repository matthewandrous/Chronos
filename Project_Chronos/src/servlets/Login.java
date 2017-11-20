package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Database;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   // this servlet needs two parameters 
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        Database db_user = new Database("UserInfo", "localhost", 3306);
        try {
			db_user.getConnection();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
        
        int hostID = db_user.authenticateHost(username, password);
        if(hostID > 0)
        {
        	//Host host = db.getHost(hostID);
        	request.setAttribute("username", username);
        	System.out.println("Host id is " + hostID);
        	Database db_meeting = new Database("MeetingInfo", "localhost", 3306);
        	try {
    			db_meeting.getConnection();
    		} catch (SQLException e) {
    			System.out.println(e.getMessage());
    		}
        	String meetingIds = db_meeting.getHostMeetings(hostID);
        	System.out.println("MeetingIds are " + meetingIds);
        	request.setAttribute("meetingIds", meetingIds);
        	request.setAttribute("hostId", hostID);
        	request.setAttribute("userType", "1");
        	// CHANGE the destination
        	System.out.println("I am redirecting");
            RequestDispatcher rs = request.getRequestDispatcher("listOfMeetings.jsp");
            rs.forward(request, response);
        }
        else if (hostID == -1)
        {
        	// go back to the login page
        	request.setAttribute("erromsg", "Username and password do not match. Please try again.");
        	RequestDispatcher rs = request.getRequestDispatcher("HostLogin.jsp");
        	rs.include(request, response);
        }
    }  
}
