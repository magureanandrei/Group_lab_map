package Models;



/**
 * Represents a reservation for a flight.
 */
public class Reservation implements HasID{
    private Integer id;
    private String date;
    private Passenger passenger;
    private Pair flight;


    /**
     * Constructs a new Reservation with the specified details.
     *
     * @param id        The unique identifier of the reservation.
     * @param date      The date of the reservation.
     * @param passenger The passenger who made the reservation.
     * @param flight    The flight associated with the reservation.
     */
    public Reservation(Integer id, String date, Passenger passenger, Pair flight) {
        this.id = id;
        this.date = date;
        this.passenger = passenger;
        this.flight = flight;
    }

    /**
     * Gets the flight associated with the reservation.
     *
     * @return The flight.
     */
    public Pair getFlight() {
        return flight;
    }

    /**
     * Sets the flight associated with the reservation.
     *
     * @param flight The flight to set.
     */
    public void setFlight(Pair flight) {
        this.flight = flight;
    }

    /**
     * Gets the date of the reservation.
     *
     * @return The reservation date.
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date of the reservation.
     *
     * @param date The date to set.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets the passenger who made the reservation.
     *
     * @return The passenger.
     */
    public Passenger getPassenger() {
        return passenger;
    }

    /**
     * Sets the passenger who made the reservation.
     *
     * @param passenger The passenger to set.
     */
    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    /**
     * Gets the unique identifier of the reservation.
     *
     * @return The reservation ID.
     */
    @Override
    public Integer getID() {
        return id;
    }

    /**
     * Returns a string representation of the Reservation.
     *
     * @return A string representing the reservation details.
     */
    @Override
    public String toString() {
        return "Reservation Details:\n" +
                "-----------------------------\n" +
                "Reservation ID : " + id + "\n" +
                "Date        : " + date + "\n" +
                "-----------------------------\n" +
                "Passenger   : " + passenger + "\n" +
                "Flight      : " + flight + "\n" +
                "-----------------------------";
    }

    /**
     * Returns the header of the CSV file.
     * @return The header of the CSV file.
     */
    @Override
    public String[] getHeader() {
        return new String[]{"id", "date", "passenger", "flight"};
    }

    /**
     * Returns the CSV representation of the reservation.
     * @return The CSV representation of the reservation.
     */
    @Override
    public String toCSV() {
        return String.join(",",
                String.valueOf(id),
                date,
                passenger != null ? passenger.toCSV() : "",
                flight != null ? flight.toCSV() : "");
    }

    /**
     * Creates a reservation from the CSV line.
     * @param csvLine The CSV line to create the reservation from.
     * @return The reservation created from the CSV line.
     */

    public static Reservation fromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        Pair flight=null;
        Passenger passenger=null;
        if (!parts[3].isEmpty()) {
            String[] pairParts = parts[3].split(";");
            if (pairParts.length == 2) {
                flight = Pair.fromCSV(parts[3]);
            }
        }
        if (!parts[2].isEmpty()) {
            String[] passengerParts = parts[2].split(":");
                passenger = Passenger.fromCSV(parts[2]);
            }

        return new Reservation(
                Integer.parseInt(parts[0]),
                parts[1],
                passenger,
                flight
        );

    }

}
