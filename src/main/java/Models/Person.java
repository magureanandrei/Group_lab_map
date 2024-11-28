package Models;
/**
 * An abstract class that represents a person in the university system.
 */
public abstract class Person{
    protected Integer id;
    protected String nume;
    protected String email;

    /**
     * Constructs a new Person with the specified ID, name and email.
     *
     * @param nume The name of the person.
     * @param id   The unique identifier for the person.
     * @param email The email of the person.
     */
    public Person(String nume, Integer id, String email) {
        this.nume = nume;
        this.id = id;
        this.email = email;
    }

    /**
     * Gets the unique identifier for the person.
     *
     * @return The unique identifier.
     */
    public Integer getId() {return id;}

    /**
     * Sets the unique identifier for the person.
     *
     * @param id The unique identifier to set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the person.
     *
     * @return The name of the person.
     */
    public String getNume() {
        return nume;
    }

    /**
     * Sets the name of the person.
     *
     * @param nume The name to set.
     */
    public void setNume(String nume) {
        this.nume = nume;
    }

    /**
     * Gets the email of the person.
     *
     * @return The email of the person.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email for the person.
     *
     * @param email The email to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns a string representation of the Person object.
     *
     * @return A string representation of the Person(for his children classes).
     */
    @Override
    public String toString() {
        return "Person Details:\n" +
                "Person ID    : " + id + "\n" +
                "Name  : " + nume + "\n" +
                "Email : " + email + "\n" +
                "-----------------------------";
    }

    public Person() {
    }
}
