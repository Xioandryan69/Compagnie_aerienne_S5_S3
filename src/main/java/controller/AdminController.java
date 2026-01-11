package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    
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
        return "gestion-vols";
    }
    
    @GetMapping("/admin/avions")
    public String gestionAvions(Model model) {
        return "gestion-avions";
    }
    
    @GetMapping("/admin/aeroports")
    public String gestionAeroports(Model model) {
        return "gestion-aeroports";
    }
    
    @GetMapping("/admin/passagers")
    public String gestionPassagers(Model model) {
        return "gestion-passagers";
    }
    
    @GetMapping("/deconnexion")
    public String deconnexion() {
        return "redirect:/";
    }
}