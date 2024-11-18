package Models;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * Represents a passenger with flight information.
 */
public class Passenger extends Person implements HasID {
    private Pair flight;

    /**
     * Constructs a new Passenger with the specified details.
     *
     * @param nume   The name of the passenger.
     * @param id     The unique identifier for the passenger.
     * @param email  The email address of the passenger.
     * @param flight The flight information associated with the passenger.
     */
    public Passenger(String nume, Integer id, String email, Pair flight) {
        super(nume, id, email);
        this.flight=flight;
    }

    /**
     * Gets the flight information associated with the passenger.
     *
     * @return The flight information as a Pair (departure and destination locations).
     */
    public Pair getFlight() {
        return flight;
    }

    /**
     * Sets the flight information for the passenger.
     *
     * @param flight The new flight information as a Pair.
     */
    public void setFlight(Pair flight) {
        this.flight = flight;
    }

    /**
     * Returns a string representation of the Passenger object, including flight information, ID, name, and email.
     *
     * @return A string representation of the Passenger.
     */
    @Override
    public String toString() {
        return "Passenger Details:\n" +
                "Passenger ID : " + id + "\n" +
                "Name     : " + nume + "\n" +
                "Email    : " + email + "\n" +
                "Flight   : " + flight + "\n" +
                "-----------------------------";
    }

    /**
     * Gets the unique identifier for the passenger.
     *
     * @return The passenger's ID.
     */
    @Override
    public Integer getID() {
        return getId();
    }

    @Override
    public String[] getHeader() {
        return new String[]{"id", "nume", "email", "flight"};
    }

    @Override
    public String toCSV() {
        return String.join(",", String.valueOf(id), nume, email,
                flight != null ? flight.toCSV() : "");
    }


    public static Passenger fromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        Pair flight = parts[3].isEmpty() ? null : Pair.fromCSV(parts[3]);

        return new Passenger(
                parts[0],
                Integer.parseInt(parts[1]),
                parts[2],
                flight);
    }


}
