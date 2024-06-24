package co.com.msservielectrogas.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

import co.com.msservielectrogas.enums.ERoles;

@Data
public class WarrantyDTO {
    private Long id;
    private String reason;
    private Date startDate;
    private Date endDate;
    private Long orderServiceId;
    
    public WarrantyDTO() {
    }

    public WarrantyDTO(String reason, Date startDate, Date endDate, Long orderServiceId) {
        this.reason = reason;
        this.startDate = startDate;
        this.endDate = endDate;
        this.orderServiceId = orderServiceId;
    }
    
    public WarrantyDTO(Long id, String reason, Date startDate, Date endDate, Long orderServiceId) {
        this.id = id;
        this.reason = reason;
        this.startDate = startDate;
        this.endDate = endDate;
        this.orderServiceId = orderServiceId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getOrderServiceId() {
        return orderServiceId;
    }

    public void setOrderServiceId(Long orderServiceId) {
        this.orderServiceId = orderServiceId;
    }
}
