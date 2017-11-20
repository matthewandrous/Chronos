<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "java.io.IOException, java.sql.Connection, java.sql.DriverManager, java.sql.ResultSet, java.sql.SQLException, java.sql.Statement" %>
<% 
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	System.out.println(username + ":" + password);
	Connection conn = null;
	Statement st = null;
	ResultSet rs = null;
	try { //reflection, dynamically load an object at runtime 
		Class.forName("com.mysql.jdbc.Driver"); //at runtime instantiate this class
		conn = DriverManager.getConnection("jdbc:mysql://localhost/Chronos?user=root&password=112911&useSSL=false"); //can only load one database at a time and StudentGrades is the one we created
		st = conn.createStatement();
		if (username != null && password.length() > 0) {
			rs = st.executeQuery("Select* from UserInfo"); //select returns a table that is placed in resultSet
		
		
		//execute update tells you how many rows were updated
		//originally pointer in rs points to the space right before first row, can do next to go to another row
		while (rs.next()) {
			
			String myusername = rs.getString("username");
			System.out.println("username: " + myusername);
			String mypassword = rs.getString("hostPassword");
			System.out.println("myPassword: " + mypassword);
			if (myusername.equals(username)) {
				if (mypassword.equals(password)) {
					System.out.println("I am about good");
					//RequestDispatcher dispatch = getServletContext().getRequestDispatcher("/listOfMeetings.jsp");
					//dispatch.forward(request,response);
				}
				else {
					//request.getSession().setAttribute("erromsg", "The password does not match with the username");
%>
 <font color="red"> You have entered an incorrect password. </font><br /> 
<% 
				}
			}

		}
	}
		else {
			%>
			 <font color="red"> You must enter a username and a password. </font><br /> 
			<% 
		}
		
	} catch (SQLException sqle) {
		System.out.println("sqle");
	} catch (ClassNotFoundException cnfe) {
		System.out.println("cnfe");
	} finally {
		try {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch(SQLException sqle) {
			System.out.println("sqle closing stuff");
		}
	}

%>