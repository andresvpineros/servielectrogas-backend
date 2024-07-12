package co.com.msservielectrogas.dto;

public class WarrantiesByTechnicianDTO {

    private String technicianName;
    private int month;
    private long warrantyCount;

    public WarrantiesByTechnicianDTO(String technicianName, int month, long warrantyCount) {
        this.technicianName = technicianName;
        this.month = month;
        this.warrantyCount = warrantyCount;
    }

    // Getters and setters
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

    public long getWarrantyCount() {
        return warrantyCount;
    }

    public void setWarrantyCount(long warrantyCount) {
        this.warrantyCount = warrantyCount;
    }
}
