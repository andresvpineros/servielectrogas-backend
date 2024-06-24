package co.com.msservielectrogas.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.JoinColumn;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
@Table(name = "Orders")
public class Order {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @Transient
    private List<OrderService> orderServices;
	
	@Column(nullable = true, length = 255)
    private String observations;
	
	@Column(name = "status") 
    private Integer status;
	
	@Column(nullable = true)
    private Long totalCharged;
	
	@Column(nullable = true)
    private Long aditionalCharged;
	
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clients_id")
    @JsonBackReference
    private Clients client;
	
    // Default constructor
    public Order() {
    }

    // Parameterized constructor
    public Order(Long id, String observations,Integer status, Long totalCharged, Long aditionalCharged, LocalDateTime createdAt, LocalDateTime updatedAt, Clients client) {
        this.id = id;
        this.observations = observations;
        this.status = status;
        this.totalCharged = totalCharged;
        this.aditionalCharged = aditionalCharged;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.client = client;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderService> getOrderServices() {
        return orderServices;
    }

    public void setOrderServices(List<OrderService> orderServices) {
        this.orderServices = orderServices;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }
    
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getTotalCharged() {
        return totalCharged;
    }

    public void setTotalCharged(Long totalCharged) {
        this.totalCharged = totalCharged;
    }
    
    public Long getAditionalCharged() {
        return aditionalCharged;
    }

    public void setAditionalCharged(Long aditionalCharged) {
        this.aditionalCharged = aditionalCharged;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Clients getClient() {
        return client;
    }

    public void setClient(Clients client) {
        this.client = client;
    }
}
