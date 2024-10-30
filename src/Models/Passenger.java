package Models;

public class Passenger extends Person implements HasID {
    public Passenger(String nume, int id, String email) {
        super(nume, id, email);
    }

    @Override
    public int getID() {
        return getId();
    }
}
