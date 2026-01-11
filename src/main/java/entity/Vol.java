package entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "vol")
public class Vol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_vol", nullable = false)
    private String numeroVol;

    @Column(name = "date_vol", nullable = false)
    private LocalDate dateVol;

    @Column(name = "heure_depart", nullable = false)
    private LocalTime heureDepart;

    @Column(name = "heure_arrivee", nullable = false)
    private LocalTime heureArrivee;

    @Column(name = "prix", nullable = false)
    private double prix;

    @Column(name = "depart_iata", nullable = false)
    private String departIata;

    @Column(name = "arrivee_iata", nullable = false)
    private String arriveeIata;

    @Column(name = "avion_id", nullable = false)
    private Long avionId;

    // Pour l'affichage (relations) - non persistées
    @Transient
    private Aeroport depart;

    @Transient
    private Aeroport arrivee;

    @Transient
    private Avion avion;

    // Constructeurs
    public Vol() {
    }

    public Vol(Long id, String numeroVol, LocalDate dateVol, LocalTime heureDepart,
            LocalTime heureArrivee, double prix, String departIata,
            String arriveeIata, Long avionId) {
        this.id = id;
        this.numeroVol = numeroVol;
        this.dateVol = dateVol;
        this.heureDepart = heureDepart;
        this.heureArrivee = heureArrivee;
        this.prix = prix;
        this.departIata = departIata;
        this.arriveeIata = arriveeIata;
        this.avionId = avionId;
    }

    // Getters/Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroVol() {
        return numeroVol;
    }

    public void setNumeroVol(String numeroVol) {
        this.numeroVol = numeroVol;
    }

    public LocalDate getDateVol() {
        return dateVol;
    }

    public void setDateVol(LocalDate dateVol) {
        this.dateVol = dateVol;
    }

    public LocalTime getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(LocalTime heureDepart) {
        this.heureDepart = heureDepart;
    }

    public LocalTime getHeureArrivee() {
        return heureArrivee;
    }

    public void setHeureArrivee(LocalTime heureArrivee) {
        this.heureArrivee = heureArrivee;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getDepartIata() {
        return departIata;
    }

    public void setDepartIata(String departIata) {
        this.departIata = departIata;
    }

    public String getArriveeIata() {
        return arriveeIata;
    }

    public void setArriveeIata(String arriveeIata) {
        this.arriveeIata = arriveeIata;
    }

    public Long getAvionId() {
        return avionId;
    }

    public void setAvionId(Long avionId) {
        this.avionId = avionId;
    }

    public Aeroport getDepart() {
        return depart;
    }

    public void setDepart(Aeroport depart) {
        this.depart = depart;
    }

    public Aeroport getArrivee() {
        return arrivee;
    }

    public void setArrivee(Aeroport arrivee) {
        this.arrivee = arrivee;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }
}
