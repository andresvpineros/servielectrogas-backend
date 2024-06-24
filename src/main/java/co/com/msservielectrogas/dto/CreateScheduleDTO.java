package co.com.msservielectrogas.dto;

import java.util.Date;
import java.time.LocalTime;

public class CreateScheduleDTO {

    private Date date;
    private LocalTime hour;
    private Long orderId;
    private Integer technicianId;

    // Default constructor
    public CreateScheduleDTO() {
    }

    // Parameterized constructor
    public CreateScheduleDTO(Date date, LocalTime hour, Long orderId, Integer technicianId) {
        this.date = date;
        this.hour = hour;
        this.orderId = orderId;
        this.technicianId = technicianId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LocalTime getHour() {
        return hour;
    }

    public void setHour(LocalTime hour) {
        this.hour = hour;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getTechnicianId() {
        return technicianId;
    }

    public void setTechnicianId(Integer technicianId) {
        this.technicianId = technicianId;
    }
}
