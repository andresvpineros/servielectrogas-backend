package co.com.msservielectrogas.dto;

public class TechnicianEffectivenessDTO {
    private String technicianName;
    private int month;
    private double effectiveness;

    public TechnicianEffectivenessDTO(String technicianName, int month, double effectiveness) {
        this.technicianName = technicianName;
        this.month = month;
        this.effectiveness = effectiveness;
    }

    // Getters y setters
    public String getTechnicianName() {
        return technicianName;
    }

    public void setTechnicianName(String technicianName) {
        this.technicianName = technicianName;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public double getEffectiveness() {
        return effectiveness;
    }

    public void setEffectiveness(double effectiveness) {
        this.effectiveness = effectiveness;
    }
}
