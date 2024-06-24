package co.com.msservielectrogas.services;

import co.com.msservielectrogas.entity.Evidence;
import co.com.msservielectrogas.entity.OrderService;
import co.com.msservielectrogas.repository.IEvidenceRepository;
import co.com.msservielectrogas.repository.IOrderServiceRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class IEvidenceService {
    private final IEvidenceRepository evidenceRepository;
    private final IOrderServiceRepository orderServiceRepository;


    public IEvidenceService(IEvidenceRepository evidenceRepository, IOrderServiceRepository orderServiceRepository) {
        this.evidenceRepository = evidenceRepository;
        this.orderServiceRepository = orderServiceRepository;
    }
    
    public Evidence saveEvidence(MultipartFile file, Long orderServiceId) throws IOException {
        Optional<Evidence> existingEvidence = evidenceRepository.findByOrderServiceId(orderServiceId);
        if (existingEvidence.isPresent()) {
            evidenceRepository.delete(existingEvidence.get());
        }
        
        Evidence evidence = new Evidence();
        evidence.setFilename(file.getOriginalFilename());
        evidence.setFileType(file.getContentType());       
        evidence.setData(file.getBytes());
        evidence.setCreatedAt(LocalDateTime.now());
        
        OrderService orderService = orderServiceRepository.findById(orderServiceId);
        evidence.setOrderService(orderService);
        
        return evidenceRepository.save(evidence);
    }
    
    public Optional<Evidence> getEvidenceByOrderServiceId(Long orderServiceId) {
        return evidenceRepository.findByOrderServiceId(orderServiceId);
    }
    
    public Optional<Evidence> getEvidence(Long id) {
        return evidenceRepository.findById(id);
    }
}