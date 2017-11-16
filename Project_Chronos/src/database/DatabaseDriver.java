package database;

import java.sql.SQLException;

import objectFiles.Host;

public class DatabaseDriver {
	
	public static void main (String args[]) {
		Database db = new Database("HostInfo", "localhost", 3306);
		try {
			db.getConnection();
			db.addHost("byron", "root", "a@gmail.com"); //testing add host WORKS
		} catch (SQLException e) {
			System.out.println("test");
			e.printStackTrace();
		}
		
		Host h = db.getHost(1); //testing gethost WORKS
		System.out.println(h.getEmail() + h.getUsername() + h.getPassword());
		System.out.println(db.authenticateHost("byron", "root")); //should be 2 WORKS
		System.out.println(db.authenticateHost("byron", "root2")); //should be -1 WORKS
		
		//Not tested: Get meeting, setavailabilities
		
		
		
	}

}
