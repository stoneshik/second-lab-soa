package lab.is.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import lab.is.bd.entities.Flat;

@Repository
public interface MusicBandRepository extends JpaRepository<Flat, Long>,
    JpaSpecificationExecutor<Flat> {
    Optional<Flat> findFirstByEstablishmentDate(LocalDate establishmentDate);

    Optional<Flat> findFirstByOrderByIdAsc();

    List<Flat> findByEstablishmentDateAfter(LocalDate date);

    @Override
    @EntityGraph(attributePaths = {"coordinates", "bestAlbum", "studio"})
    Page<Flat> findAll(Specification<Flat> specification, Pageable pageable);

    @Override
    @EntityGraph(attributePaths = {"coordinates", "bestAlbum", "studio"})
    Optional<Flat> findById(Long id);
}
