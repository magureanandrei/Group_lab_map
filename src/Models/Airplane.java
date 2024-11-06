package Models;

public class Airplane implements HasID{
    private Integer id;
    private String model;
    private Integer capacity;
    private Boolean available;

    public Airplane(Integer id, String model, Integer capacity, Boolean available) {
        this.id = id;
        this.model = model;
        this.capacity = capacity;
        this.available = available;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Airplane{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", capacity=" + capacity +
                ", available=" + available +
                '}';
    }

    @Override
    public Integer getID() {
        return id;
    }
}
