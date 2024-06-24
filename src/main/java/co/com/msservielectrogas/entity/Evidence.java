package co.com.msservielectrogas.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "evidences")
public class Evidence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String filename;
    
    @Column(nullable = false)
    @JoinColumn(name = "file_type")
    private String fileType;
    
    @Lob
    @Column(nullable = false)
    private byte[] data;
    
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "order_service_id")
    private OrderService orderService;

    public Evidence() {
    }

    public Evidence(Long id, String filename, String fileType, byte[] data, LocalDateTime createdAt, OrderService orderService) {
        this.id = id;
        this.filename = filename;
        this.fileType = fileType;
        this.data = data;
        this.createdAt = createdAt;
        this.orderService = orderService;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
    
    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
    
    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}
