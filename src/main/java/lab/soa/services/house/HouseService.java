package lab.soa.services.house;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import lab.soa.bd.entities.House;
import lab.soa.dto.requests.house.HouseUpdateRequestDto;
import lab.soa.repositories.HouseRepository;
import lombok.RequiredArgsConstructor;

@Service
@Validated
@RequiredArgsConstructor
public class HouseService {
    private final HouseRepository houseRepository;

    @Transactional
    public House create(String name, Integer year, Integer numberOfFlatsOnFloor) {
        House house = House.builder()
            .name(name)
            .year(year)
            .numberOfFlatsOnFloor(numberOfFlatsOnFloor)
            .build();
        House savedHouse = houseRepository.save(house);
        houseRepository.flush();
        return savedHouse;
    }

    @Transactional
    public House update(House house, HouseUpdateRequestDto updateRequestDto) {
        House updatedHouse = house.toBuilder()
            .name(updateRequestDto.getName())
            .year(updateRequestDto.getYear())
            .numberOfFlatsOnFloor(updateRequestDto.getNumberOfFlatsOnFloor())
            .build();
        House savedHouse = houseRepository.save(updatedHouse);
        houseRepository.flush();
        return savedHouse;
    }
}
