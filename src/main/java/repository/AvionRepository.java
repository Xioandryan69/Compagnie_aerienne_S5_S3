package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entity.Avion;

@Repository
public interface AvionRepository extends JpaRepository<Avion, Long> {

}
