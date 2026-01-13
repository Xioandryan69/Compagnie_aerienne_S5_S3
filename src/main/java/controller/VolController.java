package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import repository.OptionSupplementaireRepository;
import service.VolService;

@Controller
public class VolController {

    private final VolService volService;
    private final OptionSupplementaireRepository optionRepository;

    public VolController(VolService volService, OptionSupplementaireRepository optionRepository) {
        this.volService = volService;
        this.optionRepository = optionRepository;
    }

    @GetMapping("/vols")
    public String listeVols(Model model) {
        model.addAttribute("vols", volService.findAll());
        return "vols";
    }

    @GetMapping("/vol/{id}")
    public String detailVol(@PathVariable Long id, Model model) {
        return volService.findById(id)
                .map(vol -> {
                    model.addAttribute("vol", vol);
                    model.addAttribute("optionsSupplementaires", optionRepository.findAll());
                    return "details-vol";
                })
                .orElse("redirect:/vols");
    }
}
