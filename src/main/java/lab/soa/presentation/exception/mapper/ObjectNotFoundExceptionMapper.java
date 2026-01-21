package lab.soa.presentation.exception.mapper;

import java.time.LocalDateTime;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lab.soa.infrastructure.exceptions.ObjectNotFoundException;
import lab.soa.presentation.dto.MyMediaType;
import lab.soa.presentation.dto.responses.ErrorMessageResponseDto;

@Provider
public class ObjectNotFoundExceptionMapper implements ExceptionMapper<ObjectNotFoundException> {
    @Override
    public Response toResponse(ObjectNotFoundException exception) {
        ErrorMessageResponseDto dto = ErrorMessageResponseDto.builder()
            .message(exception.getMessage())
            .time(LocalDateTime.now())
            .build();
        return Response.status(Response.Status.NOT_FOUND)
            .entity(dto)
            .type(MyMediaType.xml.getMediaType())
            .build();
    }
}
