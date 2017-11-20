Use Chronos;

USE Chronos;
CREATE TABLE MeetingInfo (
	-- the very first row will be classID 1 and each time I add a row classId will increment
	meetingID int(11) primary key auto_increment, -- name f column followed by the type(number of characters)
	meetingName varchar(20),
    hostID int(11) Not null, -- varchar is a variable length character
	startDate Date not null,
    startTime int(11) not null,
    numUsers int(11),
    numDays int(11),
    numHoursPerDay int(11)
    
);


        
create table UserInfo (
	userID int(11) primary key auto_increment,
    username varchar(20),
    hostPassword varchar(20),
    email varchar(20),
    isHost bool not null
);

/*
create table GuestInfo(
	guestID int(11) primary key auto_increment,
    meetingID int(11),
    foreign key fk1(meetingID) references MeetingInfo(meetingID)

);*/

create table AvailabilityInfo(
	meetingID int(11),
	foreign key fk1(meetingID) references MeetingInfo(meetingID),
    userId int(11) not null,
    foreign key fk2(userID) references UserInfo(userID),
    rowIndex int(11) not null,
    colIndex int(11) not null,
    available bool not null
);



