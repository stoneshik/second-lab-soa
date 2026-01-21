package lab.soa.service.services.flat;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lab.soa.domain.models.Flat;
import lab.soa.domain.repositories.flat.FlatRepository;
import lab.soa.infrastructure.exceptions.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class FlatTxService {
    private final FlatRepository musicBandRepository;

    @Transactional
    public Flat findByIdReturnsEntity(Long id) {
        return musicBandRepository.findById(id)
            .orElseThrow(
                () -> new ObjectNotFoundException(
                    "Not found flat by id: " + id
                )
            );
    }
}
