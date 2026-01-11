package entity;

public class Avion {
    private Long id;
    private String modele;
    private int capacite;
    private String etat;
    
    // Constructeurs
    public Avion() {}
    
    public Avion(Long id, String modele, int capacite, String etat) {
        this.id = id;
        this.modele = modele;
        this.capacite = capacite;
        this.etat = etat;
    }
    
    // Getters/Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getModele() { return modele; }
    public void setModele(String modele) { this.modele = modele; }
    
    public int getCapacite() { return capacite; }
    public void setCapacite(int capacite) { this.capacite = capacite; }
    
    public String getEtat() { return etat; }
    public void setEtat(String etat) { this.etat = etat; }
}