package database;

import java.sql.*;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import objectFiles.Availability;
import objectFiles.Host;
import objectFiles.Meeting;
import objectFiles.User;

public class Database{
	
	private Connection conn = null;
	private String table = "";
	private String server = "";
	private int port = -1;
	
	//TODO
	//Add adduser function
	
	
	public Database(String table, String server, int port){
		
			this.table = table;
			this.server = server;
			this.port = port;
			
	}

	
	public boolean getConnection() throws SQLException{
		/*
		 * Attempts to connect to sql server
		 * Returns true on successful connect
		 */
		String myDriver = "com.mysql.jdbc.Driver";
		String myUrl = "jdbc:mysql://" + server + "/Chronos";
		
		//"?user=root&password=root&useSSL=false"
		
		try {
		Class.forName(myDriver);
		conn = DriverManager.getConnection(myUrl, "root", "root");
		System.out.println("Connected to SQL Database");
		return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	public boolean addUser(String username, String password, String email, boolean isHost) throws SQLException{
		/*
		 * Adds a new host to sql table given username, password, and email.
		 * Returns true on successful insert
		 */
		//boolean isHost = true;
		
		String checkQ = String.format("SELECT username FROM UserInfo WHERE username='%s'", username);
		
		boolean hostExists = false;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(checkQ);
			while (rs.next()) {
				
				hostExists = true;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		if (hostExists) {
			return false;
		}
		
		String query = String.format("INSERT INTO USERINFO (username, hostPassword, email, isHost) VALUES (?, ?, ?, ?)", table);

		
		try {
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, username);
			st.setString(2, password);
			st.setString(3, email);
			st.setBoolean(4, isHost);
			int result = st.executeUpdate();
			if (result > 0)
				return true;
			else
				return false;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public int authenticateHost(String username, String password) {
		/*
		 * Returns hostId if inputed password matches given username's password
		 * Returns -1 on password not match, -2 on anything else
		 */
		String query = String.format("SELECT userID, hostPassword FROM %s WHERE username='%s'", table, username);
		String hostPassword = "";
		int hostId = -1;
		boolean isHost = false;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				//TODO check whether the user is a host or not
				hostId = rs.getInt("userID");
				hostPassword = rs.getString("hostPassword");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			return -2;
		}
		if (hostPassword.equals(password)) {
			return hostId;
		}
		else {
			return -1;
		}
	}
	
	public int getUserId(String username) {
		String query = String.format("SELECT userID FROM userINFO WHERE username='%s'", table, username);
		int hostId = -1;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				hostId = rs.getInt("userID");
				
			}
			return hostId;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public String getResponseNames(int meetingId) {
		
		String query = String.format("SELECT userID FROM AvailabilityInfo WHERE meetingID='%d'", meetingId);
		ArrayList<Integer> userIdArray = new ArrayList<Integer>();
		
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				userIdArray.add(rs.getInt("userID"));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			return "";
		}
		StringBuilder sb = new StringBuilder();
		int counter = 0;
		for (int i : userIdArray) {
			try {
				String nameQuery = String.format("SELECT username FROM UserInfo WHERE UserID='%d'", userIdArray.get(counter++));
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(nameQuery);
				while (rs.next()) {
					sb.append(rs.getString("username"));
					sb.append(",");
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
				return "";
			}
		}
		
		if (sb.length() != 0) {
			sb.setLength(sb.length() - 1);
		}
		
		return sb.toString();
		
		
		
	}

	public Host getHost(int hostId) {
		/*
		 * Gets host object given hostID
		 */
		String query = String.format("SELECT username, hostPassword, email FROM %s WHERE userId=%d", "UserInfo", hostId);
		String username = "";
		String hostPassword = "";
		String email = "";
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				username = rs.getString("username");
				hostPassword = rs.getString("hostPassword");
				email = rs.getString("email");
			}	
			Host h = new Host(username, hostPassword, email);
			h.setUserId(hostId);
			return h;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public String getHostMeetings(int hostId) {
		
		
		String query = String.format("SELECT meetingID FROM %s WHERE hostID='%s'", table, hostId);
		StringBuilder sb = new StringBuilder();
		
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				int meetingId = rs.getInt("meetingID");
				sb.append(meetingId + ",");
			}
			
			if(sb.length() == 0) {
				return "";
			}
			sb.setLength(sb.length()-1);
			return sb.toString();
		}
		catch(SQLException e) {
			e.printStackTrace();
			return "";
		}
		
		
		
		
	}
	
	public Meeting getMeeting(int meetingId) {
		/*
		 * Returns meeting given meeting id
		 * 
		 */
		String query = String.format("SELECT * FROM MeetingInfo WHERE meetingID=%d", meetingId);
		int numUsers = -1;
		int hostId = -1;
		int startTime = 0;
		String startDateString = "";
		String meetingName = "";
		Date d = null;
		// number of day options ( = num cols of timetable)
		int numDays = 0;
		// number of meeting options per day ( = num rows of timetable)
		int numHoursPerDay = 0;
		// col: numDays, row: numHoursPerDay, ASSUME every cell is one hour long

		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				numUsers = rs.getInt("numUsers");
				hostId = rs.getInt("hostID");
				numDays = rs.getInt("numDays");
				numHoursPerDay = rs.getInt("numHoursPerDay");
				meetingName = rs.getString("meetingName");
				startTime = rs.getInt("startTime");
				startDateString = rs.getString("startDate");
				System.out.println(startDateString);
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
				try {
					d = (Date)formatter.parse(startDateString);
					System.out.println(d.getDate() + " " + d.getMonth() + " " + d.getYear());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Meeting m = new Meeting(numHoursPerDay, numDays, numUsers);
			m.setMeetingID(meetingId);
			m.setMeetingName(meetingName);
			m.setHost(getHost(hostId));
			m.setStartTime(startTime);
			m.setStartDate(d);
			return m;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public int addMeeting(String meetingName, int numUsers, int numDays, int numHoursPerDay, int hostId, Date startDate, int startTime) {
		SimpleDateFormat sdp = new SimpleDateFormat("yyyy-MM-dd");
		String s = sdp.format(startDate);
		System.out.println(s);
		System.out.println(meetingName + " " + numUsers + " " + numHoursPerDay+ " " + hostId+ " " + s+ " " +startTime);

		String query = String.format("INSERT INTO %s (meetingName, hostID, startDate, startTime, numUsers, numDays, numHoursPerDay) VALUES (\"%s\", %d, \"%s\", %d, %d, %d, %d)", table, meetingName, hostId, s, startTime, numUsers, numDays, numHoursPerDay);
		
		try {
			PreparedStatement st = conn.prepareStatement(query);
			int result = st.executeUpdate();
			
			try {
				String query2 = String.format("SELECT meetingID FROM %s WHERE meetingName='%s'", table, meetingName);
				Statement stat = conn.createStatement();
				ResultSet rs = stat.executeQuery(query2);
				int meetingID = -1;
				while (rs.next()) {
					meetingID = rs.getInt("meetingID");
				}
				
				if (result > 0)
					return meetingID;
				else
					return -1;
				
			}
			catch(SQLException e) {
				e.printStackTrace();
				return -1;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			return -1;
		}
		catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		
		
	}
	
	public boolean setAvailFromString(String bools, int meetingId, int userId) {
		
		List<String> list = new ArrayList<String>(Arrays.asList(bools.split(",")));
		
		Meeting m = getMeeting(meetingId);
		int counter = 0;
		
		String query = String.format("DELETE FROM AvailabilityInfo WHERE userID=%d AND meetingID=%d", userId, meetingId);
		
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.execute();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		for (int i = 0; i < m.getNumHoursPerDay(); i++) {
			for (int j = 0; j < m.getNumDays(); j++) {
				String queryInsert = String.format("INSERT INTO %s (meetingID, userID, rowIndex, colIndex, available) VALUES (%d, %d, %d, %d, %s)", table, meetingId, userId, i, j, list.get(counter++));
				try {
					PreparedStatement ps = conn.prepareStatement(queryInsert);
					ps.execute();
				}
				catch(SQLException e) {
					e.printStackTrace();
					return false;
				}
			}
		}
		
		return false;
		
	}
	
	public boolean setAvailabilities(Availability[][] av, int meetingId) {
		/*
		 * Given array of availabilities, creates many sql entries that stores user available time periods as booleans in table
		 * Message me on slack if you want me to explain it
		 */
		

		//int userId = av[0][0].getUserId();
		//int meetingId = av[0][0].getMeetingId();
		
		Availability clearA = av[0][0];
		
		for (User u : clearA.getAvailableUsers()) {
			String query = String.format("DELETE FROM AvailabilityInfo WHERE userID=%d AND meetingID=%d", u.getUserId(), meetingId);
			
			try {
				PreparedStatement ps = conn.prepareStatement(query);
				ps.execute();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		for (User u : clearA.getUnavailableUsers()) {
			String query = String.format("DELETE FROM AvailabilityInfo WHERE userID=%d AND meetingID=%d", u.getUserId(), meetingId);
			
			try {
				PreparedStatement ps = conn.prepareStatement(query);
				ps.execute();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}

		for (int i = 0; i < av.length; i++) {
			for (int j = 0; j < av[0].length; j++) {
				Availability a = av[i][j];
				
				System.out.println("Test");
				
				
				for (User u : a.getAvailableUsers()) {
					
//					String query = String.format("DELETE FROM AvailabilityInfo WHERE userID=%d", u.getUserId());
//					
//					try {
//						PreparedStatement ps = conn.prepareStatement(query);
//						ps.execute();
//					}
//					catch(SQLException e) {
//						e.printStackTrace();
//					}
					
					String queryInsert = String.format("INSERT INTO %s (meetingID, userID, rowIndex, colIndex, available) VALUES (%d, %d, %d, %d, %d)", table, meetingId, u.getUserId(), i, j, 1);
					try {
						PreparedStatement ps = conn.prepareStatement(queryInsert);
						ps.execute();
					}
					catch(SQLException e) {
						e.printStackTrace();
						return false;
					}
				}
				for (User u : a.getUnavailableUsers()) {
					
//					String query = String.format("DELETE FROM AvailabilityInfo WHERE userID=%d", u.getUserId());
//					
//					try {
//						PreparedStatement ps = conn.prepareStatement(query);
//						ps.execute();
//					}
//					catch(SQLException e) {
//						e.printStackTrace();
//					}
					
					String queryInsert = String.format("INSERT INTO %s (meetingID, userID, rowIndex, colIndex, available) VALUES (%d, %d, %d, %d, %d)", table, meetingId, u.getUserId(), i,j, 0);
					try {
						PreparedStatement ps = conn.prepareStatement(queryInsert);
						ps.execute();
					}
					catch(SQLException e) {
						e.printStackTrace();
						return false;
					}
				}
				
			}
		}
		
		return false;
	}
	
	public String getMeetingAvailabilities(int meetingId) {
		/*
		 * 
		 * Sends output string to frontend for number of users available / unavailable at that time
		 * 
		 */
		
		Meeting currMeeting = getMeeting(meetingId);
		if (currMeeting == null) {
			System.out.println("No meeting with that ID");
			return "";
		}
		String query = String.format("SELECT * FROM AvailabilityInfo WHERE meetingID=%d", meetingId);
		int[][] availabilityCount;
		//System.out.println(x);
		availabilityCount = new int[currMeeting.getNumHoursPerDay()][currMeeting.getNumDays()];
		
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				int startTime = rs.getInt("rowIndex"); //start time from zero, assuming zero will be adjusted in the meeting
				//int endTime = rs.getInt("endTime");  end time from zero. (Ex. endtime of 1 with meeting startTime of 8 am = 9 am)
				int userId = rs.getInt("userID");
				int day = rs.getInt("colIndex"); //day from zero, so day 0 = first day. Keep dates tracked in meeting objects
				boolean available = rs.getBoolean("available"); //if user is busy during this time period
				if (!available) {
					availabilityCount[startTime][day]++;
				}
			}
			StringBuilder sb = new StringBuilder();
			/*for (int i = 0; i < currMeeting.getNumHoursPerDay(); i++) {
				for (int j = 0; j < currMeeting.getNumDays(); j++) {
					sb.append(availabilityCount[i][j]);
					sb.append(",");
				}
			}*/
			for (int i = 0; i < currMeeting.getNumHoursPerDay(); i++) {
				for (int j = 0; j < currMeeting.getNumDays(); j++) {
					sb.append(availabilityCount[i][j]);
					sb.append(",");
				}
			}
			sb.setLength(sb.length()-1);
			return sb.toString();
		}
		catch(SQLException e) {
			e.printStackTrace();
			return "";
		}
	
		
	}

}