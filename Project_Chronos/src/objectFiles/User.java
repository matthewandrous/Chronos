package objectFiles;

public abstract class User{
	private String username;
	private String email;
	private int id;
	
	//auto generate setters and getters
	
	public User(){
		this.username = "";
		this.email = "";
		this.id = -1;
	}
	
	public User(String username, String email) {
		this.username = username;
		this.email = email;
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
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	//TODO
	public void setAvailability(boolean isAvailable, 
							String startTime, 
							String endTime){
		
	}	
	
	//TODO
	public void submit(){
		
	}
	
	//TODO
	public void reset(){
		
	}
	
	//TODO
	public void selectAll(){
		
	}
}