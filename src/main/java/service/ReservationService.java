package service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dto.ReservationDTO;
import entity.OptionSupplementaire;
import entity.Passager;
import entity.Reservation;
import repository.OptionSupplementaireRepository;
import repository.PassagerRepository;
import repository.ReservationRepository;
import repository.VolRepository;

@Service
@Transactional
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final PassagerRepository passagerRepository;
    private final VolRepository volRepository;
    private final OptionSupplementaireRepository optionRepository;

    public ReservationService(ReservationRepository reservationRepository,
            PassagerRepository passagerRepository,
            VolRepository volRepository,
            OptionSupplementaireRepository optionRepository) {
        this.reservationRepository = reservationRepository;
        this.passagerRepository = passagerRepository;
        this.volRepository = volRepository;
        this.optionRepository = optionRepository;
    }

    public Optional<Reservation> findById(Long id) {
        return reservationRepository.findById(id);
    }

    public Reservation save(Reservation r) {
        // validations/minimales ici si nécessaire
        return reservationRepository.save(r);
    }

    /**
     * Create a reservation from a DTO: creates Passager, Reservation and
     * OptionSupplementaire rows
     */
    public Reservation createFromDto(ReservationDTO dto) {
        // 1. create/save passager
        Passager p = new Passager();
        p.setNom(dto.getNom());
        p.setPrenom(dto.getPrenom());
        p.setDateNaissance(dto.getDateNaissance());
        p.setNumeroDocument(dto.getNumeroDocument());
        p.setNationalite(dto.getNationalite());
        p.setEmail(dto.getEmail());
        p.setTelephone(dto.getTelephone());
        p.setSexe(dto.getSexe());

        Passager savedPassager = passagerRepository.save(p);

        // 2. create reservation
        Reservation r = new Reservation();
        r.setDateReservation(java.time.LocalDateTime.now());
        r.setStatut("EN_COURS");
        r.setVolId(dto.getVolId());
        r.setPassagerId(savedPassager.getId());

        Reservation savedRes = reservationRepository.save(r);

        // 3. create options if any
        if (dto.getOptionIds() != null) {
            for (Long optId : dto.getOptionIds()) {
                OptionSupplementaire o = new OptionSupplementaire();
                // If options are predefined in DB we might fetch and clone them; here we just create linkage
                o.setTypeOption("OPTION");
                o.setDescription("Option id: " + optId);
                o.setPrix(0.0);
                o.setReservationId(savedRes.getId());
                optionRepository.save(o);
            }
        }

        // load transient relations if needed
        volRepository.findById(savedRes.getVolId()).ifPresent(savedRes::setVol);
        passagerRepository.findById(savedPassager.getId()).ifPresent(savedRes::setPassager);

        return savedRes;
    }
}
