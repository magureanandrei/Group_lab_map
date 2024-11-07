package Models;
/**
 * A functional interface that represents an object with a unique identifier.
 */
@FunctionalInterface
public interface HasID {
    /**
     * Gets the unique identifier of the object.
     *
     * @return The unique identifier.
     */
    public Integer getID();
}
