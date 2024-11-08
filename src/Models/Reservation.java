package Models;
/**
 * Represents a reservation for a flight.
 */
public class Reservation implements HasID {
    private Integer id;
    private String date;
    private Payment payment;
    private Passenger passenger;
    private Pair flight;


    /**
     * Constructs a new Reservation with the specified details.
     *
     * @param id        The unique identifier of the reservation.
     * @param date      The date of the reservation.
     * @param payment   The payment associated with the reservation.
     * @param passenger The passenger who made the reservation.
     * @param flight    The flight associated with the reservation.
     */
    public Reservation(Integer id, String date, Payment payment, Passenger passenger, Pair flight) {
        this.id = id;
        this.date = date;
        this.payment = payment;
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
     * Gets the payment associated with the reservation.
     *
     * @return The payment.
     */
    public Payment getPayment() {
        return payment;
    }

    /**
     * Sets the payment associated with the reservation.
     *
     * @param payment The payment to set.
     */
    public void setPayment(Payment payment) {
        this.payment = payment;
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
        return "Reservation{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", payment=" + payment +
                ", passenger=" + passenger +
                ", flight=" + flight +
                '}';
    }

}
