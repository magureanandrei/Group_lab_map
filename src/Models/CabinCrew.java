package Models;
/**
 * Represents a cabin crew member with a specific profession, inheriting general details from the Person class.
 */
public class CabinCrew extends Person implements HasID{

    private String profession;

    /**
     * Constructs a new CabinCrew member with the specified details.
     *
     * @param nume       The name of the cabin crew member.
     * @param id         The unique identifier of the cabin crew member.
     * @param email      The email of the cabin crew member.
     * @param profession The profession of the cabin crew member (like flight attendant).
     */
    public CabinCrew(String nume, Integer id, String email, String profession) {
        super(nume, id, email);
        this.profession=profession;
    }

    /**
     * Gets the profession of the cabin crew member.
     *
     * @return The profession of the cabin crew member.
     */
    public String getProfession() {
        return profession;
    }

    /**
     * Sets the profession of the cabin crew member.
     *
     * @param profession The new profession of the cabin crew member.
     */
    public void setProfession(String profession) {
        this.profession = profession;
    }

    /**
     * Returns a string representation of the CabinCrew object, including the profession, ID, name, and email.
     *
     * @return A string representation of the CabinCrew member.
     */
    @Override
    public String toString() {
        return "Cabin Crew Details:\n" +
                "Cabin Crew ID: " + id + "\n" +
                "Name       : " + nume + "\n" +
                "Email      : " + email + "\n" +
                "Profession : " + profession + "\n" +
                "-----------------------------";
    }

    /**
     * Gets the unique identifier for the cabin crew member.
     *
     * @return The unique identifier of the cabin crew member.
     */
    @Override
    public Integer getID() {
        return getId();
    }
}
