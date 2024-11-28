

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
        System.out.println("Are you an Operator(1) or a Passenger(2)?");
        Integer userType = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character left by nextInt()
        // Validăm tipul de utilizator
        if (userType.equals(1)) {
            // Opțiuni pentru Operator
            boolean running = true;
            while (running) {
                System.out.println(operatorMenu());
                int choice = scanner.nextInt();// citeste un număr întreg (de tip int) introdus de utilizator în consolă
                scanner.nextLine();
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
                                System.out.print("Enter a Pilot ID for your flight: ");
                                Integer pilotID = scanner.nextInt();
                                scanner.nextLine();
                                System.out.print("Enter an Airplane ID for your flight: ");
                                Integer airplaneID = scanner.nextInt();
                                scanner.nextLine();
                                System.out.print("Enter the departure location for your flight: ");
                                String departure = scanner.nextLine();
                                System.out.print("Enter the destination: ");
                                String destination = scanner.nextLine();
                                System.out.print("Enter the Airport ID: ");
                                Integer airportID = scanner.nextInt();
                                scanner.nextLine();
                                System.out.print("Enter the date of form (\"2023-10-01\"): ");
                                String date = scanner.nextLine();
                                System.out.print("Enter Payment Amount: ");
                                double amount = scanner.nextDouble();
                                scanner.nextLine();
                                flightController.createFlight(departure,destination,pilotID,airplaneID,airportID,date,amount);
                                break;
                            case 2:
                                System.out.print("Enter a Flight ID to update: ");
                                Integer flightID = scanner.nextInt();
                                scanner.nextLine();
                                System.out.print("Enter the new departure location for your flight: ");
                                String departure2 = scanner.nextLine();
                                System.out.print("Enter the new destination: ");
                                String destination2 = scanner.nextLine();
                                System.out.print("Enter a Pilot ID for your flight: ");
                                Integer pilotID2 = scanner.nextInt();
                                scanner.nextLine();
                                System.out.print("Enter an Airplane ID for your flight: ");
                                Integer airplaneID2 = scanner.nextInt();
                                scanner.nextLine();
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
                                System.out.print("Enter Passenger ID: ");
                                Integer passengerID = scanner.nextInt();
                                scanner.nextLine();
                                System.out.print("Enter Reservation Date: ");
                                String date = scanner.nextLine();
                                System.out.print("Enter Reservation Departure Location: ");
                                String departure = scanner.nextLine();
                                System.out.print("Enter Reservation Destination: ");
                                String destination = scanner.nextLine();
                                flightController.createReservation(date,passengerID,departure,destination);
                                break;
                            case 2:
                                System.out.print("Enter Reservation ID to update: ");
                                Integer reservationID = scanner.nextInt();
                                scanner.nextLine();
                                System.out.print("Enter new Reservation Date: ");
                                String newDate = scanner.nextLine();
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
                                System.out.print("Enter Passenger ID for the Payment: ");
                                Integer passengerID = scanner.nextInt();
                                scanner.nextLine();
                                System.out.print("Enter Payment Description: ");
                                String description = scanner.nextLine();
                                System.out.print("Enter Payment Amount: ");
                                double amount = scanner.nextDouble();
                                scanner.nextLine();
                                flightController.createPayment(description, amount, passengerID);
                                break;
                            case 2:
                                System.out.print("Enter Payment ID to update: ");
                                Integer paymentID = scanner.nextInt();
                                scanner.nextLine();
                                System.out.print("Enter new Payment Description: ");
                                String newDescription = scanner.nextLine();
                                System.out.print("Enter new Payment Amount: ");
                                double newAmount = scanner.nextDouble();
                                scanner.nextLine();
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
                                System.out.print("Enter Payment ID for Ticket: ");
                                Integer paymentID = scanner.nextInt();
                                scanner.nextLine();
                                System.out.print("Enter Ticket Title: ");
                                String title = scanner.nextLine();
                                System.out.print("Enter Ticket Description: ");
                                String description = scanner.nextLine();
                                System.out.print("Enter the Flight date associated with the Ticket: ");
                                String date = scanner.nextLine();
                                flightController.createTicket(title, description, paymentID, date);
                                break;
                            case 2:
                                System.out.print("Enter Ticket ID to update: ");
                                Integer ticketID = scanner.nextInt();
                                scanner.nextLine();
                                System.out.print("Enter new Ticket Title: ");
                                String newTitle = scanner.nextLine();
                                System.out.print("Enter new Ticket Description: ");
                                String newDescription = scanner.nextLine();
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
                                System.out.print("Enter Airplane Model: ");
                                String model = scanner.nextLine();
                                System.out.print("Enter Airplane Capacity: ");
                                Integer capacity = scanner.nextInt();
                                scanner.nextLine();
                                System.out.print("Is the airplane available? (true/false): ");
                                Boolean available = scanner.nextBoolean();
                                scanner.nextLine();
                                flightController.createAirplane(model, capacity, available);
                                break;
                            case 2:
                                System.out.print("Enter Airplane ID to update: ");
                                Integer airplaneID = scanner.nextInt();
                                scanner.nextLine();
                                System.out.print("Enter new Airplane Model: ");
                                String newModel = scanner.nextLine();
                                System.out.print("Enter new Airplane Capacity: ");
                                Integer newCapacity = scanner.nextInt();
                                scanner.nextLine();
                                System.out.print("Is the airplane available? (true/false): ");
                                Boolean newAvailable = scanner.nextBoolean();
                                scanner.nextLine();
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
                                System.out.print("Enter Passenger Name: ");
                                String passengerName = scanner.nextLine();
                                System.out.print("Enter Departure Location: ");
                                String from = scanner.nextLine();
                                System.out.print("Enter Destination: ");
                                String to = scanner.nextLine();
                                System.out.print("Enter Passenger Email: ");
                                String email = scanner.nextLine();
                                flightController.createPassenger(passengerName, from, to, email);
                                break;
                            case 2:
                                System.out.print("Enter Passenger ID to update: ");
                                Integer passengerID = scanner.nextInt();
                                scanner.nextLine();
                                System.out.print("Enter new Passenger Name: ");
                                String newName = scanner.nextLine();
                                System.out.print("Enter new Passenger Email: ");
                                String newEmail = scanner.nextLine();
                                System.out.print("Enter new Departure Location: ");
                                String newFrom = scanner.nextLine();
                                System.out.print("Enter new Destination: ");
                                String newTo = scanner.nextLine();
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
                                System.out.print("Enter Pilot Name: ");
                                String pilotName = scanner.nextLine();
                                System.out.print("Enter Pilot Email: ");
                                String pilotEmail = scanner.nextLine();
                                System.out.print("Is the pilot available? (true/false): ");
                                Boolean availability = scanner.nextBoolean();
                                scanner.nextLine();
                                flightController.createPilot(pilotName, pilotEmail, availability);
                                break;
                            case 2:
                                System.out.print("Enter Pilot ID to update: ");
                                Integer pilotID = scanner.nextInt();
                                scanner.nextLine();
                                System.out.print("Enter new Pilot Name: ");
                                String newPilotName = scanner.nextLine();
                                System.out.print("Enter new Pilot Email: ");
                                String newPilotEmail = scanner.nextLine();
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
                                System.out.print("Enter Airport Name: ");
                                String airportName = scanner.nextLine();
                                System.out.print("Enter the location of the Airport: ");
                                String location = scanner.nextLine();
                                System.out.print("Enter the number of airstrips of the Airport: ");
                                Integer airstrips = scanner.nextInt();
                                scanner.nextLine();
                                System.out.print("Is the airport available? (true/false): ");
                                Boolean availability = scanner.nextBoolean();
                                scanner.nextLine();
                                flightController.createAirport(airportName, location, airstrips, availability);
                                break;
                            case 2:
                                System.out.print("Enter Airport ID to update: ");
                                Integer airportID = scanner.nextInt();
                                scanner.nextLine();
                                System.out.print("Enter the new Airport Name: ");
                                String newAirportName = scanner.nextLine();
                                System.out.print("Enter the new location of the Airport: ");
                                String newLocation = scanner.nextLine();
                                System.out.print("Enter the new number of airstrips of the Airport: ");
                                Integer newAirstrips = scanner.nextInt();
                                scanner.nextLine();
                                System.out.print("Is the airport available? (true/false): ");
                                Boolean newAvailability = scanner.nextBoolean();
                                scanner.nextLine();
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
                                System.out.print("Enter Cabin Crew Name: ");
                                String crewName = scanner.nextLine();
                                System.out.print("Enter Cabin Crew Email: ");
                                String crewEmail = scanner.nextLine();
                                System.out.print("Enter Cabin Crew Profession: ");
                                String profession = scanner.nextLine();
                                flightController.createCabinCrew(crewName, crewEmail, profession);
                                break;
                            case 2:
                                System.out.print("Enter Cabin Crew ID to update: ");
                                Integer cabinID = scanner.nextInt();
                                scanner.nextLine();
                                System.out.print("Enter new Cabin Crew Name: ");
                                String newCrewName = scanner.nextLine();
                                System.out.print("Enter new Cabin Crew Email: ");
                                String newCrewEmail = scanner.nextLine();
                                System.out.print("Enter new Cabin Crew Profession: ");
                                String newProfession = scanner.nextLine();
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
                        System.out.print("Enter Flight ID to see its Passengers: ");
                        Integer flightID = scanner.nextInt();
                        scanner.nextLine();
                        flightController.viewPassengersByFlight(flightID);
                        break;
                    case 11:
                        flightController.sortAirplanesByCapacity();
                        break;
                    case 12:
                        System.out.print("Enter the amount for your Flight: ");
                        double flightAmount = scanner.nextDouble();
                        scanner.nextLine();
                        flightController.filterFlightsByAmount(flightAmount);
                        break;
                    case 13: //sort flight by date
                        flightController.sortFlightsByDate();
                        break;
                    case 14: //filter cabincrew by profession
                        System.out.println("Enter the profession of the cabin crew you want to filter by: ");
                        String profession = scanner.nextLine();
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
                System.out.println( passengerMenu());
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 0:
                        running = false;
                        System.out.println("Exiting Passenger mode.");
                        break;
                    case 1:
                        System.out.print("Enter the date when you would like to fly: ");
                        String date = scanner.nextLine();
                        System.out.print("Enter your ID as passenger: ");
                        Integer passengerID = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("How do you want to pay? (credit card/debit card)");
                        String paymentType=scanner.nextLine();
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
                        System.out.print("Enter Ticket ID to update: ");
                        Integer ticketID = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter new Title: ");
                        String newTitle = scanner.nextLine();
                        System.out.print("Enter new Description: ");
                        String newDescription = scanner.nextLine();
                        flightController.updateTicket(ticketID, newTitle, newDescription);
                        System.out.println("Updated Ticket: " + newTitle + " with new description.");
                        flightController.updateTicket(ticketID, newTitle, newDescription);
                        break;
                    case 5:
                        System.out.print("Enter Reservation ID to update: ");
                        Integer reservationID = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter new Date: ");
                        String newDate = scanner.nextLine();
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
                        System.out.print("Enter the date when you would like to fly: ");
                        String date1 = scanner.nextLine();
                        System.out.print("Enter your ID as passenger: ");
                        Integer passengerID1 = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("How do you want to pay? (credit card/debit card)");
                        String paymentType1=scanner.nextLine();
                        System.out.println("Enter the departure location for your flight: ");
                        String departure = scanner.nextLine();
                        System.out.println("Enter the destination: ");
                        String destination = scanner.nextLine();
                        Boolean ans1=flightController.getAllAvalibleFlightsForPassenger(passengerID1,date1);
                        if(ans1.equals(Boolean.FALSE)) {
                            Passenger pas=flightController.getPassengerByID(passengerID1);
                            flightController.createReservation(date1,passengerID1,departure,destination);
                            break;
                        }
                        else {
                            System.out.println("Choose the ID of your flight");
                            Integer flightID = scanner.nextInt();
                            scanner.nextLine();
                            flightController.bookSeatByFlight(date1,passengerID1,flightID,paymentType1,departure,destination);
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
