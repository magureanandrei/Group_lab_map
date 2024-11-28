package Models;

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
                "Name     : " + nume + "\n" +
                "Passenger ID : " + id + "\n" +
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

    /**
     *  Returns the header of the CSV file.
     * @return The header of the CSV file.
     */
    @Override
    public String[] getHeader() {
        return new String[]{"id", "nume", "email", "flight"};
    }

    /**
     * Returns the values of the Passenger class.
     * @return The values of the Passenger class.
     */
    @Override
    public String toCSV() {
        return String.join(":",  nume, String.valueOf(id), email,
                flight != null ? flight.toCSV() : "");
    }

    /**
     * Creates a new Passenger from a CSV line.
     * @param csvLine The CSV line to create the Passenger from.
     * @return The Passenger created from the CSV line.
     */

    public static Passenger fromCSV(String csvLine) {
        String[] parts = csvLine.split(":");
        Pair flight = null;
        if (!parts[3].isEmpty()) {
            String[] pairParts = parts[3].split(";");
            if (pairParts.length == 2) {
                flight = new Pair(pairParts[0], pairParts[1]);
            }
        }


        return new Passenger(
                parts[0],
                Integer.parseInt(parts[1]),
                parts[2],
                flight);
    }

}
