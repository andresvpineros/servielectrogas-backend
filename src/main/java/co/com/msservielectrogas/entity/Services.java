package co.com.msservielectrogas.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "services")
public class Services {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer servicesType;

    @Column(nullable = false)
    private String servicesDescription;

    @Column(nullable = false)
    private Long price;
    
    @Column(nullable = false)
    private String productName;
    
    @Column(nullable = false)
    private Long warrantyTime;

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getServicesType() {
        return servicesType;
    }

    public void setServicesType(Integer servicesType) {
        this.servicesType = servicesType;
    }

    public String getServicesDescription() {
        return servicesDescription;
    }

    public void setServicesDescription(String servicesDescription) {
        this.servicesDescription = servicesDescription;
    }
    
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
    
    public Long getWarrantyTime() {
        return warrantyTime;
    }

    public void setWarrantyTime(Long warrantyTime) {
        this.warrantyTime = warrantyTime;
    }
}
