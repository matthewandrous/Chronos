/**
 * Here is a tester for User, Guest, Host, Meeting and Availability classes
 * No front-end or database involved
 */
package objectFiles;

public class ObjectTester {
	public static void main(String[] args) {
		//Assume there is a host with
		//username "Barack", password "Obama", email "obama@usc.edu" and id 100
		Host host = new Host();
		host.setUsername("Barack");
		host.setPassword("Obaba");
		host.setEmail("obama@usc.edu");
		host.setId(100);
		
		//The host starts a new meeting poll with 
		//2 participants, 2 hours per day and 3 days to choose from
		//(So there would be a 2*3 timetable)
		Meeting m = host.initializeMeeting(2, 2, 3);
			
		//And assume the meeting has an ID 201
		host.getCurMeeting().setMeetingID(201);
		
		//Known that host is unavailable day #1, hour #2 (which is index (1,0))
		//and available all the rest of the time
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 3; j++) {
				if(i == 2-1 && j == 1-1) {
					host.setAvailability(i, j, false);
				}
				else {
					host.setAvailability(i, j, true);
				}
			}
		}
		//tell the Meeting instance that the host answered
		m.getUsersAnswered().add(host);
		
		//A guest joins the meeting with username "Michelle" and id 101
		Guest guest = new Guest();
		guest.setUsername("Michelle");
		guest.setId(101);
		guest.joinMeeting(m);
		
		//The guest is unavailable day #2, hour #1 and  day #2, hour #2 (indexes (0,1) and (1,1))
		//and available all the rest of the time
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 3; j++) {
				if((i == 1-1 && j == 2-1) || (i == 2-1 && j == 2-1)) {
					guest.setAvailability(i, j, false);
				}
				else {
					guest.setAvailability(i, j, true);
				}
			}
		}
		//tell the Meeting instance that a guest answered
		m.getUsersAnswered().add(guest);
		
		//Expect 2 users answer the poll 
		if(m.isResultReady()) {
			//If 2 users have answered, print out meeting information
			System.out.println(m);
		}
	}
}
