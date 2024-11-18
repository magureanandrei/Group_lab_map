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
    String[] getHeader();
    String toCSV();
    public static <T> T fromCSV(String csvLine) {
        return null;
    }
}
