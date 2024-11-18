package Models;

import java.io.Serializable;

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
        return "Payment Details:\n" +
                "Payment ID  : " + id + "\n" +
                "Description : " + Description + "\n" +
                "Amount      : " + Amount + "\n" +
                "-----------------------------\n" +
                "Passenger   : " + passenger + "\n" +
                "-----------------------------";
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

    /**
     *  Returns the header of the CSV file.
     * @return The header of the CSV file.
     *
     */
    @Override
    public String[] getHeader() {
        return new String[]{"id", "description", "amount", "passenger"};
    }

    /**
     * Returns the object in CSV format.
     * @return The object in CSV format.
     */
    @Override
    public String toCSV() {
        return String.join(",", String.valueOf(id), Description, String.valueOf(Amount),
                passenger != null ? passenger.toCSV() : "");
    }

    /**
     * Creates a new Payment object from a CSV string.
     * @param csvLine The CSV string to create the object from.
     * @return A new Payment object.
     */
    public static  Payment fromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        Passenger passenger = parts[3].isEmpty() ? null : Passenger.fromCSV(parts[3]);

        return new Payment(
                Integer.parseInt(parts[0]),
                parts[1],
                Double.parseDouble(parts[2]),
                passenger);
    }
}
