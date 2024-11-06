package Models;

public class Payment implements HasID{
    private Integer id;
    private String Description;
    private double Amount;
    private Passenger passenger;

    public Payment(int id, String description, double amount, Passenger passenger) {
        this.id = id;
        Description = description;
        Amount = amount;
        this.passenger = passenger;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", Description='" + Description + '\'' +
                ", Amount=" + Amount +
                ", passenger=" + passenger +
                '}';
    }

    @Override
    public Integer getID() {
        return id;
    }
}
