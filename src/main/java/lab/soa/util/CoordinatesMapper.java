package lab.soa.util;

import lab.soa.bd.entities.Coordinates;
import lab.soa.dto.requests.coordinates.CoordinatesCreateRequestDto;
import lab.soa.dto.requests.coordinates.CoordinatesUpdateRequestDto;
import lab.soa.dto.responses.coordinates.CoordinatesResponseDto;

public class CoordinatesMapper {
    private CoordinatesMapper() {}

    public static Coordinates toEntityFromDto(CoordinatesCreateRequestDto dto) {
        return Coordinates.builder()
            .x(dto.getX())
            .y(dto.getY())
            .build();
    }

    public static Coordinates toEntityFromDto(CoordinatesUpdateRequestDto dto) {
        return Coordinates.builder()
            .x(dto.getX())
            .y(dto.getY())
            .build();
    }

    public static CoordinatesResponseDto toDtoFromEntity(Coordinates entity) {
        return CoordinatesResponseDto.builder()
            .id(entity.getId())
            .x(entity.getX())
            .y(entity.getY())
            .build();
    }
}
