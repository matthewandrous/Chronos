/**
 * Guest class - guests are those who join a meeting and fill in their availability
 */
package objectFiles;

public class Guest extends User {	
	/**
	 * Given an instance of Meeting, guest will join it
	 * a.k.a curMeeting will be set to this instance of Meeting
	 * @param meeting
	 */
	public void joinMeeting(Meeting meeting) {
		this.setCurMeeting(meeting);
	}

	/**
	 * @return string representation of the object
	 */
	@Override
	public String toString() {
		return "Guest [" + super.toString() + "]";
	}
	
}
