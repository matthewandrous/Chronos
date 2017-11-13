package objectFiles;

import java.util.ArrayList;
import java.util.Date;

public class Meeting {
	private int meetingID;
	private Date startDate;

	// 24 hour clock
	private int startTime;
	private int numDays;
	// number of meeting options per day
	private int numHoursPerDay;
	// col: numDays row: numHoursPerDay
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
	
	public void markTimetable(int index, int availability, User user) {
		int row = index / numDays;
		int col = index % numDays;
		Availability curr_ava = timetable.get(row).get(col);
		if (availability == 1) {
			curr_ava.setAvailability(user, true);
		}
		else {
			curr_ava.setAvailability(user, false);
		}
			
	}
	public void setNumUsers(int n) {
		numUsers = n;
	}
	
	public void setNumDays(int n) {
		numDays = n;
	}
	
	public void setnumHoursPerDay(int n) {
		numHoursPerDay = n;
	}
	
	public int getMeetingID() {
		return meetingID;
	}
	
	public int getNumAvailableUsers(int row, int col) {
		Availability curr_ava = timetable.get(row).get(col);
		return curr_ava.getNumAvailableUsers();
	}
	
	public int getNumDays() {
		return numDays;
	}
	
	public int getNumHoursPerDay() {
		return numHoursPerDay;
	}
}