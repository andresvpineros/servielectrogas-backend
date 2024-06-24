package co.com.msservielectrogas.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
public class EvidenceDTO {

    private Long id;
    private MultipartFile photoUrl;
    private LocalDate createdAt;
    private Long orderService;

    public EvidenceDTO() {
    }

    public EvidenceDTO(Long id, MultipartFile photoUrl, LocalDate createdAt, Long orderService) {
        this.id = id;
        this.photoUrl = photoUrl;
        this.createdAt = createdAt;
        this.orderService = orderService;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MultipartFile getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(MultipartFile photoUrl) {
        this.photoUrl = photoUrl;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Long getOrderService() {
        return orderService;
    }

    public void setOrderService(Long orderService) {
        this.orderService = orderService;
    }
}
