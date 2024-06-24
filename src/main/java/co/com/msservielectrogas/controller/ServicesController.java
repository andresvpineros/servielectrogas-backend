package co.com.msservielectrogas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.msservielectrogas.dto.ApiResponseDTO;
import co.com.msservielectrogas.dto.ServicesDTO;
import co.com.msservielectrogas.services.IServicesService;


@RestController
@RequestMapping(value = "/api/services")
public class ServicesController {
	
    @Autowired
    private IServicesService servicesService;
	
    
    @GetMapping("/search")
    public ResponseEntity<ApiResponseDTO<List<ServicesDTO>>> searchUsers(@RequestParam String description) {
        List<ServicesDTO> users = servicesService.searchServices(description);
        ApiResponseDTO<List<ServicesDTO>> response = new ApiResponseDTO<>("Success", HttpStatus.OK.value(), users);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
   

}
