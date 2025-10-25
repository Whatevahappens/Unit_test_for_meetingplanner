package edu.sc.csce747.MeetingPlanner;

import static org.junit.Assert.*;
import org.junit.Test;

public class OrganizationTest {
	   
    @Test
    public void testOrganizationInitialization() {
        Organization org = new Organization();
        
        assertTrue("Should have employees", org.getEmployees().size() > 0);
        assertTrue("Should have rooms", org.getRooms().size() > 0);
    }
    
    @Test
    public void testGetExistingRoom() {
        Organization org = new Organization();
        
        try {
            Room room = org.getRoom("2A01");
            assertNotNull("Should return room", room);
            assertEquals("Room ID should match", "2A01", room.getID());
        } catch(Exception e) {
            fail("Should not throw exception for existing room");
        }
    }
    
    @Test
    public void testGetNonExistingRoom() {
        Organization org = new Organization();
        
        try {
            Room room = org.getRoom("NonExistentRoom");
            fail("Should have thrown exception for non-existent room");
        } catch(Exception e) {
            assertEquals("Should report room doesn't exist", "Requested room does not exist", e.getMessage());
        }
    }
    
    @Test
    public void testGetExistingEmployee() {
        Organization org = new Organization();
        
        try {
            Person employee = org.getEmployee("Greg Gay");
            assertNotNull("Should return employee", employee);
            assertEquals("Employee name should match", "Greg Gay", employee.getName());
        } catch(Exception e) {
            fail("Should not throw exception for existing employee");
        }
    }
    
    @Test
    public void testGetNonExistingEmployee() {
        Organization org = new Organization();
        
        try {
            Person employee = org.getEmployee("Non Existent");
            fail("Should have thrown exception for non-existent employee");
        } catch(Exception e) {
            assertEquals("Should report employee doesn't exist", "Requested employee does not exist", e.getMessage());
        }
    }
}
