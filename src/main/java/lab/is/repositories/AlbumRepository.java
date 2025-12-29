package lab.is.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lab.is.bd.entities.House;

@Repository
public interface AlbumRepository extends JpaRepository<House, Long> {
}
