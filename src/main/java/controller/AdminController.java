package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import service.AeroportService;
import service.AvionService;
import service.PassagerService;
import service.ReservationService;
import service.VolService;

@Controller
public class AdminController {

    private final VolService volService;
    private final AvionService avionService;
    private final AeroportService aeroportService;
    private final PassagerService passagerService;
    private final ReservationService reservationService;

    public AdminController(VolService volService,
            AvionService avionService,
            AeroportService aeroportService,
            PassagerService passagerService,
            ReservationService reservationService) {
        this.volService = volService;
        this.avionService = avionService;
        this.aeroportService = aeroportService;
        this.passagerService = passagerService;
        this.reservationService = reservationService;
    }

    @GetMapping("/admin")
    public String dashboard(Model model) {
        return "admin";
    }

    @GetMapping("/compte")
    public String compteClient(Model model) {
        return "compte-client";
    }

    @GetMapping("/connexion")
    public String connexion(Model model) {
        return "connexion";
    }

    @GetMapping("/admin/vols")
    public String gestionVols(Model model) {
        model.addAttribute("vols", volService.findAll());
        model.addAttribute("aeroports", aeroportService.findAll());
        model.addAttribute("avions", avionService.findAll());
        model.addAttribute("vol", new entity.Vol());
        return "gestion-vols";
    }

    @org.springframework.web.bind.annotation.PostMapping("/admin/vols/ajouter")
    public String ajouterVol(@org.springframework.web.bind.annotation.ModelAttribute entity.Vol vol) {
        // Ensure basic validation could be added; for now save directly
        volService.save(vol);
        return "redirect:/admin/vols";
    }

    @GetMapping("/admin/vols/edit/{id}")
    public String editVol(@PathVariable Long id, Model model) {
        model.addAttribute("vols", volService.findAll());
        model.addAttribute("aeroports", aeroportService.findAll());
        model.addAttribute("avions", avionService.findAll());
        volService.findById(id).ifPresent(v -> model.addAttribute("vol", v));
        // signaler au template d'ouvrir la modal édition
        model.addAttribute("openEditModal", true);
        return "gestion-vols";
    }

    @org.springframework.web.bind.annotation.PostMapping("/admin/vols/modifier")
    public String modifierVol(@org.springframework.web.bind.annotation.ModelAttribute entity.Vol vol) {
        volService.save(vol);
        return "redirect:/admin/vols";
    }

    @GetMapping("/admin/vols/supprimer/{id}")
    public String supprimerVol(@PathVariable Long id) {
        volService.deleteById(id);
        return "redirect:/admin/vols";
    }

    @GetMapping("/admin/avions")
    public String gestionAvions(Model model) {
        model.addAttribute("avions", avionService.findAll());
        model.addAttribute("avion", new entity.Avion());
        return "gestion-avions";
    }

    @org.springframework.web.bind.annotation.PostMapping("/admin/avions/ajouter")
    public String ajouterAvion(@org.springframework.web.bind.annotation.ModelAttribute entity.Avion avion) {
        avionService.save(avion);
        return "redirect:/admin/avions";
    }

    @org.springframework.web.bind.annotation.PostMapping("/admin/avions/modifier")
    public String modifierAvion(@org.springframework.web.bind.annotation.ModelAttribute entity.Avion avion) {
        avionService.save(avion);
        return "redirect:/admin/avions";
    }

    @GetMapping("/admin/avions/supprimer/{id}")
    public String supprimerAvion(@PathVariable Long id) {
        avionService.deleteById(id);
        return "redirect:/admin/avions";
    }

    @GetMapping("/admin/aeroports")
    public String gestionAeroports(Model model) {
        model.addAttribute("aeroports", aeroportService.findAll());
        model.addAttribute("aeroport", new entity.Aeroport());
        return "gestion-aeroports";
    }

    @org.springframework.web.bind.annotation.PostMapping("/admin/aeroports/ajouter")
    public String ajouterAeroport(@org.springframework.web.bind.annotation.ModelAttribute entity.Aeroport aeroport) {
        aeroportService.save(aeroport);
        return "redirect:/admin/aeroports";
    }

    @org.springframework.web.bind.annotation.PostMapping("/admin/aeroports/modifier")
    public String modifierAeroport(@org.springframework.web.bind.annotation.ModelAttribute entity.Aeroport aeroport) {
        aeroportService.save(aeroport);
        return "redirect:/admin/aeroports";
    }

    @GetMapping("/admin/aeroports/supprimer/{code}")
    public String supprimerAeroport(@PathVariable String code) {
        aeroportService.deleteById(code);
        return "redirect:/admin/aeroports";
    }

    @GetMapping("/admin/passagers")
    public String gestionPassagers(Model model) {
        model.addAttribute("passagers", passagerService.findAll());
        return "gestion-passagers";
    }

    @org.springframework.web.bind.annotation.PostMapping("/admin/passagers/modifier")
    public String modifierPassager(@org.springframework.web.bind.annotation.ModelAttribute entity.Passager passager) {
        passagerService.save(passager);
        return "redirect:/admin/passagers";
    }

    @GetMapping("/admin/passagers/supprimer/{id}")
    public String supprimerPassager(@PathVariable Long id) {
        passagerService.deleteById(id);
        return "redirect:/admin/passagers";
    }

    @GetMapping("/admin/utilisateurs")
    public String gestionUtilisateurs(Model model) {
        // Page "utilisateurs" not implemented separately - reuse passagers management for now
        return "redirect:/admin/passagers";
    }

    @GetMapping("/admin/paiements")
    public String gestionPaiements(Model model) {
        var reservations = reservationService.findAll();
        model.addAttribute("paiements", reservations);
        model.addAttribute("facturesCount", reservations.size());
        long valides = reservations.stream().filter(r -> r.getStatut() != null && (r.getStatut().equalsIgnoreCase("VALIDÉ") || r.getStatut().equalsIgnoreCase("CONFIRME") || r.getStatut().equalsIgnoreCase("CONFIRMED"))).count();
        long annules = reservations.stream().filter(r -> r.getStatut() != null && r.getStatut().equalsIgnoreCase("ANNULÉ")).count();
        model.addAttribute("paiementsValides", valides);
        model.addAttribute("paiementsAnnules", annules);
        return "gestion-paiements";
    }

    @GetMapping("/admin/notifications")
    public String notifications(Model model) {
        var recent = reservationService.findAll().stream()
                .sorted((a, b) -> b.getDateReservation().compareTo(a.getDateReservation()))
                .limit(5)
                .toList();
        model.addAttribute("notifications", recent);
        return "notifications";
    }

    @GetMapping("/admin/parametres")
    public String parametres(Model model) {
        model.addAttribute("classes", java.util.Arrays.asList("Economy", "Business", "First"));
        java.util.Map<String, Double> prix = new java.util.HashMap<>();
        prix.put("Economy", 120.00);
        prix.put("Business", 380.00);
        prix.put("First", 950.00);
        model.addAttribute("prix", prix);

        java.util.Map<String, String> bagages = new java.util.LinkedHashMap<>();
        bagages.put("Economy", "1 bagage en soute 23kg + 1 bagage cabine 7kg");
        bagages.put("Business", "2 bagages en soute 32kg + 1 bagage cabine 10kg");
        bagages.put("First", "3 bagages en soute 32kg + 1 bagage cabine 14kg");
        model.addAttribute("bagages", bagages);

        return "parametre";
    }

    @GetMapping("/deconnexion")
    public String deconnexion() {
        return "redirect:/";
    }
}
