package persistence;

import model.ListOfParkingSpaces;
import model.ParkingSpace;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkParkingSpace(ParkingSpace p, String location, double charge) {
        assertEquals(location, p.getLocation());
        assertEquals(charge, p.getCharge());
    }
}

