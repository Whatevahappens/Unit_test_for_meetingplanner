package edu.sc.csce747.MeetingPlanner;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;

public class CalendarTest {
    
    @Test
    public void testAddMeeting_validMeeting() {
        Calendar calendar = new Calendar();
        try {
            Meeting meeting = new Meeting(5, 15, 9, 11, new ArrayList<Person>(), new Room("2A01"), "Team Meeting");
            calendar.addMeeting(meeting);
            assertTrue("Should be busy during meeting hours", calendar.isBusy(5, 15, 9, 11));
        } catch(TimeConflictException e) {
            fail("Should not throw exception for valid meeting: " + e.getMessage());
        }
    }
    
    @Test
    public void testAddMeeting_timeConflict() {
        Calendar calendar = new Calendar();
        try {
            Meeting meeting1 = new Meeting(5, 15, 9, 11, new ArrayList<Person>(), new Room("2A01"), "Team Meeting");
            Meeting meeting2 = new Meeting(5, 15, 10, 12, new ArrayList<Person>(), new Room("2A01"), "Conflict Meeting");
            calendar.addMeeting(meeting1);
            calendar.addMeeting(meeting2);
            fail("Should have thrown TimeConflictException for overlapping meetings");
        } catch(TimeConflictException e) {
            assertTrue("Should report time conflict", e.getMessage().contains("Overlap with another item"));
        }
    }
    
    @Test
    public void testAddMeeting_invalidDate_February30() {
        Calendar calendar = new Calendar();
        try {
            Meeting invalidMeeting = new Meeting(2, 30, 9, 11, new ArrayList<Person>(), new Room("2A01"), "Invalid Date Meeting");
            calendar.addMeeting(invalidMeeting);
            fail("Should have thrown TimeConflictException for invalid date");
        } catch(TimeConflictException e) {
            assertEquals("Should report day doesn't exist", "Day does not exist.", e.getMessage());
        }
    }
    
    @Test
    public void testAddMeeting_invalidTime_boundaryConditions() {
        Calendar calendar = new Calendar();
        
        // Test negative hour
        try {
            Meeting invalidMeeting = new Meeting(5, 15, -1, 11, new ArrayList<Person>(), new Room("2A01"), "Invalid Time Meeting");
            calendar.addMeeting(invalidMeeting);
            fail("Should have thrown TimeConflictException for negative hour");
        } catch(TimeConflictException e) {
            assertEquals("Should report illegal hour", "Illegal hour.", e.getMessage());
        }
        
        // Test hour > 23
        try {
            Meeting invalidMeeting = new Meeting(5, 15, 9, 24, new ArrayList<Person>(), new Room("2A01"), "Invalid Time Meeting");
            calendar.addMeeting(invalidMeeting);
            fail("Should have thrown TimeConflictException for hour > 23");
        } catch(TimeConflictException e) {
            assertEquals("Should report illegal hour", "Illegal hour.", e.getMessage());
        }
        
        // Test start time >= end time
        try {
            Meeting invalidMeeting = new Meeting(5, 15, 14, 10, new ArrayList<Person>(), new Room("2A01"), "Invalid Time Meeting");
            calendar.addMeeting(invalidMeeting);
            fail("Should have thrown TimeConflictException for start after end");
        } catch(TimeConflictException e) {
            assertEquals("Should report start before end", "Meeting starts before it ends.", e.getMessage());
        }
    }
    
    @Test
    public void testAddMeeting_validBoundaryConditions() {
        Calendar calendar = new Calendar();
        
        try {
            // Test valid boundary times
            Meeting earlyMeeting = new Meeting(5, 15, 0, 1); // Midnight to 1 AM
            Meeting lateMeeting = new Meeting(5, 15, 22, 23); // 10 PM to 11 PM
            calendar.addMeeting(earlyMeeting);
            calendar.addMeeting(lateMeeting);
            
            // Test valid boundary dates
            Meeting jan1 = new Meeting(1, 1, 9, 10); // January 1st
            Meeting dec31 = new Meeting(12, 31, 9, 10); // December 31st
            calendar.addMeeting(jan1);
            calendar.addMeeting(dec31);
            
            // Should not throw any exceptions
            assertTrue("All valid boundary meetings should be added", true);
        } catch(TimeConflictException e) {
            fail("Should not throw exception for valid boundary conditions: " + e.getMessage());
        }
    }
}