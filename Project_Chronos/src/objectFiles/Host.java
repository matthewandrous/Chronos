package objectFiles;

import java.util.ArrayList;
import java.util.HashMap;

public class Host extends User{
	private String password;
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
	 * Initialize a new meeting
	 * @param numUsers
	 * @param numRows
	 * @param numCols
	 */
	public void initializeMeeting(int numUsers, int numHoursPerDay, int numDays){
		  Meeting newM = new Meeting();
		  newM.setNumUsers(numUsers);
		  newM.setnumHoursPerDay(numHoursPerDay);
		  newM.setNumDays(numDays);
		  this.setCurMeeting(newM);
	}
	
	/**
	 * This method will not be used if we're going to 
	 * use Database.authenticateHost()
	 * 
	 * Check if input password matches this host's password
	 * @param password
	 * @return password is correct
	 */
	/*public boolean authenticate(String password){
		return password.equals(this.password);
	}*/
	
}
