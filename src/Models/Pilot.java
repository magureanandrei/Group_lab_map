package Models;

import java.io.Serializable;

/**
 * Represents a pilot and his availability status.
 */
public class Pilot extends Person implements HasID{
    private Boolean availability;

    /**
     * Constructs a new Pilot with the specified details.
     *
     * @param nume        The name of the pilot.
     * @param id          The unique identifier for the pilot.
     * @param email       The email address of the pilot.
     * @param availability The availability status of the pilot.
     */
    public Pilot(String nume, Integer id, String email, Boolean availability) {
        super(nume, id, email);
        this.availability = availability;
    }

    /**
     * Gets the availability status of the pilot.
     *
     * @return True if the pilot is available, false otherwise.
     */
    public Boolean getAvailability() {
        return availability;
    }

    /**
     * Sets the availability status of the pilot.
     *
     * @param availability The new availability status to set.
     */
    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    /**
     * Returns a string representation of the Pilot object, including availability, ID, name, and email.
     *
     * @return A string representation of the Pilot.
     */
    @Override
    public String toString() {
        return "Pilot Details:\n" +
                "Pilot ID     : " + id + "\n" +
                "Name         : " + nume + "\n" +
                "Email        : " + email + "\n" +
                "Availability : " + (availability ? "Available" : "Not Available") + "\n" +
                "-----------------------------";
    }

    /**
     * Gets the unique identifier for the pilot.
     *
     * @return The pilot's ID.
     */
    @Override
    public Integer getID() {
        return getId();
    }

    @Override
    public String[] getHeader() {
        return new String[]{"id", "nume", "email", "availability"};
    }

    @Override
    public String toCSV() {
        return String.join(",", nume, String.valueOf(id), email, String.valueOf(availability));
    }

    public static Pilot fromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        return new Pilot(
                parts[0],
                Integer.parseInt(parts[1]),
                parts[2],
                Boolean.parseBoolean(parts[4]));
    }

}
