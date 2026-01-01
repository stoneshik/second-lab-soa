package lab.soa.service.mappers.flat;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lab.soa.domain.models.Coordinates;
import lab.soa.domain.models.Flat;
import lab.soa.domain.models.House;
import lab.soa.presentation.dto.requests.flat.FlatRequestUpdateDto;
import lab.soa.service.coordinates.CoordinatesService;
import lab.soa.service.house.HouseService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FlatToEntityFromDtoUpdateRequest {
    private final CoordinatesService coordinatesService;
    private final HouseService houseService;

    @Transactional
    public Flat toEntityFromDto(
        FlatRequestUpdateDto requestUpdateDto,
        Flat foundFlat
    ) {
        Coordinates updatedCoordinates = coordinatesService.update(
            foundFlat.getCoordinates(),
            requestUpdateDto.getCoordinates()
        );
        House updatedHouse = houseService.update(
            foundFlat.getHouse(),
            requestUpdateDto.getHouse()
        );
        return foundFlat.toBuilder()
            .name(requestUpdateDto.getName())
            .coordinates(updatedCoordinates)
            .area(requestUpdateDto.getArea())
            .numberOfRooms(requestUpdateDto.getNumberOfRooms())
            .height(requestUpdateDto.getHeight())
            .view(requestUpdateDto.getView())
            .transport(requestUpdateDto.getTransport())
            .house(updatedHouse)
            .build();
    }
}
