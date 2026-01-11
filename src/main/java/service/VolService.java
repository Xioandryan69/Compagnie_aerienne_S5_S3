package service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import entity.Aeroport;
import entity.Vol;
import repository.AeroportRepository;
import repository.AvionRepository;
import repository.VolRepository;

@Service
@Transactional(readOnly = true)
public class VolService {

    private final VolRepository volRepository;
    private final AeroportRepository aeroportRepository;
    private final AvionRepository avionRepository;

    public VolService(VolRepository volRepository, AeroportRepository aeroportRepository, AvionRepository avionRepository) {
        this.volRepository = volRepository;
        this.aeroportRepository = aeroportRepository;
        this.avionRepository = avionRepository;
    }

    public List<Vol> findAll() {
        List<Vol> vols = volRepository.findAll();
        // charger relations transitoires
        for (Vol v : vols) {
            loadRelations(v);
        }
        return vols;
    }

    public Optional<Vol> findById(Long id) {
        Optional<Vol> opt = volRepository.findById(id);
        opt.ifPresent(this::loadRelations);
        return opt;
    }

    public List<Vol> findByDepartIata(String departIata) {
        List<Vol> vols = volRepository.findByDepartIata(departIata);
        for (Vol v : vols) {
            loadRelations(v);
        }
        return vols;
    }

    public java.util.List<Aeroport> findAllAeroports() {
        return aeroportRepository.findAll();
    }

    private void loadRelations(Vol v) {
        if (v.getDepartIata() != null) {
            aeroportRepository.findById(v.getDepartIata()).ifPresent(v::setDepart);
        }
        if (v.getArriveeIata() != null) {
            aeroportRepository.findById(v.getArriveeIata()).ifPresent(v::setArrivee);
        }
        if (v.getAvionId() != null) {
            avionRepository.findById(v.getAvionId()).ifPresent(v::setAvion);
        }
    }

    // Persist a vol (create or update)
    public Vol save(Vol vol) {
        Vol saved = volRepository.save(vol);
        loadRelations(saved);
        return saved;
    }

    // Delete by id
    public void deleteById(Long id) {
        volRepository.deleteById(id);
    }
}
