package Models;

public class Reservation implements HasID {
    private Integer id;
    private String date;
    private Payment payment;
    private Passenger passenger;
    private Flight flight;

    public Reservation(Integer id, String date, Payment payment, Passenger passenger, Flight flight) {
        this.id = id;
        this.date = date;
        this.payment = payment;
        this.passenger = passenger;
        this.flight = flight;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    @Override
    public Integer getID() {
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
