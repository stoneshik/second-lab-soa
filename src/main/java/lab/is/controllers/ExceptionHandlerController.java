package lab.is.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
    public ResponseEntity<ErrorMessageResponseDto> handleException(ObjectNotFoundException e) {
        ErrorMessageResponseDto dto = ErrorMessageResponseDto.builder()
            .message(e.getMessage())
            .time(LocalDateTime.now())
            .build();
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .contentType(MediaType.APPLICATION_XML)
            .body(dto);
    }

    @ExceptionHandler(IncorrectDtoInRequestException.class)
    public ResponseEntity<ErrorMessageResponseDto> handleException(IncorrectDtoInRequestException e) {
        ErrorMessageResponseDto dto = ErrorMessageResponseDto.builder()
            .message(e.getMessage())
            .time(LocalDateTime.now())
            .build();
        return ResponseEntity
            .status(HttpStatus.UNPROCESSABLE_ENTITY)
            .contentType(MediaType.APPLICATION_XML)
            .body(dto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessageResponseDto> handleException(MethodArgumentNotValidException e) {
        ErrorMessageResponseDto dto = ErrorMessageResponseDto.builder()
            .message("Invalid param supplied")
            .time(LocalDateTime.now())
            .build();
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .contentType(MediaType.APPLICATION_XML)
            .body(dto);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorMessagesResponseDto> handleException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        List<String> errors = new ArrayList<>();
        violations.forEach(violation ->
            errors.add(violation.getMessage())
        );
        ErrorMessagesResponseDto dto = ErrorMessagesResponseDto.builder()
            .messages(errors)
            .time(LocalDateTime.now())
            .build();
        return ResponseEntity
            .status(HttpStatus.UNPROCESSABLE_ENTITY)
            .contentType(MediaType.APPLICATION_XML)
            .body(dto);
    }

    @ExceptionHandler(InternalServerError.class)
    public ResponseEntity<ErrorMessageResponseDto> handleException(InternalServerError e) {
        ErrorMessageResponseDto dto = ErrorMessageResponseDto.builder()
            .message("Internal server error")
            .time(LocalDateTime.now())
            .build();
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .contentType(MediaType.APPLICATION_XML)
            .body(dto);
    }
}
