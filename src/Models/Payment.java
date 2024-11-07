package Models;

/**
 * Represents a payment associated with a passenger.
 */
public class Payment implements HasID{
    private Integer id;
    private String Description;
    private double Amount;
    private Passenger passenger;

    /**
     * Constructs a new Payment with the specified details.
     *
     * @param id          The unique identifier for the payment.
     * @param description The description of the payment.
     * @param amount      The amount of the payment.
     * @param passenger   The passenger associated with the payment.
     */
    public Payment(int id, String description, double amount, Passenger passenger) {
        this.id = id;
        Description = description;
        Amount = amount;
        this.passenger = passenger;
    }

    /**
     * Gets the description of the payment.
     *
     * @return The payment description.
     */
    public String getDescription() {
        return Description;
    }

    /**
     * Sets the description of the payment.
     *
     * @param description The description to set.
     */
    public void setDescription(String description) {
        Description = description;
    }

    /**
     * Gets the amount of the payment.
     *
     * @return The payment amount.
     */
    public double getAmount() {
        return Amount;
    }

    /**
     * Sets the amount of the payment.
     *
     * @param amount The amount to set.
     */
    public void setAmount(double amount) {
        Amount = amount;
    }

    /**
     * Gets the passenger associated with the payment.
     *
     * @return The passenger linked to this payment.
     */
    public Passenger getPassenger() {
        return passenger;
    }

    /**
     * Sets the passenger associated with the payment.
     *
     * @param passenger The passenger to associate with this payment.
     */
    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    /**
     * Returns a string representation of the Payment object, including ID, description, amount, and associated passenger.
     *
     * @return A string representation of the Payment.
     */
    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", Description='" + Description + '\'' +
                ", Amount=" + Amount +
                ", passenger=" + passenger +
                '}';
    }

    /**
     * Gets the unique identifier for the payment.
     *
     * @return The payment ID.
     */
    @Override
    public Integer getID() {
        return id;
    }
}
