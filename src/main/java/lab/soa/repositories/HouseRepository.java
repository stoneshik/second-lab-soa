package lab.soa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lab.soa.bd.entities.House;

@Repository
public interface HouseRepository extends JpaRepository<House, Long> {
}
