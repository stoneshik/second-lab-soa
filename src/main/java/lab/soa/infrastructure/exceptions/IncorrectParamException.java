package lab.soa.infrastructure.exceptions;

public class IncorrectParamException extends RuntimeException {
    public IncorrectParamException(String message) {
        super(message);
    }
}
