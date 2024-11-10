import Models.*;

import java.util.ArrayList;
import java.util.List;
// did the implementation for all, delete, update, get Functions
//create functions from passenger, pilot and cabin crew are done
//we still need to do the create functions for Flight, reservation, payment and ticket
/**
 * A controller class that handles the business logic for the  flight system.
 */
public class Controller {
    private final Service flightService;

    /**
     * Constructs a new Controller with the given Service.
     *
     * @param flightService The service that provides the business logic for the flight system.
     */
    public Controller(Service flightService) {
        this.flightService = flightService;
    }
    /**
     * Displays all available pilots.
     */
    public void viewAvailablePilots() {
        //1- availible 2- not available
        ArrayList<Pilot> availablePilots = flightService.getAvailablePilots();
        System.out.println("Available Pilots:\n");
        for (Pilot pilot : availablePilots) {
            System.out.println(pilot.toString() + " \n");
        }
    }
    /**
     * Displays all passengers.
     */
    public void viewPassengers() {
        List<Passenger> allPassengers = flightService.getAllPassengers();
        System.out.println("Passengers:\n");
        for (Passenger passenger : allPassengers) {
            System.out.println(passenger.toString() + " \n");
        }
    }
    /**
     * Displays all cabin crew members.
     */
    public void viewCabinCrew() {
        List<CabinCrew> allCabinCrew = flightService.getAllCabinCrews();
        System.out.println("Cabin Crew:\n");
        for (CabinCrew cabinCrew : allCabinCrew) {
            System.out.println(cabinCrew.toString() + " \n");
        }
    }
    /**
     * Displays all passengers for a specific flight.
     *
     * @param flightID The unique identifier of the flight.
     */
    public void viewPassengersByFlight(Integer flightID){
        List<Passenger> allPassengersByFlight = flightService.getPassengersByFlight(flightID);
        System.out.println("Passengers By Flight:\n");
        for (Passenger passenger : allPassengersByFlight) {
            System.out.println(passenger.toString() + " \n");
        }
    }
    /**
     * Displays all flights.
     */
    public void viewAllFlights(){
        List<Flight> allFlights = flightService.getAllFlights();
        System.out.println("All Flights:\n");
        for (Flight flight : allFlights) {
            System.out.println(flight.toString() + " \n");
        }
    }
    /**
     * Displays all reservations.
     */
    public void viewAllReservation(){
        List<Reservation> allReservations = flightService.getAllReservations();
        System.out.println("All Reservations:\n");
        for (Reservation reservation : allReservations) {
            System.out.println(reservation.toString() + " \n");
        }
    }

    /**
     * Displays all tickets.
     */
    public void viewAllTickets(){
        List<Ticket> allTickets = flightService.getAllTickets();
        System.out.println("All Tickets:\n");
        for (Ticket ticket : allTickets) {
            System.out.println(ticket.toString() + " \n");
        }
    }
    /**
     * Displays all payments.
     */
    public void viewAllPayments(){
        List<Payment> allPayments = flightService.getAllPayments();
        System.out.println("All Payments:\n");
        for (Payment payment : allPayments) {
            System.out.println(payment.toString() + " \n");
        }
    }

    /**
     * Displays all airplanes.
     */
    public void viewAllAirplanes(){
        List<Airplane> allPlanes = flightService.getAllAirplanes();
        System.out.println("All Airplanes:\n");
        for(Airplane airplane : allPlanes){
            System.out.println(airplane.toString() + " \n");
        }
    }

    /**
     * Displays all airports.
     */
    public void viewAllAirports(){
        List<Airport> allAirports = flightService.getAllAirports();
        System.out.println("All Airports:\n");
        for(Airport airport : allAirports){
            System.out.println(airport.toString() + " \n");
        }
    }

    /**
     * Deletes a passenger.
     *
     * @param passengerID The unique identifier of the passenger to delete.
     */
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
    /**
     * Deletes a cabin crew member.
     *
     * @param cabinID The unique identifier of the cabin crew member to delete.
     */
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
    /**
     * Deletes a flight.
     *
     * @param flightID The unique identifier of the flight to delete.
     */
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
    /**
     * Deletes a pilot.
     *
     * @param pilotID The unique identifier of the pilot to delete.
     */
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
    /**
     * Updates a passenger's details.
     *
     * @param passengerID The unique identifier of the passenger.
     * @param newName     The new name of the passenger.
     * @param newEmail    The new email of the passenger.
     * @param newTo       The new destination of the passenger.
     * @param newFrom     The new origin of the passenger.
     */
    public void updatePassenger(Integer passengerID,String newName, String newEmail, String newTo, String newFrom){
        flightService.updatePassenger(passengerID, newName, newEmail, newTo, newFrom);
        System.out.println("Updated Passenger: " + newName + ".");
    }
    /**
     * Updates a cabin crew member's details.
     *
     * @param cabinID      The unique identifier of the cabin crew.
     * @param newName      The new name of the cabin crew member.
     * @param newEmail     The new email of the cabin crew member.
     * @param newProfesion The new profession of the cabin crew member.
     */
    public void updateCabinCrew(Integer cabinID, String newName, String newEmail, String newProfesion){
        flightService.updateCabinCrew(cabinID, newName, newEmail, newProfesion);
        System.out.println("Updated Cabin Crew: " + newName + ".");
    }
    /**
     * Updates a flight's details.
     *
     * @param flightID  The unique identifier of the flight.
     * @param newFrom   The new origin location of the flight.
     * @param newTo     The new destination location of the flight.
     * @param pilotID   The ID of the new pilot assigned to the flight.
     * @param airplaneID The ID of the airplane for the flight.
     */
    public void updateFlight(Integer flightID,String newFrom, String newTo, Integer pilotID, Integer airplaneID){
        flightService.updateFlight(flightID, newFrom, newTo, pilotID, airplaneID);
        System.out.println("Updated Flight " + newFrom + ","+ newTo + ".");
    }

    /**
     * Updates a pilot's details.
     *
     * @param pilotID   The unique identifier of the pilot.
     * @param newName   The new name of the pilot.
     * @param newEmail  The new email of the pilot.
     */
    public void updatePilot(Integer pilotID,String newName, String newEmail){
        flightService.updatePilot(pilotID, newName, newEmail);
        System.out.println("Updated Pilot " + newName + ".");
    }
    /**
     * Creates a new pilot.
     *
     * @param nume       The name of the pilot.
     * @param email      The email of the pilot.
     * @param availibility  The availability status of the pilot.
     */
    public void createPilot(String nume, String email, Boolean availibility){
        flightService.createPilot(nume, email, availibility);
        System.out.println("Pilot was created");
    }
    /**
     * Creates a new cabin crew member.
     *
     * @param nume       The name of the cabin crew member.
     * @param email      The email of the cabin crew member.
     * @param profession The profession of the cabin crew member (e.g., flight attendant).
     */
    public void createCabinCrew(String nume, String email, String profession){
        flightService.createCabinCrew(nume, email, profession);
        System.out.println("Cabin Crew was created");
    }
    /**
     * Creates a new passenger.
     *
     * @param passengerName The name of the passenger.
     * @param from          The origin location of the passenger's trip.
     * @param to            The destination location of the passenger's trip.
     * @param email         The email of the passenger.
     */
    public void createPassenger(String passengerName, String from, String to, String email){
        flightService.createPassenger(passengerName, from, to, email);
        System.out.println("Passenger was created");
    }
    /**
     * Creates a new flight.
     *
     * @param from       The origin location of the flight.
     * @param to         The destination location of the flight.
     * @param pilotID    The ID of the pilot for the flight.
     * @param airplaneID The ID of the airplane used for the flight.
     * @param airportID  The ID of the airport of the flight.
     */
    public void createFlight(String from, String to, Integer pilotID, Integer airplaneID,Integer airportID) {
        flightService.createFlight(from, to, pilotID, airplaneID,airportID);
        System.out.println("Flight was created");
    }

    /**
     * Creates a new reservation.
     *
     * @param date        The date of the reservation.
     * @param paymentID   The unique identifier of the payment associated with the reservation.
     * @param passengerID The unique identifier of the passenger associated with the reservation.
     * @param from        The location of the departure.
     * @param to          The destination.
     */
    public void createReservation(String date, Integer paymentID, Integer passengerID,String from,String to ) {
        flightService.createReservation(date, paymentID, passengerID, from,to);
        System.out.println("Reservation was created");
    }

    /**
     * Creates a new payment.
     *
     * @param description The description of the payment (e.g., ticket purchase, baggage fee).
     * @param amount      The amount of the payment.
     * @param passengerID The unique identifier of the passenger associated with the payment.
     */
    public void createPayment(String description, double amount, Integer passengerID) {
        flightService.createPayment(description, amount, passengerID);
        System.out.println("Payment was created");
    }

    /**
     * Creates a new ticket.
     *
     * @param title       The title or type of the ticket (e.g., Economy, Business).
     * @param description The description of the ticket, including details like seating class or privileges.
     * @param paymentID   The unique identifier of the payment associated with the ticket.
     */
    public void createTicket(String title, String description, Integer paymentID) {
        flightService.createTicket(title, description, paymentID);
        System.out.println("Ticket was created");
    }

    /**
     * Creates a new airplane.
     *
     * @param model     The model of the airplane (e.g., Boeing 737).
     * @param capacity  The seating capacity of the airplane.
     * @param available The availability status of the airplane (true if available for flights, false otherwise).
     */
    public void createAirplane(String model, Integer capacity, Boolean available) {
        flightService.createAirplane(model, capacity, available);
        System.out.println("Airplane was created");
    }

    /**
     *  Creates a new airport.
     *
     * @param name                   The name of the airport.
     * @param location               The location of the airport.
     * @param number_of_airstrips    The number of Airstrips on the airport.
     * @param avaliable             The availability status of the airport (true if available for flights, false otherwise).
     */
    public void createAirport(String name, String location, Integer number_of_airstrips, Boolean avaliable){
        flightService.createAirport(name,location,number_of_airstrips,avaliable);
        System.out.println("Airport was created");
    }


    /**
     * Deletes a reservation.
     *
     * @param reservationID The unique identifier of the reservation to delete.
     */
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

    /**
     * Deletes a payment.
     *
     * @param paymentID The unique identifier of the payment to delete.
     */
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

    /**
     * Deletes a ticket.
     *
     * @param ticketID The unique identifier of the ticket to delete.
     */
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

    /**
     * Deletes a airplane.
     *
     * @param airplaneID The unique identifier of the airplane to delete.
     */
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

    /**
     * Deletes an airport.
     *
     * @param airportID The unique identifier of the airport to delete.
     */
    public void deleteAirport(Integer airportID) {
        Airport a = null;
        for (Airport airport : flightService.getAllAirports()) {
            if (airport.getID().equals(airportID)) {
                a = airport;
            }
        }
        if (a != null) {
            flightService.deleteAirport(airportID);
            System.out.println("Removed Airport " + a + ".");
        } else {
            System.out.println("Airport not found.");
        }
    }

    /**
     * Updates a reservation's details.
     *
     * @param reservationID The unique identifier of the reservation.
     * @param newDate       The new date for the reservation.
     */
    public void updateReservation(Integer reservationID, String newDate) {
        flightService.updateReservation(reservationID, newDate);
        System.out.println("Updated Reservation: " + newDate + ".");
    }

    /**
     * Updates a payment's details.
     *
     * @param paymentID       The unique identifier of the payment.
     * @param newDescription  The new description for the payment (e.g., ticket fee, upgrade fee).
     * @param newAmount       The new amount for the payment.
     */
    public void updatePayment(Integer paymentID, String newDescription, double newAmount) {
        flightService.updatePayment(paymentID, newDescription, newAmount);
        System.out.println("Updated Payment: " + newDescription + ".");
    }

    /**
     * Updates a ticket's details.
     *
     * @param ticketID       The unique identifier of the ticket.
     * @param newTitle       The new title or class for the ticket (e.g., Economy, Business).
     * @param newDescription The new description of the ticket, including details like privileges or restrictions.
     */
    public void updateTicket(Integer ticketID, String newTitle, String newDescription) {
        flightService.updateTicket(ticketID, newTitle, newDescription);
        System.out.println("Updated Ticket: " + newTitle + ".");
    }

    /**
     * Updates an airplane's details.
     *
     * @param airplaneID   The unique identifier of the airplane.
     * @param newModel     The new model of the airplane (e.g., Airbus A320).
     * @param newCapacity  The new seating capacity of the airplane.
     * @param newAvailable The new availability status of the airplane (true if available for flights, false otherwise).
     */
    public void updateAirplane(Integer airplaneID, String newModel, Integer newCapacity, Boolean newAvailable) {
        flightService.updateAirplane(airplaneID, newModel, newCapacity, newAvailable);
        System.out.println("Updated Airplane: " + newModel + ".");
    }

    /**
     *
     * @param airportID                 The unique identifier of the airport.
     * @param newName                   The new name of the airport.
     * @param newLocation               The new location of the airport.
     * @param newNumberOfAirstrips      The new number of Airstrips of the airport.
     * @param newAvaliable               The new availability status of the airport (true if available for flights, false otherwise).
     */
    public void updateAirport(Integer airportID, String newName, String newLocation,Integer newNumberOfAirstrips, Boolean newAvaliable){
        flightService.updateAirport(airportID,newName,newLocation,newNumberOfAirstrips,newAvaliable);
        System.out.println("Updated Airport: " + newName + ".");
    }

    /**
     * Gets a ticket by the ID given from the user
     *
     * @param ticketID The unique identifier of the ticket.
     */
    public void getTicket(Integer ticketID){
        Ticket ticket = flightService.getTicket(ticketID);
        System.out.println(ticket.toString());
    }

    /**
     * Gets a reservation by the ID given from the user
     *
     * @param reservationID The unique identifier of the reservation.
     */
    public void getReservation(Integer reservationID){
        Reservation reservation = flightService.getReservation(reservationID);
        System.out.println(reservation.toString());
    }
}