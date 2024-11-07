package Models;

import java.util.List;
/**
 * Represents an airport with a specific location, number of airstrips, and their lengths.
 */
public class Airport {
    private String location;
    private Integer number_of_airstrips;
    private List<Integer> length_of_airstrips;

    /**
     * Constructs a new Airport with the specified details.
     *
     * @param location            The location of the airport.
     * @param number_of_airstrips The number of airstrips at the airport.
     * @param length_of_airstrips A list containing the lengths of each airstrip.
     */
    public Airport(String location, Integer number_of_airstrips, List<Integer> length_of_airstrips) {
        this.location = location;
        this.number_of_airstrips = number_of_airstrips;
        this.length_of_airstrips = length_of_airstrips;
    }

    /**
     * Gets the location of the airport.
     *
     * @return The location of the airport.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location of the airport.
     *
     * @param location The new location of the airport.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets the number of airstrips at the airport.
     *
     * @return The number of airstrips.
     */
    public Integer getNumber_of_airstrips() {
        return number_of_airstrips;
    }

    /**
     * Sets the number of airstrips at the airport.
     *
     * @param number_of_airstrips The new number of airstrips at the airport.
     */
    public void setNumber_of_airstrips(Integer number_of_airstrips) {
        this.number_of_airstrips = number_of_airstrips;
    }

    /**
     * Gets the list of airstrip lengths at the airport.
     *
     * @return A list of integers representing the lengths of each airstrip.
     */
    public List<Integer> getLength_of_airstrips() {
        return length_of_airstrips;
    }

    /**
     * Sets the lengths of the airstrips at the airport.
     *
     * @param length_of_airstrips A list of integers representing the new lengths of each airstrip.
     */
    public void setLength_of_airstrips(List<Integer> length_of_airstrips) {
        this.length_of_airstrips = length_of_airstrips;
    }

}
