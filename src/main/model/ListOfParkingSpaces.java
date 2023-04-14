package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.LinkedList;
import java.util.List;

// Represents a list of parking spaces
public class ListOfParkingSpaces implements Writable {
    private LinkedList<ParkingSpace> listOfParkingSpaces;

    /*
     * Requires: list does not contain parking spaces with same id
     * Represents a list of parking spaces
     */
    public ListOfParkingSpaces() {
        listOfParkingSpaces = new LinkedList<>();
    }

    // EFFECTS: add parking space to the end of list of parkingSpaces
    // MODIFIES: this
    public void addParkingSpace(ParkingSpace p) {
        listOfParkingSpaces.add(p);
        EventLog.getInstance().logEvent(new Event("parking space added to list of parking space"));
    }

    // REQUIRES: index num > 0
    // EFFECTS: remove parking space of index num(index starts at one)
    // MODIFIES: this
    public void removeParkingSpaceOfIndex(int num) {
        listOfParkingSpaces.remove(getParkingSpaceOfIndex(num));
        EventLog.getInstance().logEvent(new Event("parking space removed from list of parking space"));
    }

    // REQUIRES: index num > 0
    // EFFECTS: change location of parking space with index num(index starts at one)
    // MODIFIES: this
    public void changeLocationOfIndex(String newLocation, int num) {
        getParkingSpaceOfIndex(num).updateLocation(newLocation);
        EventLog.getInstance().logEvent(new Event("location of parking space changed"));
    }

    // REQUIRES: index num > 0
    // EFFECTS: change charge of parking space with index num
    // MODIFIES: this
    public void changeChargeOfIndex(double newCharge, int num) {
        getParkingSpaceOfIndex(num).updateCharge(newCharge);
        EventLog.getInstance().logEvent(new Event("charge of parking space changed"));
    }


    // REQUIRES: index num > 0
    // EFFECTS: Switch Availability of parking space with index num;
    // Updated to false if true, and changed to true to false
    // MODIFIES: this
    public void changeAvailabilityOfIndex(int num) {
        getParkingSpaceOfIndex(num).updateAvailability();
        EventLog.getInstance().logEvent(new Event("availability of parking space changed"));
    }

    // EFFECTS: return number of parking spaces
    public int length() {
        return listOfParkingSpaces.size();
    }

    // EFFECTS: return true if queue is empty, false otherwise
    public boolean isEmpty() {
        return listOfParkingSpaces.size() == 0;
    }

    // REQUIRES: length of currentLocation > 0
    // EFFECTS: return a list of parking spaces that share
    // same location with user's current location
    public ListOfParkingSpaces searchParkingSpaces(String currentLocation) {
        ListOfParkingSpaces searchResult = new ListOfParkingSpaces();
        for (ParkingSpace p : listOfParkingSpaces) {
            if (p.getLocation().contains(currentLocation)) {
                searchResult.addParkingSpace(p);
            }
        }
        EventLog.getInstance().logEvent(new Event("search action performed"));
        return searchResult;
    }

    // REQUIRES: length of currentLocation > 0
    // EFFECTS: return a list of available parking spaces that contain
    // the keyword the user enters
    public ListOfParkingSpaces searchAvailableParkingSpaces(String currentLocation) {
        ListOfParkingSpaces searchResult = new ListOfParkingSpaces();
        for (ParkingSpace p : listOfParkingSpaces) {
            if ((p.getLocation().contains(currentLocation)) && (p.getAvailability())) {
                searchResult.addParkingSpace(p);
            }
        }
        EventLog.getInstance().logEvent(new Event("list of parking spaces filtered to available parking spaces"));
        return searchResult;
    }

    // REQUIRES: index num > 0
    // EFFECTS: return parking space with index num (num starts at 1)
    public ParkingSpace getParkingSpaceOfIndex(int num) {
        return listOfParkingSpaces.get(num - 1);
    }

    /*
     * EFFECTS: returns a string representation of list of parking
     */
    public List<String> displayList() {
        LinkedList<String> list = new LinkedList<>();
        for (ParkingSpace p : listOfParkingSpaces) {
            list.add(listOfParkingSpaces.indexOf(p) + 1 + ". " + p.toString());
        }
        return list;
    }

    // EFFECTS: return json representation of list of parking spaces
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("list of parking spaces", parkingSpacesToJson());
        return json;
    }

    // EFFECTS: return parking spaces in list of parking spaces as a JSON array
    private JSONArray parkingSpacesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (ParkingSpace p : listOfParkingSpaces) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }
}

