package co.com.msservielectrogas.services;

import co.com.msservielectrogas.entity.OrderService;
import co.com.msservielectrogas.entity.Warranty;
import co.com.msservielectrogas.dto.ApiResponseDTO;
import co.com.msservielectrogas.dto.CreateWarrantyDTO;
import co.com.msservielectrogas.dto.OrderServiceDTO;
import co.com.msservielectrogas.dto.WarrantyDTO;
import co.com.msservielectrogas.repository.IWarrantyRepository;
import co.com.msservielectrogas.repository.IOrderRepository;
import co.com.msservielectrogas.repository.IServicesRepository;
import co.com.msservielectrogas.repository.IUsersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class IWarrantyService {
	
    @Autowired
    private IWarrantyRepository warrantyRepository;
	
    @Autowired
    private IOrderRepository orderRepository;
    
    @Autowired
    private IServicesRepository serviceRepository;
    
    @Autowired
    private IUsersRepository usersRepository;
    
    @Autowired
    private IOrderServiceService orderServiceService;
    
    public ApiResponseDTO<WarrantyDTO> createWarranty(CreateWarrantyDTO createWarrantyDTO) {
        if (createWarrantyDTO.getOrderServiceId() == null) {
            return new ApiResponseDTO<>("La Orden de Servicio es requerido", HttpStatus.BAD_REQUEST.value(), null);
        }

        OrderServiceDTO orderServiceDTO = orderServiceService.getOrderServiceById(createWarrantyDTO.getOrderServiceId());
        
        if (orderServiceDTO == null) {
            return new ApiResponseDTO<>("La Orden de Servicio no se ha encontrado", HttpStatus.BAD_REQUEST.value(), null);
        }
        
        OrderService orderService = new OrderService();
    	orderService.setOrder(orderRepository.findById(orderServiceDTO.getOrderId()));    		
    	orderService.setService(serviceRepository.findById(orderServiceDTO.getServiceId()).orElse(null));
    	orderService.setObservations(orderServiceDTO.getObservations());
    	orderService.setOrderServiceDate(orderServiceDTO.getOrderServiceDate());
    	orderService.setDuration(orderServiceDTO.getDuration());
    	orderService.setPriority(orderServiceDTO.getPriority());
    	orderService.setStatus(orderServiceDTO.getStatus());
        orderService.setCreatedAt(LocalDateTime.now());
    	orderService.setTechnician(usersRepository.findById(orderServiceDTO.getTechnicianId()).orElse(null));

    	Warranty warranty = new Warranty();
    	warranty.setReason(createWarrantyDTO.getReason());
    	warranty.setStartDate(createWarrantyDTO.getStartDate());
    	warranty.setEndDate(createWarrantyDTO.getEndDate());
    	warranty.setOrderService(orderService);
        
    	warranty = warrantyRepository.save(warranty);

        WarrantyDTO warrantyDTO = convertToDTO(warranty);
        
        return new ApiResponseDTO<>("Order created successfully", HttpStatus.CREATED.value(), warrantyDTO);
    }
    
    private WarrantyDTO convertToDTO(Warranty warranty) {
        return new WarrantyDTO(
        		warranty.getId(),
        		warranty.getReason(),
        		warranty.getStartDate(),
        		warranty.getEndDate(),
        		warranty.getOrderService().getId()
        );
    }
}