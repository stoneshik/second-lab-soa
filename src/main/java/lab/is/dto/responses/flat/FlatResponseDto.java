package lab.is.dto.responses.flat;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lab.is.dto.responses.TransportResponseDto;
import lab.is.dto.responses.ViewResponseDto;
import lab.is.dto.responses.coordinates.CoordinatesResponseDto;
import lab.is.dto.responses.house.HouseResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlAccessorType(XmlAccessType.FIELD)
public class FlatResponseDto {
    @XmlElement(name = "id")
    private Long id;

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "coordinates")
    private CoordinatesResponseDto coordinates;

    @XmlElement(name = "area")
    private Integer area;

    @XmlElement(name = "numberOfRooms")
    private Integer numberOfRooms;

    @XmlElement(name = "height")
    private Integer height;

    @XmlElement(name = "view")
    @Enumerated(EnumType.STRING)
    private ViewResponseDto view;

    @XmlElement(name = "transport")
    @Enumerated(EnumType.STRING)
    private TransportResponseDto transport;

    @XmlElement(name = "house")
    private HouseResponseDto house;
}
