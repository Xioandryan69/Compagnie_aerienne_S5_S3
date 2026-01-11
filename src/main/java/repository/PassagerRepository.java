package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entity.Passager;

@Repository
public interface PassagerRepository extends JpaRepository<Passager, Long> {

}
