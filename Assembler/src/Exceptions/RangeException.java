package Exceptions;

public class RangeException extends RuntimeException {
    private String message;
    public RangeException(String s) {
        message = s;
    }

    public String getMessage() {
        return message;
    }
}
