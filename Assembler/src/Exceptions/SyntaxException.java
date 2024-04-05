package Exceptions;

public class SyntaxException extends RuntimeException {
    private String message;
    public SyntaxException(String s) {
        message = s;
    }

    public String getMessage() {
        return message;
    }
}
