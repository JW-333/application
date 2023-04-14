package ui;

import java.awt.*;
import java.awt.event.*;

import model.Event;
import model.EventLog;
import model.ListOfParkingSpaces;
import model.ParkingSpace;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


import persistence.JsonReader;
import persistence.JsonWriter;


import javax.swing.*;


/**
 * Represents Parking App's main frame.
 */
class ParkingAppUI implements ActionListener {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
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
    private static final String JSON_STORE = "./data/listofparkingspaces.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    /**
     * MODIFIES: this
     * EFFECTS: sets up button panel, key pad and visual alarm status window.
     */
    public ParkingAppUI() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        init();
        desktop = new JFrame();
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));
        list = new JList<String>();
        JLabel label = new JLabel("Let's find parking spaces!");
        desktop.setTitle("Parking App");
        desktop.setSize(WIDTH, HEIGHT);
        addButtonPanel();
        panel.add(new Car(), BorderLayout.PAGE_END);
        desktop.pack();
        desktop.add(panel, BorderLayout.CENTER);
        addListener();
        panel.add(label);
        desktop.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        desktop.setSize(WIDTH, HEIGHT);
        centreOnScreen();
        desktop.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: adds Window Listener to desktop
    private void addListener() {
        desktop.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                for (Event event : EventLog.getInstance()) {
                    System.out.println(event.getDescription());
                }
            }
        });
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

    // MODIFIES: this
    // EFFECTS: creates button panel
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


    // MODIFIES: this
    // EFFECTS: helper to centre main application window on desktop
    private void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        desktop.setLocation((width - desktop.getWidth()) / 2, (height - desktop.getHeight()) / 2);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == load) {
            load();
        } else if (e.getSource() == search) {
            search();
        } else if (e.getSource() == add) {
            add();
        } else if (e.getSource() == remove) {
            remove();
        } else if (e.getSource() == edit) {
            edit();
        } else if (e.getSource() == save) {
            save();
        }
    }

    // MODIFIES: this
    // EFFECTS: display parking spaces whose location contain keyword that the user types in
    private void search() {
        JPanel secondPanel = new JPanel();
        secondPanel.setLayout(null);
        String input = searchResults(secondPanel);
        filter(secondPanel, input);
        addBackButton(secondPanel);
        desktop.add(secondPanel);
        panel.setVisible(false);
        secondPanel.setVisible(true);
    }

    // MODIFIES: this
    //EFFECTS: adds search text field and search button,
    // shows a list of parking spaces when "search" button is clicked,
    // returns user input
    public String searchResults(JPanel panel) {
        JButton search = new JButton("search");
        search.setBounds(660, 20, 80, 25);
        JTextField userText = new JTextField(20);
        userText.setBounds(480, 20, 165, 25);
        panel.add(search);
        panel.add(userText);

        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = userText.getText();
                panel.remove(list);
                List<String> newList = listOfParkingSpaces.searchParkingSpaces(input).displayList();
                list = new JList<>(newList.toArray(new String[0]));
                list.setBounds(0, 0, 1000, 1000);
                panel.add(list);
                panel.validate();
                panel.repaint();
            }
        });
        return userText.getText();
    }

    // MODIFIES: this
    //EFFECTS: adds search text field and search button,
    // shows a list of parking spaces when "search" button is clicked
    public void filter(JPanel panel, String input) {
        JButton filter = new JButton("filter");
        filter.setBounds(660, 50, 80, 25);

        panel.add(filter);
        filter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.remove(list);
                List<String> newList = listOfParkingSpaces.searchAvailableParkingSpaces(input).displayList();
                list = new JList<>(newList.toArray(new String[0]));
                list.setBounds(0, 0, 1000, 1000);
                panel.add(list);
                panel.validate();
                panel.repaint();

            }
        });
    }

    // MODIFIES: this
    // EFFECTS: Add a "back" button to the second panel
    private void addBackButton(JPanel subPanel) {

        JButton backButton = new JButton("Back");
        subPanel.add(backButton);
        backButton.setBounds(660, 80, 80, 25);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show the main panel and hide the second panel
                subPanel.setVisible(false);
                panel.setVisible(true);
            }
        });
    }

    // EFFECTS: add new parking space to list
    private void add() {
        JPanel secondPanel = new JPanel();
        secondPanel.setLayout(null);
        addBackButton(secondPanel);
        setUpForAdd(secondPanel);
        desktop.add(secondPanel);
        panel.setVisible(false);
        secondPanel.setVisible(true);
        addParkingSpace(secondPanel);
    }


    // MODIFIES: this
    // EFFECT: creates a new JPanel for add
    private void addParkingSpace(JPanel panel) {
        JButton add = new JButton("add");
        add.setBounds(660, 50, 80, 25);
        JTextField location = new JTextField(20);
        location.setBounds(480, 50, 165, 25);
        panel.add(add);
        panel.add(location);
        JTextField charge = new JTextField(20);
        charge.setBounds(480, 80, 165, 25);
        panel.add(charge);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String locationString = location.getText();
                int chargeInt = Integer.parseInt(charge.getText());
                listOfParkingSpaces.addParkingSpace(new ParkingSpace(locationString, chargeInt));
                JLabel addedSuccessfully = new JLabel("added successfully");
                addedSuccessfully.setBounds(480, 100, 165, 25);
                panel.add(addedSuccessfully);
                addList(panel);
                panel.validate();
                panel.repaint();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: creates components for add
    private void setUpForAdd(JPanel panel) {
        JLabel locationLabel = new JLabel("location");
        JLabel chargeLabel = new JLabel("charge");
        locationLabel.setBounds(410, 50, 165, 25);
        chargeLabel.setBounds(410, 80, 165, 25);
        panel.add(locationLabel);
        panel.add(chargeLabel);
    }

    // MODIFIES: this
    // EFFECTS: creates components for addParkingSpace
    private void addList(JPanel panel) {
        panel.remove(list);
        List<String> newList = listOfParkingSpaces.displayList();
        list = new JList<>(newList.toArray(new String[0]));
        list.setBounds(0, 0, 1000, 1000);
        panel.add(list);
    }

    // MODIFIES: this
    // EFFECTS: creates new panel for removing a parking space from list
    private void remove() {
        JPanel secondPanel = new JPanel();
        secondPanel.setLayout(null);
        addBackButton(secondPanel);
        desktop.add(secondPanel);
        panel.setVisible(false);
        secondPanel.setVisible(true);
        removeParkingSpace(secondPanel);
    }

    // MODIFIES: this
    // EFFECTS: creates functionality for remove
    private void removeParkingSpace(JPanel panel) {
        JButton remove = new JButton("remove");
        remove.setBounds(660, 50, 80, 25);
        JTextField indexField = new JTextField(20);
        indexField.setBounds(480, 50, 165, 25);
        panel.add(remove);
        panel.add(indexField);
        setUpForRemove(panel);
        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indexInt = Integer.parseInt(indexField.getText());
                listOfParkingSpaces.removeParkingSpaceOfIndex(indexInt);
                JLabel removedSuccessfully = new JLabel("removed successfully");
                panel.add(removedSuccessfully);
                panel.remove(list);
                List<String> newList = listOfParkingSpaces.displayList();
                list = new JList<>(newList.toArray(new String[0]));
                list.setBounds(0, 0, 1000, 1000);
                panel.add(list);
                panel.validate();
                panel.repaint();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: creates components for remove
    private void setUpForRemove(JPanel panel) {
        JLabel index = new JLabel("index");
        index.setBounds(410, 50, 50, 25);
        panel.add(index);
        List<String> newList = listOfParkingSpaces.displayList();
        list = new JList<>(newList.toArray(new String[0]));
        list.setBounds(0, 0, 1000, 1000);
        panel.add(list);
    }


    // MODIFIES: this
    // EFFECTS: creates new panel for changing location, charge, or availability of a parking space
    private void edit() {
        JPanel secondPanel = new JPanel();
        secondPanel.setLayout(null);
        addBackButton(secondPanel);
        desktop.add(secondPanel);
        changeAvailability(secondPanel);
        editParkingSpace(secondPanel);
        secondPanel.validate();
        secondPanel.repaint();
        panel.setVisible(false);
        secondPanel.setVisible(true);
    }

    @SuppressWarnings("methodlength")
    // MODIFIES: this
    // EFFECTS: creates buttons and functionality for edit
    private void editParkingSpace(JPanel panel) {
        JButton done = new JButton("done");
        done.setBounds(660, 20, 80, 25);
        JLabel locationText = new JLabel("location");
        locationText.setBounds(420, 45, 165, 25);
        JTextField location = new JTextField(20);
        location.setBounds(480, 45, 165, 25);
        JLabel indexText = new JLabel("index");
        indexText.setBounds(420, 20, 165, 25);
        JTextField index = new JTextField(20);
        index.setBounds(480, 20, 165, 25);
        JLabel chargeText = new JLabel("charge");
        chargeText.setBounds(420, 70, 165, 25);
        JTextField charge = new JTextField(20);
        charge.setBounds(480, 70, 165, 25);
        panel.add(done);
        panel.add(index);
        panel.add(location);
        panel.add(charge);
        panel.add(locationText);
        panel.add(chargeText);
        panel.add(indexText);
        List<String> newList = listOfParkingSpaces.displayList();
        list = new JList<>(newList.toArray(new String[0]));
        list.setBounds(0, 0, 1000, 1000);
        panel.add(list);
        done.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.remove(list);
                String locationText = location.getText();
                int chargeInt = Integer.parseInt(charge.getText());
                int input = Integer.parseInt(index.getText());
                listOfParkingSpaces.changeLocationOfIndex(locationText, input);
                listOfParkingSpaces.changeChargeOfIndex(chargeInt, input);
                List<String> newList = listOfParkingSpaces.displayList();
                list = new JList<>(newList.toArray(new String[0]));
                list.setBounds(0, 0, 1000, 1000);
                panel.add(list);
                panel.validate();
                panel.repaint();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: adds change availability button to edit
    private void changeAvailability(JPanel panel) {
        JLabel indexText = new JLabel("index");
        indexText.setBounds(420, 150, 165, 25);
        JTextField index = new JTextField();
        index.setBounds(480, 150, 165, 25);
        JButton availability = new JButton("change availability");
        availability.setBounds(660, 150, 145, 25);
        panel.add(indexText);
        panel.add(index);
        panel.add(availability);
        availability.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int input = Integer.parseInt(index.getText());
                listOfParkingSpaces.changeAvailabilityOfIndex(input);
                addList(panel);
                panel.validate();
                panel.repaint();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: saves the list of parking spaces to file
    private void save() {
        JLabel savedSuccessfully = new JLabel("Saved " + "list of parking spaces" + " to " + JSON_STORE);
        JLabel notSaved = new JLabel("Unable to write to file: " + JSON_STORE);
        try {
            jsonWriter.open();
            jsonWriter.write(listOfParkingSpaces);
            jsonWriter.close();
            panel.add(savedSuccessfully);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
            panel.add(notSaved);
        } finally {
            panel.validate();
            panel.repaint();
        }
    }

    // MODIFIES: this
    // EFFECTS: loads list of parking spaces from file
    private void load() {
        JLabel loadedSuccessfully = new JLabel("Loaded " + "list of parking spaces" + " from " + JSON_STORE);
        JLabel notLoaded = new JLabel("Unable to read from file: " + JSON_STORE);
        try {
            listOfParkingSpaces = jsonReader.read();
            panel.add(loadedSuccessfully, BorderLayout.PAGE_END);
        } catch (IOException e) {
            panel.add(notLoaded);
        } finally {
            panel.validate();
            panel.repaint();
        }
    }
}
