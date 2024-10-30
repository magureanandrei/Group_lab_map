package Models;

public class Airplane implements HasID{
    private int id;
    private String model;
    private int capacity;

    public Airplane(int id, String model, int capacity) {
        this.id = id;
        this.model = model;
        this.capacity = capacity;
    }


    @Override
    public int getID() {
        return id;
    }
}
