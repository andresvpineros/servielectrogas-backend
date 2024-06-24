package co.com.msservielectrogas.dto;

import java.util.Date;

public class CreateWarrantyDTO {
    private Date endDate;
    private Date startDate;
    private String reason;
    private Long orderServiceId;

    public CreateWarrantyDTO() {
    }

    public CreateWarrantyDTO(Date endDate, Date startDate, String reason, Long orderServiceId) {
        this.endDate = endDate;
        this.startDate = startDate;
        this.reason = reason;
        this.orderServiceId = orderServiceId;
    }

    // Getters and Setters
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Long getOrderServiceId() {
        return orderServiceId;
    }

    public void setOrderServiceId(Long orderServiceId) {
        this.orderServiceId = orderServiceId;
    }
}
