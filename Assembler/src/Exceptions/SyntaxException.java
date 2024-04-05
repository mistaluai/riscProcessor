package Exceptions;

/**
 * Exception thrown when a syntax error is encountered during code parsing or interpretation.
 * This exception is typically used to indicate errors in the syntax of input code or instructions.
 */
public class SyntaxException extends RuntimeException {

    /**
     * Constructs a new SyntaxException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage method).
     */
    public SyntaxException(String message) {
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
