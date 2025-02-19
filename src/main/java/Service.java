import Exceptions.BusinessLogicException;
import Exceptions.DatabaseException;
import Exceptions.EntityNotFoundException;
import Models.*;
import Repo.Repository;


import java.util.ArrayList;
import java.util.List;

/**
 * Represents the service class that handles the flight system logic.
 */
public class Service {

    private final Repository<Pilot> pilotsRepo;
    private final Repository<Passenger> passengerRepo;
    private final Repository<CabinCrew> cabinCrewRepo;
    private final Repository<Flight> flightRepo;
    private final Repository<Payment> paymentRepo;
    private final Repository<Reservation> reservationRepo;
    private final Repository<Ticket> ticketRepo;
    private final Repository<Airplane> airplaneRepo;
    private final Repository<Airport> airportRepo;

    Integer counterPassengerID = 5;
    Integer counterPilotID = 5;
    Integer counterCabincrewID = 5;
    Integer counterFlightID = 5;
    Integer counterPaymentID = 5;
    Integer counterReservationID = 5;
    Integer counterTicketID = 5;
    Integer counterAirplaneID = 5;
    Integer counterAirportID = 5;

    /**
     * Creates a new identifier for a pilot.
     *
     * @return The new identifier for a pilot.
     */
    public Integer createPilotID() {
        counterPilotID++;
        return counterPilotID;
    }

    /**
     * Creates a new identifier for a cabin crew.
     *
     * @return The new identifier for a cabin crew.
     */
    public Integer createCabincrewID() {
        counterCabincrewID++;
        return counterCabincrewID;
    }

    /**
     * Creates a new identifier for a flight.
     *
     * @return The new identifier for a flight.
     */
    public Integer createFlightID() {
        counterFlightID++;
        return counterFlightID;
    }

    /**
     * Creates a new identifier for a payment.
     *
     * @return The new identifier for a payment.
     */
    public Integer createPaymentID() {
        counterPaymentID++;
        return counterPaymentID;
    }

    /**
     * Creates a new identifier for a reservation.
     *
     * @return The new identifier for a reservation.
     */
    public Integer createReservationID() {
        counterReservationID++;
        return counterReservationID;
    }

    /**
     * Creates a new identifier for a ticket.
     *
     * @return The new identifier for a ticket.
     */
    public Integer createTicketID() {
        counterTicketID++;
        return counterTicketID;
    }

    /**
     * Creates a new identifier for a passenger.
     *
     * @return The new identifier for a passenger.
     */
    public Integer createPassengerID() {
        counterPassengerID += 1;
        return counterPassengerID;
    }

    /**
     * Creates a new identifier for an airplane.
     *
     * @return The new identifier for an airplane.
     */
    public Integer createAirplaneID() {
        counterAirplaneID += 1;
        return counterAirplaneID;
    }

    /**
     * Creates a new identifier for an airport.
     *
     * @return The new identifier for an airport.
     */
    public Integer createAirportID() {
        counterAirportID += 1;
        return counterAirportID;
    }

    ArrayList<Flight> flightArrayList = new ArrayList<Flight>();

    /**
     * The service constructor
     *
     * @param pilotsRepo      initializes the repository for pilots
     * @param passengerRepo   initializes the repository for passengers
     * @param cabinCrewRepo   initializes the repository for cabin crew
     * @param flightRepo      initializes the repository for flights
     * @param paymentRepo     initializes the repository for payments
     * @param reservationRepo initializes the repository for reservations
     * @param ticketRepo      initializes the repository for tickets
     * @param airplaneRepo    initializes the repository for airplanes
     * @param airportRepo     initializes the repository for airports
     */
    public Service(Repository<Pilot> pilotsRepo, Repository<Passenger> passengerRepo, Repository<CabinCrew> cabinCrewRepo, Repository<Flight> flightRepo, Repository<Payment> paymentRepo, Repository<Reservation> reservationRepo, Repository<Ticket> ticketRepo, Repository<Airplane> airplaneRepo, Repository<Airport> airportRepo) {
        this.pilotsRepo = pilotsRepo;
        this.passengerRepo = passengerRepo;
        this.cabinCrewRepo = cabinCrewRepo;
        this.flightRepo = flightRepo;
        this.paymentRepo = paymentRepo;
        this.reservationRepo = reservationRepo;
        this.ticketRepo = ticketRepo;
        this.airplaneRepo = airplaneRepo;
        this.airportRepo = airportRepo;
    }

    /**
     * Gets all the pilots.
     *
     * @return The list of all pilots.
     */
    public List<Pilot> getPilots() throws DatabaseException {
        try {
            return pilotsRepo.getAll();
        } catch (DatabaseException e) {
            throw new DatabaseException("Error in getting pilots");
        }
    }

    /**
     * Gets all the passengers.
     *
     * @return The list of all passengers.
     */
    public List<Passenger> getAllPassengers() throws DatabaseException {
        try {
            return passengerRepo.getAll();
        } catch (DatabaseException e) {
            throw new DatabaseException("Error in getting passengers");
        }
    }

    /**
     * Gets all the passengers filtered by flight.
     *
     * @return The list of all passengers filtered by flight.
     */
    public List<Passenger> getPassengersByFlight(Integer flightID) throws BusinessLogicException, EntityNotFoundException, DatabaseException {
        try {
            ArrayList<Passenger> passengerByFlight = new ArrayList<Passenger>();
            Flight f = null;
            if (flightID == null) {
                throw new BusinessLogicException("Flight ID cannot be null");
            }
            for (Flight flight : flightRepo.getAll()) {
                if (flight.getID().equals(flightID)) {
                    f = flight;
                }
            }
            if (f == null) {
                throw new EntityNotFoundException("Flight not found");
            }
            for (Passenger passenger : passengerRepo.getAll())
                if (passenger.getFlight().getFrom().equals(f.from) && passenger.getFlight().getTo().equals(f.to)) {
                    passengerByFlight.add(passenger);
                }
            if (passengerByFlight.isEmpty()) {
                throw new EntityNotFoundException("No passengers found for this flight");
            }
            return passengerByFlight;
        } catch (DatabaseException e) {
            throw new DatabaseException("Error in getting passengers by flight");
        }
    }

    /**
     * Gets all the cabin crew.
     *
     * @return The list of all cabin crew.
     */
    public List<CabinCrew> getAllCabinCrews() throws DatabaseException {
        try {
            return cabinCrewRepo.getAll();
        } catch (DatabaseException e) {
            throw new DatabaseException("Error in getting cabin crew");
        }
    }

    /**
     * Gets all the payments.
     *
     * @return The list of all payments.
     */
    public List<Payment> getAllPayments() throws DatabaseException {
        try {
            return paymentRepo.getAll();
        } catch (DatabaseException e) {
            throw new DatabaseException("Error in getting payments");
        }
    }


    /**
     * Gets all the reservations.
     *
     * @return The list of all reservations.
     */
    public List<Reservation> getAllReservations() throws DatabaseException {
        try {
            return reservationRepo.getAll();
        } catch (DatabaseException e) {
            throw new DatabaseException("Error in getting reservations");
        }
    }

    /**
     * Gets all the tickets.
     *
     * @return The list of all tickets.
     */
    public List<Ticket> getAllTickets() throws DatabaseException {
        try {
            return ticketRepo.getAll();
        } catch (DatabaseException e) {
            throw new DatabaseException("Error in getting tickets");
        }
    }

    /**
     * Gets all the airplanes.
     *
     * @return The list of all airplanes.
     */
    public List<Airplane> getAllAirplanes() throws DatabaseException {
        try {
            return airplaneRepo.getAll();
        } catch (DatabaseException e) {
            throw new DatabaseException("Error in getting airplanes");
        }
    }

    /**
     * Gets all the airports.
     *
     * @return The list of all airports.
     */
    public List<Airport> getAllAirports() throws DatabaseException {
        try {
            return airportRepo.getAll();
        } catch (DatabaseException e) {
            throw new DatabaseException("Error in getting airports");
        }
    }

    /**
     * Creates a new flight.
     *
     * @param from       The departure location.
     * @param to         The arrival location.
     * @param pilotID    The pilot identifier.
     * @param airplaneID The airplane identifier.
     * @param airportID  The airport identifier.
     * @param date       The date of the flight.
     * @param amount     The amount of the flight.
     */
    public void createFlight(String from, String to, Integer pilotID, Integer airplaneID, Integer airportID, String date, double amount) throws BusinessLogicException, EntityNotFoundException, DatabaseException {
        try {
            Integer flightID = createFlightID();
            Pilot p = null;
            Airplane a = null;
            Airport ap = null;
            if (from == null || from.isEmpty()) {
                throw new BusinessLogicException("From cannot be null or empty");
            }
            if (to == null || to.isEmpty()) {
                throw new BusinessLogicException("To cannot be null or empty");
            }
            if (date == null || date.isEmpty()) {
                throw new BusinessLogicException("Date cannot be null or empty");
            }
            if (amount <= 0) {
                throw new BusinessLogicException("Amount must be greater than zero");
            }
            if (pilotID == null) {
                throw new BusinessLogicException("Pilot ID cannot be null");
            }
            if (airplaneID == null) {
                throw new BusinessLogicException("Airplane ID cannot be null");
            }
            if (airportID == null) {
                throw new BusinessLogicException("Airport ID cannot be null");
            }
            for (Pilot pilot : pilotsRepo.getAll())
                if (pilotID.equals(pilot.getID()) && pilot.getAvailability().equals(true))
                    p = pilot;
            if (p == null)
                throw new EntityNotFoundException("Pilot not found");
            for (Airplane airplane : airplaneRepo.getAll())
                if (airplaneID.equals(airplane.getID()) && airplane.getAvailable().equals(true))
                    a = airplane;
            if (a == null)
                throw new EntityNotFoundException("Airplane not found");
            for (Airport airport : airportRepo.getAll())
                if (airportID.equals(airport.getID()) && airport.getAvaliable().equals(true))
                    ap = airport;
            if (ap == null)
                throw new EntityNotFoundException("Airport not found");

            Flight flight = new Flight(flightID, from, to, p, a, ap, date, amount);
            flightRepo.create(flight);
        } catch (DatabaseException e) {
            throw new DatabaseException("Error in creating flight");
        }
    }

    /**
     * Creates a new passenger.
     *
     * @param passengerName The name of the passenger.
     * @param from          The departure location.
     * @param to            The arrival location.
     * @param email         The email of the passenger.
     */
    public void createPassenger(String passengerName, String from, String to, String email) throws BusinessLogicException, DatabaseException {
        try {
            if (passengerName == null || passengerName.isEmpty()) {
                throw new BusinessLogicException("Passenger name cannot be null or empty");
            }
            if (from == null || from.isEmpty()) {
                throw new BusinessLogicException("Departure Location cannot be null or empty");
            }
            if (to == null || to.isEmpty()) {
                throw new BusinessLogicException("Destination Location cannot be null or empty");
            }
            if (email == null || email.isEmpty()) {
                throw new BusinessLogicException("Email cannot be null or empty");
            }

            Pair pair = new Pair(from, to);
            Integer passengerID = createPassengerID();
            Passenger passenger = new Passenger(passengerName, passengerID, email, pair);
            passengerRepo.create(passenger);
        } catch (DatabaseException e) {
            throw new DatabaseException("Error in creating passenger");
        }
    }

    /**
     * Creates a new pilot.
     *
     * @param nume         The name of the pilot.
     * @param email        The email of the pilot.
     * @param availibility The availability of the pilot.
     */
    public void createPilot(String nume, String email, Boolean availibility) throws BusinessLogicException, DatabaseException {
        try {
            if (nume == null || nume.isEmpty()) {
                throw new BusinessLogicException("Pilot name cannot be null or empty");
            }
            if (email == null || email.isEmpty()) {
                throw new BusinessLogicException("Email cannot be null or empty");
            }
            if (availibility == null) {
                throw new BusinessLogicException("Availability cannot be null");
            }

            Integer id = createPilotID();
            Pilot pilot = new Pilot(nume, id, email, availibility);
            pilotsRepo.create(pilot);
        } catch (DatabaseException e) {
            throw new DatabaseException("Error in creating pilot");
        }
    }

    /**
     * Creates a new cabin crew.
     *
     * @param nume       The name of the cabin crew.
     * @param email      The email of the cabin crew.
     * @param profession The profession of the cabin crew.
     */
    public void createCabinCrew(String nume, String email, String profession) throws BusinessLogicException, DatabaseException {
        try {
            if (nume == null || nume.isEmpty()) {
                throw new BusinessLogicException("Cabin crew name cannot be null or empty");
            }
            if (email == null || email.isEmpty()) {
                throw new BusinessLogicException("Email cannot be null or empty");
            }
            if (profession == null || profession.isEmpty()) {
                throw new BusinessLogicException("Profession cannot be null or empty");
            }

            Integer id = createCabincrewID();
            CabinCrew cabin = new CabinCrew(nume, id, email, profession);
            cabinCrewRepo.create(cabin);
        } catch (DatabaseException e) {
            throw new DatabaseException("Error in creating cabin crew");
        }
    }

    /**
     * Books a seat for a passenger on a specified flight.
     * This is the complex function.
     *
     * @param date        The date of the flight.
     * @param passengerID The unique identifier of the passenger.
     * @param flightID    The unique identifier of the flight.
     * @param paymentType The type of payment used for the booking (e.g., credit card, cash).
     * @return A new Ticket object representing the booking.
     */
    public Ticket bookSeat(String date, Integer passengerID, Integer flightID, String paymentType) throws BusinessLogicException, EntityNotFoundException, DatabaseException {
        //asta e o functie care e folosita doar de pasager(in ui ar trebui sa se autentifice cu id-ul sau si asa luam from si to)
        try {
            Passenger p = null;
            Flight f = null;

            if (date == null || date.isEmpty()) {
                throw new BusinessLogicException("Date cannot be null or empty");
            }
            if (passengerID == null) {
                throw new BusinessLogicException("Passenger ID cannot be null");
            }
            if (flightID == null) {
                throw new BusinessLogicException("Flight ID cannot be null");
            }
            if (paymentType == null || paymentType.isEmpty()) {
                throw new BusinessLogicException("Payment type cannot be null or empty");
            }


            for (Passenger passenger : passengerRepo.getAll())
                if (passenger.getID().equals(passengerID))
                    p = passenger;
            if (p == null)
                throw new EntityNotFoundException("Passenger not found");
            for (Flight flight : flightRepo.getAll())
                if (flight.getID().equals(flightID) && flight.getAirplane().getCapacity() > 0 && flight.getDate().equals(date))
                    f = flight;
            if (f == null)
                throw new EntityNotFoundException("Flight not found");

            f.getAirplane().setCapacity(f.getAirplane().getCapacity() - 1);
            Payment pay = createPayment(paymentType, f.getAmount(), p.getID());

            return createTicket("Ticket" + " " + f.getFrom().toString() + " " + f.getTo().toString(), paymentType, pay.getID(), f.getDate());
        } catch (DatabaseException e) {
            throw new DatabaseException("Error in booking seat");
        }
    }

    /**
     * Gets all the flights.
     *
     * @return The list of all flights.
     */
    public List<Flight> getAllFlights() throws DatabaseException {
        try {
            return flightRepo.getAll();
        } catch (DatabaseException e) {
            throw new DatabaseException("Error in getting flights");
        }
    }


    /**
     * Deletes a pilot.
     *
     * @param pilotID The pilot identifier.
     */
    public void deletePilot(Integer pilotID) throws BusinessLogicException, EntityNotFoundException, DatabaseException {
        try {
            if (pilotID == null) {
                throw new BusinessLogicException("Pilot ID cannot be null");
            }
            Pilot p = null;
            for (Pilot pilot : pilotsRepo.getAll())
                if (pilot.getID().equals(pilotID)) {
                    pilotsRepo.delete(pilot.getID());
                    p = pilot;
                }
            if (p == null)
                throw new EntityNotFoundException("Pilot not found");
        } catch (DatabaseException e) {
            throw new DatabaseException("Error in deleting pilot");
        }
    }

    /**
     * Deletes a passenger.
     *
     * @param passengerID The passenger identifier.
     */
    public void deletePassenger(Integer passengerID) throws BusinessLogicException, EntityNotFoundException, DatabaseException {
        try {
            if (passengerID == null) {
                throw new BusinessLogicException("Passenger ID cannot be null");
            }
            Passenger p = null;
            for (Passenger passenger : passengerRepo.getAll())
                if (passenger.getID().equals(passengerID)) {
                    passengerRepo.delete(passenger.getID());
                    p = passenger;
                }
            if (p == null)
                throw new EntityNotFoundException("Passenger not found");
        } catch (DatabaseException e) {
            throw new DatabaseException("Error in deleting passenger");
        }
    }

    /**
     * Deletes a cabin crew.
     *
     * @param cabincrewID The cabin crew identifier.
     */
    public void deleteCabinCrew(Integer cabincrewID) throws BusinessLogicException, EntityNotFoundException, DatabaseException {
        try {
            if (cabincrewID == null) {
                throw new BusinessLogicException("Cabin crew ID cannot be null");
            }
            CabinCrew c = null;
            for (CabinCrew cabinCrew : cabinCrewRepo.getAll())
                if (cabinCrew.getID().equals(cabincrewID)) {
                    cabinCrewRepo.delete(cabinCrew.getID());
                    c = cabinCrew;
                }
            if (c == null)
                throw new EntityNotFoundException("Cabin crew not found");
        } catch (DatabaseException e) {
            throw new DatabaseException("Error in deleting cabin crew");
        }
    }

    /**
     * Deletes a flight.
     *
     * @param flightID The flight identifier.
     */
    public void deleteFlight(Integer flightID) throws BusinessLogicException, EntityNotFoundException, DatabaseException {
        try {
            if (flightID == null) {
                throw new BusinessLogicException("Flight ID cannot be null");
            }
            Flight f = null;
            for (Flight flight : flightRepo.getAll())
                if (flight.getID().equals(flightID)) {
                    flightRepo.delete(flight.getID());
                    f = flight;
                }
            if (f == null)
                throw new EntityNotFoundException("Flight not found");
        } catch (DatabaseException e) {
            throw new DatabaseException("Error in deleting flight");
        }
    }

    /**
     * Updates a pilot.
     *
     * @param pilotID  The pilot identifier.
     * @param newName  The new name of the pilot.
     * @param newEmail The new email of the pilot.
     */
    public void updatePilot(Integer pilotID, String newName, String newEmail) throws BusinessLogicException, EntityNotFoundException, DatabaseException {
//        for(Pilot pilot: pilotsRepo.getAll())
//            if(pilot.getID().equals(pilotID))
//            {
//                pilot.setNume(newName);
//                pilot.setEmail(newEmail);
//                break;
//            }
        try {
            if (pilotID == null) {
                throw new BusinessLogicException("Pilot ID cannot be null");
            }
            if (newName == null || newName.isEmpty()) {
                throw new BusinessLogicException("New name cannot be null or empty");
            }
            if (newEmail == null || newEmail.isEmpty()) {
                throw new BusinessLogicException("New email cannot be null or empty");
            }
            Pilot pilot = null;
            pilot = pilotsRepo.get(pilotID);
            if (pilot == null) {
                throw new EntityNotFoundException("Pilot not found");
            }
            pilot.setNume(newName);
            pilot.setEmail(newEmail);
            pilotsRepo.update(pilot);
        } catch (DatabaseException e) {
            throw new DatabaseException("Error in updating pilot");
        }
    }

    /**
     * Updates a passenger.
     *
     * @param passengerID The passenger identifier.
     * @param newName     The new name of the passenger.
     * @param newEmail    The new email of the passenger.
     * @param newTo       The new arrival location of the passenger.
     * @param newFrom     The new departure location of the passenger.
     */
    public void updatePassenger(Integer passengerID, String newName, String newEmail, String newTo, String newFrom) throws BusinessLogicException, EntityNotFoundException, DatabaseException {
//        for(Passenger passenger: passengerRepo.getAll())
//
//            if(passenger.getID().equals(passengerID))
//            {
//                passenger.setNume(newName);
//                passenger.setEmail(newEmail);
//                Pair pair= new Pair(newFrom,newTo);
//                passenger.setFlight(pair);
//                break;
//            }
        try {
            if (passengerID == null) {
                throw new BusinessLogicException("Passenger ID cannot be null");
            }
            if (newName == null || newName.isEmpty()) {
                throw new BusinessLogicException("New name cannot be null or empty");
            }
            if (newEmail == null || newEmail.isEmpty()) {
                throw new BusinessLogicException("New email cannot be null or empty");
            }
            if (newTo == null || newTo.isEmpty()) {
                throw new BusinessLogicException("New to cannot be null or empty");
            }
            if (newFrom == null || newFrom.isEmpty()) {
                throw new BusinessLogicException("New from cannot be null or empty");
            }
            Passenger passenger = null;
            passenger = passengerRepo.get(passengerID);
            if (passenger == null) {
                throw new EntityNotFoundException("Passenger not found");
            }
            passenger.setNume(newName);
            passenger.setEmail(newEmail);
            Pair pair = new Pair(newFrom, newTo);
            passenger.setFlight(pair);
            passengerRepo.update(passenger);
        } catch (DatabaseException e) {
            throw new DatabaseException("Error in updating passenger");
        }
    }

    /**
     * Updates a cabin crew.
     *
     * @param cabincrewID  The cabin crew identifier.
     * @param newName      The new name of the cabin crew.
     * @param newEmail     The new email of the cabin crew.
     * @param newProfesion The new profession of the cabin crew.
     */
    public void updateCabinCrew(Integer cabincrewID, String newName, String newEmail, String newProfesion) throws BusinessLogicException, EntityNotFoundException, DatabaseException {
//        for(CabinCrew cabinCrew: cabinCrewRepo.getAll())
//            if(cabinCrew.getID().equals(cabincrewID))
//            {
//                cabinCrew.setNume(newName);
//                cabinCrew.setEmail(newEmail);
//                cabinCrew.setProfession(newProfesion);
//                break;
//            }
        try {
            if (cabincrewID == null) {
                throw new BusinessLogicException("Cabin crew ID cannot be null");
            }
            if (newName == null || newName.isEmpty()) {
                throw new BusinessLogicException("New name cannot be null or empty");
            }
            if (newEmail == null || newEmail.isEmpty()) {
                throw new BusinessLogicException("New email cannot be null or empty");
            }
            if (newProfesion == null || newProfesion.isEmpty()) {
                throw new BusinessLogicException("New profession cannot be null or empty");
            }
            CabinCrew cabinCrew = null;
            cabinCrew = cabinCrewRepo.get(cabincrewID);
            if (cabinCrew == null) {
                throw new EntityNotFoundException("Cabin crew not found");
            }
            cabinCrew.setNume(newName);
            cabinCrew.setEmail(newEmail);
            cabinCrew.setProfession(newProfesion);
            cabinCrewRepo.update(cabinCrew);
        } catch (DatabaseException e) {
            throw new DatabaseException("Error in updating cabin crew");
        }
    }

    /**
     * Updates a flight.
     *
     * @param flightID   The flight identifier.
     * @param newFrom    The new departure location of the flight.
     * @param newTo      The new arrival location of the flight.
     * @param pilotID    The pilot identifier.
     * @param airplaneID The airplane identifier.
     */
    public void updateFlight(Integer flightID, String newFrom, String newTo, Integer pilotID, Integer airplaneID) throws BusinessLogicException, EntityNotFoundException, DatabaseException {
        try {
            Pilot p = null;
            Airplane a = null;

            if (flightID == null) {
                throw new BusinessLogicException("Flight ID cannot be null");
            }
            if (newFrom == null || newFrom.isEmpty()) {
                throw new BusinessLogicException("Departure cannot be null or empty");
            }
            if (newTo == null || newTo.isEmpty()) {
                throw new BusinessLogicException("Destination cannot be null or empty");
            }
            if (pilotID == null) {
                throw new BusinessLogicException("Pilot ID cannot be null");
            }
            if (airplaneID == null) {
                throw new BusinessLogicException("Airplane ID cannot be null");
            }

            for (Pilot pilot : pilotsRepo.getAll())
                if (pilot.getID().equals(pilotID))
                    p = pilot;
            if (p == null)
                throw new EntityNotFoundException("Pilot not found");
            for (Airplane airplane : airplaneRepo.getAll())
                if (airplane.getID().equals(airplaneID))
                    a = airplane;
            if (a == null)
                throw new EntityNotFoundException("Airplane not found");

//        for (Flight flight: flightRepo.getAll())
//            if(flight.getID().equals(flightID))
//            {
//                flight.to=newTo;
//                flight.from=newFrom;
//                flight.pilot=p;
//                flight.airplane=a;
//                break;
//            }
            Flight flight = null;
            flight = flightRepo.get(flightID);
            if (flight == null) {
                throw new EntityNotFoundException("Flight not found");
            }
            flight.setTo(newTo);
            flight.setFrom(newFrom);
            flight.setPilot(p);
            flight.setAirplane(a);
            flightRepo.update(flight);
        } catch (DatabaseException e) {
            throw new DatabaseException("Error in updating flight");
        }
    }

    /**
     * Creates a new payment.
     *
     * @param description The description of the payment.
     * @param amount      The amount of the payment.
     * @param passengerID The passenger identifier.
     */
    public Payment createPayment(String description, double amount, Integer passengerID) throws BusinessLogicException, EntityNotFoundException, DatabaseException {
        //trebe scris si o descriere din main.java.UI cumva si main.java.data ca parametru
        try {
            if (description == null || description.isEmpty()) {
                throw new BusinessLogicException("Description cannot be null or empty");
            }
            if (amount <= 0) {
                throw new BusinessLogicException("Amount must be greater than zero");
            }
            if (passengerID == null) {
                throw new BusinessLogicException("Passenger ID cannot be null");
            }

            Integer payID = createPaymentID();
            Passenger p = null;
            for (Passenger passenger : passengerRepo.getAll())
                if (passenger.getID().equals(passengerID)) {
                    p = passenger;
                    break;
                }
            if (p == null)
                throw new EntityNotFoundException("Passenger not found");
            Payment newpay = new Payment(payID, description, amount, p);
            paymentRepo.create(newpay);
            return newpay;
        } catch (DatabaseException e) {
            throw new DatabaseException("Error in creating payment");
        }
    }

    /**
     * Deletes a payment.
     *
     * @param payID The payment identifier.
     */
    public void deletePayment(Integer payID) throws BusinessLogicException, EntityNotFoundException, DatabaseException {
        try {
            if (payID == null) {
                throw new BusinessLogicException("Payment ID cannot be null");
            }
            Payment p = null;
            for (Payment payment : paymentRepo.getAll())
                if (payment.getID().equals(payID)) {
                    paymentRepo.delete(payment.getID());
                    p = payment;
                }
            if (p == null)
                throw new EntityNotFoundException("Payment not found");
        } catch (DatabaseException e) {
            throw new DatabaseException("Error in deleting payment");
        }
    }

    /**
     * Updates a payment.
     *
     * @param paymentID      The payment identifier.
     * @param newDescription The new description of the payment.
     * @param newAmount      The new amount of the payment.
     */
    public void updatePayment(Integer paymentID, String newDescription, double newAmount) throws BusinessLogicException, EntityNotFoundException, DatabaseException {
        //id of payment stays the same and id of passenger that pays stays the same

//        for(Payment payment: paymentRepo.getAll())
//            if(payment.getID().equals(paymentID))
//            {
//                payment.setAmount(newAmount);
//                payment.setDescription(newDescription);
//                break;
//            }
        try {
            if (paymentID == null) {
                throw new BusinessLogicException("Payment ID cannot be null");
            }
            if (newDescription == null || newDescription.isEmpty()) {
                throw new BusinessLogicException("New description cannot be null or empty");
            }
            if (newAmount <= 0) {
                throw new BusinessLogicException("New amount must be greater than zero");
            }
            Payment payment = null;
            payment = paymentRepo.get(paymentID);
            if (payment == null) {
                throw new EntityNotFoundException("Payment not found");
            }
            payment.setAmount(newAmount);
            payment.setDescription(newDescription);
            paymentRepo.update(payment);
        } catch (DatabaseException e) {
            throw new DatabaseException("Error in updating payment");
        }
    }

    // ar trebui sa facem o lista de reservations( si asta ar fi main.java.data si ca lista pt operator, dar filtrata cumva
    //undeva, nu-s sigur unde)

    /**
     * Creates a new reservation.
     *
     * @param date        The date of the reservation.
     * @param passengerID The passenger identifier.
     * @param from        The departure location.
     * @param to          The arrival location.
     */
    public void createReservation(String date, Integer passengerID, String from, String to) throws BusinessLogicException, EntityNotFoundException, DatabaseException {
        try {
            if (date == null || date.isEmpty()) {
                throw new BusinessLogicException("Date cannot be null or empty");
            }
            if (passengerID == null) {
                throw new BusinessLogicException("Passenger ID cannot be null");
            }
            if (from == null || from.isEmpty()) {
                throw new BusinessLogicException("From cannot be null or empty");
            }
            if (to == null || to.isEmpty()) {
                throw new BusinessLogicException("To cannot be null or empty");
            }

            Passenger p = null;
            Pair fl = new Pair(from, to);
            Integer resID = createReservationID();

            for (Passenger passenger : passengerRepo.getAll())
                if (passenger.getID().equals(passengerID))
                    p = passenger;
            if (p == null)
                throw new EntityNotFoundException("Passenger not found");

            Reservation newReservation = new Reservation(resID, date, p, fl);
            reservationRepo.create(newReservation);
        } catch (DatabaseException e) {
            throw new DatabaseException("Error in creating reservation");
        }

    }

    /**
     * Deletes a reservation.
     *
     * @param reservationID The reservation identifier.
     */
    public void deleteReservation(Integer reservationID) throws BusinessLogicException, EntityNotFoundException, DatabaseException {
        try {
            if (reservationID == null) {
                throw new BusinessLogicException("Reservation ID cannot be null");
            }
            Reservation r = null;
            for (Reservation reservation : reservationRepo.getAll())
                if (reservation.getID().equals(reservationID)) {
                    reservationRepo.delete(reservationID);
                    r = reservation;
                }
            if (r == null)
                throw new EntityNotFoundException("Reservation not found");
        } catch (DatabaseException e) {
            throw new DatabaseException("Error in deleting reservation");
        }
    }

    /**
     * Updates a reservation.
     *
     * @param reservationID The reservation identifier.
     * @param newDate       The new date of the reservation.
     */
    public void updateReservation(Integer reservationID, String newDate) throws BusinessLogicException, EntityNotFoundException, DatabaseException {
//        for(Reservation reservation: reservationRepo.getAll())
//            if(reservation.getID().equals(reservationID)) {
//                reservation.setDate(newDate);
//                break;
//            }
        try {
            if (reservationID == null) {
                throw new BusinessLogicException("Reservation ID cannot be null");
            }
            if (newDate == null || newDate.isEmpty()) {
                throw new BusinessLogicException("New date cannot be null or empty");
            }
            Reservation reservation = null;
            reservation = reservationRepo.get(reservationID);
            if (reservation == null) {
                throw new EntityNotFoundException("Reservation not found");
            }
            reservation.setDate(newDate);
            reservationRepo.update(reservation);
        } catch (DatabaseException e) {
            throw new DatabaseException("Error in updating reservation");
        }

    }

    /**
     * Creates a new ticket.
     *
     * @param title       The title of the ticket.
     * @param description The description of the ticket.
     * @param paymentID   The payment identifier.
     * @param date        The date .
     */
    public Ticket createTicket(String title, String description, Integer paymentID, String date) throws BusinessLogicException, EntityNotFoundException, DatabaseException {
        try {
            Integer ticketID = createTicketID();
            Payment p = null;

            if (title == null || title.isEmpty()) {
                throw new BusinessLogicException("Title cannot be null or empty");
            }
            if (description == null || description.isEmpty()) {
                throw new BusinessLogicException("Description cannot be null or empty");
            }
            if (paymentID == null) {
                throw new BusinessLogicException("Payment ID cannot be null");
            }
            if (date == null || date.isEmpty()) {
                throw new BusinessLogicException("Date cannot be null or empty");
            }

            for (Payment payment : paymentRepo.getAll())
                if (payment.getID().equals(paymentID))
                    p = payment;
            if (p == null)
                throw new EntityNotFoundException("Payment not found");
            Ticket newTicket = new Ticket(ticketID, title, description, p, date);
            ticketRepo.create(newTicket);
            return newTicket;
        } catch (DatabaseException e) {
            throw new DatabaseException("Error in creating ticket");
        }
    }

    /**
     * Deletes a ticket.
     *
     * @param ticketID The ticket identifier.
     */
    public void deleteTicket(Integer ticketID) throws BusinessLogicException, EntityNotFoundException, DatabaseException {
        try {
            if (ticketID == null) {
                throw new BusinessLogicException("Ticket ID cannot be null");
            }

            Ticket t = null;
            for (Ticket ticket : ticketRepo.getAll())
                if (ticket.getID().equals(ticketID)) {
                    ticketRepo.delete(ticketID);
                    t = ticket;
                }
            if (t == null)
                throw new EntityNotFoundException("Ticket not found");
        } catch (DatabaseException e) {
            throw new DatabaseException("Error in deleting ticket");
        }
    }

    /**
     * Updates a ticket.
     *
     * @param ticketID       The ticket identifier.
     * @param newTitle       The new title of the ticket.
     * @param newDescription The new description of the ticket.
     */
    public void updateTicket(Integer ticketID, String newTitle, String newDescription) throws BusinessLogicException, EntityNotFoundException, DatabaseException {
//        for(Ticket ticket: ticketRepo.getAll())
//            if(ticket.getID().equals(ticketID)) {
//                ticket.setTitle(newTitle);
//                ticket.setDescription(newDescription);
//                break;
//            }
        try {
            if (ticketID == null) {
                throw new BusinessLogicException("Ticket ID cannot be null");
            }
            if (newTitle == null || newTitle.isEmpty()) {
                throw new BusinessLogicException("New title cannot be null or empty");
            }
            if (newDescription == null || newDescription.isEmpty()) {
                throw new BusinessLogicException("New description cannot be null or empty");
            }
            Ticket ticket = null;
            ticket = ticketRepo.get(ticketID);
            if (ticket == null) {
                throw new EntityNotFoundException("Ticket not found");
            }
            ticket.setTitle(newTitle);
            ticket.setDescription(newDescription);
            ticketRepo.update(ticket);
        } catch (DatabaseException e) {
            throw new DatabaseException("Error in updating ticket");
        }
    }

    /**
     * Gets all the available pilots.
     *
     * @return The list of all available pilots.
     */
    public ArrayList<Pilot> getAvailablePilots() throws EntityNotFoundException, DatabaseException {
        try {
            ArrayList<Pilot> pilots = new ArrayList<Pilot>();
            Pilot p = null;
            for (Pilot pilot : pilotsRepo.getAll())
                if (pilot.getAvailability().equals(true)) {
                    pilots.add(pilot);
                    p = pilot;
                }
            if (p == null)
                throw new EntityNotFoundException("No available pilots found");
            else
                return pilots;
        } catch (DatabaseException e) {
            throw new DatabaseException("Error in getting available pilots");
        }
    }


    /**
     * Gets all the available cabin crew.
     *
     * @return The list of all available cabin crew.
     */
    public void createAirplane(String model, Integer capacity, Boolean avaliable) throws BusinessLogicException, DatabaseException {
        try {
            Integer airplaneID = createAirplaneID();

            if (model == null || model.isEmpty()) {
                throw new BusinessLogicException("Model cannot be null or empty");
            }
            if (capacity == null || capacity <= 0) {
                throw new BusinessLogicException("Capacity must be greater than zero");
            }
            if (avaliable == null) {
                throw new BusinessLogicException("Availability cannot be null");
            }

            Airplane newaAirplane = new Airplane(airplaneID, model, capacity, avaliable);
            airplaneRepo.create(newaAirplane);
        } catch (DatabaseException e) {
            throw new DatabaseException("Error in creating airplane");
        }
    }

    /**
     * Deletes an airplane.
     *
     * @param airplaneID The airplane identifier.
     */
    public void deleteAirplane(Integer airplaneID) throws BusinessLogicException, EntityNotFoundException, DatabaseException {
        try {
            if (airplaneID == null) {
                throw new BusinessLogicException("Airplane ID cannot be null");
            }
            Airplane a = null;
            for (Airplane airplane : airplaneRepo.getAll())
                if (airplane.getID().equals(airplaneID)) {
                    airplaneRepo.delete(airplaneID);
                    a = airplane;
                }
            if (a == null)
                throw new EntityNotFoundException("Airplane not found");
        } catch (DatabaseException e) {
            throw new DatabaseException("Error in deleting airplane");
        }
    }

    /**
     * Updates an airplane.
     *
     * @param airplaneID   The airplane identifier.
     * @param newModel     The new model of the airplane.
     * @param newCapacity  The new capacity of the airplane.
     * @param newAvaliable The new availability of the airplane.
     */
    public void updateAirplane(Integer airplaneID, String newModel, Integer newCapacity, Boolean newAvaliable) throws BusinessLogicException, EntityNotFoundException, DatabaseException {

//        for(Airplane airplane: airplaneRepo.getAll())
//            if(airplane.getID().equals(airplaneID)) {
//                airplane.setModel(newModel);
//                airplane.setAvailable(newAvaliable);
//                airplane.setCapacity(newCapacity);
//            }
        try {
            if (airplaneID == null) {
                throw new BusinessLogicException("Airplane ID cannot be null");
            }
            if (newModel == null || newModel.isEmpty()) {
                throw new BusinessLogicException("New model cannot be null or empty");
            }
            if (newCapacity == null || newCapacity <= 0) {
                throw new BusinessLogicException("New capacity must be greater than zero");
            }
            if (newAvaliable == null) {
                throw new BusinessLogicException("New availability cannot be null");
            }
            Airplane airplane = null;
            airplane = airplaneRepo.get(airplaneID);
            if (airplane == null) {
                throw new EntityNotFoundException("Airplane not found");
            }
            airplane.setModel(newModel);
            airplane.setAvailable(newAvaliable);
            airplane.setCapacity(newCapacity);
            airplaneRepo.update(airplane);
        } catch (DatabaseException e) {
            throw new DatabaseException("Error in updating airplane");
        }

    }

    /**
     * Gets all the available airplanes.
     *
     * @return The list of all available airplanes.
     */
    public Ticket getTicket(Integer ticketID) throws BusinessLogicException, EntityNotFoundException, DatabaseException {
        try {
            if (ticketID == null) {
                throw new BusinessLogicException("Ticket ID cannot be null");
            }
            Ticket t = null;
            for (Ticket ticket : ticketRepo.getAll()) {
                if (ticket.getID().equals(ticketID))
                    t = ticket;
            }
            if (t == null)
                throw new EntityNotFoundException("Ticket not found");
            else
                return t;
        } catch (DatabaseException e) {
            throw new DatabaseException("Error in getting ticket");
        }
    }

    /**
     * Gets all the available airports.
     *
     * @return The list of all available airports.
     */
    public Reservation getReservation(Integer reservationID) throws BusinessLogicException, EntityNotFoundException, DatabaseException {
        try {
            if (reservationID == null) {
                throw new BusinessLogicException("Reservation ID cannot be null");
            }
            Reservation r = null;
            for (Reservation reservation : reservationRepo.getAll()) {
                if (reservation.getID().equals(reservationID)) {
                    r = reservation;
                }
            }
            if (r == null)
                throw new EntityNotFoundException("Reservation not found");
            else
                return r;
        } catch (DatabaseException e) {
            throw new DatabaseException("Error in getting reservation");
        }
    }

    /**
     * Creates a new airport.
     *
     * @param name                The name of the airport.
     * @param location            The location of the airport.
     * @param number_of_airstrips The number of airstrips of the airport.
     * @param avaliable           The availability of the airport.
     */
    public void createAirport(String name, String location, Integer number_of_airstrips, Boolean avaliable) throws BusinessLogicException, DatabaseException {

        try {
            if (name == null || name.isEmpty()) {
                throw new BusinessLogicException("Airport name cannot be null or empty");
            }
            if (location == null || location.isEmpty()) {
                throw new BusinessLogicException("Airport location cannot be null or empty");
            }
            if (number_of_airstrips == null || number_of_airstrips <= 0) {
                throw new BusinessLogicException("Number of airstrips must be greater than zero");
            }
            Integer airportID = createAirportID();
            Airport newAirport = new Airport(airportID, name, location, number_of_airstrips, avaliable);
            airportRepo.create(newAirport);
        } catch (DatabaseException e) {
            throw new DatabaseException("Error in creating airport");
        }
    }

    /**
     * Deletes an airport.
     *
     * @param airportID The airport identifier.
     */
    public void deleteAirport(Integer airportID) throws BusinessLogicException, EntityNotFoundException, DatabaseException {
        try {
            if (airportID == null) {
                throw new BusinessLogicException("Airport ID cannot be null");
            }
            Airport a = null;
            for (Airport airport : airportRepo.getAll())
                if (airport.getID().equals(airportID)) {
                    airportRepo.delete(airportID);
                    a = airport;
                }
            if (a == null)
                throw new EntityNotFoundException("Airport not found");
        } catch (DatabaseException e) {
            throw new DatabaseException("Error in deleting airport");
        }
    }

    /**
     * Updates an airport.
     *
     * @param airportID            The airport identifier.
     * @param newName              The new name of the airport.
     * @param newLocation          The new location of the airport.
     * @param newNumberOfAirstrips The new number of airstrips of the airport.
     * @param newAvaliable         The new availability of the airport.
     */
    public void updateAirport(Integer airportID, String newName, String newLocation, Integer newNumberOfAirstrips, Boolean newAvaliable) throws BusinessLogicException, EntityNotFoundException, DatabaseException {
//        for(Airport airport: airportRepo.getAll())
//            if(airport.getID().equals(airportID)) {
//                airport.setName(newName);
//                airport.setNumber_of_airstrips(newNumberOfAirstrips);
//                airport.setLocation(newLocation);
//                airport.setAvaliable(newAvaliable);
//            }
        try {
            if (airportID == null) {
                throw new BusinessLogicException("Airport ID cannot be null");
            }
            if (newName == null || newName.isEmpty()) {
                throw new BusinessLogicException("New name cannot be null or empty");
            }
            if (newLocation == null || newLocation.isEmpty()) {
                throw new BusinessLogicException("New location cannot be null or empty");
            }
            if (newNumberOfAirstrips == null || newNumberOfAirstrips <= 0) {
                throw new BusinessLogicException("New number of airstrips must be greater than zero");
            }
            if (newAvaliable == null) {
                throw new BusinessLogicException("New availability cannot be null");
            }

            Airport airport = null;
            airport = airportRepo.get(airportID);
            if (airport == null) {
                throw new EntityNotFoundException("Airport not found");
            }
            airport.setName(newName);
            airport.setNumber_of_airstrips(newNumberOfAirstrips);
            airport.setLocation(newLocation);
            airport.setAvaliable(newAvaliable);
            airportRepo.update(airport);
        } catch (DatabaseException e) {
            throw new DatabaseException("Error in updating airport");
        }
    }

    /**
     * Retrieves all available flights for a passenger on a specified date.
     *
     * @param passengerID The unique identifier of the passenger.
     * @param date        The date for which to retrieve available flights.
     * @return A list of flights that match the passenger's destination and departure location.
     */
    public ArrayList<Flight> getAllAvalibleFlightsForPassenger(Integer passengerID, String date) throws BusinessLogicException, EntityNotFoundException, DatabaseException {
        try {
            String from = null;
            String to = null;

            if (passengerID == null) {
                throw new BusinessLogicException("Passenger ID cannot be null");
            }
            if (date == null || date.isEmpty()) {
                throw new BusinessLogicException("Date cannot be null or empty");
            }

            for (Passenger passenger : passengerRepo.getAll())
                if (passenger.getID().equals(passengerID)) {
                    from = passenger.getFlight().getFrom();
                    to = passenger.getFlight().getTo();
                }
            if (from == null || to == null)
                throw new EntityNotFoundException("Passenger not found");
            Flight f = null;
            for (Flight flight : flightRepo.getAll())
                if (flight.getFrom().equals(from) && flight.getTo().equals(to) && flight.getDate().equals(date)) {
                    flightArrayList.add(flight);
                    f = flight;
                }
            if (f == null)
                throw new EntityNotFoundException("Flight not found");

            return flightArrayList;
        } catch (DatabaseException e) {
            throw new DatabaseException("Error in getting available flights for passenger");
        }
    }

    /**
     * Retrieves a passenger by their unique identifier.
     *
     * @param passengerID The unique identifier of the passenger.
     * @return The passenger with the specified ID, or null if not found.
     */
    public Passenger getPassengerByID(Integer passengerID) throws BusinessLogicException, EntityNotFoundException, DatabaseException {
        try {
            Passenger p = null;

            if (passengerID == null) {
                throw new BusinessLogicException("Passenger ID cannot be null");
            }

            for (Passenger passenger : passengerRepo.getAll())
                if (passenger.getID().equals(passengerID)) {
                    p = passenger;
                    break;
                }
            if (p == null)
                throw new EntityNotFoundException("Passenger not found");
            return p;
        } catch (DatabaseException e) {
            throw new DatabaseException("Error in getting passenger by ID");
        }
    }

    /**
     * Retrieves a pilot by their unique identifier.
     *
     * @return The pilot with the specified ID, or null if not found.
     */
    public ArrayList<Airplane> sortAirplanesByCapacity() throws DatabaseException {
        try {
            ArrayList<Airplane> sortedAirplanes = new ArrayList<Airplane>();
            sortedAirplanes.addAll(airplaneRepo.getAll());
            sortedAirplanes.sort((Airplane airplane1, Airplane airplane2) -> airplane1.getCapacity().compareTo(airplane2.getCapacity()));
            return sortedAirplanes;
        } catch (DatabaseException e) {
            throw new DatabaseException("Error in sorting airplanes by capacity");
        }
    }

    /**
     * Filters the flights by amount.
     *
     * @param amount The amount of the flight.
     * @return The list of flights filtered by amount.
     */
    public ArrayList<Flight> filterFlightsByAmount(double amount) throws BusinessLogicException, DatabaseException {
        try {
            ArrayList<Flight> filteredFlights = new ArrayList<Flight>();

            if (amount <= 0) {
                throw new BusinessLogicException("Amount must be greater than zero");
            }

            filteredFlights.addAll(flightRepo.getAll().stream().filter(flight -> flight.getAmount() <= amount).toList());
            return filteredFlights;
        } catch (DatabaseException e) {
            throw new DatabaseException("Error in filtering flights by amount");
        }
    }

    /**
     * Sorts the flights by date.
     *
     * @return The list of flights sorted by date.
     */
    public ArrayList<Flight> sortFlightsByDate() throws DatabaseException {
        try {
            ArrayList<Flight> sortedFlights = new ArrayList<Flight>();
            sortedFlights.addAll(flightRepo.getAll());
            sortedFlights.sort((Flight f1, Flight f2) -> f1.getDate().compareTo(f2.getDate()));
            return sortedFlights;
        } catch (DatabaseException e) {
            throw new DatabaseException("Error in sorting flights by date");
        }
    }

    /**
     * Filters the cabin crew by profession.
     *
     * @param profession The profession of the cabin crew.
     * @return The list of cabin crew filtered by profession.
     */
    public ArrayList<CabinCrew> filterCabinCrewByProfession(String profession) throws BusinessLogicException, DatabaseException {
        try {
            ArrayList<CabinCrew> filteredCabinCrew = new ArrayList<CabinCrew>();

            if (profession == null || profession.isEmpty()) {
                throw new BusinessLogicException("Profession cannot be null or empty");
            }

            filteredCabinCrew.addAll(cabinCrewRepo.getAll().stream()
                    .filter(cabinCrew -> cabinCrew.getProfession().equals(profession))
                    .toList());
            return filteredCabinCrew;
        } catch (DatabaseException e) {
            throw new DatabaseException("Error in filtering cabin crew by profession");
        }
    }

    /**
     * Books a seat for a passenger on a specified flight.
     *
     * @param date        The date of the flight.
     * @param passengerID The unique identifier of the passenger.
     * @param flightID    The unique identifier of the flight.
     * @param paymentType The type of payment used for the booking (e.g., credit card, cash).
     * @param from        The departure location.
     * @param to          The arrival location.
     * @return A new Ticket object representing the booking.
     */
    public Ticket bookSeatByFlight(String date, Integer passengerID, Integer flightID, String paymentType, String from, String to) throws BusinessLogicException, EntityNotFoundException, DatabaseException {
        try {
            Passenger p = null;
            Flight f = null;

            if (date == null || date.isEmpty()) {
                throw new BusinessLogicException("Date cannot be null or empty");
            }
            if (passengerID == null) {
                throw new BusinessLogicException("Passenger ID cannot be null");
            }
            if (flightID == null) {
                throw new BusinessLogicException("Flight ID cannot be null");
            }
            if (paymentType == null || paymentType.isEmpty()) {
                throw new BusinessLogicException("Payment type cannot be null or empty");
            }
            if (from == null || from.isEmpty()) {
                throw new BusinessLogicException("From cannot be null or empty");
            }
            if (to == null || to.isEmpty()) {
                throw new BusinessLogicException("To cannot be null or empty");
            }

            for (Passenger passenger : passengerRepo.getAll())
                if (passenger.getID().equals(passengerID))
                    p = passenger;
            if (p == null)
                throw new EntityNotFoundException("Passenger not found");

            for (Flight flight : flightRepo.getAll())
                if (flight.getID().equals(flightID) && flight.getAirplane().getCapacity() > 0 && flight.getFrom().equals(from) && flight.getTo().equals(to))
                    f = flight;
            if (f == null)
                throw new EntityNotFoundException("Flight not found");

            f.getAirplane().setCapacity(f.getAirplane().getCapacity() - 1);
            Payment pay = createPayment(paymentType, f.getAmount(), p.getID());

            return createTicket("Ticket" + " " + from + " " + to, paymentType, pay.getID(), f.getDate());
        } catch (DatabaseException e) {
            throw new DatabaseException("Error in booking seat by flight");
        }
    }
}
