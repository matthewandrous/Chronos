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
        
		String meetingId = request.getParameter("meetingId");
		String freeTimes = request.getParameter("freeTimes");
		// NEED USERID
		String userId = request.getParameter("userId");
		
       Database db = new Database("AvailabilityInfo", "localhost", 3306);
       db.setAvailFromString(freeTimes, Integer.parseInt(meetingId), Integer.parseInt(userId));
      
        RequestDispatcher rs = request.getRequestDispatcher("host.jsp");
        rs.forward(request, response);
      
    }  

}
