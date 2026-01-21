package lab.soa.service.services.flat;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lab.soa.domain.models.Flat;
import lab.soa.domain.repositories.flat.FlatRepositoryImpl;
import lab.soa.infrastructure.exceptions.ObjectNotFoundException;

@ApplicationScoped
public class FlatTxService {
    @Inject
    private FlatRepositoryImpl flatRepository;

    @Transactional
    public Flat findByIdReturnsEntity(Long id) {
        return flatRepository.findById(id)
            .orElseThrow(
                () -> new ObjectNotFoundException(
                    "Not found flat by id: " + id
                )
            );
    }
}
