package Models;

public class CabinCrew extends Person implements HasID{
    public CabinCrew(String nume, int id, String email) {
        super(nume, id, email);
    }
    String profession;

    @Override
    public int getID() {
        return getId();
    }
}
