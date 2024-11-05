package Models;

public class Ticket implements HasID{
    public Integer id;
    public String title;
    public String description;
    public Payment payment;

    public Ticket(Integer id, String title, String description, Payment payment) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.payment = payment;
    }

    @Override
    public Integer getID() {
        return id;
    }
}
