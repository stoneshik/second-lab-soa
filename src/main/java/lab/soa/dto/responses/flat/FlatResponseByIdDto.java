package lab.soa.dto.responses.flat;

import java.time.LocalDateTime;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lab.soa.bd.entities.Transport;
import lab.soa.bd.entities.View;
import lab.soa.dto.responses.coordinates.CoordinatesResponseDto;
import lab.soa.dto.responses.house.HouseResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JacksonXmlRootElement(localName = "flat")
public class FlatResponseByIdDto {
    @JacksonXmlProperty(localName = "id")
    private Long id;

    @JacksonXmlProperty(localName = "name")
    private String name;

    @JacksonXmlProperty(localName = "coordinates")
    private CoordinatesResponseDto coordinates;

    @JacksonXmlProperty(localName = "creationDate")
    private LocalDateTime creationDate;

    @JacksonXmlProperty(localName = "area")
    private Integer area;

    @JacksonXmlProperty(localName = "numberOfRooms")
    private Integer numberOfRooms;

    @JacksonXmlProperty(localName = "height")
    private Integer height;

    @JacksonXmlProperty(localName = "view")
    private View view;

    @JacksonXmlProperty(localName = "transport")
    private Transport transport;

    @JacksonXmlProperty(localName = "house")
    private HouseResponseDto house;
}
