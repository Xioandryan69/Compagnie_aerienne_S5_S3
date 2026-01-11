package entity;

import java.time.LocalDateTime;

public class Reservation {
    private Long id;
    private LocalDateTime dateReservation;
    private String statut;
    private Long volId;
    private Long passagerId;
    
    // Pour l'affichage
    private Vol vol;
    private Passager passager;
    
    // Constructeurs
    public Reservation() {}
    
    public Reservation(Long id, LocalDateTime dateReservation, String statut, 
                      Long volId, Long passagerId) {
        this.id = id;
        this.dateReservation = dateReservation;
        this.statut = statut;
        this.volId = volId;
        this.passagerId = passagerId;
    }
    
    // Getters/Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public LocalDateTime getDateReservation() { return dateReservation; }
    public void setDateReservation(LocalDateTime dateReservation) { this.dateReservation = dateReservation; }
    
    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }
    
    public Long getVolId() { return volId; }
    public void setVolId(Long volId) { this.volId = volId; }
    
    public Long getPassagerId() { return passagerId; }
    public void setPassagerId(Long passagerId) { this.passagerId = passagerId; }
    
    public Vol getVol() { return vol; }
    public void setVol(Vol vol) { this.vol = vol; }
    
    public Passager getPassager() { return passager; }
    public void setPassager(Passager passager) { this.passager = passager; }
}