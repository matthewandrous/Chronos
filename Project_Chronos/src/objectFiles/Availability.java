package objectFiles;

import java.util.ArrayList;

public class Availability {
	private ArrayList<User> availableUsers;
	private ArrayList<User> unAvailableUsers;
	/**
	 * @return the availableUsers
	 */
	public ArrayList<User> getAvailableUsers() {
		return availableUsers;
	}
	/**
	 * @param availableUsers the availableUsers to set
	 */
	public void setAvailableUsers(ArrayList<User> availableUsers) {
		this.availableUsers = availableUsers;
	}
	/**
	 * @return the unAvailableUsers
	 */
	public ArrayList<User> getUnAvailableUsers() {
		return unAvailableUsers;
	}
	/**
	 * @param unAvailableUsers the unAvailableUsers to set
	 */
	public void setUnAvailableUsers(ArrayList<User> unAvailableUsers) {
		this.unAvailableUsers = unAvailableUsers;
	}
	
	/**
	 * @return true when no user is unavailable
	 */
	boolean isAvailable() {
		return unAvailableUsers.isEmpty();
	}
}
