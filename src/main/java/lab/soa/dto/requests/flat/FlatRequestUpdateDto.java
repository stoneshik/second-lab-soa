package lab.soa.dto.requests.flat;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lab.soa.bd.entities.Transport;
import lab.soa.bd.entities.View;
import lab.soa.dto.requests.coordinates.CoordinatesUpdateRequestDto;
import lab.soa.dto.requests.house.HouseUpdateRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JacksonXmlRootElement(localName = "flat")
public class FlatRequestUpdateDto {
    @JacksonXmlProperty(localName = "name")
    @NotNull
    @NotBlank
    private String name;

    @JacksonXmlProperty(localName = "coordinates")
    @Valid
    @NotNull
    private CoordinatesUpdateRequestDto coordinates;

    @JacksonXmlProperty(localName = "area")
    @Positive
    private Integer area;

    @JacksonXmlProperty(localName = "numberOfRooms")
    @NotNull
    @Positive
    private Integer numberOfRooms;

    @JacksonXmlProperty(localName = "height")
    @NotNull
    @Positive
    private Integer height;

    @JacksonXmlProperty(localName = "view")
    @Enumerated(EnumType.STRING)
    private View view;

    @JacksonXmlProperty(localName = "transport")
    @Enumerated(EnumType.STRING)
    private Transport transport;

    @JacksonXmlProperty(localName = "house")
    @Valid
    @NotNull
    private HouseUpdateRequestDto house;
}
