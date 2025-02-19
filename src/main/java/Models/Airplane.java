package Models;

/**
 * Represents an airplane.
 */
public class Airplane implements HasID{
    private Integer id;
    private String model;
    private Integer capacity;
    private Boolean available;

    /**
     * Constructs a new Airplane with the specified details.
     *
     * @param id           The unique identifier for the airplane.
     * @param model        The model of the airplane.
     * @param capacity     The capacity of the airplane.
     * @param available    The availability of the airplane.

     */
    public Airplane(Integer id, String model, Integer capacity, Boolean available) {
        this.id = id;
        this.model = model;
        this.capacity = capacity;
        this.available = available;
    }

    /**
     * Gets the model of an airplane.
     *
     * @return The model of an airplane.
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets the model of the airplane.
     *
     * @param model The model of the airplane to set.
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Gets the availability of an airplane.
     *
     * @return The availability of an airplane.
     */
    public Boolean getAvailable() {
        return available;
    }

    /**
     * Sets the availability of the airplane.
     *
     * @param available The availability of the airplane to set.
     */
    public void setAvailable(Boolean available) {
        this.available = available;
    }

    /**
     * Gets the capacity of an airplane.
     *
     * @return The capacity of an airplane.
     */
    public Integer getCapacity() {
        return capacity;
    }

    /**
     * Sets the capacity of the airplane.
     *
     * @param capacity The capacity of the airplane to set.
     */
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    /**
     * Returns a string representation of the Airplane object.
     *
     * @return A string representation of the Airplane.
     */
    @Override
    public String toString() {
        return "Airplane Details:\n" +
                "Airplane ID: " + id + "\n" +
                "Model      : " + model + "\n" +
                "Capacity   : " + capacity + " seats\n" +
                "Available  : " + (available ? "Yes" : "No") + "\n" +
                "-----------------------------";
    }

    /**
     * Gets the unique identifier for the airplane.
     *
     * @return The unique identifier.
     */
    @Override
    public Integer getID() {
        return id;
    }

    /**
     * Returns the header of the CSV file.
     * @return The header of the CSV file.
     */
    @Override
    public String[] getHeader() {
        return new String[]{"id", "model", "capacity", "available"};
    }

    /**
     * Returns the object as a CSV string.
     * @return The object as a CSV string.
     */
    @Override
    public String toCSV() {
        return String.join(";", String.valueOf(id), model, String.valueOf(capacity),  String.valueOf(available));
    }

    /**
     * Creates an Airplane object from a CSV string.
     * @param csvLine The CSV string to create the object from.
     * @return The Airplane object created from the CSV string.
     */
    public static Airplane fromCSV(String csvLine) {
        String[] parts = csvLine.split(";");
        Airplane airplane = new Airplane(Integer.parseInt(parts[0]), parts[1], Integer.parseInt(parts[2]),  Boolean.parseBoolean(parts[3]));
        return airplane;
    }



}
