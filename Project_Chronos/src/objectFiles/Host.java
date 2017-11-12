/**
 * Host class - hosts are those who initialize meetings and view the results
 */

package objectFiles;

public class Host extends User{
	private String password;
	private String email;

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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param curMeeting the curMeeting to set
	 */
	@Override
	public void setCurMeeting(Meeting curMeeting) {
		super.setCurMeeting(curMeeting);
		curMeeting.setHost(this);
	}

	/**
	 * Initialize a new meeting which this will host
	 * @param numUsers
	 * @param numRows
	 * @param numCols
	 * @return the Meeting object created
	 */
	public Meeting initializeMeeting(int numUsers, 
									 int numHoursPerDay, 
									 int numDays){
		  Meeting newM = new Meeting(numHoursPerDay, numDays, numUsers);
		  newM.setHost(this);
		  this.setCurMeeting(newM);
		  return newM;
	}

	/**
	 * @return string representation of the object
	 */
	@Override
	public String toString() {
		return "Host [" + super.toString() + ", password=" + password 
				+ ", email=" + email + "]";
	}
}
