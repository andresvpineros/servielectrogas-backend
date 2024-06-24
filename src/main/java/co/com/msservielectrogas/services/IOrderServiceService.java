package co.com.msservielectrogas.services;

import co.com.msservielectrogas.entity.OrderService;
import co.com.msservielectrogas.dto.OrderServiceDTO;
import co.com.msservielectrogas.repository.IOrderServiceRepository;
import co.com.msservielectrogas.repository.IServicesRepository;
import co.com.msservielectrogas.repository.IOrderRepository;
import co.com.msservielectrogas.repository.IUsersRepository;
import co.com.msservielectrogas.enums.EPriority;
import co.com.msservielectrogas.enums.EStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class IOrderServiceService {
	
    @Autowired
    private IOrderServiceRepository orderServiceRepository;
	
    @Autowired
    private IOrderRepository orderRepository;
    
    @Autowired
    private IServicesRepository serviceRepository;
    
    @Autowired
    private IUsersRepository usersRepository;
    
    public OrderServiceDTO getOrderServiceById(Long id) {
    	OrderService orderService = orderServiceRepository.findById(id);
    	
        if (orderService == null) {
            return null;
        }
        return convertToDTO(orderService);
    }
    
    public OrderServiceDTO createOrderService(OrderServiceDTO createOrderServiceDTO, Long orderId, Integer technicianId) {
    	OrderService orderService = new OrderService();
    	
    	orderService.setOrder(orderRepository.findById(orderId));    		
    	orderService.setService(serviceRepository.findById(createOrderServiceDTO.getServiceId()).orElse(null));
    	orderService.setObservations(createOrderServiceDTO.getObservations());
    	orderService.setOrderServiceDate(createOrderServiceDTO.getOrderServiceDate());
    	orderService.setDuration(createOrderServiceDTO.getDuration());
    	orderService.setPriority(createOrderServiceDTO.getPriority());
    	orderService.setStatus(createOrderServiceDTO.getStatus());
        orderService.setCreatedAt(LocalDateTime.now());
    	orderService.setTechnician(usersRepository.findById(technicianId).orElse(null));
        
    	OrderService savedOrderService = orderServiceRepository.save(orderService);

        return convertToDTO(savedOrderService);
    }
    
    public OrderServiceDTO updateOrderService(OrderServiceDTO createOrderServiceDTO, Long orderId, Integer technicianId) {
    	OrderService orderService = new OrderService();
    	
    	orderService.setOrder(orderRepository.findById(orderId));    		
    	orderService.setService(serviceRepository.findById(createOrderServiceDTO.getServiceId()).orElse(null));
    	orderService.setObservations(createOrderServiceDTO.getObservations());
    	orderService.setOrderServiceDate(createOrderServiceDTO.getOrderServiceDate());
    	orderService.setDuration(createOrderServiceDTO.getDuration());
    	orderService.setPriority(createOrderServiceDTO.getPriority());
    	orderService.setStatus(createOrderServiceDTO.getStatus());
        orderService.setCreatedAt(LocalDateTime.now());
    	orderService.setTechnician(usersRepository.findById(technicianId).orElse(null));
        
    	OrderService savedOrderService = orderServiceRepository.save(orderService);

        return convertToDTO(savedOrderService);
    }

    private OrderServiceDTO convertToDTO(OrderService orderService) {
        EPriority priorityName = EPriority.values()[orderService.getPriority().intValue()];
        EStatus statusName = EStatus.values()[orderService.getStatus().intValue()];

        return new OrderServiceDTO(
        		orderService.getId(),
        		orderService.getOrder().getId(),
        		orderService.getService(),
        		orderService.getService().getId(),
        		orderService.getObservations(),
        		orderService.getOrderServiceDate(),
        		orderService.getDuration(),
        		priorityName,
        		orderService.getPriority(),
        		statusName,
        		orderService.getStatus(),
        		orderService.getCreatedAt(),
        		orderService.getTechnician().getName(),
        		orderService.getTechnician().getId()
        );
    }
    
    
/*
    @Autowired
    private IServiceActivityRepository serviceActivityRepository;
    @Autowired
    private IOrderRepository orderRepository;
    @Autowired
    private IUsersRepository userRepository;
    
    public Page<ServiceActivityDTO> getAllServiceActivities(String description, Pageable pageable) {
        Specification<ServiceActivity> spec = Specification.where(ServiceActivitySpecifications.hasDescription(description));
        return serviceActivityRepository.findAll(spec, pageable)
                .map(this::convertToDTO);
    }

    public Optional<ServiceActivityDTO> getServiceActivityById(Long id) {
        return serviceActivityRepository.findById(id)
                .map(this::convertToDTO);
    }
    
    public ServiceActivityDTO createServiceActivity(CreateServiceActivityDTO createServiceActivityDTO) {
        ServiceActivity serviceActivity = new ServiceActivity();
        serviceActivity.setDescription(createServiceActivityDTO.getDescription());
        serviceActivity.setActivityDate(createServiceActivityDTO.getActivityDate());
        serviceActivity.setDuration(createServiceActivityDTO.getDuration());
        serviceActivity.setPriority(createServiceActivityDTO.getPriority());
        serviceActivity.setStatus(createServiceActivityDTO.getStatus());
        serviceActivity.setOrder(orderRepository.findById(createServiceActivityDTO.getOrderId()).orElse(null));
        serviceActivity.setTechnician(userRepository.findById(createServiceActivityDTO.getTechnicianId()).orElse(null));
        serviceActivity.setCreatedAt(LocalDateTime.now());
        
        ServiceActivity savedServiceActivity = serviceActivityRepository.save(serviceActivity);

        return new ServiceActivityDTO(
                savedServiceActivity.getId(),
                savedServiceActivity.getDescription(),
                savedServiceActivity.getActivityDate(),
                savedServiceActivity.getDuration(),
                savedServiceActivity.getPriority(),
                savedServiceActivity.getStatus(),
                savedServiceActivity.getCreatedAt(),
                savedServiceActivity.getOrder().getId(),
                savedServiceActivity.getTechnician().getId()
        );
    }
    
    public ServiceActivityDTO updateServiceActivity(Long id, UpdateServiceActivityDTO updateServiceActivityDTO) {
        Optional<ServiceActivity> optionalServiceActivity = serviceActivityRepository.findById(id);

        if (!optionalServiceActivity.isPresent()) {
            throw new RuntimeException("Actividad de servicio no encontrada.");
        }

        ServiceActivity serviceActivity = optionalServiceActivity.get();
        serviceActivity.setDescription(updateServiceActivityDTO.getDescription());
        serviceActivity.setActivityDate(updateServiceActivityDTO.getActivityDate());
        serviceActivity.setDuration(updateServiceActivityDTO.getDuration());
        serviceActivity.setPriority(updateServiceActivityDTO.getPriority());
        serviceActivity.setStatus(updateServiceActivityDTO.getStatus());

        ServiceActivity updatedServiceActivity = serviceActivityRepository.save(serviceActivity);

        return new ServiceActivityDTO(
                updatedServiceActivity.getId(),
                updatedServiceActivity.getDescription(),
                updatedServiceActivity.getActivityDate(),
                updatedServiceActivity.getDuration(),
                updatedServiceActivity.getPriority(),
                updatedServiceActivity.getStatus(),
                updatedServiceActivity.getCreatedAt(),
                updatedServiceActivity.getOrder().getId(),
                updatedServiceActivity.getTechnician().getId()
        );
    }
    
    public void deleteServiceActivityById(Long id) {
        if (serviceActivityRepository.existsById(id)) {
            serviceActivityRepository.deleteById(id);
        } else {
            throw new RuntimeException("Actividad de servicio no encontrada");
        }
    }

    private ServiceActivityDTO convertToDTO(ServiceActivity serviceActivity) {
        EPriority priorityName = EPriority.values()[serviceActivity.getPriority().intValue()];
        EStatus statusName = EStatus.values()[serviceActivity.getStatus().intValue()];

        return new ServiceActivityDTO(
                serviceActivity.getId(),
                serviceActivity.getDescription(),
                serviceActivity.getActivityDate(),
                serviceActivity.getDuration(),
                priorityName,
                serviceActivity.getPriority(),
                statusName,
                serviceActivity.getStatus(),
                serviceActivity.getCreatedAt(),
                serviceActivity.getOrder().getId(),
                serviceActivity.getTechnician().getId()
        );
    }
    */
}