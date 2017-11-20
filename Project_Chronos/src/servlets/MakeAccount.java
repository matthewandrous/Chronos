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
import objectFiles.Host;

/**
 * Servlet implementation class MakeAccount
 */
@WebServlet("/MakeAccount")
public class MakeAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	   // this servlet needs two parameters 
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        
	        String username = request.getParameter("username");
	        String password = request.getParameter("password");
	        String email = request.getParameter("email");
	        
	        // what should the port be here????
	        //Port 3306
	        Database db = new Database("UserInfo", "localhost", 3306);
	        try {
				db.getConnection();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
	        try {
				db.addUser(username, password, email, true);
				int hostId = db.authenticateHost(username, password);
				request.setAttribute("username", username);
				request.setAttribute("hostId", hostId);
				request.setAttribute("type", "host");
				RequestDispatcher dispatch = getServletContext().getRequestDispatcher("/listOfMeetings.jsp");
				dispatch.forward(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

}
