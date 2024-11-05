package Models;

import java.util.ArrayList;

public class Passenger extends Person implements HasID {
    public ArrayList<Pair> flight;

    public Passenger(String nume, Integer id, String email, ArrayList<Pair> flight) {
        super(nume, id, email);
        this.flight=flight;
    }

    @Override
    public Integer getID() {
        return getId();
    }

}
