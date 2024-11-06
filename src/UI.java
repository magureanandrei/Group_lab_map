import Models.Pilot;
import Repo.Repository;

import java.util.Scanner;

public class UI {
    private final Controller flightController;

    public UI(Controller flightController) {
        this.flightController = flightController;
    }

    public void greeting() {
        System.out.println("Welcome to Airplane Management System! \n");
    }
    public String operatorMenu(){
        return """
                Operator Options:
                1. Create Flight
                0. Exit Program
                """;
    }

    public String passengerMenu(){
        return """
                Passenger Options:
                1. Book Flight
                2. Cancel Booking
                0. Exit Program
                """;
    }
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
