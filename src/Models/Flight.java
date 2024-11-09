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
    //public Airport airport; asta ar fi aeroportul de sosire
    //public String date;
    /**
     * Constructs a new Flight with the specified details.
     *
     * @param flightID The unique identifier for the flight.
     * @param from     The departure location of the flight.
     * @param to       The destination location of the flight.
     * @param pilot    The pilot assigned to the flight.
     * @param airplane The airplane assigned to the flight.
     */
    public Flight(Integer flightID, String from, String to, Pilot pilot,Airplane airplane) {
        this.flightID = flightID;
        this.from = from;
        this.to = to;
        this.pilot = pilot;
        this.airplane = airplane;
    }

    /**
     * Returns a string representation of the Flight object, including flight ID, departure, destination, pilot, and airplane.
     *
     * @return A string representation of the Flight.
     */
    @Override
    public String toString() {
        return "Flight{" +
                "flightID=" + flightID +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", pilot=" + pilot +
                ", airplane=" + airplane +
                '}';
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
}
