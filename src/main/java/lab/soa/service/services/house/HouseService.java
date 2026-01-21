package lab.soa.service.services.house;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lab.soa.domain.models.House;
import lab.soa.domain.repositories.house.HouseRepositoryImpl;
import lab.soa.presentation.dto.requests.house.HouseUpdateRequestDto;

@ApplicationScoped
public class HouseService {
    @Inject
    private HouseRepositoryImpl houseRepository;

    @Transactional
    public House create(String name, Integer year, Integer numberOfFlatsOnFloor) {
        House savedHouse = houseRepository.create(name, year, numberOfFlatsOnFloor);
        return savedHouse;
    }

    @Transactional
    public House update(House foundHouse, @Valid HouseUpdateRequestDto updateRequestDto) {
        House updatedHouse = foundHouse.toBuilder()
            .name(updateRequestDto.getName())
            .year(updateRequestDto.getYear())
            .numberOfFlatsOnFloor(updateRequestDto.getNumberOfFlatsOnFloor())
            .build();
        House savedHouse = houseRepository.update(updatedHouse);
        return savedHouse;
    }
}
