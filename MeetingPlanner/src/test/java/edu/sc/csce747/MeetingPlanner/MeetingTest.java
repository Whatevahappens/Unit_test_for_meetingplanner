package edu.sc.csce747.MeetingPlanner;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class MeetingTest {
	@Test
    public void testMeetingCreation_validParameters() {
        ArrayList<Person> attendees = new ArrayList<Person>();
        attendees.add(new Person("John Doe"));
        Room room = new Room("2A01");
        
        Meeting meeting = new Meeting(5, 15, 9, 11, attendees, room, "Team Meeting");
        
        assertEquals("Month should match", 5, meeting.getMonth());
        assertEquals("Day should match", 15, meeting.getDay());
        assertEquals("Start time should match", 9, meeting.getStartTime());
        assertEquals("End time should match", 11, meeting.getEndTime());
        assertEquals("Description should match", "Team Meeting", meeting.getDescription());
        assertEquals("Room should match", room, meeting.getRoom());
        assertEquals("Attendees should match", attendees, meeting.getAttendees());
    }
    
    @Test
    public void testMeetingToString() {
        ArrayList<Person> attendees = new ArrayList<Person>();
        attendees.add(new Person("John Doe"));
        Room room = new Room("2A01");
        
        Meeting meeting = new Meeting(5, 15, 9, 11, attendees, room, "Team Meeting");
        String meetingString = meeting.toString();
        
        assertTrue("String should contain date", meetingString.contains("5/15"));
        assertTrue("String should contain time", meetingString.contains("9 - 11"));
        assertTrue("String should contain room", meetingString.contains("2A01"));
        assertTrue("String should contain description", meetingString.contains("Team Meeting"));
        assertTrue("String should contain attendee", meetingString.contains("John Doe"));
    }
    
    @Test
    public void testAllDayMeeting() {
        Meeting meeting = new Meeting(5, 15, "Holiday");
        
        assertEquals("Start time should be 0 for all-day", 0, meeting.getStartTime());
        assertEquals("End time should be 23 for all-day", 23, meeting.getEndTime());
        assertEquals("Description should match", "Holiday", meeting.getDescription());
    }
}
