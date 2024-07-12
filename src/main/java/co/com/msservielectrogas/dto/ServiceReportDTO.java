package co.com.msservielectrogas.dto;

import java.time.LocalDateTime;
import java.util.Date;

public class ServiceReportDTO {
    private String technicianName;
    private String clientName;
    private String observations;
    private long totalCharged;
    private LocalDateTime createdAt;

    // Constructor
    public ServiceReportDTO(String technicianName, String clientName, String observations, long totalCharged, LocalDateTime createdAt) {
        System.out.println("Creating ServiceReportDTO: technicianName=" + technicianName + ", clientName=" + clientName + ", observations=" + observations + ", totalCharged=" + totalCharged + ", createdAt=" + createdAt);
        this.technicianName = technicianName;
        this.clientName = clientName;
        this.observations = observations;
        this.totalCharged = totalCharged;
        this.createdAt = createdAt;
    }

    // Getters and setters
    public String getTechnicianName() {
        return technicianName;
    }

    public void setTechnicianName(String technicianName) {
        this.technicianName = technicianName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public long getTotalCharged() {
        return totalCharged;
    }

    public void setTotalCharged(long totalCharged) {
        this.totalCharged = totalCharged;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
