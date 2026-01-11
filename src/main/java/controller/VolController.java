package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class VolController {
    
    @GetMapping("/vols")
    public String listeVols(Model model) {
        model.addAttribute("vols", getVolsStatiques());
        return "vols";
    }
    
    @GetMapping("/vol/{id}")
    public String detailVol(@PathVariable Long id, Model model) {
        // Récupérer le vol par ID
        entity.Vol vol = getVolById(id);
        
        if (vol != null) {
            // Simuler les relations
            vol.setDepart(getAeroportByCode(vol.getDepartIata()));
            vol.setArrivee(getAeroportByCode(vol.getArriveeIata()));
            vol.setAvion(getAvionById(vol.getAvionId()));
            
            model.addAttribute("vol", vol);
            model.addAttribute("optionsSupplementaires", getOptionsStatiques());
            return "details-vol";
        }
        
        return "redirect:/vols";
    }
    
    // Méthodes pour données statiques
    private java.util.List<entity.Vol> getVolsStatiques() {
        java.util.List<entity.Vol> vols = new java.util.ArrayList<>();
        
        vols.add(new entity.Vol(1L, "MK101", 
            java.time.LocalDate.of(2026, 1, 15),
            java.time.LocalTime.of(8, 0),
            java.time.LocalTime.of(16, 0),
            450.00, "TNR", "CDG", 1L));
        
        vols.add(new entity.Vol(2L, "MK102",
            java.time.LocalDate.of(2026, 1, 16),
            java.time.LocalTime.of(10, 0),
            java.time.LocalTime.of(18, 0),
            470.00, "TNR", "CDG", 2L));
        
        return vols;
    }
    
    private entity.Vol getVolById(Long id) {
        for (entity.Vol vol : getVolsStatiques()) {
            if (vol.getId().equals(id)) {
                return vol;
            }
        }
        return null;
    }
    
    private entity.Aeroport getAeroportByCode(String code) {
        if ("TNR".equals(code)) {
            return new entity.Aeroport("TNR", "Ivato", "Antananarivo", "Madagascar");
        } else if ("CDG".equals(code)) {
            return new entity.Aeroport("CDG", "Charles de Gaulle", "Paris", "France");
        } else if ("JFK".equals(code)) {
            return new entity.Aeroport("JFK", "John F. Kennedy", "New York", "USA");
        }
        return null;
    }
    
    private entity.Avion getAvionById(Long id) {
        if (id == 1L) {
            return new entity.Avion(1L, "Airbus A320", 180, "DISPONIBLE");
        } else if (id == 2L) {
            return new entity.Avion(2L, "Boeing 737", 160, "DISPONIBLE");
        }
        return null;
    }
    
    private java.util.List<entity.OptionSupplementaire> getOptionsStatiques() {
        java.util.List<entity.OptionSupplementaire> options = new java.util.ArrayList<>();
        options.add(new entity.OptionSupplementaire(1L, "BAGAGE", "+1 bagage 23kg", 30.00, null));
        options.add(new entity.OptionSupplementaire(2L, "REPAS", "Repas végétarien", 10.00, null));
        options.add(new entity.OptionSupplementaire(3L, "ASSURANCE", "Assurance voyage", 20.00, null));
        return options;
    }
}