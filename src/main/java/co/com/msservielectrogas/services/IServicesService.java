package co.com.msservielectrogas.services;

import co.com.msservielectrogas.entity.Services;
import co.com.msservielectrogas.dto.ServicesDTO;
import co.com.msservielectrogas.repository.IServicesRepository;
import co.com.msservielectrogas.enums.EServicesType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IServicesService {

    @Autowired
    private IServicesRepository serviceRepository;

    public List<ServicesDTO> searchServices(String query) {
        List<Services> services = serviceRepository.searchServices(query);
        
        if (services == null) {
            return null;
        }
        
        return services.stream()
                      .map(this::convertToDTO)
                      .collect(Collectors.toList());
    }

    private ServicesDTO convertToDTO(Services service) {
    	EServicesType typeName = EServicesType.values()[service.getServicesType().intValue()];

        return new ServicesDTO(
        		service.getId(),
        		service.getServicesDescription(),
        		service.getPrice(),
        		typeName,
        		service.getServicesType(),
        		service.getProductName(),
        		service.getWarrantyTime()
        );
    }
}