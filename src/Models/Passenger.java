package Models;

import java.util.ArrayList;

public class Passenger extends Person implements HasID {
    private ArrayList<Pair> flight;

    public Passenger(String nume, Integer id, String email, ArrayList<Pair> flight) {
        super(nume, id, email);
        this.flight=flight;
    }

    public ArrayList<Pair> getFlight() {
        return flight;
    }

    public void setFlight(ArrayList<Pair> flight) {
        this.flight = flight;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "flight=" + flight +
                ", id=" + id +
                ", nume='" + nume + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public Integer getID() {
        return getId();
    }

}
