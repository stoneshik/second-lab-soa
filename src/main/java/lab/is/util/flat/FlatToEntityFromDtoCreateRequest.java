package lab.is.util.flat;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lab.is.bd.entities.Coordinates;
import lab.is.bd.entities.Flat;
import lab.is.bd.entities.House;
import lab.is.dto.requests.coordinates.CoordinatesCreateRequestDto;
import lab.is.dto.requests.flat.FlatRequestCreateDto;
import lab.is.dto.requests.house.HouseCreateRequestDto;
import lab.is.services.coordinates.CoordinatesService;
import lab.is.services.house.HouseService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FlatToEntityFromDtoCreateRequest {
    private final CoordinatesService coordinatesService;
    private final HouseService houseService;

    @Transactional
    public Flat toEntityFromDto(FlatRequestCreateDto flatDto) {
        Coordinates coordinates = createCoordinatesEntityByMusicBandDto(
            flatDto.getCoordinates()
        );
        House house = createHouseEntityByFlatDto(
            flatDto.getHouse()
        );
        return Flat.builder()
            .name(flatDto.getName())
            .coordinates(coordinates)
            .area(flatDto.getArea())
            .numberOfRooms(flatDto.getNumberOfRooms())
            .height(flatDto.getHeight())
            .view(flatDto.getView())
            .transport(flatDto.getTransport())
            .house(house)
            .build();
    }

    private Coordinates createCoordinatesEntityByMusicBandDto(CoordinatesCreateRequestDto dto) {
        return coordinatesService.create(
            dto.getX(),
            dto.getY()
        );
    }

    private House createHouseEntityByFlatDto(
        HouseCreateRequestDto dto
    ) {
        return houseService.create(
            dto.getName(),
            dto.getYear(),
            dto.getNumberOfFlatsOnFloor()
        );
    }
}
