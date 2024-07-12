package co.com.msservielectrogas.dto;

public class WarrantiesByTypeDTO {
    private String warrantyType;
    private int month;
    private long warrantyCount;

    // Constructor
    public WarrantiesByTypeDTO(String warrantyType, int month, long warrantyCount) {
        this.warrantyType = warrantyType;
        this.month = month;
        this.warrantyCount = warrantyCount;
    }

    // Getters and Setters
    public String getWarrantyType() {
        return warrantyType;
    }

    public void setWarrantyType(String warrantyType) {
        this.warrantyType = warrantyType;
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
