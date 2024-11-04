package Models;

import java.util.ArrayList;
import java.util.List;

public class Passenger extends Person implements HasID {
    public ArrayList<Pair> flight;

    public Passenger(String nume, int id, String email, ArrayList<Pair> flight) {
        super(nume, id, email);
        this.flight=flight;
    }

    @Override
    public int getID() {
        return getId();
    }
}
