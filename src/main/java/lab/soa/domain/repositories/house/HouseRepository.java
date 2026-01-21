package lab.soa.domain.repositories.house;

import lab.soa.domain.models.House;

public interface HouseRepository {
    House create(String name, Integer year, Integer numberOfFlatsOnFloor);
    House save(House house);
    House update(House house);
}
