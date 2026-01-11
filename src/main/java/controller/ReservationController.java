package controller;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import dto.ReservationDTO;
import entity.Passager;
import entity.Reservation;
import repository.PassagerRepository;
import service.ReservationService;
import service.VolService;

@Controller
public class ReservationController {

    private final VolService volService;
    private final ReservationService reservationService;
    private final PassagerRepository passagerRepository;

    public ReservationController(VolService volService, ReservationService reservationService, PassagerRepository passagerRepository) {
        this.volService = volService;
        this.reservationService = reservationService;
        this.passagerRepository = passagerRepository;
    }

    @GetMapping("/reservation/{volId}")
    public String formulairePassager(@PathVariable Long volId, Model model) {
        return volService.findById(volId).map(vol -> {
            model.addAttribute("vol", vol);
            model.addAttribute("passager", new Passager());
            return "informations-passager";
        }).orElse("redirect:/vols");
    }

    @PostMapping("/reservation/{volId}/passager")
    public String traiterPassager(@PathVariable Long volId,
            @ModelAttribute Passager passager,
            Model model) {
        // Enregistrer le passager
        Passager saved = passagerRepository.save(passager);

        // Créer la réservation
        Reservation r = new Reservation();
        r.setDateReservation(LocalDateTime.now());
        r.setStatut("EN_COURS");
        r.setVolId(volId);
        r.setPassagerId(saved.getId());

        Reservation savedRes = reservationService.save(r);

        model.addAttribute("volId", volId);
        model.addAttribute("passager", saved);
        model.addAttribute("reservation", savedRes);

        return "choix-siege";
    }

    @PostMapping("/reservation/create")
    public String creerReservationDepuisDto(@ModelAttribute ReservationDTO dto, Model model) {
        // delegate to service
        Reservation saved = reservationService.createFromDto(dto);
        // redirect to confirmation page
        return "redirect:/reservation/confirmation/" + saved.getId();
    }

    @GetMapping("/reservation/{volId}/siege")
    public String choixSiege(@PathVariable Long volId, Model model) {
        model.addAttribute("volId", volId);
        model.addAttribute("sieges", getSiegesStatiques());
        return "choix-siege";
    }

    @GetMapping("/reservation/{volId}/options")
    public String optionsSupplementaires(@PathVariable Long volId, Model model) {
        model.addAttribute("volId", volId);
        model.addAttribute("options", getOptionsStatiques());
        return "options";
    }

    @GetMapping("/reservation/{volId}/paiement")
    public String paiement(@PathVariable Long volId, Model model) {
        return volService.findById(volId).map(vol -> {
            model.addAttribute("vol", vol);
            model.addAttribute("total", vol.getPrix() + 30.00);
            return "paiement";
        }).orElse("redirect:/vols");
    }

    @GetMapping("/reservation/confirmation/{id}")
    public String confirmation(@PathVariable Long id, Model model) {
        return reservationService.findById(id).map(res -> {
            // charger vol et passager pour affichage
            volService.findById(res.getVolId()).ifPresent(res::setVol);
            passagerRepository.findById(res.getPassagerId()).ifPresent(res::setPassager);
            model.addAttribute("reservation", res);
            return "confirmation";
        }).orElse("redirect:/vols");
    }

    // Méthodes utilitaires
    private java.util.List<String> getSiegesStatiques() {
        java.util.List<String> sieges = new java.util.ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            sieges.add("A" + i);
            sieges.add("B" + i);
            sieges.add("C" + i);
            sieges.add("D" + i);
        }
        return sieges;
    }

    private java.util.List<entity.OptionSupplementaire> getOptionsStatiques() {
        java.util.List<entity.OptionSupplementaire> options = new java.util.ArrayList<>();
        options.add(new entity.OptionSupplementaire(1L, "BAGAGE", "+1 bagage 23kg", 30.00, null));
        options.add(new entity.OptionSupplementaire(2L, "REPAS", "Repas végétarien", 10.00, null));
        options.add(new entity.OptionSupplementaire(3L, "ASSURANCE", "Assurance voyage", 20.00, null));
        return options;
    }
}
