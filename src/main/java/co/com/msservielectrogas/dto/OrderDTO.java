package co.com.msservielectrogas.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import co.com.msservielectrogas.enums.EStatus;
import lombok.Data;

@Data
public class OrderDTO {

	private Long id;
	private List<OrderServiceDTO> orderServices;
	private String observations;
	private EStatus statusName;
	private Integer status;
	private Long totalCharged;
	private Long aditionalCharged;
    private Long clientId;
    private String clientNames;
    private String clientDocument;
    private String clientPhone;
    private String clientAddress;

	private Integer technicianId;
	private String technicianName;
    private Date scheduleDate;
    private LocalTime scheduleTime;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    public OrderDTO() {
    }
    
    public OrderDTO(Long id, String observations, EStatus statusName, Integer status, Long totalCharged, Long aditionalCharged, Long clientId, String clientNames, String clientDocument, String clientPhone, String clientAddress, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.observations = observations;
        this.statusName = statusName;
        this.status = status;
        this.totalCharged = totalCharged;
        this.aditionalCharged = aditionalCharged;
        this.clientId = clientId;
        this.clientNames = clientNames;
        this.clientDocument = clientDocument;
        this.clientPhone = clientPhone;
        this.clientAddress = clientAddress;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
    public OrderDTO(Long id, List<OrderServiceDTO> orderServices, String observations,Integer status, Long totalCharged, Long aditionalCharged, Long clientId, String clientNames, String clientDocument, String clientPhone, String clientAddress) {
        this.id = id;
        this.orderServices = orderServices;
        this.observations = observations;
        this.status = status;
        this.totalCharged = totalCharged;
        this.aditionalCharged = aditionalCharged;
        this.clientId = clientId;
        this.clientNames = clientNames;
        this.clientDocument = clientDocument;
        this.clientPhone = clientPhone;   
        this.clientAddress = clientAddress;
      }
    
    // Getters and Setters
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderServiceDTO> getOrderServices() {
        return orderServices;
    }

    public void setOrderServices(List<OrderServiceDTO> orderServices) {
        this.orderServices = orderServices;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
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
    
    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
    
    public String getClientNames() {
        return clientNames;
    }

    public void setClientNames(String clientNames) {
        this.clientNames = clientNames;
    }
    
    public String getClientDocument() {
        return clientDocument;
    }

    public void setClientDocument(String clientDocument) {
        this.clientDocument = clientDocument;
    }
    
    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }
    
    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
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
}
