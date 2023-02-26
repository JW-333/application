package model;

// Represents a parking space with a location, charge/hour (in dollars), availability, and ID
public class ParkingSpace {
    private String location;                 // location of parking
    private static int nextId = 1;           // tracks id of next parking space created
    private int id;                          // id of parking space
    private double charge;                   // charge per hour
    private boolean availability;            // whether parking is available

    /*
     * REQUIRES: location has a non-zero length, and charge >= 0
     * EFFECTS: location of parking space is set to location; charge is set to charge;
     *          availability is set to available; id is a
     *          positive integer not assigned to any other parking space
     */
    public ParkingSpace(String locationOfParking, double chargeOfParking) {
        id = nextId;
        nextId += 1;
        location = locationOfParking;
        charge = chargeOfParking;
        availability = true;
    }

    public String getLocation() {
        return location;
    }

    public Double getCharge() {
        return charge;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public int getId() {
        return id;
    }

    /*
     * REQUIRES: length of newLocation > 0
     * MODIFIES: this
     * EFFECTS: location is updated to newLocation
     */
    public void updateLocation(String newLocation) {
        location = newLocation;
    }

    /*
     * REQUIRES: newCharge > 0
     * MODIFIES: this
     * EFFECTS: charge is updated to newCharge
     */
    public void updateCharge(double newCharge) {
        charge = newCharge;
    }

    /*
     * MODIFIES: this
     * EFFECTS: availability is updated to false if true,
     * and changed to true to false
     */
    public void updateAvailability() {
        availability = !availability;

    }

    /*
     * EFFECTS: returns a string representation of parking
     */
    public String toString() {
        String ifAvailable;
        if (availability) {
            ifAvailable = "available";
        } else {
            ifAvailable = "unavailable";
        }
        return "location:" + location + ", "
                + charge + "$/hour" + ", " + ifAvailable;
    }
}

