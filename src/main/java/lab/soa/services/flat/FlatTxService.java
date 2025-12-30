package lab.soa.services.flat;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lab.soa.bd.entities.Flat;
import lab.soa.exceptions.ObjectNotFoundException;
import lab.soa.repositories.flat.FlatRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FlatTxService {
    private final FlatRepository musicBandRepository;

    @Transactional(readOnly = true)
    public Flat findByIdReturnsEntity(Long id) {
        return musicBandRepository.findById(id)
            .orElseThrow(
                () -> new ObjectNotFoundException(
                    "Not found flat by id: " + id
                )
            );
    }
}
