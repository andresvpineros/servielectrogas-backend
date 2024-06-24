package co.com.msservielectrogas.dto;

import co.com.msservielectrogas.enums.EPriority;
import co.com.msservielectrogas.enums.EStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.Duration;

@Data
public class ServiceActivityDTO {

    private Long id;
    private String description;
    private LocalDateTime activityDate;
    private Duration duration;
    private EPriority priorityName;
    private Integer priority;
    private EStatus statusName;
    private Integer status;
    private LocalDateTime createdAt;
    private Long orderId;
    private Integer technicianId;

    public ServiceActivityDTO() {
    }
    
    public ServiceActivityDTO(Long id, String description, LocalDateTime activityDate, Duration duration, Integer priority, Integer status, LocalDateTime createdAt, Long orderId, Integer technicianId) {
        this.id = id;
        this.description = description;
        this.activityDate = activityDate;
        this.duration = duration;
        this.priority = priority;
        this.status = status;
        this.createdAt = createdAt;
        this.orderId = orderId;
        this.technicianId = technicianId;
    }
    
    public ServiceActivityDTO(Long id, String description, LocalDateTime activityDate, Duration duration, EPriority priorityName, Integer priority, EStatus statusName, Integer status, LocalDateTime createdAt, Long orderId, Integer technicianId) {
        this.id = id;
        this.description = description;
        this.activityDate = activityDate;
        this.duration = duration;
        this.priorityName = priorityName;
        this.priority = priority;
        this.statusName = statusName;
        this.status = status;
        this.createdAt = createdAt;
        this.orderId = orderId;
        this.technicianId = technicianId;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(LocalDateTime activityDate) {
        this.activityDate = activityDate;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
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
