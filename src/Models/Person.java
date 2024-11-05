package Models;

public abstract class Person{
    protected Integer id;
    protected String nume;
    protected String email;

    public Person(String nume, Integer id, String email) {
        this.nume = nume;
        this.id = id;
        this.email = email;
    }

    public Integer getId() {return id;}

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Models.Person{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
