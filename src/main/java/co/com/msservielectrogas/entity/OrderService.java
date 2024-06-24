package co.com.msservielectrogas.entity;

import java.time.Duration;
import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
@Table(name = "order_services")
public class OrderService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "orders_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "services_id")
    private Services service;
    
    private String observations;
    private LocalDateTime orderServiceDate;
    private Duration duration;
    
    private Integer priority;
    private Integer status;
    
    private LocalDateTime createdAt;
    
    @ManyToOne
    @JoinColumn(name = "technician_id")
    private Users technician;

    public OrderService() {
    }

    public OrderService(Long id) {
        this.id = id;
    }
        
    public OrderService(Long id, Order order, Services service, String observations, LocalDateTime orderServiceDate, Duration duration, Integer priority, Integer status, LocalDateTime createdAt, Users technician) {
        this.id = id;
        this.order = order;
        this.service = service;
        this.observations = observations;
        this.orderServiceDate = orderServiceDate;
        this.duration = duration;
        this.priority = priority;
        this.status = status;
        this.createdAt = createdAt;
        this.technician = technician;
        this.createdAt = LocalDateTime.now();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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

    public Users getTechnician() {
        return technician;
    }

    public void setTechnician(Users technician) {
        this.technician = technician;
    }
}
