package Models;

public class Airplane implements HasID{
    private Integer id;
    private String model;
    private int capacity;

    public Airplane(Integer id, String model, int capacity) {
        this.id = id;
        this.model = model;
        this.capacity = capacity;
    }

    @Override
    public Integer getID() {
        return id;
    }
}
