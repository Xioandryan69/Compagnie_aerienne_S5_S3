package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import dto.RechercheDTO;
import service.AeroportService;
import service.VolService;

@Controller
public class HomeController {

    private final AeroportService aeroportService;
    private final VolService volService;

    public HomeController(AeroportService aeroportService, VolService volService) {
        this.aeroportService = aeroportService;
        this.volService = volService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("titre", "Bienvenue chez ITU Airlines");
        model.addAttribute("message", "Voyagez loin, payez juste.");
        model.addAttribute("recherche", new RechercheDTO());

        model.addAttribute("aeroports", aeroportService.findAll());

        return "index";
    }

    @PostMapping("/rechercher")
    public String rechercherVols(@ModelAttribute RechercheDTO recherche, Model model) {
        model.addAttribute("critere", recherche);

        if (recherche.getDepart() != null && !recherche.getDepart().isEmpty()) {
            model.addAttribute("vols", volService.findByDepartIata(recherche.getDepart()));
        } else {
            model.addAttribute("vols", volService.findAll());
        }

        return "vols";
    }
}
