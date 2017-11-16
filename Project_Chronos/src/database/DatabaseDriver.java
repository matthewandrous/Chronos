package database;

import java.sql.SQLException;

import objectFiles.Host;

public class DatabaseDriver {
	
	public static void main (String args[]) {
		Database db = new Database("HostInfo", "localhost", 3306);
		try {
			db.getConnection();
			db.addHost("byron", "root", "a@gmail.com");
		} catch (SQLException e) {
			System.out.println("test");
			e.printStackTrace();
		}
		
		Host h = db.getHost(1);
		System.out.println(h.getEmail() + h.getUsername() + h.getPassword());
		
		
		
		
	}

}
