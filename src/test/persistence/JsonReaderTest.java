package persistence;

import model.ListOfParkingSpaces;
import model.ParkingSpace;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ListOfParkingSpaces listOfParkingSpaces = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyListOfParkingSpaces() {
        try {
            ListOfParkingSpaces listOfParkingSpaces = new ListOfParkingSpaces();
            JsonWriter writer = new JsonWriter("./data/testReaderEmptyListOfParkingSpaces.json");
            writer.open();
            writer.write(listOfParkingSpaces);
            writer.close();
            JsonReader reader = new JsonReader("./data/testReaderEmptyListOfParkingSpaces.json");
            listOfParkingSpaces = reader.read();
            assertEquals(0, listOfParkingSpaces.length());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralListOfParkingSpaces() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralListOfParkingSpaces.json");
        try {
            ListOfParkingSpaces listOfParkingSpaces = new ListOfParkingSpaces();
            listOfParkingSpaces.addParkingSpace(new ParkingSpace("UBC", 6));
            listOfParkingSpaces.addParkingSpace(new ParkingSpace("Kistlano", 7));
            JsonWriter writer = new JsonWriter("./data/testReaderGeneralListOfParkingSpaces.json");
            writer.open();
            writer.write(listOfParkingSpaces);
            writer.close();
            listOfParkingSpaces = reader.read();
            assertEquals(2, listOfParkingSpaces.length());
            checkParkingSpace(listOfParkingSpaces.getParkingSpaceOfIndex(1), "UBC", 6);
            checkParkingSpace(listOfParkingSpaces.getParkingSpaceOfIndex(2), "Kistlano", 7);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
