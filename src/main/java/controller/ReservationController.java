package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class ReservationController {
    
    @GetMapping("/reservation/{volId}")
    public String formulairePassager(@PathVariable Long volId, Model model) {
        // Récupérer le vol
        entity.Vol vol = getVolById(volId);
        
        if (vol != null) {
            model.addAttribute("vol", vol);
            model.addAttribute("passager", new entity.Passager());
            return "informations-passager";
        }
        
        return "redirect:/vols";
    }
    
    @PostMapping("/reservation/{volId}/passager")
    public String traiterPassager(@PathVariable Long volId, 
                                 @ModelAttribute entity.Passager passager,
                                 Model model) {
        // Enregistrer les infos passager (simulation)
        passager.setId(1L);
        
        model.addAttribute("volId", volId);
        model.addAttribute("passager", passager);
        
        return "choix-siege";
    }
    
    @GetMapping("/reservation/{volId}/siege")
    public String choixSiege(@PathVariable Long volId, Model model) {
        model.addAttribute("volId", volId);
        // Simuler des sièges
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
        entity.Vol vol = getVolById(volId);
        if (vol != null) {
            model.addAttribute("vol", vol);
            model.addAttribute("total", vol.getPrix() + 30.00); // Prix + option
            return "paiement";
        }
        return "redirect:/vols";
    }
    
    @GetMapping("/reservation/confirmation/{id}")
    public String confirmation(@PathVariable Long id, Model model) {
        // Simuler une réservation
        entity.Reservation reservation = new entity.Reservation();
        reservation.setId(id);
        reservation.setDateReservation(java.time.LocalDateTime.now());
        reservation.setStatut("CONFIRME");
        
        entity.Vol vol = getVolById(1L);
        if (vol != null) {
            reservation.setVol(vol);
        }
        
        model.addAttribute("reservation", reservation);
        return "confirmation";
    }
    
    // Méthodes utilitaires
    private entity.Vol getVolById(Long id) {
        if (id == 1L) {
            return new entity.Vol(1L, "MK101", 
                java.time.LocalDate.of(2026, 1, 15),
                java.time.LocalTime.of(8, 0),
                java.time.LocalTime.of(16, 0),
                450.00, "TNR", "CDG", 1L);
        }
        return null;
    }
    
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