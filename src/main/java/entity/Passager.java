package entity;

import java.time.LocalDate;

public class Passager {
    private Long id;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String numeroDocument;
    private String nationalite;
    private String email;
    private String telephone;
    private String sexe; // Ajout pour le formulaire
    
    // Constructeurs
    public Passager() {}
    
    public Passager(Long id, String nom, String prenom, LocalDate dateNaissance, 
                   String numeroDocument, String nationalite, String email, 
                   String telephone) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.numeroDocument = numeroDocument;
        this.nationalite = nationalite;
        this.email = email;
        this.telephone = telephone;
    }
    
    // Getters/Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    
    public LocalDate getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(LocalDate dateNaissance) { this.dateNaissance = dateNaissance; }
    
    public String getNumeroDocument() { return numeroDocument; }
    public void setNumeroDocument(String numeroDocument) { this.numeroDocument = numeroDocument; }
    
    public String getNationalite() { return nationalite; }
    public void setNationalite(String nationalite) { this.nationalite = nationalite; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    
    public String getSexe() { return sexe; }
    public void setSexe(String sexe) { this.sexe = sexe; }
}