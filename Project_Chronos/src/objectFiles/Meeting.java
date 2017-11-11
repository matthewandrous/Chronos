package objectFiles;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class Meeting {
	private int eventID;
	private Date startDate;
	// 24 hour clock
	private int startTime;
	private int numDays;
	// number of meeting options per day
	private int numMeetingsPerDay;
	private ArrayList<ArrayList<Availability>> timetable;
	private ArrayList<User> usersAnswered;
	private int numUsers;
	
	public Meeting() {
		
	}
	
	public int getNumUsersAvailable(int row, int col) {
		Availability curr_ava = timetable.get(row).get(col);
		return curr_ava.getNumAvailableUsers();
	}
	
	public void markTimetable(int row, int col, Boolean isAvailable, User user) {
		Availability curr_ava = timetable.get(row).get(col);
		curr_ava.setAvailability(user, isAvailable);
	}
	

}