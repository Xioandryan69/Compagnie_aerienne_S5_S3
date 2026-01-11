package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "aeroport")
public class Aeroport {

    @Id
    @Column(name = "code_iata", length = 3)
    private String codeIata;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "ville", nullable = false)
    private String ville;

    @Column(name = "pays", nullable = false)
    private String pays;

    // Constructeurs
    public Aeroport() {
    }

    public Aeroport(String codeIata, String nom, String ville, String pays) {
        this.codeIata = codeIata;
        this.nom = nom;
        this.ville = ville;
        this.pays = pays;
    }

    // Getters/Setters
    public String getCodeIata() {
        return codeIata;
    }

    public void setCodeIata(String codeIata) {
        this.codeIata = codeIata;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }
}
