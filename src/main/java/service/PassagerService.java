package service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import repository.PassagerRepository;

@Service
public class PassagerService {

    private final PassagerRepository passagerRepository;

    public PassagerService(PassagerRepository passagerRepository) {
        this.passagerRepository = passagerRepository;
    }

    public List<entity.Passager> findAll() {
        return passagerRepository.findAll();
    }

    public Optional<entity.Passager> findById(Long id) {
        return passagerRepository.findById(id);
    }

    public entity.Passager save(entity.Passager passager) {
        return passagerRepository.save(passager);
    }

    public void deleteById(Long id) {
        passagerRepository.deleteById(id);
    }
}
