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
	        response.setContentType("text/html;charset=UTF-8");
	        
	        
	        String username = request.getParameter("username");
	        String password = request.getParameter("password");
	        String email = request.getParameter("email");
	        
	        // what should the port be here????
	        //Port 3306
	        Database db = new Database("HostInfo", "localhost", 3306);
	        try {
				db.getConnection();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
	        try {
				db.addHost(username, password, email);
				RequestDispatcher dispatch = getServletContext().getRequestDispatcher("/listOfMeetings.jsp");
				dispatch.forward(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

}
