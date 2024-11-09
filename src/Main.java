import Models.*;
import Repo.InFileRepo;
import Repo.InMemoryRepo;
import Repo.Repository;

import java.util.ArrayList;


/**
 * Main class that initializes the flight management system, including repositories, services, controllers, and the UI.
 */
public class Main {

    /**
     * The entry point of the application.
     * Initializes repositories, services, controllers, and starts the UI.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        Repository<Pilot> pilotsRepo = createInMemoryPilotRepository();
        Repository<Passenger> passengerRepo = createInMemoryPassengerRepository();
        Repository<CabinCrew> cabinCrewRepo = createInMemoryCabinCrewRepository();
        Repository<Flight> flightRepo = createInMemoryFlightRepository();
        Repository<Payment> paymentRepo = createInMemoryPaymentRepository();
        Repository<Reservation> reservationRepo = createInMemoryReservationRepository();
        Repository<Ticket> ticketRepo = createInMemoryTicketRepository();
        Repository<Airplane> airplaneRepository = createInMemoryAirplaneRepository();
        Repository<Airport> airportRepo = createInMemoryAirportRepository();

        Service flightService = new Service(pilotsRepo, passengerRepo,cabinCrewRepo,flightRepo,paymentRepo,reservationRepo,ticketRepo,airplaneRepository, airportRepo);
        Controller flightController = new Controller(flightService);

        UI consoleApp = new UI(flightController);
        consoleApp.run();

    }

    /**
     * Creates an in-memory repository for pilots and populates it with some initial data.
     *
     * @return The in-memory repository for pilots.
     */
    private static Repository<Pilot> createInMemoryPilotRepository(){
        Repository<Pilot> pilotRepository = new InMemoryRepo<>();
        pilotRepository.create(new Pilot("Mihai Serban", 1, "mihai.serban@gmail.com", Boolean.TRUE));
        pilotRepository.create(new Pilot("Andrei Iustin", 2, "andrei.iustin@gmail.com", Boolean.FALSE));
        pilotRepository.create(new Pilot("Serban Pop", 3, "serban.pop@gmail.com", Boolean.TRUE));
        pilotRepository.create(new Pilot("Raul Popescu", 4, "raul.popescu@gmail.com", Boolean.FALSE));
        pilotRepository.create(new Pilot("Stefan Mitica", 5, "stefan.mitica@gmail.com", Boolean.TRUE));
        return pilotRepository;
    }

    /**
     * Creates an in-memory repository for passengers and populates it with some initial data.
     *
     * @return The in-memory repository for passengers.
     */
    private static Repository<Passenger> createInMemoryPassengerRepository(){
        Repository<Passenger> passengerRepository = new InMemoryRepo<>();
        passengerRepository.create(new Passenger("Mr Johnson", 1, "mr.johnson@gmail.com", new Pair("New York", "Los Angeles")));
        passengerRepository.create(new Passenger("Ms Johnson", 2, "ms.johnson@gmail.com", new Pair("San Francisco", "Chicago")));
        passengerRepository.create(new Passenger("Johnson Jr", 3, "johnson.jr@gmail.com", new Pair("Miami", "Houston")));
        passengerRepository.create(new Passenger("Alice Johnson", 4, "alice.johnson@gmail.com", new Pair("Seattle", "Denver")));
        passengerRepository.create(new Passenger("Elvis Johnson", 5, "elvis.johnson@gmail.com", new Pair("Boston", "Atlanta")));
        return passengerRepository;
    }

    /**
     * Creates an in-memory repository for cabin crew and populates it with some initial data.
     *
     * @return The in-memory repository for cabin crew.
     */
    private static Repository<CabinCrew> createInMemoryCabinCrewRepository(){
        Repository<CabinCrew> cabinCrewRepository = new InMemoryRepo<>();
        cabinCrewRepository.create(new CabinCrew("John Doe", 1, "john.doe@gmail.com", "Steward"));
        cabinCrewRepository.create(new CabinCrew("Jane Smith", 2, "jane.smith@gmail.com", "Stewardess"));
        cabinCrewRepository.create(new CabinCrew("Robert Brown", 3, "robert.brown@gmail.com", "Steward"));
        cabinCrewRepository.create(new CabinCrew("Emily Davis", 4, "emily.davis@gmail.com", "Stewardess"));
        cabinCrewRepository.create(new CabinCrew("Michael Wilson", 5, "michael.wilson@gmail.com", "Steward"));
        return cabinCrewRepository;
    }

    /**
     * Creates an in-memory repository for flights and populates it with some initial data.
     *
     * @return The in-memory repository for flights.
     */
    private static Repository<Flight> createInMemoryFlightRepository(){
        Repository<Flight> flightRepository = new InMemoryRepo<>();
        Pilot p1 = new Pilot("Mihai Serban", 1, "mihai.serban@gmail.com", Boolean.TRUE);
        Airplane a1 = new Airplane(1, "Boeing 737", 180, Boolean.TRUE);
        flightRepository.create(new Flight(1, "Barcelona", "Madrid", p1, a1));
        flightRepository.create(new Flight(2, "New York", "Los Angeles", p1, a1));
        flightRepository.create(new Flight(3, "San Francisco", "Chicago", p1, a1));
        flightRepository.create(new Flight(4, "Miami", "Houston", p1, a1));
        flightRepository.create(new Flight(5, "Seattle", "Denver", p1, a1));
        return flightRepository;
    }

    /**
     * Creates an in-memory repository for payments and populates it with some initial data.
     *
     * @return The in-memory repository for payments.
     */
    private static Repository<Payment> createInMemoryPaymentRepository(){
        Repository<Payment> paymentRepository = new InMemoryRepo<>();
        Passenger passenger1 = new Passenger("Mr Johnson", 1, "mr.johnson@gmail.com", new Pair("New York", "Los Angeles"));
        Passenger passenger2 = new Passenger("Ms Johnson", 2, "ms.johnson@gmail.com", new Pair("San Francisco", "Chicago"));
        paymentRepository.create(new Payment(1, "Flight payment", 200.0, passenger1));
        paymentRepository.create(new Payment(2, "Baggage fee", 50.0, passenger1));
        paymentRepository.create(new Payment(3, "Flight payment", 300.0, passenger2));
        paymentRepository.create(new Payment(4, "Baggage fee", 75.0, passenger2));
        paymentRepository.create(new Payment(5, "Seat upgrade", 100.0, passenger2));
        return paymentRepository;
    }

    /**
     * Creates an in-memory repository for reservations and populates it with some initial data.
     *
     * @return The in-memory repository for reservations.
     */
    private static Repository<Reservation> createInMemoryReservationRepository(){
        Repository<Reservation> reservationRepository = new InMemoryRepo<>();
        Passenger passenger1 = new Passenger("Mr Johnson", 1, "mr.johnson@gmail.com", new Pair("New York", "Los Angeles"));
        Passenger passenger2 = new Passenger("Ms Johnson", 2, "ms.johnson@gmail.com", new Pair("San Francisco", "Chicago"));
        Payment payment1 = new Payment(1, "Flight payment", 200.0, passenger1);
        Payment payment2 = new Payment(3, "Flight payment", 300.0, passenger2);
        reservationRepository.create(new Reservation(1, "2023-10-01", payment1, passenger1, new Pair("Barcelona", "Madrid")));
        reservationRepository.create(new Reservation(2, "2023-10-02", payment1, passenger1, new Pair("New York", "Los Angeles")));
        reservationRepository.create(new Reservation(3, "2023-10-03", payment2, passenger2, new Pair("Barcelona", "Madrid")));
        reservationRepository.create(new Reservation(4, "2023-10-04", payment2, passenger2, new Pair("New York", "Los Angeles")));
        reservationRepository.create(new Reservation(5, "2023-10-05", payment2, passenger2, new Pair("Barcelona", "Madrid")));
        return reservationRepository;
    }

    /**
     * Creates an in-memory repository for tickets and populates it with some initial data.
     *
     * @return The in-memory repository for tickets.
     */
    private static Repository<Ticket> createInMemoryTicketRepository(){
        Repository<Ticket> ticketRepository = new InMemoryRepo<>();
        Payment payment1 = new Payment(1, "Flight payment", 200.0, new Passenger("Mr Johnson", 1, "mr.johnson@gmail.com", new Pair("New York", "Los Angeles")));
        Payment payment2 = new Payment(3, "Flight payment", 300.0, new Passenger("Ms Johnson", 2, "ms.johnson@gmail.com", new Pair("San Francisco", "Chicago")));
        ticketRepository.create(new Ticket(1, "Economy Class", "Seat 12A", payment1));
        ticketRepository.create(new Ticket(2, "Business Class", "Seat 1A", payment1));
        ticketRepository.create(new Ticket(3, "First Class", "Seat 2A", payment2));
        ticketRepository.create(new Ticket(4, "Economy Class", "Seat 14B", payment2));
        ticketRepository.create(new Ticket(5, "Business Class", "Seat 3A", payment2));
        return ticketRepository;
    }

    /**
     * Creates an in-memory repository for airplanes and populates it with some initial data.
     *
     * @return The in-memory repository for airplanes.
     */
    private static Repository<Airplane> createInMemoryAirplaneRepository(){
        Repository<Airplane> airplaneRepository = new InMemoryRepo<>();
        airplaneRepository.create(new Airplane(1, "Boeing 737", 180, Boolean.TRUE));
        airplaneRepository.create(new Airplane(2, "Airbus A320", 150, Boolean.FALSE));
        airplaneRepository.create(new Airplane(3, "Boeing 747", 400, Boolean.TRUE));
        airplaneRepository.create(new Airplane(4, "Airbus A380", 500, Boolean.FALSE));
        airplaneRepository.create(new Airplane(5, "Boeing 777", 300, Boolean.TRUE));
        return airplaneRepository;
    }
    private static Repository<Airport> createInMemoryAirportRepository(){
        Repository<Airport> airportRepository = new InMemoryRepo<>();
        airportRepository.create(new Airport(1, "Henri Coanda", "Otopeni", 2));
        airportRepository.create(new Airport(2, "Baneasa", "Bucharest", 1));
        airportRepository.create(new Airport(3, "Heathrow", "London", 4));
        airportRepository.create(new Airport(4, "Gatwick", "London", 3));
        airportRepository.create(new Airport(5, "Charles de Gaulle", "Paris", 3));
        return airportRepository;
    }
}