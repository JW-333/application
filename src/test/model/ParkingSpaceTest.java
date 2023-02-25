package model;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ParkingSpaceTest {
    private ParkingSpace testParkingSpace;

    @BeforeEach
    void runBefore() {
        testParkingSpace = new ParkingSpace(
                "6115 Student Union Boulevard V6T 1Z1, UBC", 1);
    }

    @Test
    void testConstructor() {
        assertEquals("6115 Student Union Boulevard V6T 1Z1, UBC",
                testParkingSpace.getLocation());
        assertEquals(1, testParkingSpace.getCharge());
        assertTrue(testParkingSpace.getAvailability());
        assertTrue(testParkingSpace.getId() > 0);
        testParkingSpace = new ParkingSpace(
                "2250 Health Sciences Mall V6T 1Z3, UBC", 1);
        assertTrue(testParkingSpace.getId() > 0);
    }

    @Test
    void testUpdateLocation() {
        testParkingSpace.updateLocation("2250 Health Sciences Mall V6T 1Z3, UBC");
        assertEquals("2250 Health Sciences Mall V6T 1Z3, UBC",
                testParkingSpace.getLocation());
    }

    @Test
    void testUpdateCharge() {
        testParkingSpace.updateCharge(10);
        assertEquals(10, testParkingSpace.getCharge());
    }

    @Test
    void testUpdateAvailability() {
        testParkingSpace.updateAvailability();
        assertFalse(testParkingSpace.getAvailability());
    }

    @Test
    void testToString() {
        assertEquals("location:" + "6115 Student Union Boulevard V6T 1Z1, UBC"
                + ", " + 1.0 + "$/hour" + "," + "available",testParkingSpace.toString());
        testParkingSpace.updateAvailability();
        assertEquals("location:" + "6115 Student Union Boulevard V6T 1Z1, UBC"
                + ", " + 1.0 + "$/hour" + "," + "unavailable",testParkingSpace.toString());
    }
}
