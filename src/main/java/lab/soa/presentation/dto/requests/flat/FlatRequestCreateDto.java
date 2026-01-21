package lab.soa.presentation.dto.requests.flat;

import java.math.BigDecimal;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lab.soa.domain.models.BalconyType;
import lab.soa.domain.models.Transport;
import lab.soa.domain.models.View;
import lab.soa.infrastructure.xml.adapters.IntegerXmlAdapter;
import lab.soa.presentation.dto.requests.coordinates.CoordinatesCreateRequestDto;
import lab.soa.presentation.dto.requests.house.HouseCreateRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlRootElement(name = "flat")
@XmlAccessorType(XmlAccessType.FIELD)
public class FlatRequestCreateDto {
    @XmlElement(name = "name")
    @NotNull
    @NotBlank
    private String name;

    @XmlElement(name = "coordinates")
    @Valid
    @NotNull
    private CoordinatesCreateRequestDto coordinates;

    @XmlElement(name = "area")
    @XmlJavaTypeAdapter(IntegerXmlAdapter.class)
    @Positive
    private Integer area;

    @XmlElement(name = "numberOfRooms")
    @NotNull
    @Positive
    private Integer numberOfRooms;

    @XmlElement(name = "height")
    @NotNull
    @Positive
    private Integer height;

    @XmlElement(name = "view")
    @Enumerated(EnumType.STRING)
    private View view;

    @XmlElement(name = "transport")
    @Enumerated(EnumType.STRING)
    private Transport transport;

    @XmlElement(name = "house")
    @Valid
    @NotNull
    private HouseCreateRequestDto house;

    @XmlElement(name = "price")
    @NotNull
    @Positive
    @Digits(integer = 12, fraction = 2)
    private BigDecimal price;

    @XmlElement(name = "balconyType")
    @NotNull
    @Enumerated(EnumType.STRING)
    private BalconyType balconyType;

    @XmlElement(name = "walkingMinutesToMetro")
    @NotNull
    @Positive
    private Integer walkingMinutesToMetro;

    @XmlElement(name = "transportMinutesToMetro")
    @NotNull
    @Positive
    private Integer transportMinutesToMetro;
}
