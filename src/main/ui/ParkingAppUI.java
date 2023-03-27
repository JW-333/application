package ui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.ListOfParkingSpaces;
import model.ParkingSpace;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;


/**
 * Represents application's main window frame.
 */
class ParkingAppUI implements ActionListener{

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final String FILE_DESCRIPTOR = "...file";
    private ParkingApp parkingApp;
    private JFrame desktop;
    private JPanel panel;
    private JButton load;
    private JButton search;
    private JButton add;
    private JButton remove;
    private JButton edit;
    private JButton save;
    private JList<String> list;
    private ParkingSpace parkingSpace1;
    private ParkingSpace parkingSpace2;
    private ParkingSpace parkingSpace3;
    private ParkingSpace parkingSpace4;
    private ParkingSpace parkingSpace5;
    private ParkingSpace parkingSpace6;
    private ListOfParkingSpaces listOfParkingSpaces;
    private Scanner input;
    private static final String JSON_STORE = "./data/listofparkingspaces.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    /**
     * Constructor sets up button panel, key pad and visual alarm status window.
     */
    public ParkingAppUI() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        init();
        desktop = new JFrame();
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        list = new JList<String>();
        JLabel label = new JLabel("Let's find parking spaces!");
        desktop.setTitle("Parking App");
        desktop.setSize(WIDTH, HEIGHT);
        addButtonPanel();
        desktop.pack();
        panel.setVisible(true);
        panel.add(label);
        desktop.add(panel, BorderLayout.CENTER);
        desktop.add(list);
        desktop.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        centreOnScreen();
        desktop.setVisible(true);
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
    }

    /**
     * Helper to add control buttons.
     */
    private void addButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 2));
        load = new JButton("load");
        load.addActionListener(this);
        search = new JButton("search");
        search.addActionListener(this);
        add = new JButton("add");
        add.addActionListener(this);
        remove = new JButton("remove");
        remove.addActionListener(this);
        edit = new JButton("edit");
        edit.addActionListener(this);
        save = new JButton("save");
        save.addActionListener(this);
        buttonPanel.add(load);
        buttonPanel.add(search);
        buttonPanel.add(add);
        buttonPanel.add(remove);
        buttonPanel.add(edit);
        buttonPanel.add(save);
        panel.add(buttonPanel, BorderLayout.WEST);
    }


    /**
     * Helper to centre main application window on desktop
     */
    private void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        desktop.setLocation((width - desktop.getWidth()) / 2, (height - desktop.getHeight()) / 2);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == load) {
            search();
        } else if (e.getSource() == search) {

        } else if (e.getSource() == add) {

        } else if (e.getSource() == remove) {

        } else if (e.getSource() == edit) {

        } else if (e.getSource() == save) {

        }
        }
    // EFFECTS: display parking spaces whose location contain keyword that the user types in
    private void search() {
        JPanel secondPanel = new JPanel();
        secondPanel.setLayout(new BorderLayout());
        secondPanel.add(new JLabel("search"), BorderLayout.CENTER);
        panel.setVisible(false);
        desktop.getContentPane().add(secondPanel);
        secondPanel.setVisible(true);
        addBackButton(secondPanel);
        System.out.println("Enter a keyword");
        String keyword = input.next();
        System.out.println(listOfParkingSpaces.searchParkingSpaces(keyword).displayList());
        System.out.println("\tfilter -> show available parking spaces");
        System.out.println("\treturn -> return to menu");
        String selection = input.next();
        if (selection.equals("filter")) {
            list = new JList<>();
            List<String> newList = listOfParkingSpaces.searchAvailableParkingSpaces(keyword).displayList();
            list = new JList<>(newList.toArray(new String[0]));
        }
    }

    // EFFECTS: Add a "back" button to the second panel
    private void addBackButton(JPanel subPanel) {

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
                                         @Override
                                         public void actionPerformed(ActionEvent e) {
                                             // Show the main panel and hide the second panel
                                             subPanel.setVisible(false);
                                             panel.setVisible(true);
                                         }
                                     });
        subPanel.add(backButton, BorderLayout.SOUTH);}

    // MODIFIES: this
    // EFFECTS: add new parking space to list
    private void addParking() {
        System.out.print("enter the location");
        String location = input.next();
        System.out.print("enter the charge");
        double charge = input.nextDouble();
        if (charge >= 0) {
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
        System.out.println("What would you like to change?");
        System.out.println("Location");
        System.out.println("Charge");
        System.out.println("Availability");
        String selection = input.next();
        if (selection.equals("location")) {
            changeLocation(index);
        } else if (selection.equals("charge")) {
            changeCharge(index);
        } else if (selection.equals("availability")) {
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
        double charge = input.nextDouble();
        if (charge >= 0) {
            listOfParkingSpaces.changeChargeOfIndex(charge, num);
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

    // EFFECTS: saves the list of parking spaces to file
    private void saveListOfParkingSpaces() {
        try {
            jsonWriter.open();
            jsonWriter.write(listOfParkingSpaces);
            jsonWriter.close();
            System.out.println("Saved " + "list of parking spaces" + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads list of parking spaces from file
    private void loadListOfParkingSpaces() {
        try {
            listOfParkingSpaces = jsonReader.read();
            System.out.println("Loaded " + "list of parking spaces" + " from " + JSON_STORE);
            for (String t : listOfParkingSpaces.displayList()) {
                System.out.println(t);
            }
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
    }


