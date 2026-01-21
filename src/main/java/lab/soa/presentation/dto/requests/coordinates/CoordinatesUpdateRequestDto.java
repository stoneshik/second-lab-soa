package lab.soa.presentation.dto.requests.coordinates;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlAccessorType(XmlAccessType.FIELD)
public class CoordinatesUpdateRequestDto {
    @XmlElement(name = "x")
    @NotNull
    @DecimalMin(value = "-993.0")
    private Float x;

    @XmlElement(name = "y")
    @NotNull
    private Long y;
}
