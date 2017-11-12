package objectFiles;

import java.sql.Time;
import java.util.Date;

public class Meeting {
	private int meetingID;
	private Date startDate;

	// 24 hour clock
	private int startTime;
	private int numDays;
	// number of meeting options per day
	private int numMeetingsPerDay;
	// col: numDays row: numMeetingsPerDay
	private ArrayList<ArrayList<Availability>> timetable;
	private ArrayList<User> usersAnswered;
	private int numUsers;
	
	

	public void setNumUsers(int n) {
		numUsers = n;
	}
	
	public void setNumDays(int n) {
		numDays = n;
	}
	
	public void setNumMeetingsPerDay(int n) {
		numMeetingsPerDay = n;
	}
	
	public int getMeetingID() {
		return meetingID;
	}
	public int getNumUsersAvailable(int row, int col) {
		Availability curr_ava = timetable.get(row).get(col);
		return curr_ava.getNumAvailableUsers();
	}
}