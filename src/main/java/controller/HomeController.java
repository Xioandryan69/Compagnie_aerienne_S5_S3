package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import dto.RechercheDTO;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("titre", "Bienvenue chez ITU Airlines");
        model.addAttribute("message", "Voyagez loin, payez juste.");
        model.addAttribute("recherche", new RechercheDTO());
        
        // Données statiques pour les aéroports
        model.addAttribute("aeroports", getAeroportsStatiques());
        
        return "index";
    }
    
    @PostMapping("/rechercher")
    public String rechercherVols(@ModelAttribute RechercheDTO recherche, Model model) {
        model.addAttribute("critere", recherche);
        
        // Simuler des résultats de recherche avec données statiques
        model.addAttribute("vols", getVolsStatiques());
        
        return "vols";
    }
    
    // Méthodes pour données statiques
    private java.util.List<entity.Aeroport> getAeroportsStatiques() {
        java.util.List<entity.Aeroport> aeroports = new java.util.ArrayList<>();
        aeroports.add(new entity.Aeroport("TNR", "Ivato", "Antananarivo", "Madagascar"));
        aeroports.add(new entity.Aeroport("CDG", "Charles de Gaulle", "Paris", "France"));
        aeroports.add(new entity.Aeroport("JFK", "John F. Kennedy", "New York", "USA"));
        aeroports.add(new entity.Aeroport("LHR", "Heathrow", "London", "UK"));
        return aeroports;
    }
    
    private java.util.List<entity.Vol> getVolsStatiques() {
        java.util.List<entity.Vol> vols = new java.util.ArrayList<>();
        
        // Créer des vols de test
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
        
        vols.add(new entity.Vol(3L, "MK201",
            java.time.LocalDate.of(2026, 1, 15),
            java.time.LocalTime.of(12, 0),
            java.time.LocalTime.of(16, 0),
            300.00, "CDG", "JFK", 1L));
            
        return vols;
    }
}