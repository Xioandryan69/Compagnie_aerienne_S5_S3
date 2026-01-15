package service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import repository.AvionRepository;

@Service
public class AvionService {

    private final AvionRepository avionRepository;

    public AvionService(AvionRepository avionRepository) {
        this.avionRepository = avionRepository;
    }

    public List<entity.Avion> findAll() {
        return avionRepository.findAll();
    }

    public Optional<entity.Avion> findById(Long id) {
        return avionRepository.findById(id);
    }

    public entity.Avion save(entity.Avion avion) {
        return avionRepository.save(avion);
    }

    public void deleteById(Long id) {
        avionRepository.deleteById(id);
    }
}
