package Assember.Exceptions;

/**
 * Exception thrown when the input value is out of the range of the number of bits
 * expected for an immediate binary representation.
 * This exception is typically used when the immediate value exceeds the allowed range
 * of bits for a specific binary representation, such as in assembly code processing.
 */
public class RangeException extends RuntimeException {

    /**
     * Constructs a new RangeException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage method).
     */
    public RangeException(String message) {
        super(message);
    }

    /**
     * Retrieves the detail message of this exception.
     *
     * @return the detail message string of this exception.
     */
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
