package lab.is.util.flat;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lab.is.bd.entities.Coordinates;
import lab.is.bd.entities.Flat;
import lab.is.bd.entities.House;
import lab.is.dto.requests.flat.FlatRequestUpdateDto;
import lab.is.services.coordinates.CoordinatesService;
import lab.is.services.house.HouseService;
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
