package exceptions;

public class CoupJouerInvalideException extends Exception {
    String message;

    public CoupJouerInvalideException(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
