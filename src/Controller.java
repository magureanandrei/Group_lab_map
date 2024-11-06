import Models.*;

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
        List<Passenger> allPassengersByFlight = flightService.getPassengersByFlight(flightID);
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
    public void viewAllReservation(){
        List<Reservation> allReservations = flightService.getAllReservations();
        System.out.println("All Reservations:\n");
        for (Reservation reservation : allReservations) {
            System.out.println(reservation.toString() + " \n");
        }
    }

    public void viewAllTickets(){
        List<Ticket> allTickets = flightService.getAllTickets();
        System.out.println("All Tickets:\n");
        for (Ticket ticket : allTickets) {
            System.out.println(ticket.toString() + " \n");
        }
    }
    public void viewAllPayments(){
        List<Payment> allPayments = flightService.getAllPayments();
        System.out.println("All Payments:\n");
        for (Payment payment : allPayments) {
            System.out.println(payment.toString() + " \n");
        }
    }

    public void deletePassenger(Integer passengerID) {
        Passenger p = null;
        for(Passenger passenger : flightService.getAllPassengers()){
            if(passenger.getID().equals(passengerID)){
                p=passenger;
            }
        }
        if(p!=null){
            flightService.deletePassenger(passengerID);
            System.out.println("Removed Passenger " + p + ".");
        }
        else
            System.out.println("Passenger not found.");

    }
    public void deleteCabinCrew(Integer cabinID) {

        CabinCrew c = null;
        for(CabinCrew cabinCrew : flightService.getAllCabinCrews()){
            if(cabinCrew.getID().equals(cabinID)){
                c=cabinCrew;
            }
        }
        if(c!=null){
            flightService.deleteCabinCrew(cabinID);
            System.out.println("Removed Cabin Crew " + c + ".");
        }
        else
            System.out.println("Cabin crew not found.");

    }
    public void deleteFlight(Integer flightID) {
        Flight f = null;
        for(Flight flight : flightService.getAllFlights()){
            if(flight.getID().equals(flightID)){
                f=flight;
            }
        }
        if(f!=null){
            flightService.deleteFlight(flightID);
            System.out.println("Removed Flight " + f + ".");
        }
        else
            System.out.println("Flight not found.");

    }
    public void deletePilot(Integer pilotID) {
        Pilot p = null;
        for(Pilot pilot: flightService.getPilots())
            if(pilot.getID().equals(pilotID)){
                p=pilot;
            }
        if(p!=null){
            flightService.deletePilot(pilotID);
            System.out.println("Removed Pilot " + p + ".");
        }
        else
            System.out.println("Pilot not found.");

    }
    public void updatePassenger(Integer passengerID,String newName, String newEmail, String newTo, String newFrom){
        flightService.updatePassenger(passengerID, newName, newEmail, newTo, newFrom);
        System.out.println("Updated Passenger: " + newName + ".");
    }
    public void updateCabinCrew(Integer cabinID, String newName, String newEmail, String newProfesion){
        flightService.updateCabinCrew(cabinID, newName, newEmail, newProfesion);
        System.out.println("Updated Cabin Crew: " + newName + ".");
    }
    public void updateFlight(Integer flightID,String newFrom, String newTo, Integer pilotID, Integer airplaneID){
        flightService.updateFlight(flightID, newFrom, newTo, pilotID, airplaneID);
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
    public void createFlight(String from, String to, Integer pilotID, Integer airplaneID) {
        flightService.createFlight(from, to, pilotID, airplaneID);
        System.out.println("Flight was created");
    }

    public void createReservation(String date, Integer paymentID, Integer passengerID, Integer flightID) {
        flightService.createReservation(date, paymentID, passengerID, flightID);
        System.out.println("Reservation was created");
    }

    public void createPayment(String description, double amount, Integer passengerID) {
        flightService.createPayment(description, amount, passengerID);
        System.out.println("Payment was created");
    }

    public void createTicket(String title, String description, Integer paymentID) {
        flightService.createTicket(title, description, paymentID);
        System.out.println("Ticket was created");
    }

    public void createAirplane(String model, Integer capacity, Boolean available) {
        flightService.createAirplane(model, capacity, available);
        System.out.println("Airplane was created");
    }

    public void deleteReservation(Integer reservationID) {
        Reservation r = null;
        for (Reservation reservation : flightService.getAllReservations()) {
            if (reservation.getID().equals(reservationID)) {
                r = reservation;
            }
        }
        if (r != null) {
            flightService.deleteReservation(reservationID);
            System.out.println("Removed Reservation " + r + ".");
        } else {
            System.out.println("Reservation not found.");
        }
    }

    public void deletePayment(Integer paymentID) {
        Payment p = null;
        for (Payment payment : flightService.getAllPayments()) {
            if (payment.getID().equals(paymentID)) {
                p = payment;
            }
        }
        if (p != null) {
            flightService.deletePayment(paymentID);
            System.out.println("Removed Payment " + p + ".");
        } else {
            System.out.println("Payment not found.");
        }
    }

    public void deleteTicket(Integer ticketID) {
        Ticket t = null;
        for (Ticket ticket : flightService.getAllTickets()) {
            if (ticket.getID().equals(ticketID)) {
                t = ticket;
            }
        }
        if (t != null) {
            flightService.deleteTicket(ticketID);
            System.out.println("Removed Ticket " + t + ".");
        } else {
            System.out.println("Ticket not found.");
        }
    }

    public void deleteAirplane(Integer airplaneID) {
        Airplane a = null;
        for (Airplane airplane : flightService.getAllAirplanes()) {
            if (airplane.getID().equals(airplaneID)) {
                a = airplane;
            }
        }
        if (a != null) {
            flightService.deleteAirplane(airplaneID);
            System.out.println("Removed Airplane " + a + ".");
        } else {
            System.out.println("Airplane not found.");
        }
    }

    public void updateReservation(Integer reservationID, String newDate) {
        flightService.updateReservation(reservationID, newDate);
        System.out.println("Updated Reservation: " + newDate + ".");
    }

    public void updatePayment(Integer paymentID, String newDescription, double newAmount) {
        flightService.updatePayment(paymentID, newDescription, newAmount);
        System.out.println("Updated Payment: " + newDescription + ".");
    }

    public void updateTicket(Integer ticketID, String newTitle, String newDescription) {
        flightService.updateTicket(ticketID, newTitle, newDescription);
        System.out.println("Updated Ticket: " + newTitle + ".");
    }

    public void updateAirplane(Integer airplaneID, String newModel, Integer newCapacity, Boolean newAvailable) {
        flightService.updateAirplane(airplaneID, newModel, newCapacity, newAvailable);
        System.out.println("Updated Airplane: " + newModel + ".");
    }
}