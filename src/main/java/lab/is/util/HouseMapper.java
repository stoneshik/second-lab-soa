package lab.is.util;

import lab.is.bd.entities.House;
import lab.is.dto.requests.house.HouseCreateRequestDto;
import lab.is.dto.requests.house.HouseUpdateRequestDto;
import lab.is.dto.responses.house.HouseResponseDto;

public class HouseMapper {
    private HouseMapper() {}

    public static House toEntityFromDto(HouseCreateRequestDto dto) {
        return House.builder()
            .name(dto.getName())
            .year(dto.getYear())
            .numberOfFlatsOnFloor(dto.getNumberOfFlatsOnFloor())
            .build();
    }

    public static House toEntityFromDto(HouseUpdateRequestDto dto) {
        return House.builder()
            .name(dto.getName())
            .year(dto.getYear())
            .numberOfFlatsOnFloor(dto.getNumberOfFlatsOnFloor())
            .build();
    }

    public static HouseResponseDto toDtoFromEntity(House entity) {
        return HouseResponseDto.builder()
            .id(entity.getId())
            .name(entity.getName())
            .year(entity.getYear())
            .numberOfFlatsOnFloor(entity.getNumberOfFlatsOnFloor())
            .build();
    }
}
