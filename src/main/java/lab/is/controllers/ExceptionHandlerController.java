package lab.is.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lab.is.dto.responses.ErrorMessageResponseDto;
import lab.is.dto.responses.ErrorMessagesResponseDto;
import lab.is.exceptions.IncorrectDtoInRequestException;
import lab.is.exceptions.ObjectNotFoundException;

@RestControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessageResponseDto handleException(ObjectNotFoundException e) {
        return ErrorMessageResponseDto.builder()
            .message(e.getMessage())
            .time(LocalDateTime.now())
            .build();
    }

    @ExceptionHandler(IncorrectDtoInRequestException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorMessageResponseDto handleException(IncorrectDtoInRequestException e) {
        return ErrorMessageResponseDto.builder()
            .message(e.getMessage())
            .time(LocalDateTime.now())
            .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessageResponseDto handleException(MethodArgumentNotValidException e) {
        return ErrorMessageResponseDto.builder()
            .message("Invalid param supplied")
            .time(LocalDateTime.now())
            .build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorMessagesResponseDto handleException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        List<String> errors = new ArrayList<>();
        violations.forEach(violation ->
            errors.add(violation.getMessage())
        );
        return ErrorMessagesResponseDto.builder()
            .messages(errors)
            .time(LocalDateTime.now())
            .build();
    }

    @ExceptionHandler(InternalServerError.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessageResponseDto handleException(InternalServerError e) {
        return ErrorMessageResponseDto.builder()
            .message("Internal server error")
            .time(LocalDateTime.now())
            .build();
    }
}
