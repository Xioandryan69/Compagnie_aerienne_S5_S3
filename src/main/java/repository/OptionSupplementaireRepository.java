package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entity.OptionSupplementaire;

@Repository
public interface OptionSupplementaireRepository extends JpaRepository<OptionSupplementaire, Long> {

}
