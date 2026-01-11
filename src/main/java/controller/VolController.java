package controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import entity.Vol;
import repository.AeroportRepository;
import repository.AvionRepository;
import repository.OptionSupplementaireRepository;
import service.VolService;

@Controller
public class VolController {

    private final VolService volService;
    private final OptionSupplementaireRepository optionRepo;
    private final AeroportRepository aeroportRepo;
    private final AvionRepository avionRepo;

    public VolController(VolService volService,
            OptionSupplementaireRepository optionRepo,
            AeroportRepository aeroportRepo,
            AvionRepository avionRepo) {
        this.volService = volService;
        this.optionRepo = optionRepo;
        this.aeroportRepo = aeroportRepo;
        this.avionRepo = avionRepo;
    }

    // Public pages
    @GetMapping("/vols")
    public String listeVols(Model model) {
        List<Vol> vols = volService.findAll();
        model.addAttribute("vols", vols);
        return "vols";
    }

    @GetMapping("/vol/{id}")
    public String detailVol(@PathVariable Long id, Model model) {
        return volService.findById(id)
                .map(vol -> {
                    model.addAttribute("vol", vol);
                    model.addAttribute("optionsSupplementaires", optionRepo.findAll());
                    return "details-vol";
                })
                .orElse("redirect:/vols");
    }

    // Admin CRUD (basic backend)
    @GetMapping("/admin/vols")
    public String adminList(Model model) {
        model.addAttribute("vols", volService.findAll());
        return "gestion-vols";
    }

    @GetMapping("/admin/vols/new")
    public String newVolForm(Model model) {
        model.addAttribute("vol", new Vol());
        model.addAttribute("aeroports", aeroportRepo.findAll());
        model.addAttribute("avions", avionRepo.findAll());
        return "gestion-vols"; // utiliser le template admin (adapter la vue si besoin)
    }

    @PostMapping("/admin/vols")
    public String createVol(@ModelAttribute Vol vol) {
        volService.save(vol);
        return "redirect:/admin/vols";
    }

    @GetMapping("/admin/vols/{id}/edit")
    public String editVolForm(@PathVariable Long id, Model model) {
        return volService.findById(id).map(v -> {
            model.addAttribute("vol", v);
            model.addAttribute("aeroports", aeroportRepo.findAll());
            model.addAttribute("avions", avionRepo.findAll());
            return "gestion-vols";
        }).orElse("redirect:/admin/vols");
    }

    @PostMapping("/admin/vols/{id}/delete")
    public String deleteVol(@PathVariable Long id) {
        volService.deleteById(id);
        return "redirect:/admin/vols";
    }
}
