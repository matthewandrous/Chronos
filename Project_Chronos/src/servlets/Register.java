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
 * currently not in use
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        
	        String username = request.getParameter("username");
	        String email = request.getParameter("email");
	        String password = request.getParameter("password");
	        
	 
	        Database db = new Database("HostInfo", "localhost", 3306);
	        try {
				db.getConnection();
				// what if the username/email already exists?
				//good point, added check for that in addHost
				db.addUser(username, password, email, true);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
	        request.getSession().setAttribute("hostName", username);
            RequestDispatcher rs = request.getRequestDispatcher("host.jsp");
            rs.forward(request, response);
	      
	    }  

}
