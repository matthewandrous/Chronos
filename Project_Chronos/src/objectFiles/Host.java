package objectFiles;

import java.util.ArrayList;
import java.util.HashMap;

public class Host extends User{
	private String password;
	private HashMap<Integer, Meeting> meetings;
	private Meeting curMeeting;

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	

	/**
	 * @return the meetings
	 */
	public HashMap<Integer, Meeting> getMeetings() {
		return meetings;
	}

	/**
	 * @param meetings the meetings to set
	 */
	public void setMeetings(HashMap<Integer, Meeting> meetings) {
		this.meetings = meetings;
	}

	/**
	 * @return the curMeeting
	 */
	public Meeting getCurMeeting() {
		return curMeeting;
	}

	/**
	 * Set current meeting to the given meeting
	 * @param curMeeting the curMeeting to set
	 */
	public void setCurMeeting(Meeting curMeeting) {
		this.curMeeting = curMeeting;
	}

	/**
	 * Set current meeting based on given meeting id
	 * @param meetingID
	 */
	public void setCurMeeting(int meetingID) {
		this.setCurMeeting(meetings.get(meetingID));
	}
	
	/**
	 * Add a meeting instance to this host's meetings list
	 * @param meeting
	 */
	public void addMeeting(Meeting meeting) {
		this.meetings.put(meeting.getMeetingID(), meeting);
	}
	
	/**
	 * Initialize a new meeting
	 * @param numUsers
	 * @param numRows
	 * @param numCols
	 */
	public void initializeMeeting(int numUsers, int numRows, int numCols){
		  Meeting newM = new Meeting();
		  newM.setNumUsers(numUsers);
		  newM.setNumMeetingsPerDay(numRows);
		  newM.setNumDays(numCols);
		  this.addMeeting(newM);
	}
	
	/**
	 * This method will not be used if we're going to 
	 * use Database.authenticateHost()
	 * 
	 * Check if input password matches this host's password
	 * @param password
	 * @return password is correct
	 */
	public boolean authenticate(String password){
		return password.equals(this.password);
	}
	
}
