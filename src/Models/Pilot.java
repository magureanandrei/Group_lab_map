package Models;

public class Pilot extends Person implements HasID{
    public Pilot(String nume, int id, String email) {
        super(nume, id, email);
    }

    @Override
    public int getID() {
        return getId();
    }

}
