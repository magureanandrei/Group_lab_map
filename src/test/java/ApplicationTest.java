import Exceptions.BusinessLogicException;
import Exceptions.DatabaseException;
import Exceptions.EntityNotFoundException;
import Models.*;
import Repo.InMemoryRepo;
import Repo.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest {
    private final Repository<Airplane> airplaneRepository =  new InMemoryRepo<>();
    private final Repository<Airport> airportRepo =  new InMemoryRepo<>();
    private final Repository<Pilot> pilotRepository = new InMemoryRepo<>();
    private final Repository<Passenger> passengerRepository = new InMemoryRepo<>();
    private final  Repository<CabinCrew> cabinCrewRepository = new InMemoryRepo<>();
    private final Repository<Flight> flightRepository = new InMemoryRepo<>();
    private final  Repository<Payment> paymentRepository = new InMemoryRepo<>();
    private final  Repository<Reservation> reservationRepository = new InMemoryRepo<>();
    private final Repository<Ticket> ticketRepository = new InMemoryRepo<>();
    Service flightService = new Service(pilotRepository, passengerRepository,cabinCrewRepository,flightRepository,paymentRepository,reservationRepository,ticketRepository,airplaneRepository, airportRepo);

    @Test
    void testCRUDOperationsForAirplane() throws DatabaseException {
        // Create
        airplaneRepository.create(new Airplane(1, "Boeing 747", 400, true));
        List<Airplane> airplanes = airplaneRepository.getAll();
        assertEquals(1, airplanes.size());
        assertEquals("Boeing 747", airplanes.get(0).getModel());
        assertTrue(airplanes.get(0).getAvailable());
        assertEquals(400, airplanes.get(0).getCapacity());

        // Read
        Airplane airplane = airplaneRepository.get(1);
        assertEquals(1, airplane.getID());
        assertEquals(400, airplane.getCapacity());

        // Update
        airplane.setModel("Airbus A380");
        airplane.setCapacity(800);
        airplane.setAvailable(false);
        airplaneRepository.update(airplane);
        Airplane updatedAirplane = airplaneRepository.get(1);
        assertEquals("Airbus A380", updatedAirplane.getModel());
        assertEquals(800, updatedAirplane.getCapacity());
        assertFalse(updatedAirplane.getAvailable());

        // Delete
        airplaneRepository.delete(1);
        assertEquals(0, airplaneRepository.getAll().size());
    }

    @Test
    void testCRUDOperationsForAirport() throws DatabaseException {
        // Create
        airportRepo.create(new Airport(1, "Heathrow", "London", 5, true));
        List<Airport> airports = airportRepo.getAll();
        assertEquals(1, airports.size());
        assertEquals("Heathrow", airports.get(0).getName());
        assertEquals("London", airports.get(0).getLocation());
        assertEquals(5, airports.get(0).getNumber_of_airstrips());
        assertTrue(airports.get(0).getAvaliable());

        // Read
        Airport airport = airportRepo.get(1);
        assertEquals(1, airport.getID());
        assertEquals("London", airport.getLocation());

        // Update
        airport.setName("Gatwick");
        airport.setLocation("London South");
        airport.setNumber_of_airstrips(2);
        airport.setAvaliable(false);
        airportRepo.update(airport);
        Airport updatedAirport = airportRepo.get(1);
        assertEquals("Gatwick", updatedAirport.getName());
        assertEquals(2, updatedAirport.getNumber_of_airstrips());
        assertFalse(updatedAirport.getAvaliable());

        // Delete
        airportRepo.delete(1);
        assertEquals(0, airportRepo.getAll().size());
    }

    @Test
    void testCRUDOperationsForCabinCrew() throws DatabaseException {
        // Create
        cabinCrewRepository.create(new CabinCrew("John Doe", 1, "john.doe@gmail.com", "Steward"));
        List<CabinCrew> cabins = cabinCrewRepository.getAll();
        assertEquals(1, cabins.size());
        assertEquals("John Doe", cabins.get(0).getNume());
        assertEquals("john.doe@gmail.com", cabins.get(0).getEmail());
        assertEquals("Steward", cabins.get(0).getProfession());

        // Read
        CabinCrew cabin = cabinCrewRepository.get(1);
        assertEquals("John Doe", cabin.getNume());
        assertEquals("john.doe@gmail.com", cabin.getEmail());
        assertEquals("Steward", cabin.getProfession());
        assertEquals(1,cabin.getID());

        // Update
        cabin.setNume("Donald Duck");
        cabin.setEmail("donald_duck@gmail.com");
        cabin.setProfession("Master Chef");
        cabinCrewRepository.update(cabin);
        CabinCrew updatedCabin = cabinCrewRepository.get(1);
        assertEquals("Donald Duck", updatedCabin.getNume());
        assertEquals("donald_duck@gmail.com", updatedCabin.getEmail());
        assertEquals("Master Chef", updatedCabin.getProfession());

        // Delete
        cabinCrewRepository.delete(1);
        assertEquals(0, cabinCrewRepository.getAll().size());

    }

    @Test
    void testCRUDOperationsForPilot() throws DatabaseException {
        //Create
        pilotRepository.create(new Pilot("Mihai Serban", 1, "mihai.serban@gmail.com", Boolean.TRUE));
        List<Pilot> pilots = pilotRepository.getAll();
        assertEquals(1, pilots.size());
        assertEquals("Mihai Serban", pilots.get(0).getNume());
        assertEquals("mihai.serban@gmail.com", pilots.get(0).getEmail());
        assertTrue(pilots.get(0).getAvailability());

        // Read
        Pilot pilot = pilotRepository.get(1);
        assertEquals(1, pilot.getID());
        assertEquals("Mihai Serban", pilot.getNume());
        assertEquals("mihai.serban@gmail.com", pilot.getEmail());
        assertTrue(pilot.getAvailability());

        //Update
        pilot.setNume("Mickey Mouse");
        pilot.setEmail("mickey.mouse@gmail.com");
        pilot.setAvailability(false);
        pilotRepository.update(pilot);
        Pilot updatedPilot = pilotRepository.get(1);
        assertEquals("Mickey Mouse", updatedPilot.getNume());
        assertEquals("mickey.mouse@gmail.com", updatedPilot.getEmail());
        assertFalse(updatedPilot.getAvailability());

        //Delete
        pilotRepository.delete(1);
        assertEquals(0, pilotRepository.getAll().size());


    }

    @Test
    void testCRUDOperationsForReservation() throws DatabaseException {
        Passenger passenger1 = new Passenger("Mr Johnson", 1, "mr.johnson@gmail.com", new Pair("New York", "Los Angeles"));
        reservationRepository.create(new Reservation(1, "2023-10-02", passenger1, new Pair("New York", "Los Angeles")));
        List<Reservation> reservations = reservationRepository.getAll();
        assertEquals(1, reservations.size());
        assertEquals("Mr Johnson", reservations.get(0).getPassenger().getNume());
        assertEquals("mr.johnson@gmail.com", reservations.get(0).getPassenger().getEmail());
        assertEquals("2023-10-02",reservations.get(0).getDate());
        assertEquals("New York",reservations.get(0).getFlight().getFrom());
        assertEquals("Los Angeles",reservations.get(0).getFlight().getTo());
        assertEquals("New York", reservations.get(0).getPassenger().getFlight().getFrom());
        assertEquals("Los Angeles",reservations.get(0).getPassenger().getFlight().getTo());

        // Read
        Reservation reservation = reservationRepository.get(1);
        assertEquals(1, reservation.getID());
        assertEquals("Mr Johnson", reservation.getPassenger().getNume());
        assertEquals("mr.johnson@gmail.com", reservation.getPassenger().getEmail());
        assertEquals("2023-10-02",reservation.getDate());
        assertEquals("New York",reservation.getFlight().getFrom());
        assertEquals("Los Angeles",reservation.getFlight().getTo());
        assertEquals("New York", reservation.getPassenger().getFlight().getFrom());
        assertEquals("Los Angeles",reservation.getPassenger().getFlight().getTo());

        //Update
        reservation.setDate("2024-20-05");
        reservation.setPassenger(new Passenger("Alice Johnson", 4, "alice.johnson@gmail.com", new Pair("Seattle", "Denver")));
        reservation.setFlight( new Pair("Seattle", "Denver"));
        reservationRepository.update(reservation);
        Reservation updatedReservation = reservationRepository.get(1);
        assertEquals("Alice Johnson", updatedReservation.getPassenger().getNume());
        assertEquals("alice.johnson@gmail.com", updatedReservation.getPassenger().getEmail());
        assertEquals("2024-20-05",updatedReservation.getDate());
        assertEquals("Seattle",updatedReservation.getFlight().getFrom());
        assertEquals("Denver",updatedReservation.getFlight().getTo());
        assertEquals("Seattle",updatedReservation.getPassenger().getFlight().getFrom());
        assertEquals("Denver",updatedReservation.getPassenger().getFlight().getTo());

        //Delete
        reservationRepository.delete(1);
        assertEquals(0, reservationRepository.getAll().size());


    }

    @Test
    void testCRUDOperationsForPassenger() throws DatabaseException {
        //Create
         passengerRepository.create(new Passenger("Mr Johnson", 1, "mr.johnson@gmail.com", new Pair("Oradea", "Bucuresti"))) ;
         List<Passenger> passengers = passengerRepository.getAll();
         assertEquals(1,passengers.size());
        assertEquals("Mr Johnson", passengers.get(0).getNume());
        assertEquals("mr.johnson@gmail.com", passengers.get(0).getEmail());
        assertEquals("Oradea",passengers.get(0).getFlight().getFrom());
        assertEquals("Bucuresti",passengers.get(0).getFlight().getTo());

        //Read
        Passenger passenger = passengerRepository.get(1);
        assertEquals(1, passenger.getID());
        assertEquals("Mr Johnson",  passenger.getNume());
        assertEquals("mr.johnson@gmail.com",  passenger.getEmail());
        assertEquals("Oradea", passenger.getFlight().getFrom());
        assertEquals("Bucuresti", passenger.getFlight().getTo());

        //Update
        passenger.setNume("Markus Mars");
        passenger.setEmail("markus.mars@gmail.com");
        passenger.setFlight( new Pair("Seattle", "Denver"));
        passengerRepository.update(passenger);
        Passenger updatedPassenger = passengerRepository.get(1);
        assertEquals("Markus Mars",  updatedPassenger.getNume());
        assertEquals("markus.mars@gmail.com",  updatedPassenger.getEmail());
        assertEquals("Seattle", updatedPassenger.getFlight().getFrom());
        assertEquals("Denver", updatedPassenger.getFlight().getTo());


        //Delete
        passengerRepository.delete(1);
        assertEquals(0,passengerRepository.getAll().size());
    }
    @Test
    void testCRUDOperationsForTicket() throws DatabaseException {
        //Create
        Payment payment1 = new Payment(1, "Flight payment", 200.0, new Passenger("Mr Johnson", 1, "mr.johnson@gmail.com", new Pair("New York", "Los Angeles")));
        ticketRepository.create(new Ticket(1, "Economy Class", "Seat 12A", payment1,"2023-10-01"));
        List<Ticket> tickets =  ticketRepository.getAll();
        assertEquals(1, tickets.size());
        assertEquals("Mr Johnson", tickets.get(0).getPayment().getPassenger().getNume());
        assertEquals("mr.johnson@gmail.com", tickets.get(0).getPayment().getPassenger().getEmail());
        assertEquals("2023-10-01",tickets.get(0).getDate());
        assertEquals("New York",tickets.get(0).getPayment().getPassenger().getFlight().getFrom());
        assertEquals("Los Angeles",tickets.get(0).getPayment().getPassenger().getFlight().getTo());
       assertEquals("Economy Class", tickets.get(0).getTitle());
       assertEquals("Seat 12A", tickets.get(0).getDescription());
       assertEquals("Flight payment", tickets.get(0).getPayment().getDescription());
        assertEquals(200.0, tickets.get(0).getPayment().getAmount());

        //Read
        Ticket ticket = ticketRepository.get(1);
        assertEquals(1, ticket.getID());
        assertEquals("Mr Johnson", ticket.getPayment().getPassenger().getNume());
        assertEquals("mr.johnson@gmail.com", ticket.getPayment().getPassenger().getEmail());
        assertEquals("2023-10-01",ticket.getDate());
        assertEquals("New York",ticket.getPayment().getPassenger().getFlight().getFrom());
        assertEquals("Los Angeles",ticket.getPayment().getPassenger().getFlight().getTo());
        assertEquals("Economy Class", ticket.getTitle());
        assertEquals("Seat 12A", ticket.getDescription());
        assertEquals("Flight payment", ticket.getPayment().getDescription());
        assertEquals(200.0, ticket.getPayment().getAmount());

        //Update
        ticket.setDate("2025-10-10");
        ticket.setDescription("New Seat");
        ticket.setTitle("New title");
        ticket.setPayment(new Payment(3, "Flight payment", 300.0, new Passenger("Ms Johnson", 2, "ms.johnson@gmail.com", new Pair("San Francisco", "Chicago"))));
        ticketRepository.update(ticket);
        Ticket updatedTicket = ticketRepository.get(1);
        assertEquals("Ms Johnson", updatedTicket.getPayment().getPassenger().getNume());
        assertEquals("ms.johnson@gmail.com", updatedTicket.getPayment().getPassenger().getEmail());
        assertEquals("2025-10-10",updatedTicket.getDate());
        assertEquals("San Francisco",updatedTicket.getPayment().getPassenger().getFlight().getFrom());
        assertEquals("Chicago",updatedTicket.getPayment().getPassenger().getFlight().getTo());
        assertEquals("New title", updatedTicket.getTitle());
        assertEquals("New Seat", updatedTicket.getDescription());
        assertEquals("Flight payment", updatedTicket.getPayment().getDescription());
        assertEquals(300.0, updatedTicket.getPayment().getAmount());

        //Delete
        ticketRepository.delete(1);
        assertEquals(0,ticketRepository.getAll().size());
    }

    @Test
    void testCRUDOperationsForPayment() throws DatabaseException {
        // Create
        Passenger passenger = new Passenger("Mr Johnson", 1, "mr.johnson@gmail.com", new Pair("Oradea", "Bucuresti"));
        paymentRepository.create(new Payment(1, "Flight payment", 200.0, passenger));
        List<Payment> payments = paymentRepository.getAll();
        assertEquals(1, payments.size());
        assertEquals("Flight payment", payments.get(0).getDescription());
        assertEquals(200.0, payments.get(0).getAmount());
        assertEquals("Mr Johnson", payments.get(0).getPassenger().getNume());
        assertEquals("mr.johnson@gmail.com", payments.get(0).getPassenger().getEmail());
        assertEquals("Oradea", payments.get(0).getPassenger().getFlight().getFrom());
        assertEquals("Bucuresti", payments.get(0).getPassenger().getFlight().getTo());

        // Read
        Payment payment = paymentRepository.get(1);
        assertEquals(1, payment.getID());
        assertEquals("Flight payment", payment.getDescription());
        assertEquals(200.0, payment.getAmount());
        assertEquals("Mr Johnson", payment.getPassenger().getNume());
        assertEquals("mr.johnson@gmail.com", payment.getPassenger().getEmail());
        assertEquals("Oradea", payment.getPassenger().getFlight().getFrom());
        assertEquals("Bucuresti", payment.getPassenger().getFlight().getTo());

        // Update
        payment.setDescription("Updated payment");
        payment.setAmount(300.0);
        payment.setPassenger(new Passenger("Ms Smith", 2, "ms.smith@gmail.com", new Pair("Cluj", "Timisoara")));
        paymentRepository.update(payment);
        Payment updatedPayment = paymentRepository.get(1);
        assertEquals("Updated payment", updatedPayment.getDescription());
        assertEquals(300.0, updatedPayment.getAmount());
        assertEquals("Ms Smith", updatedPayment.getPassenger().getNume());
        assertEquals("ms.smith@gmail.com", updatedPayment.getPassenger().getEmail());
        assertEquals("Cluj", updatedPayment.getPassenger().getFlight().getFrom());
        assertEquals("Timisoara", updatedPayment.getPassenger().getFlight().getTo());

        // Delete
        paymentRepository.delete(1);
        assertEquals(0, paymentRepository.getAll().size());
    }

    @Test
    void testCRUDOperationsForFlight() throws DatabaseException {
        // Create
        Pilot pilot = new Pilot("Mihai Serban", 1, "mihai.serban@gmail.com", Boolean.TRUE);
        Airplane airplane = new Airplane(1, "Boeing 747", 400, Boolean.TRUE);
        Airport airport = new Airport(1, "Oradea Airport", "Oradea", 2, Boolean.TRUE);

        flightRepository.create(new Flight(1, "Oradea", "Bucuresti", pilot, airplane, airport, "2023-12-25", 500.0));
        List<Flight> flights = flightRepository.getAll();
        assertEquals(1, flights.size());
        assertEquals("Oradea", flights.get(0).getFrom());
        assertEquals("Bucuresti", flights.get(0).getTo());
        assertEquals("Mihai Serban", flights.get(0).getPilot().getNume());
        assertEquals("mihai.serban@gmail.com", flights.get(0).getPilot().getEmail());
        assertTrue(flights.get(0).getPilot().getAvailability());
        assertEquals("Boeing 747", flights.get(0).getAirplane().getModel());
        assertEquals("Oradea Airport", flights.get(0).getAirport().getName());
        assertEquals(400,flights.get(0).getAirplane().getCapacity());
        assertEquals("Oradea", flights.get(0).getAirport().getLocation());
        assertTrue(flights.get(0).getAirplane().getAvailable());
        assertTrue(flights.get(0).getAirport().getAvaliable());
        assertEquals("2023-12-25", flights.get(0).getDate());
        assertEquals(500.0, flights.get(0).getAmount());

        // Read
        Flight flight = flightRepository.get(1);
        assertEquals(1, flight.getID());
        assertEquals("Oradea", flight.getFrom());
        assertEquals("Bucuresti", flight.getTo());
        assertEquals("Mihai Serban", flight.getPilot().getNume());
        assertEquals("mihai.serban@gmail.com", flight.getPilot().getEmail());
        assertTrue(flight.getPilot().getAvailability());
        assertEquals(400,flight.getAirplane().getCapacity());
        assertEquals("Oradea", flight.getAirport().getLocation());
        assertTrue(flight.getAirplane().getAvailable());
        assertTrue(flight.getAirport().getAvaliable());
        assertEquals("Boeing 747", flight.getAirplane().getModel());
        assertEquals("Oradea Airport", flight.getAirport().getName());
        assertEquals("2023-12-25", flight.getDate());
        assertEquals(500.0, flight.getAmount());

        // Update
        flight.setFrom("Cluj");
        flight.setTo("Timisoara");
        flight.setPilot(new Pilot("Jane Doe", 2, "jane.doe@pilots.com", Boolean.FALSE));
        flight.setAirplane(new Airplane(2, "Airbus A320", 300, Boolean.FALSE));
        flight.setAirport(new Airport(2, "Cluj Airport", "Cluj", 3, Boolean.FALSE));
        flight.setDate("2024-01-15");
        flight.setAmount(600.0);
        flightRepository.update(flight);

        Flight updatedFlight = flightRepository.get(1);
        assertEquals("Cluj", updatedFlight.getFrom());
        assertEquals("Timisoara", updatedFlight.getTo());
        assertEquals("Jane Doe", updatedFlight.getPilot().getNume());
        assertEquals("jane.doe@pilots.com", updatedFlight.getPilot().getEmail());
        assertFalse(updatedFlight.getPilot().getAvailability());
        assertEquals(300,updatedFlight.getAirplane().getCapacity());
        assertEquals("Cluj", updatedFlight.getAirport().getLocation());
        assertFalse(updatedFlight.getAirplane().getAvailable());
        assertFalse(updatedFlight.getAirport().getAvaliable());
        assertEquals("Airbus A320", updatedFlight.getAirplane().getModel());
        assertEquals("Cluj Airport", updatedFlight.getAirport().getName());
        assertEquals("2024-01-15", updatedFlight.getDate());
        assertEquals(600.0, updatedFlight.getAmount());

        // Delete
        flightRepository.delete(1);
        assertEquals(0, flightRepository.getAll().size());
    }

    @Test
    void testGetPassengerByID() throws BusinessLogicException, EntityNotFoundException, DatabaseException {

        Passenger passenger = new Passenger("John Doe", 1, "john.doe@gmail.com", new Pair("Oradea", "Bucuresti"));
        passengerRepository.create(passenger);
        Passenger foundPassenger = flightService.getPassengerByID(1);

        assertNotNull(foundPassenger);
        assertEquals(1, foundPassenger.getID());
        assertEquals("John Doe", foundPassenger.getNume());
        assertEquals("john.doe@gmail.com", foundPassenger.getEmail());
        assertEquals("Oradea", foundPassenger.getFlight().getFrom());
        assertEquals("Bucuresti", foundPassenger.getFlight().getTo());
    }

    @Test
    void testGetAllAvailableFlightsForPassenger() throws BusinessLogicException, EntityNotFoundException, DatabaseException {

        Passenger passenger = new Passenger("John Doe", 1, "john.doe@gmail.com", new Pair("Oradea", "Bucuresti"));
        passengerRepository.create(passenger);

        Flight flight1 = new Flight(1, "Oradea", "Bucuresti", new Pilot("Jane Pilot", 1, "jane.pilot@gmail.com", true),
                new Airplane(1, "Boeing 737", 200, true), new Airport(1, "Oradea Airport", "Oradea", 2, true),
                "2023-12-25", 500.0);
        Flight flight2 = new Flight(2, "Oradea", "Bucuresti", new Pilot("Mark Captain", 2, "mark.captain@gmail.com", true),
                new Airplane(2, "Airbus A320", 150, true), new Airport(1, "Oradea Airport", "Oradea", 2, true),
                "2023-12-25", 450.0);

        flightRepository.create(flight1);
        flightRepository.create(flight2);

        ArrayList<Flight> availableFlights = flightService.getAllAvalibleFlightsForPassenger(1, "2023-12-25");

        assertNotNull(availableFlights);
        assertEquals(2, availableFlights.size());
        assertTrue(availableFlights.contains(flight1));
        assertTrue(availableFlights.contains(flight2));
    }

    @Test
    void testGetPassengersByFlight() throws BusinessLogicException, EntityNotFoundException, DatabaseException {

        Flight flight = new Flight(1, "Oradea", "Bucuresti", new Pilot("Jane Pilot", 1, "jane.pilot@gmail.com", true),
                new Airplane(1, "Boeing 737", 200, true), new Airport(1, "Oradea Airport", "Oradea", 2, true),
                "2023-12-25", 500.0);
        flightRepository.create(flight);

        Passenger passenger1 = new Passenger("John Doe", 1, "john.doe@gmail.com", new Pair("Oradea", "Bucuresti"));
        Passenger passenger2 = new Passenger("Alice Smith", 2, "alice.smith@gmail.com", new Pair("Oradea", "Bucuresti"));
        passengerRepository.create(passenger1);
        passengerRepository.create(passenger2);

        List<Passenger> passengers = flightService.getPassengersByFlight(1);

        assertNotNull(passengers);
        assertEquals(2, passengers.size());
        assertTrue(passengers.contains(passenger1));
        assertTrue(passengers.contains(passenger2));
    }

    @Test
    void testGetAvailablePilots() throws EntityNotFoundException, DatabaseException {

        Pilot pilot1 = new Pilot("Jane Pilot", 1, "jane.pilot@gmail.com", true);
        Pilot pilot2 = new Pilot("John Doe", 2, "john.doe@gmail.com", false);
        pilotRepository.create(pilot1);
        pilotRepository.create(pilot2);

        ArrayList<Pilot> availablePilots = flightService.getAvailablePilots();

        assertNotNull(availablePilots);
        assertEquals(1, availablePilots.size());
        assertTrue(availablePilots.contains(pilot1));
        assertFalse(availablePilots.contains(pilot2));
    }



    @Test
    void testBookSeatSuccessful() throws BusinessLogicException, EntityNotFoundException, DatabaseException {
        Passenger passenger = new Passenger("John Doe",1 , "john.doe@gmail.com", new Pair("Oradea", "Bucuresti"));
        Flight flight = new Flight(1, "Oradea", "Bucuresti", new Pilot("Jane Pilot",1 , "jane.pilot@gmail.com", Boolean.TRUE),
                new Airplane(1, "Boeing 737", 200, Boolean.TRUE), new Airport(1, "Oradea Airport", "Oradea", 2, Boolean.TRUE),
                "2023-12-25", 500.0);
        passengerRepository.create(passenger);
        flightRepository.create(flight);

        Ticket ticket = flightService.bookSeat("2023-12-25", 1, 1, "Card");
        assertNotNull(ticket);
        assertEquals("Oradea", ticket.getPayment().getPassenger().getFlight().getFrom());
        assertEquals("Bucuresti", ticket.getPayment().getPassenger().getFlight().getTo());
        assertEquals(199, flightRepository.get(1).getAirplane().getCapacity()); // Capacitate scăzută
    }

    @Test
    void testBookSeatThrowsExceptionWhenNoSeatsAvailable() throws DatabaseException {
        Flight flight = new Flight(1, "Oradea", "Bucuresti", new Pilot("Jane Pilot",1 , "jane.pilot@gmail.com", Boolean.TRUE),
                new Airplane(1, "Boeing 737", 100, Boolean.TRUE), new Airport(1, "Oradea Airport", "Oradea", 2, Boolean.TRUE),
                "2023-12-25", 500.0);
        flightRepository.create(flight);

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            flightService.bookSeat("2023-12-25", 100, 1, "Card");
        });
        assertEquals("Passenger not found", exception.getMessage());

    }

    @Test
    void testBookSeatByFlightSuccessful() throws BusinessLogicException, EntityNotFoundException, DatabaseException {
        Passenger passenger = new Passenger("John Doe",1 , "john.doe@gmail.com", new Pair("Oradea", "Bucuresti"));
        Flight flight = new Flight(1, "Oradea", "Bucuresti", new Pilot("Jane Pilot",1 , "jane.pilot@gmail.com", Boolean.TRUE),
                new Airplane(1, "Boeing 737", 200, Boolean.TRUE), new Airport(1, "Oradea Airport", "Oradea", 2, Boolean.TRUE),
                "2023-12-25", 500.0);
        passengerRepository.create(passenger);
        flightRepository.create(flight);

        Ticket ticket = flightService.bookSeatByFlight("2023-12-25", 1, 1, "Card", "Oradea", "Bucuresti");


        assertNotNull(ticket);
        assertEquals("Ticket Oradea Bucuresti", ticket.getTitle());
        assertEquals("Card", ticket.getDescription());
        assertEquals("2023-12-25", ticket.getDate());
        assertEquals("John Doe", ticket.getPayment().getPassenger().getNume());
        assertEquals("Oradea", ticket.getPayment().getPassenger().getFlight().getFrom());
        assertEquals("Bucuresti", ticket.getPayment().getPassenger().getFlight().getTo());
    }

    @Test
    void testBookSeatByFlightThrowsExceptionForInvalidLocations() throws DatabaseException {
        Passenger passenger = new Passenger("John Doe",1 , "john.doe@gmail.com", new Pair("Oradea", "Bucuresti"));
        Flight flight = new Flight(1, "Oradea", "Bucuresti", new Pilot("Jane Pilot",1 , "jane.pilot@gmail.com", Boolean.TRUE),
                new Airplane(1, "Boeing 737", 200, Boolean.TRUE), new Airport(1, "Oradea Airport", "Oradea", 2, Boolean.TRUE),
                "2023-12-25", 500.0);
        passengerRepository.create(passenger);
        flightRepository.create(flight);

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            flightService.bookSeatByFlight("2023-12-25", 1, 1, "Card", "Cluj", "Iasi");
        });
        assertEquals("Flight not found", exception.getMessage());

    }



    @Test
    void testFilterCabinCrewByProfession() throws BusinessLogicException, DatabaseException {
        cabinCrewRepository.create(new CabinCrew("Anna Smith",1 ,"anna@gmail.com", "Flight Attendant"));
        cabinCrewRepository.create(new CabinCrew("Tom Brown",2 , "tom@gmail.com","Pilot"));
        cabinCrewRepository.create(new CabinCrew("Sarah Johnson",3 , "sarah@gmail.com","Flight Attendant"));

        ArrayList<CabinCrew> flightAttendants = flightService.filterCabinCrewByProfession("Flight Attendant");
        assertEquals(2, flightAttendants.size());
        assertTrue(flightAttendants.stream().allMatch(c -> c.getProfession().equals("Flight Attendant")));
    }

    @Test
    void testFilterCabinCrewByProfessionEmptyResult() throws BusinessLogicException, DatabaseException {
        cabinCrewRepository.create(new CabinCrew("Anna Smith",1 ,"anna@gmail.com", "Engineer"));

        Exception exception = assertThrows(BusinessLogicException.class, () -> {
            flightService.filterCabinCrewByProfession("");
        });

        assertEquals("Profession cannot be null or empty", exception.getMessage());
    }
    @Test
    void testSortAirplanesByCapacity() throws DatabaseException {
        airplaneRepository.create(new Airplane(1, "Boeing 747", 400, true));
        airplaneRepository.create(new Airplane(2, "Airbus A320", 300, true));
        airplaneRepository.create(new Airplane(3, "Cessna 172", 100, true));

        ArrayList<Airplane> sortedAirplanes = flightService.sortAirplanesByCapacity();
        assertEquals(3, sortedAirplanes.size());
        assertEquals(100, sortedAirplanes.get(0).getCapacity());
        assertEquals(300, sortedAirplanes.get(1).getCapacity());
        assertEquals(400, sortedAirplanes.get(2).getCapacity());
    }


    @Test
    void testFilterFlightsByAmount() throws BusinessLogicException, DatabaseException {
        flightRepository.create(new Flight(1, "Oradea", "Bucuresti", null, null, null, "2023-12-25", 500.0));
        flightRepository.create(new Flight(2, "Bucuresti", "Cluj", null, null, null, "2023-10-15", 300.0));
        flightRepository.create(new Flight(3, "Cluj", "Timisoara", null, null, null, "2023-11-05", 700.0));

        ArrayList<Flight> filteredFlights = flightService.filterFlightsByAmount(500.0);
        assertEquals(2, filteredFlights.size());
        assertTrue(filteredFlights.stream().allMatch(f -> f.getAmount() <= 500.0));
    }

    @Test
    void testFilterFlightsByAmountThrowsBusinessLogicExceptionForInvalidAmount() throws DatabaseException {

        flightRepository.create(new Flight(1, "Oradea", "Bucuresti", null, null, null, "2023-12-25", 500.0));
        flightRepository.create(new Flight(2, "Bucuresti", "Cluj", null, null, null, "2023-10-15", 300.0));


        Exception exception = assertThrows(BusinessLogicException.class, () -> {
            flightService.filterFlightsByAmount(-50.0);
        });

        assertEquals("Amount must be greater than zero", exception.getMessage());
    }


    @Test
    void testSortFlightsByDate() throws DatabaseException {
        flightRepository.create(new Flight(1, "Oradea", "Bucuresti", null, null, null, "2023-12-25", 500.0));
        flightRepository.create(new Flight(2, "Bucuresti", "Cluj", null, null, null, "2023-10-15", 300.0));
        flightRepository.create(new Flight(3, "Cluj", "Timisoara", null, null, null, "2023-11-05", 400.0));

        ArrayList<Flight> sortedFlights = flightService.sortFlightsByDate();
        assertEquals(3, sortedFlights.size());
        assertEquals("2023-10-15", sortedFlights.get(0).getDate());
        assertEquals("2023-11-05", sortedFlights.get(1).getDate());
        assertEquals("2023-12-25", sortedFlights.get(2).getDate());
    }




}