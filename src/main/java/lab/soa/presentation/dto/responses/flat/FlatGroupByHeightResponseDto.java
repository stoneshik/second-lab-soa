package lab.soa.presentation.dto.responses.flat;

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
public class FlatGroupByHeightResponseDto {
    @XmlElement(name = "height")
    private Integer height;

    @XmlElement(name = "count")
    private Long count;
}
