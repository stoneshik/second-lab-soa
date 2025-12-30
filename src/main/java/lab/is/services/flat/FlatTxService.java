package lab.is.services.flat;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lab.is.bd.entities.Flat;
import lab.is.exceptions.ObjectNotFoundException;
import lab.is.repositories.FlatRepository;
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
