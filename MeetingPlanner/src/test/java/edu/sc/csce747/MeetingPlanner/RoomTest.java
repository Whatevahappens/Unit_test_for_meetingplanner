package edu.sc.csce747.MeetingPlanner;

import static org.junit.Assert.*;
import org.junit.Test;

public class RoomTest {
 
    @Test
    public void testRoomCreation() {
        Room room = new Room("2A01");
        assertEquals("Room ID should match", "2A01", room.getID());
    }
    
    @Test
    public void testAddMeetingToRoom() {
        Room room = new Room("2A01");
        Meeting meeting = new Meeting(5, 15, 9, 11);
        
        try {
            room.addMeeting(meeting);
            assertTrue("Room should be busy during meeting", room.isBusy(5, 15, 9, 11));
        } catch(TimeConflictException e) {
            fail("Should not throw exception: " + e.getMessage());
        }
    }
    
    @Test
    public void testAddMeetingConflict() {
        Room room = new Room("2A01");
        Meeting meeting1 = new Meeting(5, 15, 9, 11);
        Meeting meeting2 = new Meeting(5, 15, 10, 12);
        
        try {
            room.addMeeting(meeting1);
            room.addMeeting(meeting2);
            fail("Should have thrown TimeConflictException");
        } catch(TimeConflictException e) {
            assertTrue("Error message should mention room conflict", e.getMessage().contains("Conflict for room"));
        }
    }
}
