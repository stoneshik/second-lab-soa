package lab.soa.service.mappers.flat;

import jakarta.enterprise.context.ApplicationScoped;
import lab.soa.domain.models.Flat;
import lab.soa.presentation.dto.responses.flat.FlatResponseByIdDto;
import lab.soa.presentation.dto.responses.flat.FlatResponseDto;
import lab.soa.service.mappers.CoordinatesMapper;
import lab.soa.service.mappers.HouseMapper;

@ApplicationScoped
public class FlatToDtoFromEntityMapper {
    private FlatToDtoFromEntityMapper() {}

    public static FlatResponseDto toDtoFromEntity(Flat flatEntity) {
        return FlatResponseDto.builder()
            .id(flatEntity.getId())
            .name(flatEntity.getName())
            .coordinates(
                CoordinatesMapper.toDtoFromEntity(
                    flatEntity.getCoordinates()
                )
            )
            .creationDate(flatEntity.getCreationDate().toString())
            .area(flatEntity.getArea())
            .numberOfRooms(flatEntity.getNumberOfRooms())
            .height(flatEntity.getHeight())
            .view(flatEntity.getView() != null ? flatEntity.getView().name() : null)
            .transport(flatEntity.getTransport() != null ? flatEntity.getTransport().name() : null)
            .house(
                HouseMapper.toDtoFromEntity(
                    flatEntity.getHouse()
                )
            )
            .price(flatEntity.getPrice())
            .balconyType(flatEntity.getBalconyType() != null ? flatEntity.getBalconyType().name() : null)
            .walkingMinutesToMetro(flatEntity.getWalkingMinutesToMetro())
            .transportMinutesToMetro(flatEntity.getTransportMinutesToMetro())
            .build();
    }

    public static FlatResponseByIdDto toDtoByIdFromEntity(Flat flatEntity) {
        return FlatResponseByIdDto.builder()
            .id(flatEntity.getId())
            .name(flatEntity.getName())
            .coordinates(
                CoordinatesMapper.toDtoFromEntity(
                    flatEntity.getCoordinates()
                )
            )
            .creationDate(flatEntity.getCreationDate().toString())
            .area(flatEntity.getArea())
            .numberOfRooms(flatEntity.getNumberOfRooms())
            .height(flatEntity.getHeight())
            .view(flatEntity.getView() != null ? flatEntity.getView().name() : null)
            .transport(flatEntity.getTransport() != null ? flatEntity.getTransport().name() : null)
            .house(
                HouseMapper.toDtoFromEntity(
                    flatEntity.getHouse()
                )
            )
            .price(flatEntity.getPrice())
            .balconyType(flatEntity.getBalconyType() != null ? flatEntity.getBalconyType().name() : null)
            .walkingMinutesToMetro(flatEntity.getWalkingMinutesToMetro())
            .transportMinutesToMetro(flatEntity.getTransportMinutesToMetro())
            .build();
    }
}
