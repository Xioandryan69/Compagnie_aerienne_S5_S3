package service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repository.PassagerRepository;
import repository.ReservationRepository;
import repository.OptionSupplementaireRepository;

@Service
public class ReservationService {

    private final PassagerRepository passagerRepository;
    private final ReservationRepository reservationRepository;
    private final OptionSupplementaireRepository optionRepository;

    public ReservationService(PassagerRepository passagerRepository,
            ReservationRepository reservationRepository,
            OptionSupplementaireRepository optionRepository) {
        this.passagerRepository = passagerRepository;
        this.reservationRepository = reservationRepository;
        this.optionRepository = optionRepository;
    }

    @Transactional
    public entity.Reservation createReservation(entity.Passager passager, entity.Reservation reservation, java.util.List<entity.OptionSupplementaire> options) {
        entity.Passager saved = passagerRepository.save(passager);
        reservation.setPassagerId(saved.getId());
        entity.Reservation savedRes = reservationRepository.save(reservation);

        if (options != null) {
            for (entity.OptionSupplementaire opt : options) {
                opt.setReservationId(savedRes.getId());
                optionRepository.save(opt);
            }
        }

        return savedRes;
    }
}
