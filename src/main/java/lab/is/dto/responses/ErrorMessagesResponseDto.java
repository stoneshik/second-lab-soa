package lab.is.dto.responses;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
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
public class ErrorMessagesResponseDto {
    @Builder.Default
    @JacksonXmlElementWrapper(localName = "messages")
    @JacksonXmlProperty(localName = "message")
    private List<String> messages = new ArrayList<>();

    @JacksonXmlProperty(localName = "time")
    private LocalDateTime time;
}
