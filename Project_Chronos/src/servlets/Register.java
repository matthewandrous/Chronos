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
import objectFiles.Host;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        response.setContentType("text/html;charset=UTF-8");
	        
	        String username = request.getParameter("username");
	        String email = request.getParameter("email");
	        String password = request.getParameter("password");
	        
	        // what should the port be here????
	        Database db = new Database("HostInfo", "localhost", 6789);
	        try {
				db.getConnection();
				// what if the username/email already exists?
				db.addHost(username, password, email);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
	        request.getSession().setAttribute("hostName", username);
            RequestDispatcher rs = request.getRequestDispatcher("host.jsp");
            rs.forward(request, response);
	      
	    }  

}
