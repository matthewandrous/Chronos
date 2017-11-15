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
	
	public Date getStartDate() {
		return startDate;
	}
	
	public int getNumUsersAnswered() {
		return usersAnswered.size();
	}
	
	public int getStartTime() {
		return startTime;
	}
	
	public String getUsersAnswered() {
		String s = "";
		for (int i=0; i<usersAnswered.size(); i++) {
			if (i != 0) {
				s += ", ";
			}
			s += usersAnswered.get(i).getUsername();
		}
		return s;
	}
	
	public String getResponseTimes() {
		String s = "";
		for(int i=0; i<numHoursPerDay; i++) {
			for (int j=0; j < numDays; j++) {
				if (i != 0 && j != 0) {
					s += ",";
				}
				Availability curr_a = timetable.get(i).get(j);
				s += curr_a.getNumUnavailableUsers();
			}
		}
		return s;
		
	}
	

}