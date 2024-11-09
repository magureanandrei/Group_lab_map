import Models.*;
import Repo.Repository;

import java.util.ArrayList;
import java.util.List;


public class Service {

    private final Repository<Pilot> pilotsRepo;
    private final Repository<Passenger> passengerRepo;
    private final Repository<CabinCrew> cabinCrewRepo;
    private final Repository<Flight> flightRepo;
    private final Repository<Payment> paymentRepo;
    private final Repository<Reservation> reservationRepo;
    private final Repository<Ticket> ticketRepo;
    private final Repository<Airplane> airplaneRepo;
//    private final ArrayList<Airport> airports = new ArrayList<Airport>();
//    bagat repo normal aici ca sa fie ok

    Integer counterPassengerID=5;
    Integer counterPilotID=5;
    Integer counterCabincrewID=5;
    Integer counterFlightID=5;
    Integer counterPaymentID=5;
    Integer counterReservationID=5;
    Integer counterTicketID=5;
    Integer counterAirplaneID=5;

    public Integer createPilotID() {
        counterPilotID++;
        return counterPilotID;
    }
    public Integer createCabincrewID() {
        counterCabincrewID++;
        return counterCabincrewID;
    }
    public Integer createFlightID() {
        counterFlightID++;
        return counterFlightID;
    }
    public Integer createPaymentID() {
        counterPaymentID++;
        return counterPaymentID;
    }
    public Integer createReservationID() {
        counterReservationID++;
        return counterReservationID;
    }
    public Integer createTicketID() {
        counterTicketID++;
        return counterTicketID;
    }
    public Integer createPassengerID() {
        counterPassengerID+=1;
        return counterPassengerID;
    }
    public Integer createAirplaneID() {
        counterAirplaneID+=1;
        return counterAirplaneID;
    }



    public Service(Repository<Pilot> pilotsRepo, Repository<Passenger> passengerRepo, Repository<CabinCrew> cabinCrewRepo, Repository<Flight> flightRepo, Repository<Payment> paymentRepo, Repository<Reservation> reservationRepo, Repository<Ticket> ticketRepo, Repository<Airplane> airplaneRepo) {
        this.pilotsRepo = pilotsRepo;
        this.passengerRepo = passengerRepo;
        this.cabinCrewRepo = cabinCrewRepo;
        this.flightRepo = flightRepo;
        this.paymentRepo = paymentRepo;
        this.reservationRepo = reservationRepo;
        this.ticketRepo = ticketRepo;
        this.airplaneRepo = airplaneRepo;
    }

    public List<Pilot> getPilots() {return pilotsRepo.getAll();}

    public List<Passenger> getAllPassengers() {return passengerRepo.getAll();}

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

    public List<CabinCrew> getAllCabinCrews() {return cabinCrewRepo.getAll();}

    public List<Payment> getAllPayments() {return paymentRepo.getAll();}

    public List<Reservation> getAllReservations() {return reservationRepo.getAll();}

    public List<Ticket> getAllTickets() {return ticketRepo.getAll();}

    public List<Airplane> getAllAirplanes() {return airplaneRepo.getAll();}

    public void createFlight(String from, String to, Integer pilotID, Integer airplaneID,Integer airportID){
        Integer flightID=createFlightID();
        Pilot p=null;
        Airplane a=null;
        for(Pilot pilot: pilotsRepo.getAll())
            if(pilotID.equals(pilot.getID()) && pilot.getAvailability().equals(true))
                p=pilot;
        for(Airplane airplane: airplaneRepo.getAll())
            if(airplaneID.equals(airplane.getID()) && airplane.getAvailable().equals(true))
                a=airplane;
        //for pt airport dupa id
        //verificat avalibility pt airport
        //

        Flight flight = new Flight(flightID,from,to,p,a);
        flightRepo.create(flight);// verify if the pilot and airplane are available and everything+ adauga airport
    }
    public void createPassenger(String passengerName, String from, String to, String email){

        Pair pair = new Pair(from,to);
        Integer passengerID=createPassengerID();
        Passenger passenger = new Passenger(passengerName,passengerID,email,pair);
        passengerRepo.create(passenger);
    }
    public void createPilot(String nume, String email, Boolean availibility){
        Integer id=createPilotID();
        Pilot pilot = new Pilot(nume,id,email,availibility);
        pilotsRepo.create(pilot);
    }
    public void createCabinCrew(String nume, String email, String profession){
        Integer id=createCabincrewID();
        CabinCrew cabin = new CabinCrew(nume,id,email,profession);
        cabinCrewRepo.create(cabin);
    }


    public ArrayList<Flight> bookSeat(String from, String to){
        //un if mare aici

        ArrayList<Flight> possibleFlights= new ArrayList<Flight>();
        for(Flight flight: flightRepo.getAll()){
            if(flight.from.equals(from) && flight.to.equals(to))
                possibleFlights.add(flight);
            //aici ar trebui si date dat ca paramentu pt ca o sa folosim create reservation aici si trebe si o data
        }
        return possibleFlights;

        //aici ar trebui sa ne gandim intai la ui-ul la aceasta functie. pt ca o sa fie mai multe chestii de facut
        //dupa ce apar possible flights(ales un flight/ daca nu sunt possible flights, sa intre in createreservation
        //si sa fie bagata rezervarea in lista aia a operatorului cand creaza zborul
    }


    public List<Flight> getAllFlights() {return flightRepo.getAll();}

    public void deletePilot(Integer pilotID){
        for(Pilot pilot: pilotsRepo.getAll())
            if(pilot.getID().equals(pilotID))
                pilotsRepo.delete(pilot.getID());
    }

    public void deletePassenger(Integer passengerID){
        for(Passenger passenger: passengerRepo.getAll())
            if(passenger.getID().equals(passengerID))
                passengerRepo.delete(passenger.getID());

    }

    public void deleteCabinCrew(Integer cabincrewID){
        for(CabinCrew cabinCrew: cabinCrewRepo.getAll())
            if(cabinCrew.getID().equals(cabincrewID))
                cabinCrewRepo.delete(cabinCrew.getID());
    }

    public void deleteFlight(Integer flightID){
        for(Flight flight: flightRepo.getAll())
            if(flight.getID().equals(flightID))
                flightRepo.delete(flight.getID());
    }

    public void updatePilot(Integer pilotID,String newName, String newEmail){
        for(Pilot pilot: pilotsRepo.getAll())
            if(pilot.getID().equals(pilotID))
            {
                pilot.setNume(newName);
                pilot.setEmail(newEmail);
                break;
            }
    }

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

    public void deletePayment(Integer payID){
        for(Payment payment: paymentRepo.getAll())
            if(payment.getID().equals(payID))
                paymentRepo.delete(payment.getID());

    }

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

    public void deleteReservation(Integer reservationID){
        for(Reservation reservation: reservationRepo.getAll())
            if(reservation.getID().equals(reservationID))
                reservationRepo.delete(reservationID);
    }

    public void updateReservation(Integer reservationID,String newDate){
        for(Reservation reservation: reservationRepo.getAll())
            if(reservation.getID().equals(reservationID)) {
                reservation.setDate(newDate);
                break;
            }

    }

    public void createTicket(String title, String description, Integer paymentID){
        Integer ticketID=createTicketID();
        Payment p=null;
        for(Payment payment: paymentRepo.getAll())
            if(payment.getID().equals(paymentID))
                p=payment;
        Ticket newTicket=new Ticket(ticketID,title,description,p);
        ticketRepo.create(newTicket);

    }

    public void deleteTicket(Integer ticketID){
        for(Ticket ticket: ticketRepo.getAll())
            if(ticket.getID().equals(ticketID))
                ticketRepo.delete(ticketID);
    }

    public void updateTicket(Integer ticketID, String newTitle, String newDescription){
        for(Ticket ticket: ticketRepo.getAll())
            if(ticket.getID().equals(ticketID)) {
                ticket.setTitle(newTitle);
                ticket.setDescription(newDescription);
                break;
            }
    }

    public ArrayList<Pilot> getAvailablePilots() {
        ArrayList<Pilot> pilots= new ArrayList<Pilot>();
        for(Pilot pilot: pilotsRepo.getAll())
            if(pilot.getAvailability().equals(true))
                pilots.add(pilot);
        return pilots;
    }

    public void createAirplane(String model, Integer capacity, Boolean avaliable){
        Integer airplaneID=createAirplaneID();
        Airplane newaAirplane=new Airplane(airplaneID,model,capacity,avaliable);
        airplaneRepo.create(newaAirplane);
    }

    public void deleteAirplane(Integer airplaneID){
        for(Airplane airplane: airplaneRepo.getAll())
            if(airplane.getID().equals(airplaneID))
                airplaneRepo.delete(airplaneID);
    }

    public void updateAirplane(Integer airplaneID, String newModel, Integer newCapacity,Boolean newAvaliable){

        for(Airplane airplane: airplaneRepo.getAll())
            if(airplane.getID().equals(airplaneID)) {
                airplane.setModel(newModel);
                airplane.setAvailable(newAvaliable);
                airplane.setCapacity(newCapacity);
            }


    }

    public Ticket getTicket(Integer ticketID){
        for(Ticket ticket: ticketRepo.getAll()){
            if(ticket.getID().equals(ticketID))
                return ticket;
        }
        return null;
    }

    public Reservation getReservation(Integer reservationID){
        for(Reservation reservation: reservationRepo.getAll()) {
            if(reservation.getID().equals(reservationID))
                return reservation;
        }
        return null;
    }

}
