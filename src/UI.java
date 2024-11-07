import Models.Pilot;
import Repo.Repository;

import java.util.Scanner;
/**
 * The UI class provides a command-line interface for interacting with the flight management system.
 */
public class UI {
    private final Controller flightController;

    /**
     * Constructs a new UI with the given Controller.
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
                1. Create Flight
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
                1. Book Flight
                2. Cancel Booking
                0. Exit Program
                """;
    }

    /**
     * Starts the UI and handles user input, providing appropriate options for operator and passenger modes.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        greeting();
        System.out.println("Are you an Operator(1) or a Passenger(2)?");
        Integer userType = scanner.nextInt();
        // Validăm tipul de utilizator
        if (userType.equals(1)) {
            // Opțiuni pentru Operator
            boolean running = true;
            while (running) {
                System.out.println(operatorMenu());
                int choice = scanner.nextInt();// citeste un număr întreg (de tip int) introdus de utilizator în consolă

                switch (choice) {
                    case 0:
                        running = false;
                        System.out.println("Exiting Operator mode.");
                        break;
                    case 1:
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

                switch (choice) {
                    case 0:
                        running = false;
                        System.out.println("Exiting Passenger mode.");
                        break;
                    case 1:
                        break;
                    case 2:
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

}
