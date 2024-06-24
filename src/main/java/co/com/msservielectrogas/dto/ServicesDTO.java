package co.com.msservielectrogas.dto;

import co.com.msservielectrogas.enums.EServicesType;
import lombok.Data;

@Data
public class ServicesDTO {

	private Integer id;
	private EServicesType servicesTypeName;
	private Integer servicesType;
	private String servicesDescription;
	private Long price;
	
	public ServicesDTO() {
	}
	
    public ServicesDTO(Integer id, String servicesDescription, Long price, EServicesType servicesTypeName, Integer servicesType) {
        this.id = id;
        this.servicesTypeName = servicesTypeName;
        this.servicesType = servicesType;
        this.servicesDescription = servicesDescription;
        this.price = price;
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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
