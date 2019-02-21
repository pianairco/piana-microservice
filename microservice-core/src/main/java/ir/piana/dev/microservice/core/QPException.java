package ir.piana.dev.microservice.core;

public class QPException extends Exception {
    public QPException() {
    }

    public QPException(String message) {
        super(message);
    }

    public QPException(Throwable cause) {
        super(cause);
    }
}
