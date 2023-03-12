package persistence;

import model.ListOfParkingSpaces;
import model.ParkingSpace;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads list of parking spaces from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads list of parking spaces from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ListOfParkingSpaces read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseListOfParkingSpaces(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses list of parking spaces from JSON object and returns it
    private ListOfParkingSpaces parseListOfParkingSpaces(JSONObject jsonObject) {
        ListOfParkingSpaces listOfParkingSpaces = new ListOfParkingSpaces();
        addParkingSpaces(listOfParkingSpaces, jsonObject);
        return listOfParkingSpaces;
    }

    // MODIFIES: list of parking spaces
    // EFFECTS: parses parking spaces from JSON object and adds them to list of parking spaces
    private void addParkingSpaces(ListOfParkingSpaces listOfParkingSpaces, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("list of parking spaces");
        for (Object json : jsonArray) {
            JSONObject nextParkingSpace = (JSONObject) json;
            addParkingSpace(listOfParkingSpaces, nextParkingSpace);
        }
    }

    // MODIFIES: list of parking spaces
    // EFFECTS: parses parking space from JSON object and adds it to list of parking spaces
    private void addParkingSpace(ListOfParkingSpaces listOfParkingSpaces, JSONObject jsonObject) {
        String location = jsonObject.getString("location");
        double charge = jsonObject.getDouble("charge");
        ParkingSpace parkingSpace = new ParkingSpace(location, charge);
        listOfParkingSpaces.addParkingSpace(parkingSpace);
    }
}
