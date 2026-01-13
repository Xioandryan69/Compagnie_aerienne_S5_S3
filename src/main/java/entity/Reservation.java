package entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_reservation", nullable = false)
    private LocalDateTime dateReservation;

    @Column(name = "statut", nullable = false)
    private String statut;

    @Column(name = "vol_id", nullable = false)
    private Long volId;

    @Column(name = "passager_id", nullable = false)
    private Long passagerId;

    @ManyToOne
    @JoinColumn(name = "vol_id", insertable = false, updatable = false)
    private Vol vol;

    @ManyToOne
    @JoinColumn(name = "passager_id", insertable = false, updatable = false)
    private Passager passager;

    public Reservation() {
    }

    public Reservation(Long id, LocalDateTime dateReservation, String statut, Long volId, Long passagerId) {
        this.id = id;
        this.dateReservation = dateReservation;
        this.statut = statut;
        this.volId = volId;
        this.passagerId = passagerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(LocalDateTime dateReservation) {
        this.dateReservation = dateReservation;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Long getVolId() {
        return volId;
    }

    public void setVolId(Long volId) {
        this.volId = volId;
    }

    public Long getPassagerId() {
        return passagerId;
    }

    public void setPassagerId(Long passagerId) {
        this.passagerId = passagerId;
    }

    public Vol getVol() {
        return vol;
    }

    public Passager getPassager() {
        return passager;
    }
}
