package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ListOfParkingSpaceTest {
    private ListOfParkingSpaces testListOfParkingSpace;
    private ParkingSpace testParkingSpace;

    @BeforeEach
    void runBefore() {
        testListOfParkingSpace = new ListOfParkingSpaces();
        testParkingSpace = new ParkingSpace(
                "6115 Student Union Boulevard V6T 1Z1, UBC", 1);
    }

    @Test
    void testConstructor() {
        testListOfParkingSpace.addParkingSpace(testParkingSpace);
        assertEquals("6115 Student Union Boulevard V6T 1Z1, UBC",
                testListOfParkingSpace.getParkingSpaceOfIndex(1).getLocation());
        assertEquals(1, testListOfParkingSpace.getParkingSpaceOfIndex(1).getCharge());
        assertTrue(testListOfParkingSpace.getParkingSpaceOfIndex(1).getAvailability());
    }

    @Test
    void testAddParkingSpace() {
        testListOfParkingSpace.addParkingSpace(testParkingSpace);
        testParkingSpace = new ParkingSpace("2250 Health Sciences Mall V6T 1Z3, UBC", 2);
        testListOfParkingSpace.addParkingSpace(testParkingSpace);
        assertEquals(2, testListOfParkingSpace.length());
        assertEquals("2250 Health Sciences Mall V6T 1Z3, UBC",
                testListOfParkingSpace.getParkingSpaceOfIndex(2).getLocation());
        assertEquals(2, testListOfParkingSpace.getParkingSpaceOfIndex(2).getCharge());
        assertTrue(testListOfParkingSpace.getParkingSpaceOfIndex(2).getAvailability());
    }

    @Test
    void testGetParkingSpaceOfIndex() {
        testListOfParkingSpace.addParkingSpace(testParkingSpace);
        assertEquals("6115 Student Union Boulevard V6T 1Z1, UBC",
                testListOfParkingSpace.getParkingSpaceOfIndex(1).getLocation());
    }

    @Test
    void testRemoveParkingSpaceOfIndex() {
        testListOfParkingSpace.addParkingSpace(testParkingSpace);
        testListOfParkingSpace.removeParkingSpaceOfIndex(1);
        assertTrue(testListOfParkingSpace.isEmpty());
        testListOfParkingSpace.addParkingSpace(testParkingSpace);
        testParkingSpace = new ParkingSpace("2250 Health Sciences Mall V6T 1Z3, UBC", 2);
        testListOfParkingSpace.addParkingSpace(testParkingSpace);
        testParkingSpace = new ParkingSpace("6278 NW Marine Drive V6T 1Z1, UBC", 5);
        testListOfParkingSpace.addParkingSpace(testParkingSpace);
        testListOfParkingSpace.removeParkingSpaceOfIndex(2);
        assertEquals(2, testListOfParkingSpace.length());
        assertEquals("6278 NW Marine Drive V6T 1Z1, UBC",
                testListOfParkingSpace.getParkingSpaceOfIndex(2).getLocation());
    }

    @Test
    void testChangeLocationOfIndex() {
        testListOfParkingSpace.addParkingSpace(testParkingSpace);
        testParkingSpace = new ParkingSpace("2250 Health Sciences Mall V6T 1Z3, UBC", 2);
        testListOfParkingSpace.addParkingSpace(testParkingSpace);
        testListOfParkingSpace.changeLocationOfIndex("6278 NW Marine Drive V6T 1Z1, UBC", 1);
        assertEquals("6278 NW Marine Drive V6T 1Z1, UBC",
                testListOfParkingSpace.getParkingSpaceOfIndex(1).getLocation());
    }

    @Test
    void testChangeChargeOfIndex() {
        testListOfParkingSpace.addParkingSpace(testParkingSpace);
        testParkingSpace = new ParkingSpace("2250 Health Sciences Mall V6T 1Z3, UBC", 2);
        testListOfParkingSpace.addParkingSpace(testParkingSpace);
        testListOfParkingSpace.changeChargeOfIndex(5, 1);
        assertEquals(5,
                testListOfParkingSpace.getParkingSpaceOfIndex(1).getCharge());
    }

    @Test
    void testChangeAvailabilityOfIndex() {
        testListOfParkingSpace.addParkingSpace(testParkingSpace);
        testParkingSpace = new ParkingSpace("2250 Health Sciences Mall V6T 1Z3, UBC", 2);
        testListOfParkingSpace.addParkingSpace(testParkingSpace);
        testListOfParkingSpace.changeAvailabilityOfIndex(1);
        assertFalse(testListOfParkingSpace.getParkingSpaceOfIndex(1).getAvailability());
    }

    @Test
    void testLength() {
        testListOfParkingSpace.addParkingSpace(testParkingSpace);
        testParkingSpace = new ParkingSpace("2250 Health Sciences Mall V6T 1Z3, UBC", 2);
        assertEquals(1, testListOfParkingSpace.length());
        testListOfParkingSpace.addParkingSpace(testParkingSpace);
        assertEquals(2, testListOfParkingSpace.length());
    }

    @Test
    void testIsEmpty() {
        assertTrue(testListOfParkingSpace.isEmpty());
        testListOfParkingSpace.addParkingSpace(testParkingSpace);
        testParkingSpace = new ParkingSpace("2250 Health Sciences Mall V6T 1Z3, UBC", 2);
        testListOfParkingSpace.addParkingSpace(testParkingSpace);
        assertFalse(testListOfParkingSpace.isEmpty());
    }

    @Test
    void testSearchParkingSpaces() {
        testListOfParkingSpace.addParkingSpace(testParkingSpace);
        testParkingSpace = new ParkingSpace("2250 Health Sciences Mall V6T 1Z3, UBC", 2);
        testListOfParkingSpace.addParkingSpace(testParkingSpace);
        assertEquals(2, testListOfParkingSpace.searchParkingSpaces("UBC").size());
        assertEquals(0, testListOfParkingSpace.searchParkingSpaces("Oakridge Centre").size());
        assertEquals("2250 Health Sciences Mall V6T 1Z3, UBC",
                testListOfParkingSpace.searchParkingSpaces("UBC").get(1).getLocation());
        assertEquals(2,
                testListOfParkingSpace.searchParkingSpaces("UBC").get(1).getCharge());
        assertTrue(
                testListOfParkingSpace.searchParkingSpaces("UBC").get(1).getAvailability());
        testParkingSpace = new ParkingSpace("5251 Oak St V6M 4H1", 5);
        testListOfParkingSpace.addParkingSpace(testParkingSpace);
        assertEquals(2, testListOfParkingSpace.searchParkingSpaces("UBC").size());
        assertEquals("2250 Health Sciences Mall V6T 1Z3, UBC",
                testListOfParkingSpace.searchParkingSpaces("UBC").get(1).getLocation());
        testParkingSpace = new ParkingSpace("6525 Oak St, Vancouver, BC V6P 3Z3", 6);
        testListOfParkingSpace.addParkingSpace(testParkingSpace);
        assertTrue(testListOfParkingSpace.searchParkingSpaces("Kistlano").isEmpty());
    }

    @Test
    void testSearchAvailableParkingSpaces() {
        testListOfParkingSpace.addParkingSpace(testParkingSpace);
        testParkingSpace = new ParkingSpace("2250 Health Sciences Mall V6T 1Z3, UBC", 2);
        testListOfParkingSpace.addParkingSpace(testParkingSpace);
        assertEquals(2, testListOfParkingSpace.searchAvailableParkingSpaces("UBC").size());
        assertEquals(0, testListOfParkingSpace.searchAvailableParkingSpaces("Oakridge Centre").size());
        testListOfParkingSpace.changeAvailabilityOfIndex(1);
        assertEquals(1, testListOfParkingSpace.searchAvailableParkingSpaces("UBC").size());
        testParkingSpace = new ParkingSpace("5251 Oak St V6M 4H1", 5);
        testListOfParkingSpace.addParkingSpace(testParkingSpace);
        assertEquals(2, testListOfParkingSpace.searchAvailableParkingSpaces("V6").size());
        assertEquals("5251 Oak St V6M 4H1",
                testListOfParkingSpace.searchParkingSpaces("V6").get(2).getLocation());

    }

}



