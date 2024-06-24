package co.com.msservielectrogas.dto;

import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;

public class CreateOrderServiceDTO {
    private Long orderId;
    private Integer serviceId;
    private String observations;
    private LocalDateTime orderServiceDate;
    private Duration duration;
    private Integer priority;
    private Integer status;
    private Integer technicianId;

    // Getters and setters

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public LocalDateTime getOrderServiceDate() {
        return orderServiceDate;
    }

    public void setOrderServiceDate(LocalDateTime orderServiceDate) {
        this.orderServiceDate = orderServiceDate;
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

    public Integer getTechnicianId() {
        return technicianId;
    }

    public void setTechnicianId(Integer technicianId) {
        this.technicianId = technicianId;
    }
}