/**
 * Super class for Host and Guest
 */
package objectFiles;

public abstract class User{
	private String username = "";
	private Meeting curMeeting;
	
	/**
	 * Empty constructor
	 */
	public User() {}
	
	/**
	 * Constructor
	 * @param username
	 * @param email
	 */
	public User(String username) {
		this.username = username;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the curMeeting
	 */
	public Meeting getCurMeeting() {
		return curMeeting;
	}

	/**
	 * @param curMeeting the curMeeting to set
	 */
	public void setCurMeeting(Meeting curMeeting) {
		this.curMeeting = curMeeting;
	}
	
	/**
	 * Mark this user's availability on the timetable of curMeeting
	 * @param isAvailable true when the use is available 
	 * @param startTime 
	 * @param endTime
	 */
	public void setAvailability(int row, int col, boolean isAvailable){
		curMeeting.markTimetable(row, col, isAvailable, this);
	}	
	
	//TODO
	public void submit(){
		//submit changes to the database?
	}

	/**
	 * @return string representation of the object
	 */
	@Override
	public String toString() {
		return "username=" + username + ", "
				+ "curMeetingID=" 
				+ curMeeting.getMeetingID();
	}
}