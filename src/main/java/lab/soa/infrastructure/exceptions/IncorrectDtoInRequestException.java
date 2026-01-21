package lab.soa.infrastructure.exceptions;

public class IncorrectDtoInRequestException extends RuntimeException {
    public IncorrectDtoInRequestException(String message) {
        super(message);
    }
}
