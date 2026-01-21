package lab.soa.domain.repositories.house;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lab.soa.domain.models.House;

@ApplicationScoped
public class HouseRepositoryImpl implements HouseRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public House create(String name, Integer year, Integer numberOfFlatsOnFloor) {
        House house = House.builder()
            .name(name)
            .year(year)
            .numberOfFlatsOnFloor(numberOfFlatsOnFloor)
            .build();
        entityManager.persist(house);
        entityManager.flush();
        return house;
    }

    public House update(House house) {
        return entityManager.merge(house);
    }
}
