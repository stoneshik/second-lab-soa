package lab.is.util;

import lab.is.bd.entities.House;
import lab.is.dto.requests.house.HouseRequestCreateDto;
import lab.is.dto.requests.house.HouseRequestUpdateDto;
import lab.is.dto.responses.house.HouseResponseDto;

public class AlbumMapper {
    private AlbumMapper() {}

    public static House toEntityFromDto(HouseRequestCreateDto dto) {
        return House.builder()
            .name(dto.getName())
            .length(dto.getLength())
            .build();
    }

    public static House toEntityFromDto(HouseRequestUpdateDto dto) {
        return House.builder()
            .name(dto.getName())
            .length(dto.getLength())
            .build();
    }

    public static HouseResponseDto toDtoFromEntity(House entity) {
        return HouseResponseDto.builder()
            .id(entity.getId())
            .name(entity.getName())
            .length(entity.getLength())
            .build();
    }
}
