import Models.CabinCrew;
import Models.Flight;
import Models.Passenger;
import Models.Pilot;

import java.util.ArrayList;
import java.util.List;
// did the implementation for all, delete, update, get Functions
//create functions from passenger, pilot and cabin crew are done
//we still need to do the create functions for Flight, reservation, payment and ticket
public class Controller {
    private final Service flightService;

    public Controller(Service flightService) {
        this.flightService = flightService;
    }
    public void viewAvailablePilots() {
        //1- availible 2- not available
        ArrayList<Pilot> availablePilots = flightService.getAvailablePilots();
        System.out.println("Available Pilots:\n");
        for (Pilot pilot : availablePilots) {
            System.out.println(pilot.toString() + " \n");
        }
    }
    public void viewPassengers() {
        List<Passenger> allPassengers = flightService.getAllPassengers();
        System.out.println("Passengers:\n");
        for (Passenger passenger : allPassengers) {
            System.out.println(passenger.toString() + " \n");
        }
    }
    public void viewCabinCrew() {
        List<CabinCrew> allCabinCrew = flightService.getAllCabinCrews();
        System.out.println("Cabin Crew:\n");
        for (CabinCrew cabinCrew : allCabinCrew) {
            System.out.println(cabinCrew.toString() + " \n");
        }
    }
    public void viewPassengersByFlight(Integer flightID){
        Flight f=null;
        for(Flight flight : flightService.getAllFlights()){
            if(flight.getID().equals(flightID)){
                f=flight;
            }
        }

        List<Passenger> allPassengersByFlight = flightService.getPassengersByFlight(f);
        System.out.println("Passengers By Flight:\n");
        for (Passenger passenger : allPassengersByFlight) {
            System.out.println(passenger.toString() + " \n");
        }
    }
    public void viewAllFlights(){
        List<Flight> allFlights = flightService.getAllFlights();
        System.out.println("All Flights:\n");
        for (Flight flight : allFlights) {
            System.out.println(flight.toString() + " \n");
        }
    }

    public void deletePassenger(Integer passengerID) {
        Passenger p = null;
        for(Passenger passenger : flightService.getAllPassengers()){
            if(passenger.getID().equals(passengerID)){
                p=passenger;
            }
        }
        //aici trebe un error handling ca daca nu gaseste pasagerul sa dea eroare
        flightService.deletePassenger(passengerID);
        System.out.println("Removed Passenger " + p.toString() + ".");
    }
    public void deleteCabinCrew(Integer cabinID) {

        CabinCrew c = null;
        for(CabinCrew cabinCrew : flightService.getAllCabinCrews()){
            if(cabinCrew.getID().equals(cabinID)){
                c=cabinCrew;
            }
        }
        //aici la fel
        flightService.deleteCabinCrew(cabinID);
        System.out.println("Removed Cabin Crew " + c.toString() + ".");
    }
    public void deleteFlight(Integer flightID) {
        Flight f = null;
        for(Flight flight : flightService.getAllFlights()){
            if(flight.getID().equals(flightID)){
                f=flight;
            }
        }
        //si aici
        flightService.deleteFlight(flightID);
        System.out.println("Removed Flight " + f.toString() + ".");
    }
    public void deletePilot(Integer pilotID) {
        Pilot p = null;
        for(Pilot pilot: flightService.getPilots())
            if(pilot.getID().equals(pilotID)){
                p=pilot;
            }
        //si aici
        flightService.deletePilot(pilotID);
        System.out.println("Removed Pilot " + p.toString() + ".");
    }
    public void updatePassenger(Integer passengerID,String newName, String newEmail, String newTo, String newFrom){
        flightService.updatePassenger(passengerID, newName, newEmail, newTo, newFrom);
        System.out.println("Updated Passenger: " + newName + ".");
    }
    public void updateCabinCrew(Integer cabinID, String newName, String newEmail, String newProfesion){
        flightService.updateCabinCrew(cabinID, newName, newEmail, newProfesion);
        System.out.println("Updated Cabin Crew: " + newName + ".");
    }
    public void updateFlight(Integer flightID,String newFrom, String newTo, Integer pilotID,Integer airplaneID){
        flightService.updateFlight(flightID, newFrom, newTo, pilotID,airplaneID);
        System.out.println("Updated Flight " + newFrom + ","+ newTo + ".");
    }
    public void updatePilot(Integer pilotID,String newName, String newEmail){
        flightService.updatePilot(pilotID, newName, newEmail);
        System.out.println("Updated Pilot " + newName + ".");
    }
    public void createPilot(String nume, String email, Boolean availibility){
        flightService.createPilot(nume, email, availibility);
        System.out.println("Pilot was created");
    }
    public void createCabinCrew(String nume, String email, String profession){
        flightService.createCabinCrew(nume, email, profession);
        System.out.println("Cabin Crew was created");
    }
    public void createPassenger(String passengerName, String from, String to, String email){
        flightService.createPassenger(passengerName, from, to, email);
        System.out.println("Passenger was created");
    }
}
