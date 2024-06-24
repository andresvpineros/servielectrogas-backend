package co.com.msservielectrogas.services;

import co.com.msservielectrogas.entity.ServiceActivity;
import co.com.msservielectrogas.dto.ServiceActivityDTO;
import co.com.msservielectrogas.dto.CreateServiceActivityDTO;
import co.com.msservielectrogas.dto.UpdateServiceActivityDTO;
import co.com.msservielectrogas.repository.IServiceActivityRepository;
import co.com.msservielectrogas.repository.IOrderRepository;
import co.com.msservielectrogas.repository.IUsersRepository;
import co.com.msservielectrogas.specification.ServiceActivitySpecifications;
import co.com.msservielectrogas.enums.EPriority;
import co.com.msservielectrogas.enums.EStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class IServiceActivityService {
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