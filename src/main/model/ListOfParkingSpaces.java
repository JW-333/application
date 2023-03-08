package model;

import java.util.LinkedList;
import java.util.List;

// Represents a list of parking spaces
public class ListOfParkingSpaces {
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
    }

    // REQUIRES: index num > 0
    // EFFECTS: remove parking space of index num(index starts at one)
    // MODIFIES: this
    public void removeParkingSpaceOfIndex(int num) {
        listOfParkingSpaces.remove(getParkingSpaceOfIndex(num));
    }

    // REQUIRES: index num > 0
    // EFFECTS: change location of parking space with index num(index starts at one)
    // MODIFIES: this
    public void changeLocationOfIndex(String newLocation, int num) {
        getParkingSpaceOfIndex(num).updateLocation(newLocation);
    }

    // REQUIRES: index num > 0
    // EFFECTS: change charge of parking space with index num
    // MODIFIES: this
    public void changeChargeOfIndex(double newCharge, int num) {
        getParkingSpaceOfIndex(num).updateCharge(newCharge);
    }


    // REQUIRES: index num > 0
    // EFFECTS: Switch Availability of parking space with index num;
    // Updated to false if true, and changed to true to false
    // MODIFIES: this
    public void changeAvailabilityOfIndex(int num) {
        getParkingSpaceOfIndex(num).updateAvailability();
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
        for (ParkingSpace p: listOfParkingSpaces) {
            list.add(listOfParkingSpaces.indexOf(p) + 1 + ". " + p.toString());
        }
        return list;
    }
}

