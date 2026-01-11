package entity;

public class OptionSupplementaire {
    private Long id;
    private String typeOption;
    private String description;
    private double prix;
    private Long reservationId;
    
    // Constructeurs
    public OptionSupplementaire() {}
    
    public OptionSupplementaire(Long id, String typeOption, String description, 
                               double prix, Long reservationId) {
        this.id = id;
        this.typeOption = typeOption;
        this.description = description;
        this.prix = prix;
        this.reservationId = reservationId;
    }
    
    // Getters/Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTypeOption() { return typeOption; }
    public void setTypeOption(String typeOption) { this.typeOption = typeOption; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }
    
    public Long getReservationId() { return reservationId; }
    public void setReservationId(Long reservationId) { this.reservationId = reservationId; }
}