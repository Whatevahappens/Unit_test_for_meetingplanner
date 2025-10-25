package edu.sc.csce747.MeetingPlanner;

import static org.junit.Assert.*;
import org.junit.Test;

public class PersonTest {
	 
    @Test
    public void testPersonCreation() {
        Person person = new Person("John Doe");
        assertEquals("Name should match", "John Doe", person.getName());
    }
    
    @Test
    public void testAddMeetingToPerson() {
        Person person = new Person("John Doe");
        Meeting meeting = new Meeting(5, 15, 9, 11);
        
        try {
            person.addMeeting(meeting);
            assertTrue("Person should be busy during meeting", person.isBusy(5, 15, 9, 11));
        } catch(TimeConflictException e) {
            fail("Should not throw exception: " + e.getMessage());
        }
    }
    
    @Test
    public void testAddMeetingConflict() {
        Person person = new Person("John Doe");
        Meeting meeting1 = new Meeting(5, 15, 9, 11);
        Meeting meeting2 = new Meeting(5, 15, 10, 12);
        
        try {
            person.addMeeting(meeting1);
            person.addMeeting(meeting2);
            fail("Should have thrown TimeConflictException");
        } catch(TimeConflictException e) {
            assertTrue("Error message should mention attendee conflict", e.getMessage().contains("Conflict for attendee"));
        }
    }
}
