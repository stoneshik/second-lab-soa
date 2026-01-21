package lab.soa.presentation.dto.requests.house;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lab.soa.infrastructure.xml.adapters.IntegerXmlAdapter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlAccessorType(XmlAccessType.FIELD)
public class HouseUpdateRequestDto {
    @XmlElement(name = "name")
    @NotNull
    private String name;

    @XmlElement(name = "year")
    @XmlJavaTypeAdapter(IntegerXmlAdapter.class)
    @Positive
    private Integer year;

    @XmlElement(name = "numberOfFlatsOnFloor")
    @XmlJavaTypeAdapter(IntegerXmlAdapter.class)
    @Positive
    private Integer numberOfFlatsOnFloor;
}
