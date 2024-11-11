package Models;
/**
 * Represents a ticket associated with a payment.
 */
public class Ticket implements HasID{
    private Integer id;
    private String title;
    private String description;
    private Payment payment;
    private String date;

    /**
     * Constructs a new Ticket with the specified details.
     *
     * @param id          The unique identifier for the ticket.
     * @param title       The title of the ticket.
     * @param description The description of the ticket.
     * @param payment     The payment associated with the ticket.
     * @param date        The date associated with the ticket.
     */
    public Ticket(Integer id, String title, String description, Payment payment, String date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.payment = payment;
        this.date = date;
    }

    /**
     * Gets the title of the ticket.
     *
     * @return The title of the ticket.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the ticket.
     *
     * @param title The title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the description of the ticket.
     *
     * @return The description of the ticket.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the ticket.
     *
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the payment associated with the ticket.
     *
     * @return The payment associated with the ticket.
     */
    public Payment getPayment() {
        return payment;
    }

    /**
     * Sets the payment associated with the ticket.
     *
     * @param payment The payment to set.
     */
    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    /**
     * Gets the unique identifier of the ticket.
     *
     * @return The ticket ID.
     */
    @Override
    public Integer getID() {
        return id;
    }

    /**
     * Returns a string representation of the Ticket.
     *
     * @return A string representing the ticket details.
     */
    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", payment=" + payment +
                ", date='" + date + '\'' +
                '}';
    }

    /**
     * Gets the date of the ticket.
     *
     * @return The ticket date.
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date associated with the ticket.
     *
     * @param date The date to set.
     */
    public void setDate(String date) {
        this.date = date;
    }
}
