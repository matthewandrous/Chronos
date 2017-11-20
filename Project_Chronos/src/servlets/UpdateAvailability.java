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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
		String meetingId = request.getParameter("meetingID");
		String freeTimes = request.getParameter("freeTimes");
		String userType = request.getParameter("userType");
		// NEED USERID
		String userId = request.getParameter("userId");
		
		Database db = new Database("AvailabilityInfo", "localhost", 3306);
		db.setAvailFromString(freeTimes, Integer.parseInt(meetingId), Integer.parseInt(userId));
		
		String next;
		if (userType.equals("1")) {
			// direct to host's result page
			next = "";
		}
		else {
			// direct to guest's result page
			next = "";
		}
      
        RequestDispatcher rs = request.getRequestDispatcher(next);
        rs.forward(request, response);
      
    }  

}
