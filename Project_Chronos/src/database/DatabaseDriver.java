package database;


import java.sql.SQLException;
import java.util.Date;


import objectFiles.Guest;
import objectFiles.Host;
import objectFiles.Meeting;

public class DatabaseDriver {
	
	public static void main (String args[]) {
		Database db = new Database("UserInfo", "localhost", 3306);
		try {
			db.getConnection();
			db.addUser("byron", "root", "a@gmail.com", true); //testing add host WORKS
			db.addUser("muyao", "root", "r@gmail.com", false); //testing add host WORKS
			db.addUser("muyao", "root", "x@gmail.com", true); //should not work
			db.addUser("gautam2", "root5", "xt2@gmail.com", true); //should work
		} catch (SQLException e) {
			System.out.println("test");
			e.printStackTrace();
		}
		
		
		
		Host h = db.getHost(2); //testing gethost WORKS
		System.out.println(h.getEmail() + h.getUsername() + h.getPassword());
//		System.out.println(db.authenticateHost("byron", "root")); //should be 2 WORKS
//		System.out.println(db.authenticateHost("gautam", "mattsucks123")); //should be 2 WORKS
//		System.out.println(db.authenticateHost("byron", "root2")); //should be -1 WORKS
		System.out.println(db.authenticateHost("gautam2", "root5")); //should be -1 WORKS
		System.out.println("test2");
		
		Meeting m = new Meeting(3, 2, 3); //2 x 3 array with 3 users
		Date d = new Date(); //today's date
		m.setStartDate(d);
		m.setStartTime(8);
		m.setHost(h); //host is byron
		m.setMeetingName("testmeeting");
		Guest g = new Guest();
		g.setUserId(3);
		Guest g2 = new Guest();
		g2.setUserId(4);
		Database meetingDB = new Database("MeetingInfo", "localhost", 3306);
		try {
			meetingDB.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		
//		Meeting mtest = meetingDB.getMeeting(6);
//
//		m.setMeetingID(1);
//		
//		System.out.println(mtest.getMeetingName());
//		
//		
//		/*
//		 *		2	0
//		 * 		1	3
//		 * 		0	1
//		 * 
//		 */
//		
//		Availability av = new Availability();
//		
//	
//		m.getTimetable()[0][1].getAvailableUsers().add(g);      
//		m.getTimetable()[0][1].getAvailableUsers().add(h);
//		m.getTimetable()[0][1].getAvailableUsers().add(g2);
//		
//	
//		
//		
//		m.getTimetable()[0][0].getUnavailableUsers().add(g);
//		m.getTimetable()[0][0].getUnavailableUsers().add(h);
//		m.getTimetable()[0][0].getAvailableUsers().add(g2);
//		
//		m.getTimetable()[1][0].getAvailableUsers().add(g);
//		m.getTimetable()[1][0].getAvailableUsers().add(h);
//		m.getTimetable()[1][0].getUnavailableUsers().add(g2);
//		
//		m.getTimetable()[1][1].getUnavailableUsers().add(g);
//		m.getTimetable()[1][1].getUnavailableUsers().add(h);
//		m.getTimetable()[1][1].getUnavailableUsers().add(g2);
//		
//		m.getTimetable()[2][1].getUnavailableUsers().add(g);
//		m.getTimetable()[2][1].getAvailableUsers().add(h);
//		m.getTimetable()[2][1].getAvailableUsers().add(g2);
//		
//		m.getTimetable()[2][0].getAvailableUsers().add(g);
//		m.getTimetable()[2][0].getAvailableUsers().add(h);
//		m.getTimetable()[2][0].getAvailableUsers().add(g2);
//		
//		
//		
//		int meetingId = meetingDB.addMeeting(m.getMeetingName(), m.getNumUsers(), m.getNumDays(), m.getNumHoursPerDay(), m.getHost().getUserId(), m.getStartDate(), m.getStartTime());
//
//		
//		Database availDB = new Database("AvailabilityInfo", "localhost", 0);
//		try {
//			availDB.getConnection();
//		} catch (SQLException e) {
//		
//			e.printStackTrace();
//		}
//		
//		String test = "1,1,1,0,1,0";
//		availDB.setAvailFromString(test, meetingId, 2);
//		availDB.setAvailabilities(m.getTimetable(), 26);
//		
//		System.out.println(availDB.getMeetingAvailabilities(meetingId));
		
		
		
		
		
		
	}

}
