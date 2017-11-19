package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// directed from index.jsp
// this servlet will direct to different jsp pages according to users' choice
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public UploadServlet() {
        super();
        //System.out.println("hi");
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userType= request.getParameter("userType");
		if (userType.equals("Host")) {
			RequestDispatcher dispatch = getServletContext().getRequestDispatcher("/HostLogin.jsp");
			dispatch.forward(request, response);
		}
		else {
			RequestDispatcher dispatch = getServletContext().getRequestDispatcher("/JoinerLogin.jsp");
			dispatch.forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userType = request.getParameter("userType");
		if (userType.equals("Host")) {
			RequestDispatcher dispatch = getServletContext().getRequestDispatcher("/HostLogin.jsp");
			dispatch.forward(request, response);
		}
		else {
			RequestDispatcher dispatch = getServletContext().getRequestDispatcher("/JoinerLogin.jsp");
			dispatch.forward(request, response);
		}
	}

	

}
