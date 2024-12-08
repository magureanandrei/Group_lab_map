package Exceptions;
/**
 * Represents a custom exception for database errors.
 */
public class DatabaseException extends Exception {
    /**
     * Constructs a new DatabaseException with the specified error message.
     *
     * @param message The error message describing the issue.
     */
    public DatabaseException(String message) {
        super(message);
    }

    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
