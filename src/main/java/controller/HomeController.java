package controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import dto.RechercheDTO;
import entity.Vol;
import service.VolService;

@Controller
public class HomeController {

    private final VolService volService;

    public HomeController(VolService volService) {
        this.volService = volService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("titre", "Bienvenue chez ITU Airlines");
        model.addAttribute("message", "Voyagez loin, payez juste.");
        model.addAttribute("recherche", new RechercheDTO());

        // Récupérer les aéroports depuis la base via le service
        model.addAttribute("aeroports", volService.findAllAeroports());

        return "index";
    }

    @PostMapping("/rechercher")
    public String rechercherVols(@ModelAttribute RechercheDTO recherche, Model model) {
        model.addAttribute("critere", recherche);

        // Recherche simple : si depart renseigné, filtrer par depart; sinon renvoyer tout
        List<Vol> vols;
        if (recherche.getDepart() != null && !recherche.getDepart().isBlank()) {
            vols = volService.findByDepartIata(recherche.getDepart());
        } else {
            vols = volService.findAll();
        }

        // filtrage optionnel par arrivée ou date
        if (recherche.getArrivee() != null && !recherche.getArrivee().isBlank()) {
            vols = vols.stream()
                    .filter(v -> recherche.getArrivee().equalsIgnoreCase(v.getArriveeIata()))
                    .collect(Collectors.toList());
        }
        if (recherche.getDateDepart() != null) {
            vols = vols.stream()
                    .filter(v -> recherche.getDateDepart().equals(v.getDateVol()))
                    .collect(Collectors.toList());
        }

        model.addAttribute("vols", vols);

        return "vols";
    }
}
                  
