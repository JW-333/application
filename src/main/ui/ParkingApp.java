package ui;

import model.ListOfParkingSpaces;
import model.ParkingSpace;

import java.util.LinkedList;
import java.util.Scanner;

public class ParkingApp {

    private ParkingSpace parkingSpace1;
    private ParkingSpace parkingSpace2;
    private ParkingSpace parkingSpace3;
    private ParkingSpace parkingSpace4;
    private ParkingSpace parkingSpace5;
    private ParkingSpace parkingSpace6;
    private ListOfParkingSpaces listOfParkingSpaces;
    private Scanner input;

    // EFFECTS: runs the find parking space application
    public ParkingApp() {
        run();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void run() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("s")) {
            search();
        } else if (command.equals("a")) {
            addParking();
        } else if (command.equals("r")) {
            removeParking();
        } else if (command.equals("e")) {
            editParking();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes list of parking spaces
    private void init() {
        parkingSpace1 = new ParkingSpace(
                "6115 Student Union Boulevard V6T 1Z1, UBC", 1);
        parkingSpace2 = new ParkingSpace(
                "2250 Health Sciences Mall V6T 1Z3, UBC", 6);
        parkingSpace3 = new ParkingSpace("6525 Oak St, Vancouver, BC V6P 3Z3", 2);
        parkingSpace4 = new ParkingSpace("5251 Oak St V6M 4H1", 5);
        parkingSpace5 = new ParkingSpace(
                "6278 NW Marine Drive V6T 1Z1, UBC", 1);
        parkingSpace6 = new ParkingSpace(
                "6108 Thunderbird Boulevard UBC", 6);
        listOfParkingSpaces = new ListOfParkingSpaces();
        listOfParkingSpaces.addParkingSpace(parkingSpace1);
        listOfParkingSpaces.addParkingSpace(parkingSpace2);
        listOfParkingSpaces.addParkingSpace(parkingSpace3);
        listOfParkingSpaces.addParkingSpace(parkingSpace4);
        listOfParkingSpaces.addParkingSpace(parkingSpace5);
        listOfParkingSpaces.addParkingSpace(parkingSpace6);
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("Hi! Here are some parking spaces:");
        System.out.println(listOfParkingSpaces.displayList());
        System.out.println("\nSelect from:");
        System.out.println("\ts -> search parking");
        System.out.println("\ta -> add a parking space");
        System.out.println("\tr -> remove a parking space");
        System.out.println("\te -> edit a parking space");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: display parking spaces whose location contain keyword that the user types in
    private void search() {
        System.out.println("Enter a keyword");
        String keyword = input.next();
        System.out.println(listOfParkingSpaces.searchParkingSpaces(keyword).displayList());
        System.out.println("\tfilter -> show available parking spaces");
        System.out.println("\treturn -> return to menu");
        if (input.next().equals("filter")) {
            System.out.println(listOfParkingSpaces.searchAvailableParkingSpaces(keyword).displayList());
        } else if (input.next().equals("return")) {
            run();
        }
    }

    // MODIFIES: this
    // EFFECTS: add new parking space to list
    private void addParking() {
        System.out.print("enter the location");
        String location = input.next();
        System.out.print("enter the charge");
        if (input.nextDouble() >= 0) {
            double charge = input.nextDouble();
            listOfParkingSpaces.addParkingSpace(new ParkingSpace(location, charge));
        } else {
            System.out.println("amount not valid");

        }
    }

    // MODIFIES: this
    // EFFECTS: remove a parking space from list
    private void removeParking() {
        System.out.println("enter index of the parking space you would like to remove");
        listOfParkingSpaces.removeParkingSpaceOfIndex(input.nextInt());
    }

    // MODIFIES: this
    // EFFECTS: change location, charge, or availability of a parking space
    private void editParking() {
        System.out.println("enter the index of parking space you would like to edit");
        int index = input.nextInt();
        System.out.println(listOfParkingSpaces.getParkingSpaceOfIndex(index).toString());
        System.out.println("select from:");
        System.out.println("change location");
        System.out.println("change charge");
        System.out.println("change availability");
        if (input.next().equals("change location")) {
            changeLocation(index);
        } else if (input.next().equals("change charge")) {
            changeCharge(index);
        } else if (input.next().equals("change availability")) {
            changeAvailability(index);
        } else {
            System.out.println("selection not valid");
        }
    }

    // MODIFIES: this
    // EFFECTS: change the location of a parking space
    private void changeLocation(int num) {
        System.out.println("enter new location");
        listOfParkingSpaces.changeLocationOfIndex(input.next(), num);
        System.out.println(listOfParkingSpaces.getParkingSpaceOfIndex(num).toString());
    }

    // MODIFIES: this
    // EFFECTS: change the charge of a parking space
    private void changeCharge(int num) {
        System.out.println("enter new charge");
        if (input.nextInt() >= 0) {
            listOfParkingSpaces.changeChargeOfIndex(input.nextInt(), num);
            System.out.println(listOfParkingSpaces.getParkingSpaceOfIndex(num).toString());
        } else {
            System.out.println("amount not valid");
        }
    }

    // MODIFIES: this
    // EFFECTS: change the location of a parking space
    private void changeAvailability(int num) {
        listOfParkingSpaces.changeAvailabilityOfIndex(num);
        System.out.println(listOfParkingSpaces.getParkingSpaceOfIndex(num).toString());
    }


}

