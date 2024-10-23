public class Reservation {
    public int id;
    public String date;
    public Payment payment;
    public Passenger passenger;

    public Reservation(int id, String date, Payment payment, Passenger passenger) {
        this.id = id;
        this.date = date;
        this.payment = payment;
        this.passenger = passenger;
    }
}
