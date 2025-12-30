package lab.soa.repositories;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import lab.soa.bd.entities.Flat;

@Repository
public interface FlatRepository extends JpaRepository<Flat, Long>,
    JpaSpecificationExecutor<Flat> {
    @Override
    @EntityGraph(attributePaths = {"coordinates", "house"})
    Page<Flat> findAll(Specification<Flat> specification, Pageable pageable);

    @Override
    @EntityGraph(attributePaths = {"coordinates", "house"})
    Optional<Flat> findById(Long id);
}
