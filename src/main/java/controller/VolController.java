package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import dto.RechercheDTO;
import service.OptionSupplementaireService;
import service.VolService;

@Controller
public class VolController {

    private final VolService volService;
    private final OptionSupplementaireService optionService;

    public VolController(VolService volService, OptionSupplementaireService optionService) {
        this.volService = volService;
        this.optionService = optionService;
    }

    @GetMapping("/vols")
    public String listeVols(Model model) {
        model.addAttribute("vols", volService.findAll());
        // Provide an empty search criteria so the template can safely reference fields like critere.depart
        model.addAttribute("critere", new RechercheDTO());
        model.addAttribute("nombreVols", volService.findAll().size());
        return "vols";
    }

    @GetMapping("/vol/{id}")
    public String detailVol(@PathVariable Long id, Model model) {
        return volService.findById(id)
                .map(vol -> {
                    model.addAttribute("vol", vol);
                    model.addAttribute("optionsSupplementaires", optionService.findAll());
                    return "details-vol";
                })
                .orElse("redirect:/vols");
    }
}
