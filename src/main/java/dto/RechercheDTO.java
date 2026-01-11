package dto;

import java.time.LocalDate;

public class RechercheDTO {
    private String depart;
    private String arrivee;
    private LocalDate dateDepart;
    private LocalDate dateRetour;
    private int nombrePassagers;
    private String classe;
    
    // Constructeurs
    public RechercheDTO() {}
    
    public RechercheDTO(String depart, String arrivee, LocalDate dateDepart, 
                       LocalDate dateRetour, int nombrePassagers, String classe) {
        this.depart = depart;
        this.arrivee = arrivee;
        this.dateDepart = dateDepart;
        this.dateRetour = dateRetour;
        this.nombrePassagers = nombrePassagers;
        this.classe = classe;
    }
    
    // Getters/Setters
    public String getDepart() { return depart; }
    public void setDepart(String depart) { this.depart = depart; }
    
    public String getArrivee() { return arrivee; }
    public void setArrivee(String arrivee) { this.arrivee = arrivee; }
    
    public LocalDate getDateDepart() { return dateDepart; }
    public void setDateDepart(LocalDate dateDepart) { this.dateDepart = dateDepart; }
    
    public LocalDate getDateRetour() { return dateRetour; }
    public void setDateRetour(LocalDate dateRetour) { this.dateRetour = dateRetour; }
    
    public int getNombrePassagers() { return nombrePassagers; }
    public void setNombrePassagers(int nombrePassagers) { this.nombrePassagers = nombrePassagers; }
    
    public String getClasse() { return classe; }
    public void setClasse(String classe) { this.classe = classe; }
}