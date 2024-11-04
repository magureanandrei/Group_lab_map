package Models;

import java.util.List;

public class Passenger extends Person implements HasID {
    public List<Pair> flight;

    public Passenger(String nume, int id, String email, List<Pair> flight) {
        super(nume, id, email);
        this.flight=flight;
    }

    @Override
    public int getID() {
        return getId();
    }
}
