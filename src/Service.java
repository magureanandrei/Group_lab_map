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

    Integer counterPassengerID=5;
    Integer counterPilotID=5;
    Integer counterCabincrewID=5;
    Integer counterFlightID=5;
    Integer counterPaymentID=5;
    Integer counterReservationID=5;
    Integer counterTicketID=5;
    Integer counterAirplaneID=5;
    Integer counterAirportID=5;

    /**
     * Creates a new identifier for a pilot.
     * @return The new identifier for a pilot.
     */
    public Integer createPilotID() {
        counterPilotID++;
        return counterPilotID;
    }
    /**
     * Creates a new identifier for a cabin crew.
     * @return The new identifier for a cabin crew.
     */
    public Integer createCabincrewID() {
        counterCabincrewID++;
        return counterCabincrewID;
    }
    /**
     * Creates a new identifier for a flight.
     * @return The new identifier for a flight.
     */
    public Integer createFlightID() {
        counterFlightID++;
        return counterFlightID;
    }
    /**
     * Creates a new identifier for a payment.
     * @return The new identifier for a payment.
     */
    public Integer createPaymentID() {
        counterPaymentID++;
        return counterPaymentID;
    }
    /**
     * Creates a new identifier for a reservation.
     * @return The new identifier for a reservation.
     */
    public Integer createReservationID() {
        counterReservationID++;
        return counterReservationID;
    }
    /**
     * Creates a new identifier for a ticket.
     * @return The new identifier for a ticket.
     */
    public Integer createTicketID() {
        counterTicketID++;
        return counterTicketID;
    }
    /**
     * Creates a new identifier for a passenger.
     * @return The new identifier for a passenger.
     */
    public Integer createPassengerID() {
        counterPassengerID+=1;
        return counterPassengerID;
    }
    /**
     * Creates a new identifier for an airplane.
     * @return The new identifier for an airplane.
     */
    public Integer createAirplaneID() {
        counterAirplaneID+=1;
        return counterAirplaneID;
    }
    /**
     * Creates a new identifier for an airport.
     * @return The new identifier for an airport.
     */
    public Integer createAirportID() {
        counterAirportID+=1;
        return counterAirportID;
    }


    /**
     * The service constructor
     * @param pilotsRepo initializes the repository for pilots
     * @param passengerRepo initializes the repository for passengers
     * @param cabinCrewRepo initializes the repository for cabin crew
     * @param flightRepo initializes the repository for flights
     * @param paymentRepo initializes the repository for payments
     * @param reservationRepo initializes the repository for reservations
     * @param ticketRepo initializes the repository for tickets
     * @param airplaneRepo initializes the repository for airplanes
     * @param airportRepo initializes the repository for airports
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
     * @return The list of all pilots.
     */
    public List<Pilot> getPilots() {return pilotsRepo.getAll();}

    /**
     * Gets all the passengers.
     * @return The list of all passengers.
     */
    public List<Passenger> getAllPassengers() {return passengerRepo.getAll();}

    /**
     * Gets all the passengers filtered by flight.
     * @return The list of all passengers filtered by flight.
     */
    public List<Passenger> getPassengersByFlight(Integer flightID) {
        ArrayList<Passenger> passengerByFlight = new ArrayList<Passenger>();
        Flight f=null;
        for(Flight flight : flightRepo.getAll()) {
            if(flight.getID().equals(flightID)) {
                f=flight;
            }
        }
        for( Passenger passenger: passengerRepo.getAll())
                if(passenger.getFlight().getFrom().equals(f.from) && passenger.getFlight().getTo().equals(f.to)) {
                    passengerByFlight.add(passenger);
                }
        return passengerByFlight;
    }

    /**
     * Gets all the cabin crew.
     * @return The list of all cabin crew.
     */
    public List<CabinCrew> getAllCabinCrews() {return cabinCrewRepo.getAll();}

    /**
     * Gets all the payments.
     * @return The list of all payments.
     */
    public List<Payment> getAllPayments() {return paymentRepo.getAll();}

    /**
     * Gets all the reservations.
     * @return The list of all reservations.
     */
    public List<Reservation> getAllReservations() {return reservationRepo.getAll();}

    /**
     * Gets all the tickets.
     * @return The list of all tickets.
     */
    public List<Ticket> getAllTickets() {return ticketRepo.getAll();}

    /**
     * Gets all the airplanes.
     * @return The list of all airplanes.
     */
    public List<Airplane> getAllAirplanes() {return airplaneRepo.getAll();}

    /**
     * Gets all the airports.
     * @return The list of all airports.
     */
    public List<Airport> getAllAirports() {return airportRepo.getAll();}

    /**
     * Creates a new flight.
     * @param from The departure location.
     * @param to The arrival location.
     * @param pilotID The pilot identifier.
     * @param airplaneID The airplane identifier.
     * @param airportID The airport identifier.
     */
    public void createFlight(String from, String to, Integer pilotID, Integer airplaneID,Integer airportID){
        Integer flightID=createFlightID();
        Pilot p=null;
        Airplane a=null;
        Airport ap=null;
        for(Pilot pilot: pilotsRepo.getAll())
            if(pilotID.equals(pilot.getID()) && pilot.getAvailability().equals(true))
                p=pilot;
        for(Airplane airplane: airplaneRepo.getAll())
            if(airplaneID.equals(airplane.getID()) && airplane.getAvailable().equals(true))
                a=airplane;
        for(Airport airport: airportRepo.getAll())
            if(airportID.equals(airport.getID()) && airport.getAvaliable().equals(true) && airport.getLocation().equals(to))
                ap=airport;

        Flight flight = new Flight(flightID,from,to,p,a/*,ap*/);
        flightRepo.create(flight);
    }

    /**
     * Creates a new passenger.
     * @param passengerName The name of the passenger.
     * @param from The departure location.
     * @param to The arrival location.
     * @param email The email of the passenger.
     */
    public void createPassenger(String passengerName, String from, String to, String email){

        Pair pair = new Pair(from,to);
        Integer passengerID=createPassengerID();
        Passenger passenger = new Passenger(passengerName,passengerID,email,pair);
        passengerRepo.create(passenger);
    }

    /**
     * Creates a new pilot.
     * @param nume The name of the pilot.
     * @param email The email of the pilot.
     * @param availibility The availability of the pilot.
     */
    public void createPilot(String nume, String email, Boolean availibility){
        Integer id=createPilotID();
        Pilot pilot = new Pilot(nume,id,email,availibility);
        pilotsRepo.create(pilot);
    }

    /**
     * Creates a new cabin crew.
     * @param nume The name of the cabin crew.
     * @param email The email of the cabin crew.
     * @param profession The profession of the cabin crew.
     */
    public void createCabinCrew(String nume, String email, String profession){
        Integer id=createCabincrewID();
        CabinCrew cabin = new CabinCrew(nume,id,email,profession);
        cabinCrewRepo.create(cabin);
    }


    public ArrayList<Flight> bookSeat(String date, Integer passengerID){
        //asta e o functie care e folosita doar de pasager(in ui ar trebui sa se autentifice cu id-ul sau si asa luam from si to)
        String from=null;
        String to=null;

        for(Passenger passenger: passengerRepo.getAll())
            if(passenger.getID().equals(passengerID))
            {
                from=passenger.getFlight().getFrom();
                to=passenger.getFlight().getTo();
                break;
            }


        ArrayList<Flight> possibleFlights= new ArrayList<Flight>();
        for(Flight flight: flightRepo.getAll()){
            if(flight.from.equals(from) && flight.to.equals(to))
                possibleFlights.add(flight);
        }
        if(possibleFlights.size()==0)
        {   ArrayList <ArrayList<String>> flightsForOperator= new ArrayList<ArrayList<String>>();
            //aici ar trebui sa intram in createReservation si sa bagam rezervarea in lista aia a operatorului cand creaza zborul
            //ii dam si o notificare pasagerului ca nu sunt zboruri posibile
            //intai trebe realizat payment-ul(
            ArrayList<String>flightToAdd=new ArrayList<String>();
            flightToAdd.add(from);
            flightToAdd.add(to);
            flightsForOperator.add(flightToAdd);


            createPayment("Reservation",0,passengerID);
            createReservation(date,0,passengerID,from,to);

        }
        else {
            //aici ar trebui sa intram in ui si sa alegem unul din zborurile posibile
            //si dupa aia intram in creataticket sau createreservation
        }

        //aici ar trebui sa ne gandim intai la ui-ul la aceasta functie. pt ca o sa fie mai multe chestii de facut
        //dupa ce apar possible flights(ales un flight/ daca nu sunt possible flights, sa intre in createreservation
        //si sa fie bagata rezervarea in lista aia a operatorului cand creaza zborul
    return null;
    }

    /**
     * Gets all the flights.
     * @return The list of all flights.
     */
    public List<Flight> getAllFlights() {return flightRepo.getAll();}


    /**
     * Deletes a pilot.
     * @param pilotID The pilot identifier.
     */
    public void deletePilot(Integer pilotID){
        for(Pilot pilot: pilotsRepo.getAll())
            if(pilot.getID().equals(pilotID))
                pilotsRepo.delete(pilot.getID());
    }

    /**
     * Deletes a passenger.
     * @param passengerID The passenger identifier.
     */
    public void deletePassenger(Integer passengerID){
        for(Passenger passenger: passengerRepo.getAll())
            if(passenger.getID().equals(passengerID))
                passengerRepo.delete(passenger.getID());

    }

    /**
     * Deletes a cabin crew.
     * @param cabincrewID The cabin crew identifier.
     */
    public void deleteCabinCrew(Integer cabincrewID){
        for(CabinCrew cabinCrew: cabinCrewRepo.getAll())
            if(cabinCrew.getID().equals(cabincrewID))
                cabinCrewRepo.delete(cabinCrew.getID());
    }

    /**
     * Deletes a flight.
     * @param flightID The flight identifier.
     */
    public void deleteFlight(Integer flightID){
        for(Flight flight: flightRepo.getAll())
            if(flight.getID().equals(flightID))
                flightRepo.delete(flight.getID());
    }

    /**
     * Updates a pilot.
     * @param pilotID The pilot identifier.
     * @param newName The new name of the pilot.
     * @param newEmail The new email of the pilot.
     */
    public void updatePilot(Integer pilotID,String newName, String newEmail){
        for(Pilot pilot: pilotsRepo.getAll())
            if(pilot.getID().equals(pilotID))
            {
                pilot.setNume(newName);
                pilot.setEmail(newEmail);
                break;
            }
    }

    /**
     * Updates a passenger.
     * @param passengerID The passenger identifier.
     * @param newName The new name of the passenger.
     * @param newEmail The new email of the passenger.
     * @param newTo The new arrival location of the passenger.
     * @param newFrom The new departure location of the passenger.
     */
    public void updatePassenger(Integer passengerID,String newName, String newEmail, String newTo, String newFrom){
        for(Passenger passenger: passengerRepo.getAll())

            if(passenger.getID().equals(passengerID))
            {
                passenger.setNume(newName);
                passenger.setEmail(newEmail);
                Pair pair= new Pair(newFrom,newTo);
                passenger.setFlight(pair);
                break;
            }
    }

    /**
     * Updates a cabin crew.
     * @param cabincrewID The cabin crew identifier.
     * @param newName The new name of the cabin crew.
     * @param newEmail The new email of the cabin crew.
     * @param newProfesion The new profession of the cabin crew.
     */
    public void updateCabinCrew(Integer cabincrewID, String newName, String newEmail, String newProfesion){
        for(CabinCrew cabinCrew: cabinCrewRepo.getAll())
            if(cabinCrew.getID().equals(cabincrewID))
            {
                cabinCrew.setNume(newName);
                cabinCrew.setEmail(newEmail);
                cabinCrew.setProfession(newProfesion);
                break;
            }
    }

    /**
     * Updates a flight.
     * @param flightID The flight identifier.
     * @param newFrom The new departure location of the flight.
     * @param newTo The new arrival location of the flight.
     * @param pilotID The pilot identifier.
     * @param airplaneID The airplane identifier.
     */
    public void updateFlight(Integer flightID,String newFrom, String newTo, Integer pilotID, Integer airplaneID){
        Pilot p=null;
        Airplane a=null;
        for(Pilot pilot: pilotsRepo.getAll())
            if(pilot.getID().equals(pilotID))
                p=pilot;
        for(Airplane airplane: airplaneRepo.getAll())
            if(airplane.getID().equals(airplaneID))
                a=airplane;

        for (Flight flight: flightRepo.getAll())
            if(flight.getID().equals(flightID))
            {
                flight.to=newTo;
                flight.from=newFrom;
                flight.pilot=p;
                flight.airplane=a;
                break;
            }
    }

    /**
     * Creates a new payment.
     * @param description The description of the payment.
     * @param amount The amount of the payment.
     * @param passengerID The passenger identifier.
     */
    public void createPayment(String description, double amount,Integer passengerID){
        //trebe scris si o descriere din UI cumva si data ca parametru
        Integer payID=createPaymentID();
        Passenger p=null;
        for( Passenger passenger: passengerRepo.getAll())
            if(passenger.getID().equals(passengerID))
            {
                p=passenger;
                break;
            }
        Payment newpay=new Payment(payID,description,amount,p);
        paymentRepo.create(newpay);
    }

    /**
     * Deletes a payment.
     * @param payID The payment identifier.
     */
    public void deletePayment(Integer payID){
        for(Payment payment: paymentRepo.getAll())
            if(payment.getID().equals(payID))
                paymentRepo.delete(payment.getID());

    }

    /**
     * Updates a payment.
     * @param paymentID The payment identifier.
     * @param newDescription The new description of the payment.
     * @param newAmount The new amount of the payment.
     */
    public void updatePayment(Integer paymentID,String newDescription, double newAmount){
        //id of payment stays the same and id of passenger that pays stays the same

        for(Payment payment: paymentRepo.getAll())
            if(payment.getID().equals(paymentID))
            {
                payment.setAmount(newAmount);
                payment.setDescription(newDescription);
                break;
            }

    }

    // ar trebui sa facem o lista de reservations( si asta ar fi data si ca lista pt operator, dar filtrata cumva
    //undeva, nu-s sigur unde)

    /**
     * Creates a new reservation.
     * @param date The date of the reservation.
     * @param paymentID The payment identifier.
     * @param passengerID The passenger identifier.
     * @param from The departure location.
     * @param to The arrival location.
     */
    public void createReservation(String date, Integer paymentID, Integer passengerID, String from, String to){

        Passenger p=null;
        Payment pay = null;
        Pair fl= new Pair(from,to);
        Integer resID=createReservationID();

        for(Passenger passenger: passengerRepo.getAll())
            if(passenger.getID().equals(passengerID))
                p=passenger;

        for(Payment payment: paymentRepo.getAll())
            if(payment.getID().equals(paymentID))
                pay=payment;

        Reservation newReservation= new Reservation(resID,date,pay,p,fl);
        reservationRepo.create(newReservation);

    }

    /**
     * Deletes a reservation.
     * @param reservationID The reservation identifier.
     */
    public void deleteReservation(Integer reservationID){
        for(Reservation reservation: reservationRepo.getAll())
            if(reservation.getID().equals(reservationID))
                reservationRepo.delete(reservationID);
    }

    /**
     * Updates a reservation.
     * @param reservationID The reservation identifier.
     * @param newDate The new date of the reservation.
     */
    public void updateReservation(Integer reservationID,String newDate){
        for(Reservation reservation: reservationRepo.getAll())
            if(reservation.getID().equals(reservationID)) {
                reservation.setDate(newDate);
                break;
            }

    }

    /**
     * Creates a new ticket.
     * @param title The title of the ticket.
     * @param description The description of the ticket.
     * @param paymentID The payment identifier.
     */
    public void createTicket(String title, String description, Integer paymentID){
        Integer ticketID=createTicketID();
        Payment p=null;
        for(Payment payment: paymentRepo.getAll())
            if(payment.getID().equals(paymentID))
                p=payment;
        Ticket newTicket=new Ticket(ticketID,title,description,p);
        ticketRepo.create(newTicket);

    }

    /**
     * Deletes a ticket.
     * @param ticketID The ticket identifier.
     */
    public void deleteTicket(Integer ticketID){
        for(Ticket ticket: ticketRepo.getAll())
            if(ticket.getID().equals(ticketID))
                ticketRepo.delete(ticketID);
    }

    /**
     * Updates a ticket.
     * @param ticketID The ticket identifier.
     * @param newTitle The new title of the ticket.
     * @param newDescription The new description of the ticket.
     */
    public void updateTicket(Integer ticketID, String newTitle, String newDescription){
        for(Ticket ticket: ticketRepo.getAll())
            if(ticket.getID().equals(ticketID)) {
                ticket.setTitle(newTitle);
                ticket.setDescription(newDescription);
                break;
            }
    }

    /**
     * Gets all the available pilots.
     * @return The list of all available pilots.
     */
    public ArrayList<Pilot> getAvailablePilots() {
        ArrayList<Pilot> pilots= new ArrayList<Pilot>();
        for(Pilot pilot: pilotsRepo.getAll())
            if(pilot.getAvailability().equals(true))
                pilots.add(pilot);
        return pilots;
    }

    /**
     * Gets all the available cabin crew.
     * @return The list of all available cabin crew.
     */
    public void createAirplane(String model, Integer capacity, Boolean avaliable){
        Integer airplaneID=createAirplaneID();
        Airplane newaAirplane=new Airplane(airplaneID,model,capacity,avaliable);
        airplaneRepo.create(newaAirplane);
    }

    /**
     * Deletes an airplane.
     * @param airplaneID The airplane identifier.
     */
    public void deleteAirplane(Integer airplaneID){
        for(Airplane airplane: airplaneRepo.getAll())
            if(airplane.getID().equals(airplaneID))
                airplaneRepo.delete(airplaneID);
    }

    /**
     * Updates an airplane.
     * @param airplaneID The airplane identifier.
     * @param newModel The new model of the airplane.
     * @param newCapacity The new capacity of the airplane.
     * @param newAvaliable The new availability of the airplane.
     */
    public void updateAirplane(Integer airplaneID, String newModel, Integer newCapacity,Boolean newAvaliable){

        for(Airplane airplane: airplaneRepo.getAll())
            if(airplane.getID().equals(airplaneID)) {
                airplane.setModel(newModel);
                airplane.setAvailable(newAvaliable);
                airplane.setCapacity(newCapacity);
            }


    }

    /**
     * Gets all the available airplanes.
     * @return The list of all available airplanes.
     */
    public Ticket getTicket(Integer ticketID){
        for(Ticket ticket: ticketRepo.getAll()){
            if(ticket.getID().equals(ticketID))
                return ticket;
        }
        return null;
    }

    /**
     * Gets all the available airports.
     * @return The list of all available airports.
     */
    public Reservation getReservation(Integer reservationID){
        for(Reservation reservation: reservationRepo.getAll()) {
            if(reservation.getID().equals(reservationID))
                return reservation;
        }
        return null;
    }

    /**
     * Creates a new airport.
     * @param name The name of the airport.
     * @param location The location of the airport.
     * @param number_of_airstrips  The number of airstrips of the airport.
     * @param avaliable The availability of the airport.
     */
    public void createAirport(String name, String location, Integer number_of_airstrips, Boolean avaliable){
        Integer airportID=createAirportID();
        Airport newAirport=new Airport(airportID,name,location,number_of_airstrips,avaliable);
        airportRepo.create(newAirport);
    }

    /**
     * Deletes an airport.
     * @param airportID The airport identifier.
     */
    public void deleteAirport(Integer airportID){
        for(Airport airport: airportRepo.getAll())
            if(airport.getID().equals(airportID))
                airportRepo.delete(airportID);
    }

    /**
     * Updates an airport.
     * @param airportID The airport identifier.
     * @param newName The new name of the airport.
     * @param newLocation The new location of the airport.
     * @param newNumberOfAirstrips The new number of airstrips of the airport.
     * @param newAvaliable The new availability of the airport.
     */
    public void updateAirport(Integer airportID, String newName, String newLocation,Integer newNumberOfAirstrips, Boolean newAvaliable){
        for(Airport airport: airportRepo.getAll())
            if(airport.getID().equals(airportID)) {
                airport.setName(newName);
                airport.setNumber_of_airstrips(newNumberOfAirstrips);
                airport.setLocation(newLocation);
                airport.setAvaliable(newAvaliable);
            }
    }

}
