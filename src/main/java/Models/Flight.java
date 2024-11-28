package Models;

/**
 * Represents a flight with a unique identifier, assigned pilot and airplane, as well as the departure and destination locations.
 */
public class Flight implements HasID{
    public Integer flightID;
    public String from;
    public String to;
    public Pilot pilot;
    public Airplane airplane;
    public Airport airport;
    public String date;
    public double amount;
    /**
     * Constructs a new Flight with the specified details.
     *
     * @param flightID The unique identifier for the flight.
     * @param from     The departure location of the flight.
     * @param to       The destination location of the flight.
     * @param pilot    The pilot assigned to the flight.
     * @param airplane The airplane assigned to the flight.
     * @param airport  The airport of the flight
     * @param date     The date of the flight.
     * @param amount     The amount of the flight.
     */
    public Flight(Integer flightID, String from, String to, Pilot pilot,Airplane airplane,Airport airport, String date, double amount) {
        this.flightID = flightID;
        this.from = from;
        this.to = to;
        this.pilot = pilot;
        this.airplane = airplane;
        this.airport = airport;
        this.date=date;
        this.amount=amount;
    }

    /**
     * Returns a string representation of the Flight object, including flight ID, departure, destination, pilot, and airplane.
     *
     * @return A string representation of the Flight.
     */
    @Override
    public String toString() {
        return "Flight Details:\n" +
                "Flight ID  : " + flightID + "\n" +
                "From       : " + from + "\n" +
                "To         : " + to + "\n" +
                "Amount     : " + amount + "\n" +
                "-----------------------------\n" +
                "Pilot      : " + pilot + "\n" +
                "Airplane   : " + airplane + "\n" +
                "Date       : " + date + "\n" +
                "Airport    : " + airport + "\n" +
                "-----------------------------";
    }

    /**
     * Gets the departure location of the flight.
     *
     * @return The departure location.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Sets the departure location of the flight.
     *
     * @param from The new departure location.
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * Gets the destination location of the flight.
     *
     * @return The destination location.
     */
    public String getTo() {
        return to;
    }

    /**
     * Sets the destination location of the flight.
     *
     * @param to The new destination location.
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * Gets the pilot assigned to the flight.
     *
     * @return The pilot.
     */
    public Pilot getPilot() {
        return pilot;
    }

    /**
     * Sets the pilot assigned to the flight.
     *
     * @param pilot The new pilot for the flight.
     */
    public void setPilot(Pilot pilot) {
        this.pilot = pilot;
    }

    /**
     * Gets the airplane assigned to the flight.
     *
     * @return The airplane.
     */
    public Airplane getAirplane() {
        return airplane;
    }

    /**
     * Sets the airplane assigned to the flight.
     *
     * @param airplane The new airplane for the flight.
     */
    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    /**
     * Gets the unique identifier for the flight.
     *
     * @return The flight ID.
     */
    @Override
    public Integer getID() {
        return flightID;
    }

    /**
     * Gets the date of the flight.
     *
     * @return The flight date.
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date assigned to the flight.
     *
     * @param date The new date for the flight.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets the airport of the flight.
     *
     * @return The flight airport.
     */
    public Airport getAirport() {
        return airport;
    }

    /**
     * Sets the airport assigned to the flight.
     *
     * @param airport The new date for the flight.
     */
    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    /**
     * Gets the amount of the flight.
     *
     * @return The flight amount.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Sets the amount of the flight.
     *
     * @param amount The new amount for the flight.
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Gets the header for the CSV representation of the Flight object.
     * @return  The header for the CSV representation of the Flight object.
     */
    @Override
    public String[] getHeader() {
        return new String[]{"flightID", "from", "to", "pilot", "airplane", "airport", "date", "amount"};
    }

    /**
     * Returns a CSV representation of the Flight object, including flight ID, departure, destination, pilot, and airplane.
     *
     * @return A CSV representation of the Flight.
     */
    @Override
    public String toCSV() {
        return String.join(",",
                String.valueOf(flightID),
                from,
                to,
                pilot != null ? pilot.toCSV() : "",
                airplane != null ? airplane.toCSV() : "",
                airport != null ? airport.toCSV() : "",
                date,
                String.valueOf(amount));
    }

    /**
     * Creates a new Flight object from a CSV representation.
     *
     * @param csvLine The CSV representation of the Flight.
     * @return A new Flight object.
     */

    public static Flight fromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        Pilot pilot = null;
        Airplane airplane =null;
        Airport airport=null;
        if (!parts[3].isEmpty()) {
            String[] pilotParts = parts[3].split(":");
            pilot = Pilot.fromCSV(parts[3]);
        }
        if (!parts[4].isEmpty()) {
            String[] airplaneParts = parts[4].split(";");
            airplane = Airplane.fromCSV(parts[4]);
        }

        if (!parts[5].isEmpty()) {
            String[] airportParts = parts[5].split("%");
            airport = Airport.fromCSV(parts[5]);
        }

        return new Flight(
                Integer.parseInt(parts[0]),
                parts[1],
                parts[2],
                pilot,
                airplane,
                airport,
                parts[6],
                Double.parseDouble(parts[7])
            );

    }
}

