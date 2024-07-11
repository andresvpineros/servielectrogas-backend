package co.com.msservielectrogas.dto;

import co.com.msservielectrogas.enums.EServicesType;
import lombok.Data;

@Data
public class ServicesDTO {

	private Integer id;
	private EServicesType servicesTypeName;
	private Integer servicesType;
	private String servicesDescription;
	private String productName;
	private Long price;
	private Long warrantyTime;
	
	public ServicesDTO() {
	}
	
    public ServicesDTO(Integer id, String servicesDescription, Long price, EServicesType servicesTypeName, Integer servicesType, String productName, Long warrantyTime) {
        this.id = id;
        this.servicesTypeName = servicesTypeName;
        this.servicesType = servicesType;
        this.servicesDescription = servicesDescription;
        this.productName = productName;
        this.price = price;
        this.warrantyTime = warrantyTime;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EServicesType getServicesTypeName() {
        return servicesTypeName;
    }

    public void setServicesTypeName(EServicesType servicesTypeName) {
        this.servicesTypeName = servicesTypeName;
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
