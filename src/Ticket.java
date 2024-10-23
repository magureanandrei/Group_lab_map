public class Ticket {
    public int id;
    public String title;
    public String description;
    public Payment payment;

    public Ticket(int id, String title, String description, Payment payment) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.payment = payment;
    }
}
