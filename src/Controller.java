import Models.CabinCrew;
import Models.Flight;
import Models.Passenger;
import Models.Pilot;

import java.util.ArrayList;
import java.util.List;
// did the implementation for all, delete, update, get Functions
//create functions from passenger, pilot and cabin crew are done
//we still need to do the create functions for Flight, reservation and ticket
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
    public void viewPassengersByFlight(Flight flightPara){
        List<Passenger> allPassengersByFlight = flightService.getPassengersByFlight(flightPara);
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

    public void deletePassenger(Passenger passengerPara) {
        flightService.deletePassenger(passengerPara);
        System.out.println("Removed Passenger " + passengerPara.toString() + ".");
    }
    public void deleteCabinCrew(CabinCrew cabinCrewPara) {
        flightService.deleteCabinCrew(cabinCrewPara);
        System.out.println("Removed Cabin Crew " + cabinCrewPara.toString() + ".");
    }
    public void deleteFlight(Flight flightPara) {
        flightService.deleteFlight(flightPara);
        System.out.println("Removed Flight " + flightPara.toString() + ".");
    }
    public void deletePilot(Pilot pilotPara) {
        flightService.deletePilot(pilotPara);
        System.out.println("Removed Pilot " + pilotPara.toString() + ".");
    }
    public void updatePassenger(Passenger passengerPara,String newName, String newEmail, String newTo, String newFrom){
        flightService.updatePassenger(passengerPara, newName, newEmail, newTo, newFrom);
        System.out.println("Updated Passenger: " + passengerPara.toString() + ".");
    }
    public void updateCabinCrew(CabinCrew cabinCrewPara, String newName, String newEmail, String newProfesion){
        flightService.updateCabinCrew(cabinCrewPara, newName, newEmail, newProfesion);
        System.out.println("Updated Cabin Crew: " + cabinCrewPara.toString() + ".");
    }
    public void updateFlight(Flight flightPara,String newFrom, String newTo, Pilot newPilot){
        flightService.updateFlight(flightPara, newFrom, newTo, newPilot);
        System.out.println("Updated Flight " + flightPara.toString() + ".");
    }
    public void updatePilot(Pilot pilotPara,String newName, String newEmail){
        flightService.updatePilot(pilotPara, newName, newEmail);
        System.out.println("Updated Pilot " + pilotPara.toString() + ".");
    }
    public void createPilot(String nume, int id, String email, Boolean availibility){
        flightService.createPilot(nume, id, email, availibility);
        System.out.println("Pilot was created");
    }
    public void createCabinCrew(String nume, int id, String email, String profession){
        flightService.createCabinCrew(nume, id, email, profession);
        System.out.println("Cabin Crew was created");
    }
    public void cretePassenger(String passengerName,int passengerID, String from, String to, String email){
        flightService.createPassenger(passengerName, passengerID, from, to, email);
        System.out.println("Passenger was created");
    }
}
