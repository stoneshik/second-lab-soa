package lab.soa.domain.repositories.coordinates;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lab.soa.domain.models.Coordinates;

@ApplicationScoped
public class CoordinatesRepositoryImpl implements CoordinatesRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Coordinates create(Float x, Long y) {
        Coordinates coordinates = Coordinates.builder()
            .x(x)
            .y(y)
            .build();
        entityManager.persist(coordinates);
        entityManager.flush();
        return coordinates;
    }

    public Coordinates update(Coordinates coordinates) {
        return entityManager.merge(coordinates);
    }
}
