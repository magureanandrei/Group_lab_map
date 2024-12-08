package Exceptions;
/**
 * Represents a custom exception for validation errors.
 */
public class ValidationException extends Exception {
    /**
     * Constructs a new ValidationException with the specified error message.
     *
     * @param message The error message describing the issue.
     */
    public ValidationException(String message) {
        super(message);
    }
}
