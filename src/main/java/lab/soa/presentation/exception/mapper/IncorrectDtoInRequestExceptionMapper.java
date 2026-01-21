package lab.soa.presentation.exception.mapper;

import java.time.LocalDateTime;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lab.soa.infrastructure.exceptions.IncorrectDtoInRequestException;
import lab.soa.presentation.dto.MyMediaType;
import lab.soa.presentation.dto.responses.ErrorMessageResponseDto;

@Provider
public class IncorrectDtoInRequestExceptionMapper implements ExceptionMapper<IncorrectDtoInRequestException> {
    @Override
    public Response toResponse(IncorrectDtoInRequestException exception) {
        ErrorMessageResponseDto dto = ErrorMessageResponseDto.builder()
            .message(exception.getMessage())
            .time(LocalDateTime.now().toString())
            .build();
        return Response.status(422)
            .entity(dto)
            .type(MyMediaType.xml.getMediaType())
            .build();
    }
}
