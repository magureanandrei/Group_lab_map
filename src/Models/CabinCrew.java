package Models;

public class CabinCrew extends Person implements HasID{

    public String profession;

    public CabinCrew(String nume, Integer id, String email, String profession) {
        super(nume, id, email);
        this.profession=profession;
    }


    @Override
    public Integer getID() {
        return getId();
    }
}
