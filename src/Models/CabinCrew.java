package Models;

public class CabinCrew extends Person implements HasID{

    public String profession;

    public CabinCrew(String nume, int id, String email, String profession) {
        super(nume, id, email);
        this.profession=profession;
    }


    @Override
    public int getID() {
        return getId();
    }
}
