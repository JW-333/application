# My Personal Project

## Application that helps drivers locate parking spots and shows the price of paid parking


- What will the application do? Users will enter a keyword that specifies their general location,
and the application will show the parking spaces whose location contains this keyword and the cost of parking
as well as the availability for parking. Users are free to update the information of parking spaces in the case
that the information is not accurate or up to date. Users are also free to add or remove parking spaces.
- Who will use it? Any one who is driving and needs to find a parking spot can use it. Oftentimes
drivers do not know where to park in the area with which they are not familiar, and this application
can help them park their cars.
- Why is this project of interest? Sometimes when I visit a new place and try to park my car,
I have to spending time search for parking space. This project can make parking more efficient, and
may be useful to a lot of drivers.

**User Story**:
- *As a user, I want to be able to view the list of parking spaces in this city.*
- *As a user, I want to be able to add a new parking space to the list of parking spaces.*
- *As a user, I want to be able to search for available parking space near me.*
- *As a user, I want to be able to update the information about a parking space in the list because the
   previous information is not accurate.*
- *As a user, I want to be able to remove a parking space in the list because it no longer exists.*
- *As a user, when I quit the application, I want to have the option to save the list of parking spaces.*
- *As a user, when I start the application, I want to have the option to load the list of parking spaces.*

Citation: persistence package uses code from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

# Instructions for Grader

- You can add a parking space to the list of parking spaces by clicking add in the menu, and in the new page,
  enter the location and charge of the new parking space, and click add.
- You can search a parking space by clicking search, and in the new page, enter the location and click search.
- You can remove a parking space by clicking remove, and in the new page, enter the index and click remove.
- You can filter to available parking spaces by by clicking search, and in the new page, click filter.
- You can edit a parking space by clicking edit, and in the new page, enter the information and click done.
- You can locate my visual component in the main page, which is an image of two cars.
- You can save the state of my application by clicking save.
- You can reload the state of my application by clicking load.

# Phase 4: Task 2

 - parking space added to list of parking space
 - parking space added to list of parking space
 - parking space added to list of parking space
 - parking space added to list of parking space
 - parking space added to list of parking space
 - parking space added to list of parking space
 - parking space added to list of parking space
 - parking space removed from list of parking space
 - location of parking space changed
 - charge of parking space changed
 - availability of parking space changed
 - availability of parking space changed

("parking space added to list of parking space" appears 6 times first due to the initialization of the list in ParkingAppUI)
# Phase 4: Task 3

- There are some code clones in ParkingAppUI, especially the ones for action listener. A possible
refactoring is to simplify them by replacing them with methods.
- Some components of GUI can be moved into new classes to improve SRP.
- If I had more to add more graphics to the GUI, I would make an abstract class that contain the repetitive
methods in the classes for the graphics to simplify.
- Reflecting on the UML diagram, it is possible to break up ParkingAppUI into multiple classes for different functionality.
For example, a new class can be added for the load/save function. The new class contains JsonReader and JsonWriter and
has an association arrow from ParkingAppUI.
- The parking space list initialized in ParkingAppUI can be initialized in a new class and ParkingAppUI will contain that class
with multiplicity 1.


