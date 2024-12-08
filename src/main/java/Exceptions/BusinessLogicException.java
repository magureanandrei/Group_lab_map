package Exceptions;
/**
 * Represents a custom exception for business logic errors.
 */
public class BusinessLogicException extends Exception {

    /**
     * Constructs a new BusinessLogicException with the specified error message.
     *
     * @param message The error message describing the issue.
     */
    public BusinessLogicException(String message) {
        super(message);
    }
}
