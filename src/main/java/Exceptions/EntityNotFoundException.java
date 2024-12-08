package Exceptions;
/**
 * Represents a custom exception for entity errors.
 */
public class EntityNotFoundException extends Exception {
    /**
     * Constructs a new EntityNotFoundException with the specified error message.
     *
     * @param message The error message describing the issue.
     */
    public EntityNotFoundException(String message) {
        super(message);
    }
    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
