<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "java.io.IOException, java.sql.Connection, java.sql.DriverManager, java.sql.ResultSet, java.sql.SQLException, java.sql.Statement, database.Database, objectFiles.*" %>
<% 
	String meetingID = request.getParameter("meetingID");
	String username = request.getParameter("username");
	Connection conn = null;
	Statement st = null;
	ResultSet rs = null;
	
	Database db_meeting = new Database("MeetingInfo", "localhost", 3306);
	try {
		db_meeting.getConnection();
	} catch (SQLException e) {
		System.out.println(e.getMessage());
	}
    
    /*Meeting m = db_meeting.getMeeting(Integer.valueOf(meetingID));
    if(m == null){
    	System.out.println("m is null");
    	
    }
    else{
    	System.out.println("m is not null");
    	//TODO add user to userinfo
    }
	*/
%><%	
	try { //reflection, dynamically load an object at runtime 
		Class.forName("com.mysql.jdbc.Driver"); //at runtime instantiate this class
		conn = DriverManager.getConnection("jdbc:mysql://chronos.cerqtybkvakp.us-east-2.rds.amazonaws.com:3306/Chronos?user=root&password=rootpass&useSSL=false"); //can only load one database at a time and StudentGrades is the one we created
		st = conn.createStatement();
		if (meetingID != null) {
			rs = st.executeQuery("Select* from MeetingInfo"); //select returns a table that is placed in resultSet
		}
		
		//execute update tells you how many rows were updated
		//originally pointer in rs points to the space right before first row, can do next to go to another row
		boolean foundMeeting = false;
		while (rs.next()) {
			String myMeeting = rs.getString("meetingID");
			if (myMeeting.equals(meetingID)) {
				foundMeeting = true;
					//RequestDispatcher dispatch = getServletContext().getRequestDispatcher("/selectTimes.jsp");
					//dispatch.forward(request,response);
					//Statement st2 = conn.createStatement();
					//st.executeQuery("insert into GuestInfo(meetingID, username) values(" + meetingID + "," + username + ");");
					
					//TODO add user to userinfo
			}
			
			}
		if(!foundMeeting){
			%>
	    	 <font color="red"> You have entered an invalid meeting id. Please Try again. </font><br /> 
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