package lab.soa.service.services.coordinates;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lab.soa.domain.models.Coordinates;
import lab.soa.domain.repositories.coordinates.CoordinatesRepositoryImpl;
import lab.soa.presentation.dto.requests.coordinates.CoordinatesUpdateRequestDto;

@ApplicationScoped
public class CoordinatesService {
    @Inject
    private CoordinatesRepositoryImpl coordinatesRepository;

    @Transactional
    public Coordinates create(Float x, Long y) {
        Coordinates savedCoordinates = coordinatesRepository.create(x, y);
        return savedCoordinates;
    }

    @Transactional
    public Coordinates update(
        Coordinates coordinates,
        @Valid CoordinatesUpdateRequestDto updateRequestDto
    ) {
        Coordinates updatedCoordinates = coordinates.toBuilder()
            .x(updateRequestDto.getX())
            .y(updateRequestDto.getY())
            .build();
        Coordinates savedCoordinates = coordinatesRepository.update(updatedCoordinates);
        return savedCoordinates;
    }
}
