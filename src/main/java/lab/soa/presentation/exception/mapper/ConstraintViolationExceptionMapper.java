package lab.soa.presentation.exception.mapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lab.soa.presentation.dto.MyMediaType;
import lab.soa.presentation.dto.responses.ErrorMessageResponseDto;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
    @Override
    public Response toResponse(ConstraintViolationException exception) {
        Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
        List<String> errors = new ArrayList<>();
        violations.forEach(violation -> {
            String field = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            errors.add(field + ": " + message);
        });
        ErrorMessageResponseDto dto = ErrorMessageResponseDto.builder()
            .message("Unprocessable entity")
            .violations(errors)
            .time(LocalDateTime.now().toString())
            .build();
        return Response.status(422)
            .entity(dto)
            .type(MyMediaType.xml.getMediaType())
            .build();
    }
}
