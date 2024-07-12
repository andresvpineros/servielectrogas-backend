package co.com.msservielectrogas.dto;

import java.util.Date;

public class WarrantyStatisticsDTO {
    private String reason;
    private Date month;
    private Long count;

    public WarrantyStatisticsDTO(String reason, Date month, Long count) {
        this.reason = reason;
        this.month = month;
        this.count = count;
    }

    // Getters y setters
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getMonth() {
        return month;
    }

    public void setMonth(Date month) {
        this.month = month;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
