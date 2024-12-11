

import Exceptions.ValidationException;
import Models.*;

import java.util.Scanner;
/**
 * The main.java.UI class provides a command-line interface for interacting with the flight management system.
 */
public class UI {
    private final Controller flightController;

    /**
     * Constructs a new main.java.UI with the given main.java.Controller.
     *
     * @param flightController The controller that handles the business logic for the flight system.
     */
    public UI(Controller flightController) {
        this.flightController = flightController;
    }

    /**
     * Displays a greeting message to the user.
     */
    public void greeting() {
        System.out.println("Welcome to Airplane Management System! \n");
    }

    /**
     * Displays the menu options available for the operator.
     *
     * @return The menu options for the operator as a formatted string.
     */
    public String operatorMenu(){
        return """
                Operator Options:
                    1. Manage Flights
                    2. Manage Reservations
                    3. Manage Payments
                    4. Manage Tickets
                    5. Manage Airplanes
                    6. Manage Passengers
                    7. Manage Pilots
                    8. Manage Airports
                    9. Manage Cabin Crew
                    10. View Passenger by a specific Flight
                    11. View Airplanes sorted after capacity
                    12. View Flights filtered after one given amount
                    13. View Flights sorted after date
                    14. View Cabin Crew filtered after profession
                    0. Exit Program
                """;
    }

    /**
     * Displays the menu options available for the passenger.
     *
     * @return The menu options for the passenger as a formatted string.
     */
    public String passengerMenu(){
        return """
                Passenger Options:
                    1. Book Seat
                    2. Cancel Ticket(Booking)
                    3. Cancel Reservation
                    4. Update Booking
                    5. Update Reservation
                    6. View Ticket(Booking)
                    7. View Reservation
                    8. View Flights sorted after date
                    9. Book Seat by Flight
                    0. Exit Program
                """;
    }

    /**
     * Displays the menu options available for the operator with the crud operations.
     *
     * @return The menu options for the operator with CRUD operations.
     */
    public String crudObtions(){
        return  """
                 What do you want to do?
                 1. Create
                 2. Update
                 3. Delete
                 4. View
                """;
    }

    /**
     * Starts the main.java.UI and handles user input, providing appropriate options for operator and passenger modes.
     */
    public void run()  {
        Scanner scanner = new Scanner(System.in);
        greeting();
        Integer userType = null;
        try {
            System.out.println("Are you an Operator(1) or a Passenger(2)?");
            userType = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character left by nextInt()
            if(userType<1 || userType>2)
                throw new ValidationException("Invalid choice. Please try again.");
        }
        catch(ValidationException e) {
            System.out.println(e.getMessage());
        }
        // Validăm tipul de utilizator
        if (userType.equals(1)) {
            // Opțiuni pentru Operator
            boolean running = true;
            while (running) {
                int choice = 0;
                try {
                    System.out.println(operatorMenu());
                    choice = scanner.nextInt();// citeste un număr întreg (de tip int) introdus de utilizator în consolă
                    scanner.nextLine();
                    if(choice<0 || choice>14)
                        throw new ValidationException("Invalid choice. Please try again.");
                }
                catch(ValidationException e) {
                    System.out.println(e.getMessage());
                    continue;
                }
                switch (choice) {
                    case 0:
                        running = false;
                        System.out.println("Exiting Operator mode.");
                        break;
                    case 1://Manage Flights
                        System.out.println(crudObtions());
                        int operatorChoice1= scanner.nextInt();
                        scanner.nextLine();
                        switch (operatorChoice1) {
                            case 1:
                                Integer pilotID = null;
                                try {
                                    System.out.print("Enter a Pilot ID for your flight: ");
                                    pilotID = scanner.nextInt();
                                    scanner.nextLine();
                                    if (pilotID <= 0 || pilotID == null)
                                        throw new ValidationException("Pilot ID must be a positive number");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }
                                Integer airplaneID= null;

                                try{
                                    System.out.print("Enter an Airplane ID for your flight: ");
                                    airplaneID = scanner.nextInt();
                                    scanner.nextLine();
                                    if (airplaneID <= 0 || airplaneID == null)
                                        throw new ValidationException("Airplane ID must be a positive number");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }

                                String departure = null;
                                try {
                                    System.out.print("Enter the departure location for your flight: ");
                                    departure = scanner.nextLine();
                                    if (departure == null || departure.isEmpty())
                                        throw new ValidationException("Departure location cannot be empty");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }

                                String destination = null;
                                try {
                                    System.out.print("Enter the destination: ");
                                    destination = scanner.nextLine();
                                    if (destination == null || destination.isEmpty())
                                        throw new ValidationException("Destination cannot be empty");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }

                                Integer airportID = null;
                                try {
                                    System.out.print("Enter the Airport ID: ");
                                    airportID = scanner.nextInt();
                                    scanner.nextLine();
                                    if (airportID <= 0 || airportID == null)
                                        throw new ValidationException("Airport ID must be a positive number");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }

                                String date=null;
                                try {
                                    System.out.print("Enter the date of form (\"2023-10-01\"): ");
                                    date = scanner.nextLine();
                                    if (date == null || date.isEmpty())
                                        throw new ValidationException("Date cannot be empty");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }

                                double amount = 0;
                                try {
                                    System.out.print("Enter Payment Amount: ");
                                    amount = scanner.nextDouble();
                                    scanner.nextLine();
                                    if (amount <= 0)
                                        throw new ValidationException("Amount must be a positive number");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }
                                flightController.createFlight(departure,destination,pilotID,airplaneID,airportID,date,amount);
                                break;
                            case 2:
                                Integer flightID = null;
                                try {
                                    System.out.print("Enter a Flight ID to update: ");
                                    flightID = scanner.nextInt();
                                    scanner.nextLine();
                                    if (flightID <= 0 || flightID == null)
                                        throw new ValidationException("Flight ID must be a positive number");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }

                                String departure2 = null;
                                try {
                                    System.out.print("Enter the new departure location for your flight: ");
                                    departure2 = scanner.nextLine();
                                    if (departure2 == null || departure2.isEmpty())
                                        throw new ValidationException("Departure location cannot be empty");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }
                                String destination2 = null;
                                try {
                                    System.out.print("Enter the new destination: ");
                                    destination2 = scanner.nextLine();
                                    if (destination2 == null || destination2.isEmpty())
                                        throw new ValidationException("Destination cannot be empty");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }

                                Integer pilotID2 = null;
                                try {
                                    System.out.print("Enter a new Pilot ID for your flight: ");
                                    pilotID2 = scanner.nextInt();
                                    scanner.nextLine();
                                    if (pilotID2 <= 0 || pilotID2 == null)
                                        throw new ValidationException("Pilot ID must be a positive number");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }

                                Integer airplaneID2 = null;
                                try {
                                    System.out.print("Enter an Airplane ID for your flight: ");
                                    airplaneID2 = scanner.nextInt();
                                    scanner.nextLine();
                                    if (airplaneID2 <= 0 || airplaneID2 == null)
                                        throw new ValidationException("Airplane ID must be a positive number");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }
                                flightController.updateFlight(flightID,departure2,destination2,pilotID2,airplaneID2);
                                break;
                            case 3:
                                flightController.deleteFlight(readId(scanner));
                                break;
                            case 4:
                                flightController.viewAllFlights();
                                break;
                        }
                        break;
                    case 2://Manage Reservations
                        System.out.println(crudObtions());
                        int operatorChoice2= scanner.nextInt();
                        scanner.nextLine();
                        switch (operatorChoice2) {
                            case 1:
                                Integer passengerID = null;
                                try {
                                    System.out.print("Enter Passenger ID: ");
                                    passengerID = scanner.nextInt();
                                    scanner.nextLine();
                                    if (passengerID <= 0 || passengerID == null)
                                        throw new ValidationException("Passenger ID must be a positive number");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }

                                String date2 = null;
                                try {
                                    System.out.print("Enter Reservation Date: ");
                                    date2 = scanner.nextLine();
                                    if (date2 == null || date2.isEmpty())
                                        throw new ValidationException("Date cannot be empty");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }

                                String departure = null;
                                try {
                                    System.out.print("Enter Reservation Departure Location: ");
                                    departure = scanner.nextLine();
                                    if (departure == null || departure.isEmpty())
                                        throw new ValidationException("Departure location cannot be empty");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }

                                String destination = null;
                                try {
                                    System.out.print("Enter Reservation Destination: ");
                                    destination = scanner.nextLine();
                                    if (destination == null || destination.isEmpty())
                                        throw new ValidationException("Destination cannot be empty");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }

                                flightController.createReservation(date2,passengerID,departure,destination);
                                break;
                            case 2:
                                Integer reservationID = null;
                                try {
                                    System.out.print("Enter Reservation ID to update: ");
                                    reservationID = scanner.nextInt();
                                    scanner.nextLine();
                                    if (reservationID <= 0 || reservationID == null)
                                        throw new ValidationException("Reservation ID must be a positive number");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }

                                String newDate = null;
                                try {
                                    System.out.print("Enter new Reservation Date: ");
                                    newDate = scanner.nextLine();
                                    if (newDate == null || newDate.isEmpty())
                                        throw new ValidationException("Date cannot be empty");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }
                                flightController.updateReservation(reservationID, newDate);
                                break;
                            case 3:
                                flightController.deleteReservation(readId(scanner));
                                break;
                            case 4:
                                flightController.viewAllReservation();
                                break;
                        }
                        break;
                    case 3:// Manage Payments
                        System.out.println(crudObtions());
                        int operatorChoice3= scanner.nextInt();
                        scanner.nextLine();
                        switch (operatorChoice3) {
                            case 1:
                                Integer passengerID = null;
                                try {
                                    System.out.print("Enter Passenger ID for the Payment: ");
                                    passengerID = scanner.nextInt();
                                    scanner.nextLine();
                                    if (passengerID <= 0 || passengerID == null)
                                        throw new ValidationException("Passenger ID must be a positive number");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }

                                String description = null;
                                try{
                                    System.out.print("Enter Payment Description: ");
                                    description = scanner.nextLine();
                                    if (description == null || description.isEmpty())
                                        throw new ValidationException("Description cannot be empty");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }

                                double amount = 0;
                                try {
                                    System.out.print("Enter Payment Amount: ");
                                    amount = scanner.nextDouble();
                                    scanner.nextLine();
                                    if (amount <= 0)
                                        throw new ValidationException("Amount must be a positive number");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }
                                flightController.createPayment(description, amount, passengerID);
                                break;
                            case 2:
                                Integer paymentID = null;
                                try {
                                    System.out.print("Enter Payment ID to update: ");
                                    paymentID = scanner.nextInt();
                                    scanner.nextLine();
                                    if (paymentID <= 0 || paymentID == null)
                                        throw new ValidationException("Payment ID must be a positive number");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }
                                String newDescription = null;
                                try {
                                    System.out.print("Enter new Payment Description: ");
                                    newDescription = scanner.nextLine();
                                    if (newDescription == null || newDescription.isEmpty())
                                        throw new ValidationException("Description cannot be empty");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }
                                double newAmount = 0;
                                try {
                                    System.out.print("Enter new Payment Amount: ");
                                    newAmount = scanner.nextDouble();
                                    scanner.nextLine();
                                    if (newAmount <= 0)
                                        throw new ValidationException("Amount must be a positive number");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }
                                flightController.updatePayment(paymentID, newDescription, newAmount);
                                break;
                            case 3:
                                flightController.deletePayment(readId(scanner));
                                break;
                            case 4:
                                flightController.viewAllPayments();
                                break;
                        }
                        break;
                    case 4://Manage Tickets
                        System.out.println(crudObtions());
                        int operatorChoice4= scanner.nextInt();
                        scanner.nextLine();
                        switch (operatorChoice4) {
                            case 1:
                                Integer paymentID = null;
                                try {
                                    System.out.print("Enter Payment ID for Ticket: ");
                                    paymentID = scanner.nextInt();
                                    scanner.nextLine();
                                    if (paymentID <= 0 || paymentID == null)
                                        throw new ValidationException("Payment ID must be a positive number");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }

                                String title = null;
                                try {
                                    System.out.print("Enter Ticket Title: ");
                                    title = scanner.nextLine();
                                    if (title == null || title.isEmpty())
                                        throw new ValidationException("Title cannot be empty");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }

                                String description = null;
                                try {
                                    System.out.print("Enter Ticket Description: ");
                                    description = scanner.nextLine();
                                    if (description == null || description.isEmpty())
                                        throw new ValidationException("Description cannot be empty");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }

                                String date = null;
                                try {
                                    System.out.print("Enter the Flight date associated with the Ticket: ");
                                    date = scanner.nextLine();
                                    if (date == null || date.isEmpty())
                                        throw new ValidationException("Date cannot be empty");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }
                                flightController.createTicket(title, description, paymentID, date);
                                break;
                            case 2:
                                Integer ticketID = null;
                                try {
                                    System.out.print("Enter Ticket ID to update: ");
                                    ticketID = scanner.nextInt();
                                    scanner.nextLine();
                                    if (ticketID <= 0 || ticketID == null)
                                        throw new ValidationException("Ticket ID must be a positive number");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }

                                String newTitle = null;
                                try {
                                    System.out.print("Enter new Ticket Title: ");
                                    newTitle = scanner.nextLine();
                                    if (newTitle == null || newTitle.isEmpty())
                                        throw new ValidationException("Title cannot be empty");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }

                                String newDescription = null;
                                try {
                                    System.out.print("Enter new Ticket Description: ");
                                    newDescription = scanner.nextLine();
                                    if (newDescription == null || newDescription.isEmpty())
                                        throw new ValidationException("Description cannot be empty");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }
                                flightController.updateTicket(ticketID, newTitle, newDescription);
                                break;
                            case 3:
                                flightController.deleteTicket(readId(scanner));
                                break;
                            case 4:
                                flightController.viewAllTickets();
                                break;
                        }
                        break;
                    case 5:// Manage Airplanes
                        System.out.println(crudObtions());
                        int operatorChoice5= scanner.nextInt();
                        scanner.nextLine();
                        switch (operatorChoice5) {
                            case 1:
                                String model = null;
                                try {
                                    System.out.print("Enter Airplane Model: ");
                                    model = scanner.nextLine();
                                    if (model == null || model.isEmpty())
                                        throw new ValidationException("Model cannot be empty");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }
                                Integer capacity = null;
                                try {
                                    System.out.print("Enter Airplane Capacity: ");
                                    capacity = scanner.nextInt();
                                    scanner.nextLine();
                                    if (capacity <= 0 || capacity == null)
                                        throw new ValidationException("Capacity must be a positive number");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }
                                Boolean available = null;
                                try {
                                    System.out.print("Is the airplane available? (true/false): ");
                                    available = scanner.nextBoolean();
                                    scanner.nextLine();
                                    if (available == null)
                                        throw new ValidationException("Availability must be true or false");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }
                                flightController.createAirplane(model, capacity, available);
                                break;
                            case 2:
                                Integer airplaneID = null;
                                try {
                                    System.out.print("Enter Airplane ID to update: ");
                                    airplaneID = scanner.nextInt();
                                    scanner.nextLine();
                                    if (airplaneID <= 0 || airplaneID == null)
                                        throw new ValidationException("Airplane ID must be a positive number");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }

                                String newModel = null;
                                try {
                                    System.out.print("Enter new Airplane Model: ");
                                    newModel = scanner.nextLine();
                                    if (newModel == null || newModel.isEmpty())
                                        throw new ValidationException("Model cannot be empty");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }

                                Integer newCapacity = null;
                                try {
                                    System.out.print("Enter new Airplane Capacity: ");
                                    newCapacity = scanner.nextInt();
                                    scanner.nextLine();
                                    if (newCapacity <= 0 || newCapacity == null)
                                        throw new ValidationException("Capacity must be a positive number");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }
                                Boolean newAvailable = null;
                                try {
                                    System.out.print("Is the airplane available? (true/false): ");
                                    newAvailable = scanner.nextBoolean();
                                    scanner.nextLine();
                                    if (newAvailable == null)
                                        throw new ValidationException("Availability must be true or false");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }

                                flightController.updateAirplane(airplaneID, newModel, newCapacity, newAvailable);
                                break;
                            case 3:
                                flightController.deleteAirplane(readId(scanner));
                                break;
                            case 4:
                                flightController.viewAllAirplanes();
                                break;
                        }
                        break;
                    case 6:// Manage Passengers
                        System.out.println(crudObtions());
                        int operatorChoice6= scanner.nextInt();
                        scanner.nextLine();
                        switch (operatorChoice6) {
                            case 1:
                                String passengerName = null;
                                try {
                                    System.out.print("Enter Passenger Name: ");
                                    passengerName = scanner.nextLine();
                                    if (passengerName == null || passengerName.isEmpty())
                                        throw new ValidationException("Name cannot be empty");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }
                                String from = null;
                                try {
                                    System.out.print("Enter Departure Location: ");
                                    from = scanner.nextLine();
                                    if (from == null || from.isEmpty())
                                        throw new ValidationException("Departure location cannot be empty");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }
                                String to = null;
                                try {
                                    System.out.print("Enter Destination: ");
                                    to = scanner.nextLine();
                                    if (to == null || to.isEmpty())
                                        throw new ValidationException("Destination cannot be empty");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }
                                String email = null;
                                try {
                                    System.out.print("Enter Passenger Email: ");
                                    email = scanner.nextLine();
                                    if (email == null || email.isEmpty())
                                        throw new ValidationException("Email cannot be empty");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }
                                flightController.createPassenger(passengerName, from, to, email);
                                break;
                            case 2:
                                Integer passengerID = null;
                                try {
                                    System.out.print("Enter Passenger ID to update: ");
                                    passengerID = scanner.nextInt();
                                    scanner.nextLine();
                                    if (passengerID <= 0 || passengerID == null)
                                        throw new ValidationException("Passenger ID must be a positive number");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }
                                String newName = null;
                                try {
                                    System.out.print("Enter new Passenger Name: ");
                                    newName = scanner.nextLine();
                                    if (newName == null || newName.isEmpty())
                                        throw new ValidationException("Name cannot be empty");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }
                                String newEmail = null;
                                try {
                                    System.out.print("Enter new Passenger Email: ");
                                    newEmail = scanner.nextLine();
                                    if (newEmail == null || newEmail.isEmpty())
                                        throw new ValidationException("Email cannot be empty");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }
                                String newFrom = null;
                                try {
                                    System.out.print("Enter new Departure Location: ");
                                    newFrom = scanner.nextLine();
                                    if (newFrom == null || newFrom.isEmpty())
                                        throw new ValidationException("Departure location cannot be empty");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }
                                String newTo = null;
                                try {
                                    System.out.print("Enter new Destination: ");
                                    newTo = scanner.nextLine();
                                    if (newTo == null || newTo.isEmpty())
                                        throw new ValidationException("Destination cannot be empty");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }
                                flightController.updatePassenger(passengerID, newName, newEmail, newTo, newFrom);
                                break;
                            case 3:
                                flightController.deletePassenger(readId(scanner));
                                break;
                            case 4:
                                flightController.viewPassengers();
                                break;
                        }
                        break;
                    case 7:// Manage Pilots
                        System.out.println(crudObtions());
                        int operatorChoice7= scanner.nextInt();
                        scanner.nextLine();
                        switch (operatorChoice7) {
                            case 1:
                                String pilotName = null;
                                try {
                                    System.out.print("Enter Pilot Name: ");
                                    pilotName = scanner.nextLine();
                                    if (pilotName == null || pilotName.isEmpty())
                                        throw new ValidationException("Name cannot be empty");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }
                                String pilotEmail = null;
                                try {
                                    System.out.print("Enter Pilot Email: ");
                                    pilotEmail = scanner.nextLine();
                                    if (pilotEmail == null || pilotEmail.isEmpty())
                                        throw new ValidationException("Email cannot be empty");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }
                                Boolean availability = null;
                                try {
                                    System.out.print("Is the pilot available? (true/false): ");
                                    availability = scanner.nextBoolean();
                                    scanner.nextLine();
                                    if (availability == null)
                                        throw new ValidationException("Availability must be true or false");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }
                                flightController.createPilot(pilotName, pilotEmail, availability);
                                break;
                            case 2:
                                Integer pilotID = null;
                                try {
                                    System.out.print("Enter Pilot ID to update: ");
                                    pilotID = scanner.nextInt();
                                    scanner.nextLine();
                                    if (pilotID <= 0 || pilotID == null)
                                        throw new ValidationException("Pilot ID must be a positive number");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }
                                String newPilotName = null;
                                try {
                                    System.out.print("Enter new Pilot Name: ");
                                    newPilotName = scanner.nextLine();
                                    if (newPilotName == null || newPilotName.isEmpty())
                                        throw new ValidationException("Name cannot be empty");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }
                                String newPilotEmail = null;
                                try {
                                    System.out.print("Enter new Pilot Email: ");
                                    newPilotEmail = scanner.nextLine();
                                    if (newPilotEmail == null || newPilotEmail.isEmpty())
                                        throw new ValidationException("Email cannot be empty");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }

                                flightController.updatePilot(pilotID, newPilotName, newPilotEmail);
                                break;
                            case 3:
                                flightController.deletePilot(readId(scanner));
                                break;
                            case 4:
                                flightController.viewAvailablePilots();
                                break;
                        }
                        break;
                    case 8:// Manage Airports
                        System.out.println(crudObtions());
                        int operatorChoice8= scanner.nextInt();
                        scanner.nextLine();
                        switch (operatorChoice8) {
                            case 1:
                                String airportName = null;
                                try {
                                    System.out.print("Enter Airport Name: ");
                                    airportName = scanner.nextLine();
                                    if (airportName == null || airportName.isEmpty())
                                        throw new ValidationException("Name cannot be empty");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }
                                String location = null;
                                try {
                                    System.out.print("Enter the location of the Airport: ");
                                    location = scanner.nextLine();
                                    if (location == null || location.isEmpty())
                                        throw new ValidationException("Location cannot be empty");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }
                                Integer airstrips = null;
                                try {
                                    System.out.print("Enter the number of airstrips of the Airport: ");
                                    airstrips = scanner.nextInt();
                                    scanner.nextLine();
                                    if (airstrips <= 0 || airstrips == null)
                                        throw new ValidationException("Airstrips must be a positive number");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }
                                Boolean availability = null;
                                try {
                                    System.out.print("Is the airport available? (true/false): ");
                                    availability = scanner.nextBoolean();
                                    scanner.nextLine();
                                    if (availability == null)
                                        throw new ValidationException("Availability must be true or false");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }
                                flightController.createAirport(airportName, location, airstrips, availability);
                                break;
                            case 2:
                                Integer airportID = null;
                                try{
                                    System.out.print("Enter Airport ID to update: ");
                                    airportID = scanner.nextInt();
                                    scanner.nextLine();
                                    if (airportID <= 0 || airportID == null)
                                        throw new ValidationException("Airport ID must be a positive number");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }
                                String newAirportName = null;
                                try {
                                    System.out.print("Enter the new Airport Name: ");
                                    newAirportName = scanner.nextLine();
                                    if (newAirportName == null || newAirportName.isEmpty())
                                        throw new ValidationException("Name cannot be empty");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }
                                String newLocation = null;
                                try {
                                    System.out.print("Enter the new location of the Airport: ");
                                    newLocation = scanner.nextLine();
                                    if (newLocation == null || newLocation.isEmpty())
                                        throw new ValidationException("Location cannot be empty");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }
                                Integer newAirstrips = null;
                                try {
                                    System.out.print("Enter the new number of airstrips of the Airport: ");
                                    newAirstrips = scanner.nextInt();
                                    scanner.nextLine();
                                    if (newAirstrips <= 0 || newAirstrips == null)
                                        throw new ValidationException("Airstrips must be a positive number");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }
                                Boolean newAvailability = null;
                                try {
                                    System.out.print("Is the airport available? (true/false): ");
                                    newAvailability = scanner.nextBoolean();
                                    scanner.nextLine();
                                    if (newAvailability == null)
                                        throw new ValidationException("Availability must be true or false");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }

                                flightController.updateAirport(airportID,newAirportName,newLocation,newAirstrips,newAvailability);
                                break;
                            case 3:
                                flightController.deleteAirport(readId(scanner));
                                break;
                            case 4:
                                flightController.viewAllAirports();
                                break;
                        }
                        break;
                    case 9:// Manage Cabin Crew
                        System.out.println(crudObtions());
                        int operatorChoice9= scanner.nextInt();
                        scanner.nextLine();
                        switch (operatorChoice9) {
                            case 1:
                                String crewName = null;
                                try {
                                    System.out.print("Enter Cabin Crew Name: ");
                                    crewName = scanner.nextLine();
                                    if (crewName == null || crewName.isEmpty())
                                        throw new ValidationException("Name cannot be empty");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }
                                String crewEmail = null;
                                try {
                                    System.out.print("Enter Cabin Crew Email: ");
                                    crewEmail = scanner.nextLine();
                                    if (crewEmail == null || crewEmail.isEmpty())
                                        throw new ValidationException("Email cannot be empty");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }
                                String profession = null;
                                try {
                                    System.out.print("Enter Cabin Crew Profession: ");
                                    profession = scanner.nextLine();
                                    if (profession == null || profession.isEmpty())
                                        throw new ValidationException("Profession cannot be empty");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }
                                flightController.createCabinCrew(crewName, crewEmail, profession);
                                break;
                            case 2:
                                Integer cabinID = null;
                                try {
                                    System.out.print("Enter Cabin Crew ID to update: ");
                                    cabinID = scanner.nextInt();
                                    scanner.nextLine();
                                    if (cabinID <= 0 || cabinID == null)
                                        throw new ValidationException("Cabin Crew ID must be a positive number");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }
                                String newCrewName = null;
                                try {
                                    System.out.print("Enter new Cabin Crew Name: ");
                                    newCrewName = scanner.nextLine();
                                    if (newCrewName == null || newCrewName.isEmpty())
                                        throw new ValidationException("Name cannot be empty");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }
                                String newCrewEmail = null;
                                try {
                                    System.out.print("Enter new Cabin Crew Email: ");
                                    newCrewEmail = scanner.nextLine();
                                    if (newCrewEmail == null || newCrewEmail.isEmpty())
                                        throw new ValidationException("Email cannot be empty");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }
                                String newProfession = null;
                                try {
                                    System.out.print("Enter new Cabin Crew Profession: ");
                                    newProfession = scanner.nextLine();
                                    if (newProfession == null || newProfession.isEmpty())
                                        throw new ValidationException("Profession cannot be empty");
                                }
                                catch (ValidationException e) {
                                    System.out.println(e.getMessage());
                                    break;
                                }

                                flightController.updateCabinCrew(cabinID, newCrewName, newCrewEmail, newProfession);
                                break;
                            case 3:
                                flightController.deleteCabinCrew(readId(scanner));
                                break;
                            case 4:
                                flightController.viewCabinCrew();
                                break;
                        }
                        break;
                    case 10:
                        Integer flightID = null;
                        try {
                            System.out.print("Enter Flight ID to see its Passengers: ");
                            flightID = scanner.nextInt();
                            scanner.nextLine();
                            if (flightID <= 0 || flightID == null)
                                throw new ValidationException("Flight ID must be a positive number");
                        }
                        catch (ValidationException e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                        flightController.viewPassengersByFlight(flightID);
                        break;
                    case 11:
                        flightController.sortAirplanesByCapacity();
                        break;
                    case 12:
                        double flightAmount = 0;
                        try {
                            System.out.print("Enter the amount for your Flight: ");
                            flightAmount = scanner.nextDouble();
                            scanner.nextLine();
                            if (flightAmount <= 0)
                                throw new ValidationException("Amount must be a positive number");
                        }
                        catch (ValidationException e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                        flightController.filterFlightsByAmount(flightAmount);
                        break;
                    case 13: //sort flight by date
                        flightController.sortFlightsByDate();
                        break;
                    case 14: //filter cabincrew by profession
                        String profession = null;
                        try {
                            System.out.print("Enter the profession of the cabin crew you want to filter by: ");
                            profession = scanner.nextLine();
                            if (profession == null || profession.isEmpty())
                                throw new ValidationException("Profession cannot be empty");
                        }
                        catch (ValidationException e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                        flightController.filterCabinCrewByProfession(profession);
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
        } else if (userType.equals(2)) {
            // Opțiuni pentru Passenger
            boolean running = true;
            while (running) {
                int choice=0;
                try {
                    System.out.println(passengerMenu());
                    choice = scanner.nextInt();// citeste un număr întreg (de tip int) introdus de utilizator în consolă
                    scanner.nextLine();
                    if(choice<0 || choice>9)
                        throw new ValidationException("Invalid choice. Please try again.");
                }
                catch(ValidationException e) {
                    System.out.println(e.getMessage());
                    continue;
                }
                switch (choice) {
                    case 0:
                        running = false;
                        System.out.println("Exiting Passenger mode.");
                        break;
                    case 1:
                        String date = null;
                        try {
                            System.out.print("Enter the date when you would like to fly: ");
                            date = scanner.nextLine();
                            if (date == null || date.isEmpty())
                                throw new ValidationException("Date cannot be empty");
                        }
                        catch (ValidationException e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                        Integer passengerID = null;
                        try {
                            System.out.print("Enter your ID as passenger: ");
                            passengerID = scanner.nextInt();
                            scanner.nextLine();
                            if (passengerID <= 0 || passengerID == null)
                                throw new ValidationException("Passenger ID must be a positive number");
                        }
                        catch (ValidationException e) {
                            System.out.println(e.getMessage());
                            break;
                        }

                        String departure = null;
                        try {
                            System.out.println("Enter the departure location for your flight: ");
                            departure = scanner.nextLine();
                            if (departure == null || departure.isEmpty())
                                throw new ValidationException("Departure location cannot be empty");
                        }
                        catch (ValidationException e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                        String paymentType=null;
                        try{
                            System.out.println("How do you want to pay? (credit card/debit card)");
                            paymentType=scanner.nextLine();
                            if (paymentType == null || paymentType.isEmpty())
                                throw new ValidationException("Payment type cannot be empty");
                        }
                        catch (ValidationException e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                        Boolean ans=flightController.getAllAvalibleFlightsForPassenger(passengerID,date);
                        if(ans.equals(Boolean.FALSE)) {
                            Passenger pas=flightController.getPassengerByID(passengerID);
                            flightController.createReservation(date,passengerID,pas.getFlight().getFrom(),pas.getFlight().getTo());
                            break;
                        }
                        else {
                            System.out.println("Choose the ID of your flight");
                            Integer flightID = scanner.nextInt();
                            scanner.nextLine();
                            flightController.bookseat(passengerID,date,flightID,paymentType);
                            System.out.println();
                        }


                        break;
                    case 2:
                        flightController.deleteTicket(readId(scanner));
                        break;
                    case 3:
                        flightController.deleteReservation(readId(scanner));
                        break;
                    case 4:
                        Integer ticketID = null;
                        try {
                            System.out.print("Enter Ticket ID to update: ");
                            ticketID = scanner.nextInt();
                            scanner.nextLine();
                            if (ticketID <= 0 || ticketID == null)
                                throw new ValidationException("Ticket ID must be a positive number");
                        }
                        catch (ValidationException e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                        String newTitle = null;
                        try {
                            System.out.print("Enter new Title: ");
                            newTitle = scanner.nextLine();
                            if (newTitle == null || newTitle.isEmpty())
                                throw new ValidationException("Title cannot be empty");
                        }
                        catch (ValidationException e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                        String newDescription = null;
                        try {
                            System.out.print("Enter new Description: ");
                            newDescription = scanner.nextLine();
                            if (newDescription == null || newDescription.isEmpty())
                                throw new ValidationException("Description cannot be empty");
                        }
                        catch (ValidationException e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                        flightController.updateTicket(ticketID, newTitle, newDescription);
                        System.out.println("Updated Ticket: " + newTitle + " with new description.");
                        break;
                    case 5:
                        Integer reservationID = null;
                        try{
                            System.out.print("Enter Reservation ID to update: ");
                            reservationID = scanner.nextInt();
                            scanner.nextLine();
                            if (reservationID <= 0 || reservationID == null)
                                throw new ValidationException("Reservation ID must be a positive number");
                        }
                        catch (ValidationException e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                        String newDate = null;
                        try {
                            System.out.print("Enter new Date: ");
                            newDate = scanner.nextLine();
                            if (newDate == null || newDate.isEmpty())
                                throw new ValidationException("Date cannot be empty");
                        }
                        catch (ValidationException e) {
                            System.out.println(e.getMessage());
                            break;
                        }

                        flightController.updateReservation(reservationID, newDate);
                        break;
                    case 6:
                        flightController.getTicket(readId(scanner));
                        break;
                    case 7:
                        flightController.getReservation(readId(scanner));
                        break;
                    case 8: //sort flight by date
                        flightController.sortFlightsByDate();
                        break;
                    case 9: //bookseatByflight
                        String date1 = null;
                        try {
                            System.out.print("Enter the date when you would like to fly: ");
                            date1 = scanner.nextLine();
                            if (date1 == null || date1.isEmpty())
                                throw new ValidationException("Date cannot be empty");
                        }
                        catch (ValidationException e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                        Integer passengerID1 = null;
                        try {
                            System.out.print("Enter your ID as passenger: ");
                            passengerID1 = scanner.nextInt();
                            scanner.nextLine();
                            if (passengerID1 <= 0 || passengerID1 == null)
                                throw new ValidationException("Passenger ID must be a positive number");
                        }
                        catch (ValidationException e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                        String paymentType1=null;
                        try{
                            System.out.println("How do you want to pay? (credit card/debit card)");
                            paymentType1=scanner.nextLine();
                            if (paymentType1 == null || paymentType1.isEmpty())
                                throw new ValidationException("Payment type cannot be empty");
                        }
                        catch (ValidationException e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                        String departure1 = null;
                        try{
                            System.out.println("Enter the departure location for your flight: ");
                            departure1 = scanner.nextLine();
                            if (departure1 == null || departure1.isEmpty())
                                throw new ValidationException("Departure location cannot be empty");
                        }
                        catch (ValidationException e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                        String destination1 = null;
                        try{
                            System.out.println("Enter the destination for your flight: ");
                            destination1 = scanner.nextLine();
                            if (destination1 == null || destination1.isEmpty())
                                throw new ValidationException("Destination cannot be empty");
                        }
                        catch (ValidationException e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                        Boolean ans1=flightController.getAllAvalibleFlightsForPassenger(passengerID1,date1);
                        if(ans1.equals(Boolean.FALSE)) {
                            Passenger pas=flightController.getPassengerByID(passengerID1);
                            flightController.createReservation(date1,passengerID1,departure1,destination1);
                            break;
                        }
                        else {
                            Integer flightID = null;
                            try {
                                System.out.println("Choose the ID of your flight");
                                flightID = scanner.nextInt();
                                scanner.nextLine();
                                if(flightID<=0 || flightID==null)
                                    throw new ValidationException("Flight ID must be a positive number");
                            }
                            catch (ValidationException e) {
                                System.out.println(e.getMessage());
                                break;
                            }
                            flightController.bookSeatByFlight(date1,passengerID1,flightID,paymentType1,departure1,destination1);
                            System.out.println();
                        }
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
        } else {
            System.out.println("Invalid user type. Please restart and choose either Operator or Passenger.");
        }

        scanner.close();
    }

    /**
     * Reads an ID from the user input.
     *
     * @param scanner The Scanner object used to read user input.
     * @return The ID entered by the user.
     */
    private static int readId(Scanner scanner) {
        System.out.println("Enter an ID: ");
        return Integer.parseInt(scanner.nextLine());//citește întreaga linie de text introdusă de utilizator și o convertește într-un int
    }

}
