package service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import repository.VolRepository;

@Service
public class VolService {

    private final VolRepository volRepository;

    public VolService(VolRepository volRepository) {
        this.volRepository = volRepository;
    }

    public List<entity.Vol> findAll() {
        return volRepository.findAll();
    }

    public Optional<entity.Vol> findById(Long id) {
        return volRepository.findById(id);
    }

    public List<entity.Vol> findByDepartIata(String departIata) {
        return volRepository.findByDepartIata(departIata);
    }

    public entity.Vol save(entity.Vol vol) {
        return volRepository.save(vol);
    }

    public void deleteById(Long id) {
        volRepository.deleteById(id);
    }
}
