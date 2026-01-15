package service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import repository.OptionSupplementaireRepository;

@Service
public class OptionSupplementaireService {

    private final OptionSupplementaireRepository optionRepository;

    public OptionSupplementaireService(OptionSupplementaireRepository optionRepository) {
        this.optionRepository = optionRepository;
    }

    public List<entity.OptionSupplementaire> findAll() {
        return optionRepository.findAll();
    }

    public Optional<entity.OptionSupplementaire> findById(Long id) {
        return optionRepository.findById(id);
    }

    public entity.OptionSupplementaire save(entity.OptionSupplementaire option) {
        return optionRepository.save(option);
    }

    public void deleteById(Long id) {
        optionRepository.deleteById(id);
    }
}
