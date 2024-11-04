package Models;

public class Reservation implements HasID {
    public int id;
    public String date;
    public Payment payment;
    public Passenger passenger;
    public Flight flight;

    public Reservation(int id, String date, Payment payment, Passenger passenger, Flight flight) {
        this.id = id;
        this.date = date;
        this.payment = payment;
        this.passenger = passenger;
        this.flight = flight;
    }

    @Override
    public int getID() {
        return id;
    }

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
