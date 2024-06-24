package co.com.msservielectrogas.controller;

import co.com.msservielectrogas.dto.ApiResponseDTO;
import co.com.msservielectrogas.dto.ServiceActivityDTO;
import co.com.msservielectrogas.dto.CreateServiceActivityDTO;
import co.com.msservielectrogas.dto.UpdateServiceActivityDTO;
import co.com.msservielectrogas.services.IServiceActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/serviceActivity")
public class ServiceActivityController {
    @Autowired
    private IServiceActivityService serviceActivityService;
    /*
    @GetMapping
    public ResponseEntity<ApiResponseDTO<Page<ServiceActivityDTO>>> getAllServiceActivities(
            @RequestParam(required = false) String description,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String[] sort) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(getSortOrders(sort)));

        Page<ServiceActivityDTO> serviceActivities = serviceActivityService.getAllServiceActivities(description, pageable);
        ApiResponseDTO<Page<ServiceActivityDTO>> response = new ApiResponseDTO<>("Success", HttpStatus.OK.value(), serviceActivities);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<ServiceActivityDTO>> getServiceActivityById(@PathVariable Long id) {
        Optional<ServiceActivityDTO> serviceActivity = serviceActivityService.getServiceActivityById(id);
        if (serviceActivity.isPresent()) {
            ApiResponseDTO<ServiceActivityDTO> response = new ApiResponseDTO<>("Exitoso", HttpStatus.OK.value(), serviceActivity.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponseDTO<ServiceActivityDTO> response = new ApiResponseDTO<>("Actividad de servicio no encontrada", HttpStatus.NOT_FOUND.value(), null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping
    public ResponseEntity<ApiResponseDTO<ServiceActivityDTO>> createServiceActivity(@RequestBody CreateServiceActivityDTO createServiceActivityDTO) {
        ServiceActivityDTO serviceActivityDTO = serviceActivityService.createServiceActivity(createServiceActivityDTO);
        ApiResponseDTO<ServiceActivityDTO> response = new ApiResponseDTO<>("Actividad de servicio creada satisfactoriamente", HttpStatus.CREATED.value(), serviceActivityDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<ServiceActivityDTO>> updateServiceActivityById(@PathVariable Long id, @RequestBody UpdateServiceActivityDTO updateServiceActivityDTO) {
        try {
            ServiceActivityDTO updatedServiceActivity = serviceActivityService.updateServiceActivity(id, updateServiceActivityDTO);
            ApiResponseDTO<ServiceActivityDTO> response = new ApiResponseDTO<>("Actividad de Servicio actualizada satisfactoriamente", HttpStatus.OK.value(), updatedServiceActivity);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            ApiResponseDTO<ServiceActivityDTO> response = new ApiResponseDTO<>(e.getMessage(), HttpStatus.NOT_FOUND.value(), null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<Void>> deleteServiceActivityById(@PathVariable Long id) {
        try {
            serviceActivityService.deleteServiceActivityById(id);
            ApiResponseDTO<Void> response = new ApiResponseDTO<>("Actividad de servicio eliminada satisfactoriamente", HttpStatus.OK.value(), null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            ApiResponseDTO<Void> response = new ApiResponseDTO<>(e.getMessage(), HttpStatus.NOT_FOUND.value(), null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
    

    private List<Sort.Order> getSortOrders(String[] sort) {
        List<Sort.Order> orders = new ArrayList<>();

        if (sort[0].contains(",")) {
            // sort order like: "field, direction"
            for (String sortOrder : sort) {
                String[] _sort = sortOrder.split(",");
                orders.add(new Sort.Order(Sort.Direction.fromString(_sort[1]), _sort[0]));
            }
        } else {
            // default sort order
            orders.add(new Sort.Order(Sort.Direction.ASC, sort[0]));
        }

        return orders;
    }
    */
}
