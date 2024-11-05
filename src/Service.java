import Models.*;
import Repo.Repository;

import java.util.ArrayList;
import java.util.List;

//
//CRUD opps for all these repos.
//did read for all of them
//did create, delete and update also

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

    public ArrayList<Pilot> getAvailablePilots() {
        ArrayList<Pilot> pilots= new ArrayList<Pilot>();
        for(Pilot pilot: pilotsRepo.getAll())
            if(pilot.availability.equals(true))
                pilots.add(pilot);
        return pilots;
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
        flightRepo.create(flight);// verify in controller if the pilot is available and and if the airport has free airstrips
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
    public void createCabinCrew(String nume, int id, String email, String profession){
        CabinCrew cabin = new CabinCrew(nume,id,email,profession);
        cabinCrewRepo.create(cabin);
    }


    public ArrayList<Flight> bookSeat(String from, String to){
        ArrayList<Flight> possibleFlights= new ArrayList<Flight>();
        for(Flight flight: flightRepo.getAll()){
            if(flight.from.equals(from) && flight.to.equals(to))
                possibleFlights.add(flight);
        }
        //ar trebui createReservation daca nu sunt possibeFlight si altfel sa fie create!!
        // Sa returneze o rezervare
        return possibleFlights;
    }

    public Reservation createReservation(int reservationID, String date, Payment payment, int passengerID, int flightID) {
        Passenger passenger = passengerRepo.get(passengerID);
        Flight flight = flightRepo.get(flightID);
        Reservation reservation = new Reservation(reservationID, date, payment, passenger, flight);
        return reservation;
    }

    public List<Flight> getAllFlights() {return flightRepo.getAll();}

    public void deletePilot(Pilot pilotPara){
        for(Pilot pilot: pilotsRepo.getAll())
            if(pilot.equals(pilotPara))
                pilotsRepo.delete(pilot.getID());
    }

    public void deletePassenger(Passenger passengerPara){
        for(Passenger passenger: passengerRepo.getAll())
        {
            if(passenger.equals(passengerPara))
                passengerRepo.delete(passenger.getID());
        }
    }

    public void deleteCabinCrew(CabinCrew cabinCrewPara){
        for(CabinCrew cabinCrew: cabinCrewRepo.getAll())
            if(cabinCrew.equals(cabinCrewPara))
                cabinCrewRepo.delete(cabinCrew.getID());
    }

    public void deleteFlight(Flight flightPara){
        for(Flight flight: flightRepo.getAll())
            if(flight.equals(flightPara))
                flightRepo.delete(flight.getID());
    }

    public void updatePilot(Pilot pilotPara,String newName, String newEmail){
        for(Pilot pilot: pilotsRepo.getAll())
            if(pilot.equals(pilotPara))
            {
                pilotPara.setNume(newName);
                pilotPara.setEmail(newEmail);
            }
    }

    public void updatePassenger(Passenger passengerPara,String newName, String newEmail, String newTo, String newFrom){
        for(Passenger passenger: passengerRepo.getAll())

            if(passenger.equals(passengerPara))
            {
                passengerPara.setNume(newName);
                passengerPara.setEmail(newEmail);
                Pair pair= new Pair(newFrom,newTo);
                ArrayList<Pair> newFlight=new ArrayList<>();
                newFlight.add(pair);
                passengerPara.flight=newFlight;
            }
    }

    public void updateCabinCrew(CabinCrew cabinCrewPara, String newName, String newEmail, String newProfesion){
        for(CabinCrew cabinCrew: cabinCrewRepo.getAll())
            if(cabinCrew.equals(cabinCrewPara))
            {
                cabinCrew.setNume(newName);
                cabinCrew.setEmail(newEmail);
                cabinCrew.profession=newProfesion;
            }
    }

    public void updateFlight(Flight flightPara,String newFrom, String newTo, Pilot newPilot){
        for (Flight flight: flightRepo.getAll())
            if(flight.equals(flightPara))
            {
                flight.to=newTo;
                flight.from=newFrom;
                flight.pilot=newPilot;
            }
    }
}
