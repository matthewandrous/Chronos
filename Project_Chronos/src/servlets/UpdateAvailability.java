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
	    
		
		System.out.println("In UpdateAvailability.java " + meetingId + " " + freeTimes + " " + userId + " " + username + " " + type);
		
       Database db = new Database("AvailabilityInfo", "localhost", 3306);
       try {
		db.getConnection();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       db.setAvailFromString(freeTimes, Integer.parseInt(meetingId), userId);
      
       if(type.equals("host")) {
    	   //redirect to host list of meeting page??
    	   RequestDispatcher rs = request.getRequestDispatcher("GuestEnd.jsp");
           rs.forward(request, response);
       }
       else {
    	   RequestDispatcher rs = request.getRequestDispatcher("GuestEnd.jsp");
           rs.forward(request, response);
       }
    }  

}
