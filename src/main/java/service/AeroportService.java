package service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import repository.AeroportRepository;

@Service
public class AeroportService {

    private final AeroportRepository aeroportRepository;

    public AeroportService(AeroportRepository aeroportRepository) {
        this.aeroportRepository = aeroportRepository;
    }

    public List<entity.Aeroport> findAll() {
        return aeroportRepository.findAll();
    }

    public Optional<entity.Aeroport> findById(String codeIata) {
        return aeroportRepository.findById(codeIata);
    }

    public entity.Aeroport save(entity.Aeroport aeroport) {
        return aeroportRepository.save(aeroport);
    }

    public void deleteById(String codeIata) {
        aeroportRepository.deleteById(codeIata);
    }
}
