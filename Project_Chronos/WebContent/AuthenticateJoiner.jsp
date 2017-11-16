<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "java.io.IOException, java.sql.Connection, java.sql.DriverManager, java.sql.ResultSet, java.sql.SQLException, java.sql.Statement" %>
<% 
	String meetingID = request.getParameter("username");
	Connection conn = null;
	Statement st = null;
	ResultSet rs = null;
	try { //reflection, dynamically load an object at runtime 
		Class.forName("com.mysql.jdbc.Driver"); //at runtime instantiate this class
		conn = DriverManager.getConnection("jdbc:mysql://localhost/chronos?user=root&password=root&useSSL=false"); //can only load one database at a time and StudentGrades is the one we created
		st = conn.createStatement();
		if (meetingID != null) {
			rs = st.executeQuery("Select* from MeetingInfo"); //select returns a table that is placed in resultSet
		}
		
		//execute update tells you how many rows were updated
		//originally pointer in rs points to the space right before first row, can do next to go to another row
		while (rs.next()) {
			String myMeeting = rs.getString("meetingID");
			if (myMeeting == meetingID) {
					RequestDispatcher dispatch = getServletContext().getRequestDispatcher("/JoinerHome.jsp");
					dispatch.forward(request,response);
			}
				else {
%>
<font color="red"> You have entered an incorrect password. </font><br />
<% 
				}
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