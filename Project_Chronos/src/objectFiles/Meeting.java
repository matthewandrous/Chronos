/**
 * Meeting class
 */
package objectFiles;

import java.util.ArrayList;

public class Meeting {
	//data members
	private int meetingID;
	private int numUsers;
	private User host;
	// number of day options ( = num cols of timetable)
	private int numDays;
	// number of meeting options per day ( = num rows of timetable)
	private int numHoursPerDay;
	// col: numDays, row: numHoursPerDay, ASSUME every cell is one hour long
	private Availability[][] timetable;
	private ArrayList<User> usersAnswered;
	
	/**
	 * Constructor
	 */
	public Meeting(int numHoursPerDay, int numDays, int numUsers) {
		this.numHoursPerDay = numHoursPerDay;
		this.numDays = numDays;
		this.numUsers = numUsers;
		timetable = new Availability[numHoursPerDay][numDays];
		for(int i = 0; i < numHoursPerDay; i++) {
			for(int j = 0; j < numDays; j++) {
				timetable[i][j] = new Availability();
			}
		}
		usersAnswered = new ArrayList<>();
	}
	
	/**
	 * @return meetingID
	 */
	public int getMeetingID() {
		return this.meetingID;
	}
	
	/**
	 * @param meetingID the meetingID to set
	 */
	public void setMeetingID(int meetingID) {
		this.meetingID = meetingID;
	}
	
	/**
	 * @return the numUsers
	 */
	public int getNumUsers() {
		return this.numUsers;
	}
	
	/**
	 * 
	 * @param num the number of users of this meeting
	 */
	public void setNumUsers(int num) {
		this.numUsers = num;
	}
	
	/**
	 * @return the host
	 */
	public User getHost() {
		return host;
	}

	/**
	 * @param host the host to set
	 */
	public void setHost(User host) {
		this.host = host;
	}
	
	/**
	 * @return the numDays
	 */
	public int getNumDays() {
		return numDays;
	}
	
	/**
	 * @param num how many days can be chosen from
	 */
	public void setNumDays(int num) {
		this.numDays = num;
	}
	
	/**
	 * @return the numHoursPerDay
	 */
	public int getNumHoursPerDay() {
		return numHoursPerDay;
	}
	
	/**
	 * @param num how many hours can be chosen from each day
	 */
	public void setNumHoursPerDay(int num) {
		this.numHoursPerDay = num;
	}
	
	/**
	 * @return the timetable
	 */
	public Availability[][] getTimetable() {
		return timetable;
	}

	/**
	 * @param timetable the timetable to set
	 */
	public void setTimetable(Availability[][] timetable) {
		this.timetable = timetable;
	}

	/**
	 * @return the usersAnswered
	 */
	public ArrayList<User> getUsersAnswered() {
		return this.usersAnswered;
	}

	/**
	 * @param usersAnswered the usersAnswered to set
	 */
	public void setUsersAnswered(ArrayList<User> usersAnswered) {
		this.usersAnswered = usersAnswered;
	}

	
	/**
	 * Get how many users are available at a given position (row and col) 
	 * of the timetable
	 * @param row
	 * @param col
	 * @return numUsersAvailable 
	 */
	public int getNumUsersAvailable(int row, int col) {
		Availability curr_ava = timetable[row][col];
		return curr_ava.getNumAvailableUsers();
	}
	
	/**
	 * Get how many users are NOT available at a given position (row and col) 
	 * of the timetable
	 * @param row
	 * @param col
	 * @return numUsersUnavailable 
	 */
	public int getNumUsersUnavailable(int row, int col) {
		Availability curr_ava = timetable[row][col];
		return this.numUsers - curr_ava.getNumAvailableUsers();
	}
	
	/**
	 * Gets the number of people available at a specific position (row and col)
	 * of the timetable
	 * @param row
	 * @param col
	 * @return number of available users
	 */
	public int getNumAvailableUsers(int row, int col) {
		Availability curr_ava = timetable[row][col];
		return curr_ava.getNumAvailableUsers();
	}
	
	/**
	 * Marks who is available/unavailable at when on the timetable
	 * @param row
	 * @param col
	 * @param isAvailable
	 * @param user
	 */
	public void markTimetable(int row, int col, Boolean isAvailable, User user){
		Availability curr_ava = timetable[row][col];
		curr_ava.setAvailability(user, isAvailable);
	}
	
	/**
	 * @return true when all users have answered
	 */
	public boolean isResultReady() {
		return numUsers == usersAnswered.size();
	}

	/**
	 * @return string representation of the object
	 */
	@Override
	public String toString() {
		String timetableString = "\n";
		String usersAnsweredString = "\n";
		for(int i = 0; i < timetable.length; i++) {
			for(int j = 0; j < timetable[0].length; j++) {
				timetableString += "Row: " + i + ", Col: " + j + " | " 
						+ timetable[i][j].toString() + "\n";
			}
		}
		
		for(int i = 0; i < usersAnswered.size(); i++) {
			usersAnsweredString += usersAnswered.get(i).toString() + "\n";
		}
		return "Meeting [\nmeetingID=" + meetingID + ", numUsers=" + numUsers 
				+ ", host=" + host + ", numDays=" + numDays
				+ ", numHoursPerDay=" + numHoursPerDay 
				+ "\n\ntimetable=" + timetableString 
				+ "usersAnswered=" + usersAnsweredString
				+ "]";
	}
	
}