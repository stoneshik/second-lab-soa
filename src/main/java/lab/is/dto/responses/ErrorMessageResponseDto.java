package lab.is.dto.responses;

import java.time.LocalDateTime;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JacksonXmlRootElement(localName = "error")
public class ErrorMessageResponseDto {
    @JacksonXmlProperty(localName = "message")
    private String message;

    @JacksonXmlProperty(localName = "time")
    private LocalDateTime time;
}
