package lab.is.dto.requests.flat;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lab.is.dto.requests.TransportRequestDto;
import lab.is.dto.requests.ViewRequestDto;
import lab.is.dto.requests.coordinates.CoordinatesUpdateRequestDto;
import lab.is.dto.requests.house.HouseRequestUpdateDto;
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
public class FlatRequestUpdateDto {
    @XmlElement(name = "name")
    @NotNull
    @NotBlank
    private String name;

    @XmlElement(name = "coordinates")
    @Valid
    @NotNull
    private CoordinatesUpdateRequestDto coordinates;

    @XmlElement(name = "area")
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
    private ViewRequestDto view;

    @XmlElement(name = "transport")
    @Enumerated(EnumType.STRING)
    private TransportRequestDto transport;

    @XmlElement(name = "house")
    @Valid
    @NotNull
    private HouseRequestUpdateDto house;
}
