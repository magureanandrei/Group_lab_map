import Models.*;
import Repo.Repository;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        Repository<Pilot> pilotsRepo = createInMemoryPilotRepository();
        Repository<Passenger> passengerRepo = createInMemoryPassengerRepository();
        Repository<CabinCrew> cabinCrewRepo = createInMemoryCabinCrewRepository();
        Repository<Flight> flightRepo = createInMemoryFlightRepository();
        Repository<Payment> paymentRepo = createInMemoryPaymentRepository();
        Repository<Reservation> reservationRepo = createInMemoryReservationRepository();
        Repository<Ticket> ticketRepo = createInMemoryTicketRepository();
        Repository<Airplane> airplaneRepository = createInMemoryAirplaneRepository();

        Service flightService = new Service(pilotsRepo, passengerRepo,cabinCrewRepo,flightRepo,paymentRepo,reservationRepo,ticketRepo,airplaneRepository);
        Controller flightController = new Controller(flightService);

        UI consoleApp = new UI(flightController);
        consoleApp.run();

    }
    private static Repository<Pilot> createInMemoryPilotRepository(){
        return null;
    }
    private static Repository<Passenger> createInMemoryPassengerRepository(){
        return null;
    }
    private static Repository<CabinCrew> createInMemoryCabinCrewRepository(){
        return null;
    }
    private static Repository<Flight> createInMemoryFlightRepository(){
        return null;
    }
    private static Repository<Payment> createInMemoryPaymentRepository(){
        return null;
    }
    private static Repository<Reservation> createInMemoryReservationRepository(){
        return null;
    }
    private static Repository<Ticket> createInMemoryTicketRepository(){
        return null;
    }
    private static Repository<Airplane> createInMemoryAirplaneRepository(){
        return null;
    }

}