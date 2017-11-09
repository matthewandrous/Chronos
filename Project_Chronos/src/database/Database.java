package database;

import java.sql.*;
import java.sql.SQLException;

import objectFiles.Host;

public class Database{
	
	private Connection conn = null;
	private String table = "";
	private String server = "";
	private String port = "";
	
	
	public Database(String table, String server, int port){
		
			this.table = table;
			this.server = server;
			this.table = table;
			
	}
	
	public boolean getConnection() throws SQLException{
		/*
		 * Attempts to connect to sql server
		 * Returns true on successful connect
		 */
		String myDriver = "com.mysql.jdbc.Driver";
		String myUrl = "jdbc:mysql://" + server + ":" + port + "/";
		
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
	
	public boolean addHost(String username, String password, String email) throws SQLException{
		/*
		 * Adds a new host to sql table given username, password, and email.
		 * Returns true on successful insert
		 */
		String query = String.format("INSERT INTO %s (username, hostPassword, email) VALUES (?, ?, ?)", table);
		
		try {
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, username);
			st.setString(2, password);
			st.setString(3, email);
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
		String query = String.format("SELECT hostId, password FROM %s WHERE username='%s'", table, username);
		String hostPassword = "";
		int hostId = -1;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				hostId = rs.getInt("hostId");
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
	
	public Host getHost(int hostId) {
		String query = String.format("SELECT username, password, email FROM %s WHERE hostId=%d", table, hostId);
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
			return h;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
