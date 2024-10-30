package Models;

public class Airplane implements HasID{
    private int id;
    private String model;
    private int capacitate;

    public Airplane(int id, String model, int capacitate) {
        this.id = id;
        this.model = model;
        this.capacitate = capacitate;
    }

    void create_flght() {

    }

    @Override
    public int getID() {
        return id;
    }
}
