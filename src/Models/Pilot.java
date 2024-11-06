package Models;

public class Pilot extends Person implements HasID{
    private Boolean availability;

    public Pilot(String nume, Integer id, String email, Boolean availability) {
        super(nume, id, email);
        this.availability = availability;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "Pilot{" +
                "availability=" + availability +
                ", id=" + id +
                ", nume='" + nume + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public Integer getID() {
        return getId();
    }

}
