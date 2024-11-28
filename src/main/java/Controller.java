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
     * Constructs a new main.java.Controller with the given main.java.Service.
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
    public void viewPassengersByFlight(Integer flightID) {
        try {
            if (flightID == null || flightID <= 0) {
                throw new IllegalArgumentException("Invalid flight ID. Please provide a positive integer.");
            }
            List<Passenger> allPassengersByFlight = flightService.getPassengersByFlight(flightID);
            if (allPassengersByFlight == null || allPassengersByFlight.isEmpty()) {
                System.out.println("No passengers found for flight ID: " + flightID);
                return;
            }
            System.out.println("Passengers By Flight:\n");
            for (Passenger passenger : allPassengersByFlight) {
                System.out.println(passenger.toString() + " \n");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Input Error: " + e.getMessage());
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
        try{
            if (passengerID == null || passengerID <= 0) {
                throw new IllegalArgumentException("Invalid flight ID. Please provide a positive integer.");
            }
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
        catch (IllegalArgumentException e) {
            System.err.println("Input Error: " + e.getMessage());
        }
    }
    /**
     * Deletes a cabin crew member.
     *
     * @param cabinID The unique identifier of the cabin crew member to delete.
     */
    public void deleteCabinCrew(Integer cabinID) {

        try {
            if (cabinID == null || cabinID <= 0) {
                throw new IllegalArgumentException("Invalid flight ID. Please provide a positive integer.");
            }
            CabinCrew c = null;
            for (CabinCrew cabinCrew : flightService.getAllCabinCrews()) {
                if (cabinCrew.getID().equals(cabinID)) {
                    c = cabinCrew;
                }
            }
            if (c != null) {
                flightService.deleteCabinCrew(cabinID);
                System.out.println("Removed Cabin Crew " + c + ".");
            } else
                System.out.println("Cabin crew not found.");
        }
        catch (IllegalArgumentException e) {
            System.err.println("Input Error: " + e.getMessage());
        }
    }
    /**
     * Deletes a flight.
     *
     * @param flightID The unique identifier of the flight to delete.
     */
    public void deleteFlight(Integer flightID) {

        try {
            if (flightID == null || flightID <= 0) {
                throw new IllegalArgumentException("Invalid flight ID. Please provide a positive integer.");
            }
            Flight f = null;
            for (Flight flight : flightService.getAllFlights()) {
                if (flight.getID().equals(flightID)) {
                    f = flight;
                }
            }
            if (f != null) {
                flightService.deleteFlight(flightID);
                System.out.println("Removed Flight " + f + ".");
            } else
                System.out.println("Flight not found.");
        }
        catch (IllegalArgumentException e) {
            System.err.println("Input Error: " + e.getMessage());
        }
    }
    /**
     * Deletes a pilot.
     *
     * @param pilotID The unique identifier of the pilot to delete.
     */
    public void deletePilot(Integer pilotID) {

        try {
            if (pilotID == null || pilotID <= 0) {
                throw new IllegalArgumentException("Invalid flight ID. Please provide a positive integer.");
            }
            Pilot p = null;
            for (Pilot pilot : flightService.getPilots())
                if (pilot.getID().equals(pilotID)) {
                    p = pilot;
                }
            if (p != null) {
                flightService.deletePilot(pilotID);
                System.out.println("Removed Pilot " + p + ".");
            } else
                System.out.println("Pilot not found.");
        }
        catch (IllegalArgumentException e) {
            System.err.println("Input Error: " + e.getMessage());
        }
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
    public void updatePassenger(Integer passengerID, String newName, String newEmail, String newTo, String newFrom) {
        try {
            if (passengerID == null || passengerID <= 0) {
                throw new IllegalArgumentException("Invalid passenger ID. Please provide a positive integer.");
            }
            if (newName == null || newName.isEmpty()) {
                throw new IllegalArgumentException("Invalid name. Please provide a non-empty name.");
            }
            if (newEmail == null || newEmail.isEmpty()) {
                throw new IllegalArgumentException("Invalid email. Please provide a non-empty email.");
            }
            if (newTo == null || newTo.isEmpty()) {
                throw new IllegalArgumentException("Invalid destination. Please provide a non-empty destination.");
            }
            if (newFrom == null || newFrom.isEmpty()) {
                throw new IllegalArgumentException("Invalid origin. Please provide a non-empty origin.");
            }
            flightService.updatePassenger(passengerID, newName, newEmail, newTo, newFrom);
            System.out.println("Updated Passenger: " + newName + ".");
        } catch (IllegalArgumentException e) {
            System.err.println("Input Error: " + e.getMessage());
        }
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

        try {
            if (cabinID == null || cabinID <= 0) {
                throw new IllegalArgumentException("Invalid flight ID. Please provide a positive integer.");
            }
            if (newName == null || newName.isEmpty()) {
                throw new IllegalArgumentException("Invalid name. Please provide a non-empty name.");
            }
            if (newEmail == null || newEmail.isEmpty()) {
                throw new IllegalArgumentException("Invalid email. Please provide a non-empty email.");
            }
            if (newProfesion == null || newProfesion.isEmpty()) {
                throw new IllegalArgumentException("Invalid profession. Please provide a non-empty profession.");
            }
            flightService.updateCabinCrew(cabinID, newName, newEmail, newProfesion);
            System.out.println("Updated Cabin Crew: " + newName + ".");
        }
        catch (IllegalArgumentException e) {
            System.err.println("Input Error: " + e.getMessage());
        }
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

        try {
            if(flightID == null || flightID <0)
            {
                throw new IllegalArgumentException("Invalid flight ID. Please provide a positive integer.");
            }
            if(newFrom == null || newFrom.isEmpty())
            {
                throw new IllegalArgumentException("Invalid origin. Please provide a non-empty origin.");
            }
            if(newTo == null || newTo.isEmpty())
            {
                throw new IllegalArgumentException("Invalid destination. Please provide a non-empty destination.");
            }
            if(pilotID == null || pilotID <0)
            {
                throw new IllegalArgumentException("Invalid pilot ID. Please provide a positive integer.");
            }
            if(airplaneID == null || airplaneID <0)
            {
                throw new IllegalArgumentException("Invalid airplane ID. Please provide a positive integer.");
            }
            flightService.updateFlight(flightID, newFrom, newTo, pilotID, airplaneID);
            System.out.println("Updated Flight " + newFrom + "," + newTo + ".");
        }
        catch (IllegalArgumentException e) {
            System.err.println("Input Error: " + e.getMessage());
        }

    }

    /**
     * Updates a pilot's details.
     *
     * @param pilotID   The unique identifier of the pilot.
     * @param newName   The new name of the pilot.
     * @param newEmail  The new email of the pilot.
     */
    public void updatePilot(Integer pilotID,String newName, String newEmail){

        try {
            if (pilotID == null || pilotID <= 0) {
                throw new IllegalArgumentException("Invalid flight ID. Please provide a positive integer.");
            }
            if (newName == null || newName.isEmpty()) {
                throw new IllegalArgumentException("Invalid name. Please provide a non-empty name.");
            }
            if (newEmail == null || newEmail.isEmpty()) {
                throw new IllegalArgumentException("Invalid email. Please provide a non-empty email.");
            }
            flightService.updatePilot(pilotID, newName, newEmail);
            System.out.println("Updated Pilot " + newName + ".");
        }
        catch (IllegalArgumentException e) {
            System.err.println("Input Error: " + e.getMessage());
        }
    }
    /**
     * Creates a new pilot.
     *
     * @param nume       The name of the pilot.
     * @param email      The email of the pilot.
     * @param availibility  The availability status of the pilot.
     */
    public void createPilot(String nume, String email, Boolean availibility){

        try {
            if (nume == null || nume.isEmpty()) {
                throw new IllegalArgumentException("Invalid name. Please provide a non-empty name.");
            }
            if (email == null || email.isEmpty()) {
                throw new IllegalArgumentException("Invalid email. Please provide a non-empty email.");
            }
            flightService.createPilot(nume, email, availibility);
            System.out.println("Pilot was created");
        }
        catch (IllegalArgumentException e) {
            System.err.println("Input Error: " + e.getMessage());
        }
    }
    /**
     * Creates a new cabin crew member.
     *
     * @param nume       The name of the cabin crew member.
     * @param email      The email of the cabin crew member.
     * @param profession The profession of the cabin crew member (e.g., flight attendant).
     */
    public void createCabinCrew(String nume, String email, String profession){

        try {
            if (nume == null || nume.isEmpty()) {
                throw new IllegalArgumentException("Invalid name. Please provide a non-empty name.");
            }
            if (email == null || email.isEmpty()) {
                throw new IllegalArgumentException("Invalid email. Please provide a non-empty email.");
            }
            if (profession == null || profession.isEmpty()) {
                throw new IllegalArgumentException("Invalid profession. Please provide a non-empty profession.");
            }
            flightService.createCabinCrew(nume, email, profession);
            System.out.println("Cabin Crew was created");
        }
        catch (IllegalArgumentException e) {
            System.err.println("Input Error: " + e.getMessage());
        }
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

        try {
            if (passengerName == null || passengerName.isEmpty()) {
                throw new IllegalArgumentException("Invalid name. Please provide a non-empty name.");
            }
            if (from == null || from.isEmpty()) {
                throw new IllegalArgumentException("Invalid origin. Please provide a non-empty origin.");
            }
            if (to == null || to.isEmpty()) {
                throw new IllegalArgumentException("Invalid destination. Please provide a non-empty destination.");
            }
            if (email == null || email.isEmpty()) {
                throw new IllegalArgumentException("Invalid email. Please provide a non-empty email.");
            }
            flightService.createPassenger(passengerName, from, to, email);
            System.out.println("Passenger was created");
        }
        catch (IllegalArgumentException e) {
            System.err.println("Input Error: " + e.getMessage());
        }
    }
    /**
     * Creates a new flight.
     *
     * @param from       The origin location of the flight.
     * @param to         The destination location of the flight.
     * @param pilotID    The ID of the pilot for the flight.
     * @param airplaneID The ID of the airplane used for the flight.
     * @param airportID  The ID of the airport of the flight.
     * @param date       The date of the flight.
     * @param amount     The amount of the flight.
     */
    public void createFlight(String from, String to, Integer pilotID, Integer airplaneID,Integer airportID, String date, double amount) {

        try {
            if (from == null || from.isEmpty()) {
                throw new IllegalArgumentException("Invalid origin. Please provide a non-empty origin.");
            }
            if (to == null || to.isEmpty()) {
                throw new IllegalArgumentException("Invalid destination. Please provide a non-empty destination.");
            }
            if (pilotID == null || pilotID <= 0) {
                throw new IllegalArgumentException("Invalid pilot ID. Please provide a positive integer.");
            }
            if (airplaneID == null || airplaneID <= 0) {
                throw new IllegalArgumentException("Invalid airplane ID. Please provide a positive integer.");
            }
            if (airportID == null || airportID <= 0) {
                throw new IllegalArgumentException("Invalid airport ID. Please provide a positive integer.");
            }
            if (date == null || date.isEmpty()) {
                throw new IllegalArgumentException("Invalid date. Please provide a non-empty date.");
            }
            if (amount <= 0) {
                throw new IllegalArgumentException("Invalid amount. Please provide a positive number.");
            }
            flightService.createFlight(from, to, pilotID, airplaneID, airportID, date, amount);
            System.out.println("Flight was created");
        }
        catch (IllegalArgumentException e) {
            System.err.println("Input Error: " + e.getMessage());
        }
    }

    /**
     * Creates a new reservation.
     *
     * @param date        The date of the reservation.
     * @param passengerID The unique identifier of the passenger associated with the reservation.
     * @param from        The location of the departure.
     * @param to          The destination.
     */
    public void createReservation(String date, Integer passengerID,String from,String to ) {

        try {
            if (date == null || date.isEmpty()) {
                throw new IllegalArgumentException("Invalid date. Please provide a non-empty date.");
            }
            if (passengerID == null || passengerID <= 0) {
                throw new IllegalArgumentException("Invalid passenger ID. Please provide a positive integer.");
            }
            if (from == null || from.isEmpty()) {
                throw new IllegalArgumentException("Invalid origin. Please provide a non-empty origin.");
            }
            if (to == null || to.isEmpty()) {
                throw new IllegalArgumentException("Invalid destination. Please provide a non-empty destination.");
            }
            flightService.createReservation(date, passengerID, from, to);
            System.out.println("Reservation was created");
        }
        catch (IllegalArgumentException e) {
            System.err.println("Input Error: " + e.getMessage());
        }
    }

    /**
     * Creates a new payment.
     *
     * @param description The description of the payment (e.g., ticket purchase, baggage fee).
     * @param amount      The amount of the payment.
     * @param passengerID The unique identifier of the passenger associated with the payment.
     */
    public void createPayment(String description, double amount, Integer passengerID) {

        try{
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Invalid description. Please provide a non-empty description.");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid amount. Please provide a positive number.");
        }
        if (passengerID == null || passengerID <= 0) {
            throw new IllegalArgumentException("Invalid passenger ID. Please provide a positive integer.");
        }
        flightService.createPayment(description, amount, passengerID);
        System.out.println("Payment was created");
        }
        catch (IllegalArgumentException e) {
            System.err.println("Input Error: " + e.getMessage());
        }
    }

    /**
     * Creates a new ticket.
     *
     * @param title       The title or type of the ticket (e.g., Economy, Business).
     * @param description The description of the ticket, including details like seating class or privileges.
     * @param paymentID   The unique identifier of the payment associated with the ticket.
     * @param date        The date of the fkight.
     */
    public void createTicket(String title, String description, Integer paymentID, String date) {

        try {
            if (title == null || title.isEmpty()) {
                throw new IllegalArgumentException("Invalid title. Please provide a non-empty title.");
            }
            if (description == null || description.isEmpty()) {
                throw new IllegalArgumentException("Invalid description. Please provide a non-empty description.");
            }
            if (paymentID == null || paymentID <= 0) {
                throw new IllegalArgumentException("Invalid payment ID. Please provide a positive integer.");
            }
            if (date == null || date.isEmpty()) {
                throw new IllegalArgumentException("Invalid date. Please provide a non-empty date.");
            }
            flightService.createTicket(title, description, paymentID, date);
            System.out.println("Ticket was created");
        }
        catch (IllegalArgumentException e) {
            System.err.println("Input Error: " + e.getMessage());
        }
    }

    /**
     * Creates a new airplane.
     *
     * @param model     The model of the airplane (e.g., Boeing 737).
     * @param capacity  The seating capacity of the airplane.
     * @param available The availability status of the airplane (true if available for flights, false otherwise).
     */
    public void createAirplane(String model, Integer capacity, Boolean available) {

        try {
            if (model == null || model.isEmpty()) {
                throw new IllegalArgumentException("Invalid model. Please provide a non-empty model.");
            }
            if (capacity <= 0) {
                throw new IllegalArgumentException("Invalid capacity. Please provide a positive number.");
            }
            flightService.createAirplane(model, capacity, available);
            System.out.println("Airplane was created");
        }
        catch (IllegalArgumentException e) {
            System.err.println("Input Error: " + e.getMessage());
        }
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

        try {
            if (name == null || name.isEmpty()) {
                throw new IllegalArgumentException("Invalid name. Please provide a non-empty name.");
            }
            if (location == null || location.isEmpty()) {
                throw new IllegalArgumentException("Invalid location. Please provide a non-empty location.");
            }
            if (number_of_airstrips <= 0) {
                throw new IllegalArgumentException("Invalid number of airstrips. Please provide a positive number.");
            }
            flightService.createAirport(name, location, number_of_airstrips, avaliable);
            System.out.println("Airport was created");
        }
        catch (IllegalArgumentException e) {
            System.err.println("Input Error: " + e.getMessage());
        }
    }


    /**
     * Deletes a reservation.
     *
     * @param reservationID The unique identifier of the reservation to delete.
     */
    public void deleteReservation(Integer reservationID) {

        try {
            if (reservationID == null || reservationID <= 0) {
                throw new IllegalArgumentException("Invalid reservation ID. Please provide a positive integer.");
            }
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
        catch (IllegalArgumentException e) {
            System.err.println("Input Error: " + e.getMessage());
        }
    }

    /**
     * Deletes a payment.
     *
     * @param paymentID The unique identifier of the payment to delete.
     */
    public void deletePayment(Integer paymentID) {

        try
        {
        if (paymentID == null || paymentID <= 0) {
            throw new IllegalArgumentException("Invalid payment ID. Please provide a positive integer.");
        }
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
        catch (IllegalArgumentException e) {
            System.err.println("Input Error: " + e.getMessage());
        }
    }

    /**
     * Deletes a ticket.
     *
     * @param ticketID The unique identifier of the ticket to delete.
     */
    public void deleteTicket(Integer ticketID) {
        try {
            if (ticketID == null || ticketID <= 0) {
                throw new IllegalArgumentException("Invalid ticket ID. Please provide a positive integer.");
            }
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
        catch (IllegalArgumentException e) {
            System.err.println("Input Error: " + e.getMessage());
        }
    }

    /**
     * Deletes a airplane.
     *
     * @param airplaneID The unique identifier of the airplane to delete.
     */
    public void deleteAirplane(Integer airplaneID) {

        try {
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
        catch (IllegalArgumentException e) {
            System.err.println("Input Error: " + e.getMessage());
        }
    }

    /**
     * Deletes an airport.
     *
     * @param airportID The unique identifier of the airport to delete.
     */
    public void deleteAirport(Integer airportID) {

        try {
            if (airportID == null || airportID <= 0) {
                throw new IllegalArgumentException("Invalid airport ID. Please provide a positive integer.");
            }
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
        catch (IllegalArgumentException e) {
            System.err.println("Input Error: " + e.getMessage());
        }
    }

    /**
     * Updates a reservation's details.
     *
     * @param reservationID The unique identifier of the reservation.
     * @param newDate       The new date for the reservation.
     */
    public void updateReservation(Integer reservationID, String newDate) {

        try {
            if (reservationID == null || reservationID <= 0) {
                throw new IllegalArgumentException("Invalid reservation ID. Please provide a positive integer.");
            }
            if (newDate == null || newDate.isEmpty()) {
                throw new IllegalArgumentException("Invalid date. Please provide a non-empty date.");
            }
            flightService.updateReservation(reservationID, newDate);
            System.out.println("Updated Reservation: " + newDate + ".");
        }
        catch (IllegalArgumentException e) {
            System.err.println("Input Error: " + e.getMessage());
        }
    }

    /**
     * Updates a payment's details.
     *
     * @param paymentID       The unique identifier of the payment.
     * @param newDescription  The new description for the payment (e.g., ticket fee, upgrade fee).
     * @param newAmount       The new amount for the payment.
     */
    public void updatePayment(Integer paymentID, String newDescription, double newAmount) {

        try {
            if (paymentID == null || paymentID <= 0) {
                throw new IllegalArgumentException("Invalid payment ID. Please provide a positive integer.");
            }
            flightService.updatePayment(paymentID, newDescription, newAmount);
            System.out.println("Updated Payment: " + newDescription + ".");
        }
        catch (IllegalArgumentException e) {
            System.err.println("Input Error: " + e.getMessage());
        }
    }

    /**
     * Updates a ticket's details.
     *
     * @param ticketID       The unique identifier of the ticket.
     * @param newTitle       The new title or class for the ticket (e.g., Economy, Business).
     * @param newDescription The new description of the ticket, including details like privileges or restrictions.
     */
    public void updateTicket(Integer ticketID, String newTitle, String newDescription) {

        try {
            if (ticketID == null || ticketID <= 0) {
                throw new IllegalArgumentException("Invalid ticket ID. Please provide a positive integer.");
            }
            if (newTitle == null || newTitle.isEmpty()) {
                throw new IllegalArgumentException("Invalid title. Please provide a non-empty title.");
            }
            if (newDescription == null || newDescription.isEmpty()) {
                throw new IllegalArgumentException("Invalid description. Please provide a non-empty description.");
            }
            flightService.updateTicket(ticketID, newTitle, newDescription);
            System.out.println("Updated Ticket: " + newTitle + ".");
        }
        catch (IllegalArgumentException e) {
            System.err.println("Input Error: " + e.getMessage());
        }
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

        try {
            if (airplaneID == null || airplaneID <= 0) {
                throw new IllegalArgumentException("Invalid airplane ID. Please provide a positive integer.");
            }
            if (newModel == null || newModel.isEmpty()) {
                throw new IllegalArgumentException("Invalid model. Please provide a non-empty model.");
            }
            if (newCapacity <= 0) {
                throw new IllegalArgumentException("Invalid capacity. Please provide a positive number.");
            }
            flightService.updateAirplane(airplaneID, newModel, newCapacity, newAvailable);
            System.out.println("Updated Airplane: " + newModel + ".");
        }
        catch (IllegalArgumentException e) {
            System.err.println("Input Error: " + e.getMessage());
        }
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

        try {
            if (airportID == null || airportID <= 0) {
                throw new IllegalArgumentException("Invalid airport ID. Please provide a positive integer.");
            }
            if (newName == null || newName.isEmpty()) {
                throw new IllegalArgumentException("Invalid name. Please provide a non-empty name.");
            }
            if (newLocation == null || newLocation.isEmpty()) {
                throw new IllegalArgumentException("Invalid location. Please provide a non-empty location.");
            }
            if (newNumberOfAirstrips <= 0) {
                throw new IllegalArgumentException("Invalid number of airstrips. Please provide a positive number.");
            }
            flightService.updateAirport(airportID, newName, newLocation, newNumberOfAirstrips, newAvaliable);
            System.out.println("Updated Airport: " + newName + ".");
        }
        catch (IllegalArgumentException e) {
            System.err.println("Input Error: " + e.getMessage());
        }
    }

    /**
     * Gets a ticket by the ID given from the user
     *
     * @param ticketID The unique identifier of the ticket.
     */
    public void getTicket(Integer ticketID){

        try {
            if (ticketID == null || ticketID <= 0) {
                throw new IllegalArgumentException("Invalid ticket ID. Please provide a positive integer.");
            }
            Ticket ticket = flightService.getTicket(ticketID);
            System.out.println(ticket.toString());
        }
        catch (IllegalArgumentException e) {
            System.err.println("Input Error: " + e.getMessage());
        }
    }

    /**
     * Gets a reservation by the ID given from the user
     *
     * @param reservationID The unique identifier of the reservation.
     */
    public void getReservation(Integer reservationID){

        try {
            if (reservationID == null || reservationID <= 0) {
                throw new IllegalArgumentException("Invalid reservation ID. Please provide a positive integer.");
            }
            Reservation reservation = flightService.getReservation(reservationID);
            System.out.println(reservation.toString());
        }
        catch (IllegalArgumentException e) {
            System.err.println("Input Error: " + e.getMessage());
        }
    }

    /**
     * Retrieves and displays all available flights for a passenger on a specified date.
     *
     * @param passengerID The unique identifier of the passenger.
     * @param date        The date for which to retrieve available flights.
     * @return            TRUE if flights matching the requirements are found;FALSE if no flights are available.
     */
    public Boolean getAllAvalibleFlightsForPassenger(Integer passengerID, String date)
    {
        try {
            if (passengerID == null || passengerID <= 0) {
                throw new IllegalArgumentException("Invalid passenger ID. Please provide a positive integer.");
            }
            if (date == null || date.isEmpty()) {
                throw new IllegalArgumentException("Invalid date. Please provide a non-empty date.");
            }

            ArrayList<Flight> possibleFlights = flightService.getAllAvalibleFlightsForPassenger(passengerID, date);
            if (possibleFlights.isEmpty()) {
                System.out.println("Unfortunately there aren't any flights with your requirements available." + "\n" +
                        "A reservation has been made and you will be notified once a flight is available");
                return Boolean.FALSE;
            } else {
                System.out.println("All possible flights: ");
                for (Flight flight : possibleFlights) {
                    System.out.println(flight.toString() + "\n");
                }
                return Boolean.TRUE;
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Input Error: " + e.getMessage());
            return Boolean.FALSE;
        }
    }

    /**
     * Books a seat for a passenger on a specific flight and displays the ticket information.
     *
     * @param passengerID The unique identifier of the passenger.
     * @param date        The date of the flight.
     * @param flightID    The unique identifier of the flight.
     * @param paymentType The type of payment used for the booking (e.g., credit card, cash).
     */
    public void bookseat(Integer passengerID,String date,Integer flightID,String paymentType){

        try {
            if (passengerID == null || passengerID <= 0) {
                throw new IllegalArgumentException("Invalid passenger ID. Please provide a positive integer.");
            }
            if (date == null || date.isEmpty()) {
                throw new IllegalArgumentException("Invalid date. Please provide a non-empty date.");
            }
            if (flightID == null || flightID <= 0) {
                throw new IllegalArgumentException("Invalid flight ID. Please provide a positive integer.");
            }
            if (paymentType == null || paymentType.isEmpty()) {
                throw new IllegalArgumentException("Invalid payment type. Please provide a non-empty payment type.");
            }
            Ticket t = flightService.bookSeat(date, passengerID, flightID, paymentType);
            System.out.println("Your Ticket Information: " + "\n" + t.toString());
        }
        catch (IllegalArgumentException e) {
            System.err.println("Input Error: " + e.getMessage());
        }
    }

    /**
     * Retrieves a passenger by their unique identifier.
     *
     * @param passengerID The unique identifier of the passenger.
     * @return The {@code Passenger} object with the specified ID, or {@code null} if no such passenger exists.
     */
    public Passenger getPassengerByID(Integer passengerID){
        try {
            if (passengerID == null || passengerID <= 0) {
                throw new IllegalArgumentException("Invalid passenger ID. Please provide a positive integer.");
            }
            return flightService.getPassengerByID(passengerID);
        }
        catch (IllegalArgumentException e) {
            System.err.println("Input Error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Retrieves a pilot by their unique identifier.
     */
    public void sortFlightsByDate(){
        ArrayList<Flight> sortedFlights=flightService.sortFlightsByDate();
        System.out.println("All Flights sorted by date: ");
        for (Flight flight : sortedFlights)
            System.out.println(flight.toString() + "\n");
    }

    /**
     * Filters cabin crew members by profession.
     * @param profession The profession to filter cabin crew members by.
     */
    public void filterCabinCrewByProfession(String profession){

        try {
            if (profession == null || profession.isEmpty()) {
                throw new IllegalArgumentException("Invalid profession. Please provide a non-empty profession.");
            }
            ArrayList<CabinCrew> filteredCabinCrew = flightService.filterCabinCrewByProfession(profession);
            System.out.println("All Cabin Crew filtered by profession: ");
            for (CabinCrew cabinCrew : filteredCabinCrew)
                System.out.println(cabinCrew.toString() + "\n");
        }
        catch (IllegalArgumentException e) {
            System.err.println("Input Error: " + e.getMessage());
        }
    }
    /**
     * Retrieves and displays all available flights for a passenger on a specified date.
     *
     * @param passengerID The unique identifier of the passenger.
     * @param date        The date for which to retrieve available flights.
     * @return            TRUE if flights matching the requirements are found;FALSE if no flights are available.
     */
    public void bookSeatByFlight(String date, Integer passengerID ,Integer flightID,String paymentType, String from, String to){

        try {
            if (date == null || date.isEmpty()) {
                throw new IllegalArgumentException("Invalid date. Please provide a non-empty date.");
            }
            if (passengerID == null || passengerID <= 0) {
                throw new IllegalArgumentException("Invalid passenger ID. Please provide a positive integer.");
            }
            if (flightID == null || flightID <= 0) {
                throw new IllegalArgumentException("Invalid flight ID. Please provide a positive integer.");
            }
            if (paymentType == null || paymentType.isEmpty()) {
                throw new IllegalArgumentException("Invalid payment type. Please provide a non-empty payment type.");
            }
            if (from == null || from.isEmpty()) {
                throw new IllegalArgumentException("Invalid origin. Please provide a non-empty origin.");
            }
            if (to == null || to.isEmpty()) {
                throw new IllegalArgumentException("Invalid destination. Please provide a non-empty destination.");
            }
            Ticket t = flightService.bookSeatByFlight(date, passengerID, flightID, paymentType, from, to);
            System.out.println("Your Ticket Information: " + "\n" + t.toString());
        }
        catch (IllegalArgumentException e) {
            System.err.println("Input Error: " + e.getMessage());
        }
    }

    /**
     * Sorts airplanes by capacity.
     */
    public void sortAirplanesByCapacity(){
        ArrayList<Airplane> sortedAirplanes = flightService.sortAirplanesByCapacity();
        System.out.println("Sorted Airplanes by capacity:\n");
        for (Airplane airplane : sortedAirplanes) {
            System.out.println(airplane.toString() + " \n");
        }
    }

    /**
     * Filters flights by amount.
     * @param amount The amount to filter flights by.
     */
    public void filterFlightsByAmount(double amount){

        try {
            if (amount <= 0) {
                throw new IllegalArgumentException("Invalid amount. Please provide a positive number.");
            }
            ArrayList<Flight> filteredFlights = flightService.filterFlightsByAmount(amount);
            System.out.println("Filtered Flights by amount:\n");
            for (Flight flight : filteredFlights) {
                System.out.println(flight.toString() + " \n");
            }
        }
        catch (IllegalArgumentException e) {
            System.err.println("Input Error: " + e.getMessage());
        }
    }

}