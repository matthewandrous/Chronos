/**
 * 
 */
package objectFiles;

public class Guest extends User {	
	public void joinMeeting(Meeting m) {
		this.setCurMeeting(m);
	}
}
