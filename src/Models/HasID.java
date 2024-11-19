package Models;

import java.io.Serializable;

/**
 * A functional interface that represents an object with a unique identifier.
 */
public interface HasID extends Serializable{
    /**
     * Gets the unique identifier of the object.
     *
     * @return The unique identifier.
     */
    public Integer getID();

    /**
     *  Sets the unique identifier of the object.
     * @return The header of the CSV file
     *
     */
    String[] getHeader();

    /**
     *  Returns the object in CSV format
     * @return The object in CSV format
     */
    String toCSV();

}
