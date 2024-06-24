package co.com.msservielectrogas.controller;

import co.com.msservielectrogas.dto.ApiResponseDTO;
import co.com.msservielectrogas.entity.Evidence;
import co.com.msservielectrogas.services.IEvidenceService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/evidences")
public class EvidenceController {
    private final IEvidenceService evidenceService;

    public EvidenceController(IEvidenceService evidenceService) {
        this.evidenceService = evidenceService;
    }
    
    @GetMapping("/view/{orderServiceId}")
    public ResponseEntity<ApiResponseDTO<Optional<Evidence>>> getEvidenceByOrderServiceId(@PathVariable Long orderServiceId) {
    	Optional<Evidence> evidence = evidenceService.getEvidenceByOrderServiceId(orderServiceId);
        if (evidence != null) {
            ApiResponseDTO<Optional<Evidence>> response = new ApiResponseDTO<>("Success", HttpStatus.OK.value(), evidence);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponseDTO<Optional<Evidence>> response = new ApiResponseDTO<>("No se encontro evidencia para la orden de servicio", HttpStatus.NOT_FOUND.value(), null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping("/upload/{orderServiceId}")
    public ResponseEntity<ApiResponseDTO<String>> uploadEvidence(@RequestParam("file") MultipartFile file, @PathVariable Long orderServiceId) {
        try {
            Evidence evidence = evidenceService.saveEvidence(file, orderServiceId);
            
            if (evidence != null) {
                ApiResponseDTO<String> response = new ApiResponseDTO<>("Evidencia cargada correctamente", HttpStatus.OK.value(), evidence.getFilename());
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                ApiResponseDTO<String> response = new ApiResponseDTO<>("No se pudo cargar la evidencia", HttpStatus.NOT_FOUND.value(), null);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (IOException e) {
            ApiResponseDTO<String> response = new ApiResponseDTO<>("No se pudo cargar la evidencia", HttpStatus.NOT_FOUND.value(), null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
