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
		meetingId = "1";
		String freeTimes = request.getParameter("freeTimes");
		//freeTimes = "1,0,1,1,0,1";
		// TODO NEED USERID
		String userId = request.getParameter("userId");
		userId = "2";
		System.out.println(meetingId + " " + freeTimes + " " + userId);
		
       Database db = new Database("AvailabilityInfo", "localhost", 3306);
       try {
		db.getConnection();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       db.setAvailFromString(freeTimes, Integer.parseInt(meetingId), Integer.parseInt(userId));
      
       if(request.getParameter("type").equals("host")) {
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
