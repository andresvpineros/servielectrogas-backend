package co.com.msservielectrogas.dto;

import java.time.Duration;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import co.com.msservielectrogas.entity.Order;
import co.com.msservielectrogas.entity.Services;
import co.com.msservielectrogas.entity.Users;
import co.com.msservielectrogas.enums.EPriority;
import co.com.msservielectrogas.enums.EStatus;
import lombok.Data;

@Data
public class OrderServiceDTO {
    private Long id;
    private Long orderId;
    private Services service;
    private Integer serviceId;
    
    private String observations;
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime orderServiceDate;
    
    private Duration duration;
    private EPriority priorityName;
    private Integer priority;
    private EStatus statusName;
    private Integer status;
    private LocalDateTime createdAt;
    private String technicianName;
    private Integer technicianId;

    private Boolean alreadyCreated;

    public OrderServiceDTO() {
    }

    public OrderServiceDTO(Long id) {
        this.id = id;
    }
        
    public OrderServiceDTO(Long id, Long orderId, Services service, Integer serviceId, String observations, LocalDateTime orderServiceDate, Duration duration, Integer priority, Integer status, LocalDateTime createdAt, String technicianName, Integer technicianId) {
        this.id = id;
        this.service = service;
        this.serviceId = serviceId;
        this.observations = observations;
        this.orderServiceDate = orderServiceDate;
        this.duration = duration;
        this.priority = priority;
        this.status = status;
        this.createdAt = createdAt;
        this.technicianName = technicianName;
        this.technicianId = technicianId;
    }
    
    public OrderServiceDTO(Long id, Long orderId, Services service, Integer serviceId, String observations, LocalDateTime orderServiceDate, Duration duration, Integer priority, Integer status, LocalDateTime createdAt, String technicianName, Integer technicianId, Boolean alreadyCreated) {
        this.id = id;
        this.service = service;
        this.serviceId = serviceId;
        this.observations = observations;
        this.orderServiceDate = orderServiceDate;
        this.duration = duration;
        this.priority = priority;
        this.status = status;
        this.createdAt = createdAt;
        this.technicianName = technicianName;
        this.technicianId = technicianId;
        this.alreadyCreated = alreadyCreated;
    }
    
    public OrderServiceDTO(Long id, Long orderId, Services service, Integer serviceId, String observations, LocalDateTime orderServiceDate, Duration duration, EPriority priorityName, Integer priority, EStatus statusName, Integer status, LocalDateTime createdAt, String technicianName, Integer technicianId) {
        this.id = id;
        this.service = service;
        this.serviceId = serviceId;
        this.observations = observations;
        this.orderServiceDate = orderServiceDate;
        this.duration = duration;
        this.priorityName = priorityName;
        this.priority = priority;
        this.statusName = statusName;
        this.status = status;
        this.createdAt = createdAt;
        this.technicianName = technicianName;
        this.technicianId = technicianId;
    }
    
    public OrderServiceDTO(Long id,  Long orderId, Services service, String observations, LocalDateTime orderServiceDate, Duration duration, Integer priority, Integer status, LocalDateTime createdAt, Integer technicianId, Boolean alreadyCreated) {
        this.id = id;
        this.orderId = orderId;
        this.service = service;
        this.observations = observations;
        this.orderServiceDate = orderServiceDate;
        this.duration = duration;
        this.priority = priority;
        this.status = status;
        this.createdAt = createdAt;
        this.technicianId = technicianId;
        this.alreadyCreated = alreadyCreated;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
    
    public Services getService() {
        return service;
    }

    public void setService(Services service) {
        this.service = service;
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

    public EPriority getPriorityName() {
        return priorityName;
    }

    public void setPriorityName(EPriority priorityName) {
        this.priorityName = priorityName;
    }
    
    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
    
    public EStatus getStatusName() {
        return statusName;
    }

    public void setStatusName(EStatus statusName) {
        this.statusName = statusName;
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
    
    public String getTechnicianName() {
        return technicianName;
    }

    public void setTechnicianName(String technicianName) {
        this.technicianName = technicianName;
    }
    
    public Integer getTechnicianId() {
        return technicianId;
    }

    public void setTechnicianId(Integer technicianId) {
        this.technicianId = technicianId;
    }
    
    public Boolean getAlreadyCreated() {
        return alreadyCreated;
    }

    public void setAlreadyCreated(Boolean alreadyCreated) {
        this.alreadyCreated = alreadyCreated;
    }
}
