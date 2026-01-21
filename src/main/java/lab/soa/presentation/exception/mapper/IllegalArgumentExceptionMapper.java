package lab.soa.presentation.exception.mapper;

import java.time.LocalDateTime;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lab.soa.presentation.dto.MyMediaType;
import lab.soa.presentation.dto.responses.ErrorMessageResponseDto;

@Provider
public class IllegalArgumentExceptionMapper implements ExceptionMapper<IllegalArgumentException> {
    @Override
    public Response toResponse(IllegalArgumentException exception) {
        ErrorMessageResponseDto dto = ErrorMessageResponseDto.builder()
            .message("Invalid argument")
            .time(LocalDateTime.now())
            .build();
        return Response.status(Response.Status.BAD_REQUEST)
            .entity(dto)
            .type(MyMediaType.xml.getMediaType())
            .build();
    }
}
