package lab.is.util.flat;

import org.springframework.stereotype.Service;

import lab.is.bd.entities.Flat;
import lab.is.dto.responses.flat.FlatResponseByIdDto;
import lab.is.dto.responses.flat.FlatResponseDto;
import lab.is.util.CoordinatesMapper;
import lab.is.util.HouseMapper;

@Service
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
            .creationDate(flatEntity.getCreationDate())
            .area(flatEntity.getArea())
            .numberOfRooms(flatEntity.getNumberOfRooms())
            .height(flatEntity.getHeight())
            .view(flatEntity.getView())
            .transport(flatEntity.getTransport())
            .house(
                HouseMapper.toDtoFromEntity(
                    flatEntity.getHouse()
                )
            )
            .build();
    }

    public static FlatResponseByIdDto toByIdDtoFromEntity(Flat flatEntity) {
        return FlatResponseByIdDto.builder()
            .id(flatEntity.getId())
            .name(flatEntity.getName())
            .coordinates(
                CoordinatesMapper.toDtoFromEntity(
                    flatEntity.getCoordinates()
                )
            )
            .creationDate(flatEntity.getCreationDate())
            .area(flatEntity.getArea())
            .numberOfRooms(flatEntity.getNumberOfRooms())
            .height(flatEntity.getHeight())
            .view(flatEntity.getView())
            .transport(flatEntity.getTransport())
            .house(
                HouseMapper.toDtoFromEntity(
                    flatEntity.getHouse()
                )
            )
            .build();
    }
}
