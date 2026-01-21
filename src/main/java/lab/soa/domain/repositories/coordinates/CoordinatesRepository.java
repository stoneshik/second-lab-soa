package lab.soa.domain.repositories.coordinates;

import lab.soa.domain.models.Coordinates;

public interface CoordinatesRepository {
    Coordinates create(Float x, Long y);
    Coordinates update(Coordinates coordinates);
}
