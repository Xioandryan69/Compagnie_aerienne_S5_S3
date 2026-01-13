package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import repository.VolRepository;
import repository.PassagerRepository;
import repository.ReservationRepository;
import service.ReservationService;

@Controller
public class ReservationController {

    private final VolRepository volRepository;
    private final PassagerRepository passagerRepository;
    private final ReservationRepository reservationRepository;
    private final ReservationService reservationService;

    public ReservationController(VolRepository volRepository,
            PassagerRepository passagerRepository,
            ReservationRepository reservationRepository,
            ReservationService reservationService) {
        this.volRepository = volRepository;
        this.passagerRepository = passagerRepository;
        this.reservationRepository = reservationRepository;
        this.reservationService = reservationService;
    }

    @GetMapping("/reservation/{volId}")
    public String formulairePassager(@PathVariable Long volId, Model model) {
        return volRepository.findById(volId)
                .map(vol -> {
                    model.addAttribute("vol", vol);
                    model.addAttribute("passager", new entity.Passager());
                    return "informations-passager";
                })
                .orElse("redirect:/vols");
    }

    @PostMapping("/reservation/{volId}/passager")
    public String traiterPassager(@PathVariable Long volId,
            @ModelAttribute entity.Passager passager,
            Model model) {
        entity.Passager saved = passagerRepository.save(passager);
        model.addAttribute("volId", volId);
        model.addAttribute("passager", saved);
        return "choix-siege";
    }

    // Other steps (siege/options/paiement/confirmation) can continue to use templates; keep simple flows
    @GetMapping("/reservation/{volId}/siege")
    public String choixSiege(@PathVariable Long volId, Model model) {
        model.addAttribute("volId", volId);
        model.addAttribute("sieges", generateSieges());
        return "choix-siege";
    }

    @GetMapping("/reservation/{volId}/options")
    public String optionsSupplementaires(@PathVariable Long volId, Model model) {
        model.addAttribute("volId", volId);
        // show options from DB if available, else empty
        model.addAttribute("options", java.util.Collections.emptyList());
        return "options";
    }

    @GetMapping("/reservation/{volId}/paiement")
    public String paiement(@PathVariable Long volId, Model model) {
        return volRepository.findById(volId)
                .map(vol -> {
                    model.addAttribute("vol", vol);
                    model.addAttribute("total", vol.getPrix() + 30.00);
                    return "paiement";
                }).orElse("redirect:/vols");
    }

    @GetMapping("/reservation/confirmation/{id}")
    public String confirmation(@PathVariable Long id, Model model) {
        return reservationRepository.findById(id)
                .map(res -> {
                    model.addAttribute("reservation", res);
                    return "confirmation";
                }).orElse("redirect:/vols");
    }

    private java.util.List<String> generateSieges() {
        java.util.List<String> sieges = new java.util.ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            sieges.add("A" + i);
            sieges.add("B" + i);
            sieges.add("C" + i);
            sieges.add("D" + i);
        }
        return sieges;
    }
}
