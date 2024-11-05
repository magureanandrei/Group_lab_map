package Models;

public class Pilot extends Person implements HasID{
    private Boolean availability;

    public Pilot(String nume, Integer id, String email, Boolean availability) {
        super(nume, id, email);
        this.availability = availability;
    }
    @Override
    public Integer getID() {
        return getId();
    }

}
