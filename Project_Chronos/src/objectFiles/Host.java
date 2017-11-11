package objectFiles;

import java.util.ArrayList;

public class Host extends User{
	private String passwordHash;
	private ArrayList<Meeting> meetingEvents;

	public Host(String username, String passwordHash, String email) {
		super(username, email);
		this.passwordHash = passwordHash;
	}
	
	public boolean authenticate(String password){
		//TODO
		return true;
	}
	
	public void initializeMeetingEvent(int numUsers, String startDate, 
					String startTime, String endDate, String endTime){
		  
	}

}
