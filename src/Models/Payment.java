package Models;

public class Payment implements HasID{
    public int id;
    public String Description;
    public double Amount;
    public Passenger passenger;

    public Payment(int id, String description, double amount, Passenger passenger) {
        this.id = id;
        Description = description;
        Amount = amount;
        this.passenger = passenger;
    }

    @Override
    public int getID() {
        return id;
    }
}
