package lab.soa.presentation.exception.mapper;

import java.time.LocalDateTime;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lab.soa.infrastructure.exceptions.IncorrectParamException;
import lab.soa.presentation.dto.MyMediaType;
import lab.soa.presentation.dto.responses.ErrorMessageResponseDto;

@Provider
public class IncorrectParamExceptionMapper implements ExceptionMapper<IncorrectParamException> {
    @Override
    public Response toResponse(IncorrectParamException exception) {
        ErrorMessageResponseDto dto = ErrorMessageResponseDto.builder()
            .message(exception.getMessage())
            .time(LocalDateTime.now().toString())
            .build();
        return Response.status(Response.Status.BAD_REQUEST)
            .entity(dto)
            .type(MyMediaType.xml.getMediaType())
            .build();
    }
}
