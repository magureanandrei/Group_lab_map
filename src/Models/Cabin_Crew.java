package Models;

public class Cabin_Crew extends Person implements HasID{
    public Cabin_Crew(String nume, int id, String email) {
        super(nume, id, email);
    }

    @Override
    public int getID() {
        return getId();
    }
}
