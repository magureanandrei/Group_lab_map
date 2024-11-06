package Models;

public class CabinCrew extends Person implements HasID{

    private String profession;

    public CabinCrew(String nume, Integer id, String email, String profession) {
        super(nume, id, email);
        this.profession=profession;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @Override
    public String toString() {
        return "CabinCrew{" +
                "profession='" + profession + '\'' +
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
