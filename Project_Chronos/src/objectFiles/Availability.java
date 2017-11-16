/**
 * Availability class includes a list of users who are available
 * and a list of users who are not
 */
package objectFiles;

import java.util.ArrayList;

public class Availability {
	private ArrayList<User> availableUsers;
	private ArrayList<User> unavailableUsers;

	private int meetingId;
	//private int userId;
	private int startTime;
	private int day;
	
	
	/**
	 * Constructor
	 */
	public Availability() {
		this.availableUsers = new ArrayList<>();
		this.unavailableUsers = new ArrayList<>();
	}
	
	public int getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(int meetingId) {
		this.meetingId = meetingId;
	}

//	public int getUserId() {
//		return userId;
//	}
//
//	public void setUserId(int userId) {
//		this.userId = userId;
//	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}
	
	/**
	 * Get the number of available users
	 * @return the number of available users
	 */
	public int getNumAvailableUsers() {
 		return availableUsers.size();
 	}
	 
 	
	/**
	 * @return the number of unavailable users
	 */
	public int getNumUnavailableUsers() {
		return unavailableUsers.size();
	}
	
	/**
	 * Add users to ArrayLists based on their availability
	 * @param user
	 * @param isAvailable
	 */
 	public void setAvailability(User user, Boolean isAvailable) {
 		if (isAvailable) {
 			availableUsers.add(user);
 		}
 		else {
 			unavailableUsers.add(user);
 		}
 	}
	/**
	 * Get available users
	 * @return the availableUsers
	 */
	public ArrayList<User> getAvailableUsers() {
		return availableUsers;
	}
	/**
	 * Set available users
	 * @param availableUsers the availableUsers to set
	 */
	public void setAvailableUsers(ArrayList<User> availableUsers) {
		this.availableUsers = availableUsers;
	}
	/**
	 * Get unavailable users
	 * @return the unavailableUsers
	 */
	public ArrayList<User> getUnavailableUsers() {
		return unavailableUsers;
	}
	/**
	 * Set unavailable users
	 * @param unavailableUsers the unavailableUsers to set
	 */
	public void setUnavailableUsers(ArrayList<User> unavailableUsers) {
		this.unavailableUsers = unavailableUsers;
	}
	
	/**
	 * Check if no user is unavailable
	 * @return true when no user is unavailable
	 */
	public boolean isAvailable() {
		return unavailableUsers.isEmpty();
	}

	/**
	 * @return string representation of the object
	 */
	@Override
	public String toString() {
		String s = "";
		s += "Available: ";
		for(int i = 0; i < availableUsers.size(); i++) {
			s += availableUsers.get(i).toString() + ", ";
		}
		s += "\n";
		
		s += "Unavailable: ";
		for(int i = 0; i < unavailableUsers.size(); i++) {
			s += unavailableUsers.get(i).toString() + ", ";
		}
		s += "\n";
		
		return s;
	}	
}
