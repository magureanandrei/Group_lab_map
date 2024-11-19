package Models;

import java.io.Serializable;
import java.util.List;
/**
 * Represents an airport with a specific location, number of airstrips, and their lengths.
 */
public class Airport implements HasID{
    private Integer id;
    private String name;
    private String location;
    private Integer number_of_airstrips;
    private Boolean avaliable;

    /**
     * Constructs a new Airport with the specified details.
     *
     * @param id                  The unique identifier for the airport.
     * @param location            The location of the airport.
     * @param number_of_airstrips The number of airstrips at the airport.
     * @param avaliable           The availability status of the airport.
     */
    public Airport(Integer id,String name,String location, Integer number_of_airstrips,Boolean avaliable) {

        this.id=id;
        this.name=name;
        this.location = location;
        this.number_of_airstrips = number_of_airstrips;
        this.avaliable=avaliable;
    }

    /**
     * Returns the unique identifier of the airport
     *
     * @return the unique identifier of the airport
     */
    @Override
    public Integer getID() {
        return id;
    }
    /**
    *Returns the availability of the airport
     *
    * @return the availability of the airport
    */
    public Boolean getAvaliable() {
        return avaliable;
    }
    /**
     * Sets the availability of the airport
     *
     * @param avaliable The availability of the airport to set.
     */
    public void setAvaliable(Boolean avaliable) {
        this.avaliable = avaliable;
    }

    /**
     * Gets the name of the airport.
     *
     * @return The name of the airport.
     */
    public String getName() {
        return name;
    }
    /**
     /**
     * Sets the name of the airport.
     *
     * @param name The new name of the airport.
     */
    public void setName(String name) {
        this.name = name;
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
     * Returns a string representation of the Airport object, including airport information, ID, name, and availability.
     *
     * @return A string representation of the Airport.
     */
    @Override
    public String toString() {
        return "Airport Details:\n" +
                "Airport ID       : " + id + "\n" +
                "Name             : " + name + "\n" +
                "Location         : " + location + "\n" +
                "Number of Airstrips: " + number_of_airstrips + "\n" +
                "Available        : " + (avaliable ? "Yes" : "No") + "\n" +
                "-----------------------------";
    }

    /**
     * Returns the header of the CSV file for the Airport object.
     *
     * @return The header of the CSV file.
     */
    @Override
    public String[] getHeader() {
        return new String[]{"id", "name", "location", "number_of_airstrips", "avaliable"};
    }

    /**
     * Returns the Airport object as a CSV string.
     *
     * @return The Airport object as a CSV string.
     */
    @Override
    public String toCSV() {
        return String.join("%", String.valueOf(id), name, location, String.valueOf(number_of_airstrips),  String.valueOf(avaliable));
    }

    /**
     * Returns the Airport object as a CSV string.
     *
     * @return The Airport object as a CSV string.
     */

    public static Airport  fromCSV(String csvLine) {
        String[] parts = csvLine.split("%");
        Airport airport = new Airport(Integer.parseInt(parts[0]), parts[1], parts[2], Integer.parseInt(parts[3]),  Boolean.parseBoolean(parts[4]));
        return airport;
    }


}
