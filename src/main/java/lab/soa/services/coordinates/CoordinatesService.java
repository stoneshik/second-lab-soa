package lab.soa.services.coordinates;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import lab.soa.bd.entities.Coordinates;
import lab.soa.dto.requests.coordinates.CoordinatesUpdateRequestDto;
import lab.soa.repositories.CoordinatesRepository;
import lombok.RequiredArgsConstructor;

@Service
@Validated
@RequiredArgsConstructor
public class CoordinatesService {
    private final CoordinatesRepository coordinatesRepository;

    @Transactional
    public Coordinates create(Float x, Long y) {
        Coordinates coordinates = Coordinates.builder()
            .x(x)
            .y(y)
            .build();
        Coordinates savedCoordinates = coordinatesRepository.save(coordinates);
        coordinatesRepository.flush();
        return savedCoordinates;
    }

    @Transactional
    public Coordinates update(Coordinates coordinates, CoordinatesUpdateRequestDto updateRequestDto) {
        Coordinates updatedCoordinates = coordinates.toBuilder()
            .x(updateRequestDto.getX())
            .y(updateRequestDto.getY())
            .build();
        Coordinates savedCoordinates = coordinatesRepository.save(updatedCoordinates);
        coordinatesRepository.flush();
        return savedCoordinates;
    }
}
