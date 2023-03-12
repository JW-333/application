package persistence;

import model.ParkingSpace;
import model.ListOfParkingSpaces;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyListOfParkingSpaces() {
        try {
            ListOfParkingSpaces listOfParkingSpaces = new ListOfParkingSpaces();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyListOfParkingSpaces.json");
            writer.open();
            writer.write(listOfParkingSpaces);
            writer.close();
            JsonReader reader = new JsonReader("./data/testWriterEmptyListOfParkingSpaces.json");
            listOfParkingSpaces = reader.read();
            assertEquals(0, listOfParkingSpaces.length());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralListOfParkingSpaces() {
        try {
            ListOfParkingSpaces listOfParkingSpaces = new ListOfParkingSpaces();
            listOfParkingSpaces.addParkingSpace(new ParkingSpace("UBC", 6));
            listOfParkingSpaces.addParkingSpace(new ParkingSpace("Kistlano", 7));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralListOfParkingSpaces.json");
            writer.open();
            writer.write(listOfParkingSpaces);
            writer.close();
            JsonReader reader = new JsonReader("./data/testWriterGeneralListOfParkingSpaces.json");
            listOfParkingSpaces = reader.read();
            assertEquals(2, listOfParkingSpaces.length());
            checkParkingSpace(listOfParkingSpaces.getParkingSpaceOfIndex(1), "UBC", 6);
            checkParkingSpace(listOfParkingSpaces.getParkingSpaceOfIndex(2), "Kistlano", 7);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
