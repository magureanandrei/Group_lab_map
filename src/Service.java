import Models.*;
import Repo.Repository;

import java.util.ArrayList;
import java.util.List;

public class Service {

    private final Repository<Pilot> pilotsRepo;
    private final Repository<Passenger> passengerRepo;
    private final Repository<CabinCrew> cabinCrewRepo;
    private final Repository<Flight> flightRepo;



    public Service(Repository<Pilot> pilotsRepo, Repository<Passenger> passengerRepo, Repository<CabinCrew> cabinCrewRepo, Repository<Flight> flightRepo) {
        this.pilotsRepo = pilotsRepo;
        this.passengerRepo = passengerRepo;
        this.cabinCrewRepo = cabinCrewRepo;
        this.flightRepo = flightRepo;
    }

    public List<Pilot> getPilots() {return pilotsRepo.getAll();}

    public List<Passenger> getAllPassengers() {return passengerRepo.getAll();}

    public List<Passenger> getPassengersByFlight(Flight flightPara) {
        ArrayList<Passenger> passengerByFlight = new ArrayList<Passenger>();
        for( Passenger passenger: passengerRepo.getAll())
            for(Pair pair: passenger.flight)
            {
                if(pair.getFrom().equals(flightPara.from) && pair.getTo().equals(flightPara.to))
                    passengerByFlight.add(passenger);
            }
        return passengerByFlight;
    }

    public List<CabinCrew> getAllCabinCrews() {return cabinCrewRepo.getAll();}


    public void createFlight(int flightID, String from, String to, Pilot pilot){
         Flight flight = new Flight(flightID,from,to,pilot);
        flightRepo.create(flight);// verify in controller if the pilot is available and everything
    }
    public void createPassenger(String passengerName,int passengerID, String from, String to, String email){
        Pair pair = new Pair(from,to);
        ArrayList<Pair> passengerFlights = new ArrayList<Pair>();
        passengerFlights.add(pair);
        Passenger passenger = new Passenger(passengerName,passengerID,email,passengerFlights);
        passengerRepo.create(passenger);
    }
    public void createPilot(String nume, int id, String email, Boolean availibility){
        Pilot pilot = new Pilot(nume,id,email,availibility);
        pilotsRepo.create(pilot);
    }
    public void createCabinCrew(String nume, int id, String email){
        CabinCrew cabin = new CabinCrew(nume,id,email);
        cabinCrewRepo.create(cabin);
    }


    public ArrayList<Flight> bookSeat(String from, String to){
        ArrayList<Flight> possibleFlights= new ArrayList<Flight>();
        for(Flight flight: flightRepo.getAll()){
            if(flight.from.equals(from) && flight.to.equals(to))
                possibleFlights.add(flight);
        }
        return possibleFlights;
    }

    public Reservation createReservation(int reservationID, String date, Payment payment, int passengerID, int flightID) {
        Passenger passenger = passengerRepo.get(passengerID);
        Flight flight = flightRepo.get(flightID);
        Reservation reservation = new Reservation(reservationID, date, payment, passenger, flight);
        return reservation;
    }

}
