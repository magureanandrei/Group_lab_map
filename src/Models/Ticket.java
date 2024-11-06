package Models;

public class Ticket implements HasID{
    private Integer id;
    private String title;
    private String description;
    private Payment payment;

    public Ticket(Integer id, String title, String description, Payment payment) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.payment = payment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public Integer getID() {
        return id;
    }
}
