package entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

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
    private Double prix;

    @Column(name = "depart_iata", nullable = false)
    private String departIata;

    @Column(name = "arrivee_iata", nullable = false)
    private String arriveeIata;

    @Column(name = "avion_id", nullable = false)
    private Long avionId;

    // Relations (read-only view of foreign keys)
    @ManyToOne
    @JoinColumn(name = "depart_iata", referencedColumnName = "code_iata", insertable = false, updatable = false)
    private Aeroport depart;

    @ManyToOne
    @JoinColumn(name = "arrivee_iata", referencedColumnName = "code_iata", insertable = false, updatable = false)
    private Aeroport arrivee;

    @ManyToOne
    @JoinColumn(name = "avion_id", insertable = false, updatable = false)
    private Avion avion;

    public Vol() {
    }

    public Vol(Long id, String numeroVol, LocalDate dateVol, LocalTime heureDepart, LocalTime heureArrivee, Double prix, String departIata, String arriveeIata, Long avionId) {
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

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
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

    public Aeroport getArrivee() {
        return arrivee;
    }

    public Avion getAvion() {
        return avion;
    }
}
