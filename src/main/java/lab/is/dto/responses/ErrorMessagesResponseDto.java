package lab.is.dto.responses;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlRootElement(name = "error")
@XmlAccessorType(XmlAccessType.FIELD)
public class ErrorMessagesResponseDto {
    @Builder.Default
    @XmlElementWrapper(name = "messages")
    @XmlElement(name = "message")
    private List<String> messages = new ArrayList<>();

    @XmlElement(name = "time")
    private LocalDateTime time;
}
