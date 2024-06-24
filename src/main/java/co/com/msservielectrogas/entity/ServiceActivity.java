package co.com.msservielectrogas.entity;


import co.com.msservielectrogas.enums.EPriority;
import co.com.msservielectrogas.enums.EStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.Duration;

@Entity
@Table(name = "service_activities")
public class ServiceActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private LocalDateTime activityDate;
    private Duration duration;
    
    private Integer priority;
    private Integer status;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "technician_id")
    private Users technician;

    public ServiceActivity() {
    }

    public ServiceActivity(Long id, String description, LocalDateTime activityDate, Duration duration, Integer priority, Integer status, LocalDateTime createdAt, Order order, Users technician) {
        this.id = id;
        this.description = description;
        this.activityDate = activityDate;
        this.duration = duration;
        this.priority = priority;
        this.status = status;
        this.createdAt = createdAt;
        this.order = order;
        this.technician = technician;
        this.createdAt = LocalDateTime.now();
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Users getTechnician() {
        return technician;
    }

    public void setTechnician(Users technician) {
        this.technician = technician;
    }
}

