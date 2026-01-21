package lab.soa.service.mappers.flat;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lab.soa.domain.models.Coordinates;
import lab.soa.domain.models.Flat;
import lab.soa.domain.models.House;
import lab.soa.presentation.dto.requests.flat.FlatRequestUpdateDto;
import lab.soa.service.services.coordinates.CoordinatesService;
import lab.soa.service.services.house.HouseService;

@ApplicationScoped
public class FlatToEntityFromDtoUpdateRequest {
    @Inject
    private CoordinatesService coordinatesService;

    @Inject
    private HouseService houseService;

    @Transactional
    public Flat toEntityFromDtoAndUpdateNestedEntities(
        FlatRequestUpdateDto flatDto,
        Flat foundFlat
    ) {
        Coordinates updatedCoordinates = coordinatesService.update(
            foundFlat.getCoordinates(),
            flatDto.getCoordinates()
        );
        House updatedHouse = houseService.update(
            foundFlat.getHouse(),
            flatDto.getHouse()
        );
        return foundFlat.toBuilder()
            .name(flatDto.getName())
            .coordinates(updatedCoordinates)
            .area(flatDto.getArea())
            .numberOfRooms(flatDto.getNumberOfRooms())
            .height(flatDto.getHeight())
            .view(flatDto.getView())
            .transport(flatDto.getTransport())
            .house(updatedHouse)
            .price(flatDto.getPrice())
            .balconyType(flatDto.getBalconyType())
            .walkingMinutesToMetro(flatDto.getWalkingMinutesToMetro())
            .transportMinutesToMetro(flatDto.getTransportMinutesToMetro())
            .build();
    }
}
