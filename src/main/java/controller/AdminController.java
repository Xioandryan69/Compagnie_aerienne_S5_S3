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

    @GetMapping("/admin/paiements")
    public String gestionPaiements(Model model) {
        model.addAttribute("paiements", getPaiementsStatiques());
        model.addAttribute("facturesCount", 128);
        model.addAttribute("paiementsValides", 124);
        model.addAttribute("paiementsAnnules", 3);
        return "gestion-paiements";
    }

    @GetMapping("/admin/notifications")
    public String notifications(Model model) {
        model.addAttribute("notifications", getNotificationsStatiques());
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

    // Données statiques pour paiements
    private java.util.List<java.util.Map<String,Object>> getPaiementsStatiques() {
        java.util.List<java.util.Map<String,Object>> list = new java.util.ArrayList<>();
        java.util.Map<String,Object> p1 = new java.util.HashMap<>();
        p1.put("id", "2025-001");
        p1.put("reservation", "R2025-010");
        p1.put("montant", "350.00 EUR");
        p1.put("statut", "VALIDÉ");
        p1.put("date", "2025-01-03");
        list.add(p1);

        java.util.Map<String,Object> p2 = new java.util.HashMap<>();
        p2.put("id", "2025-023");
        p2.put("reservation", "R2025-045");
        p2.put("montant", "120.00 EUR");
        p2.put("statut", "ANNULÉ");
        p2.put("date", "2025-01-10");
        list.add(p2);

        return list;
    }

    // Données statiques pour notifications
    private java.util.List<java.util.Map<String,String>> getNotificationsStatiques() {
        java.util.List<java.util.Map<String,String>> list = new java.util.ArrayList<>();
        java.util.Map<String,String> n1 = new java.util.HashMap<>();
        n1.put("type", "Retard");
        n1.put("message", "Vol AF123 retardé de 45 minutes.");
        n1.put("date", "2025-01-05");
        list.add(n1);

        java.util.Map<String,String> n2 = new java.util.HashMap<>();
        n2.put("type", "Annulation");
        n2.put("message", "Vol XY210 annulé pour raisons opérationnelles.");
        n2.put("date", "2025-01-07");
        list.add(n2);

        java.util.Map<String,String> n3 = new java.util.HashMap<>();
        n3.put("type", "Changement porte");
        n3.put("message", "Vol AF123 : Porte A2 -> B4");
        n3.put("date", "2025-01-05");
        list.add(n3);

        return list;
    }
}