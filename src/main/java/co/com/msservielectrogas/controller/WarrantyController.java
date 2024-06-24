package co.com.msservielectrogas.controller;

import co.com.msservielectrogas.entity.Warranty;
import co.com.msservielectrogas.repository.IWarrantyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/warranties")
public class WarrantyController {

    @Autowired
    private IWarrantyRepository warrantyRepository;

    @GetMapping
    public List<Warranty> getAllWarranties() {
        return warrantyRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Warranty> getWarrantyById(@PathVariable Long id) {
        Optional<Warranty> warranty = warrantyRepository.findById(id);
        return warranty.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    /*
    @PostMapping
    public Warranty createWarranty(@RequestBody WarrantyDTO warrantyDTO) {
        Optional<OrderService> orderServiceOptional = orderServiceRepository.findById(warrantyDTO.getOrderServiceId());
        if (orderServiceOptional.isPresent()) {
            Warranty warranty = new Warranty();
            warranty.setReason(warrantyDTO.getReason());
            warranty.setStartDate(warrantyDTO.getStartDate());
            warranty.setEndDate(warrantyDTO.getEndDate());
            warranty.setOrderService(orderServiceOptional.get());
            return warrantyRepository.save(warranty);
        } else {
            throw new IllegalArgumentException("OrderService not found");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Warranty> updateWarranty(@PathVariable Long id, @RequestBody WarrantyDTO warrantyDTO) {
        Optional<Warranty> warrantyOptional = warrantyRepository.findById(id);
        Optional<OrderService> orderServiceOptional = orderServiceRepository.findById(warrantyDTO.getOrderServiceId());
        if (warrantyOptional.isPresent() && orderServiceOptional.isPresent()) {
            Warranty warranty = warrantyOptional.get();
            warranty.setReason(warrantyDTO.getReason());
            warranty.setStartDate(warrantyDTO.getStartDate());
            warranty.setEndDate(warrantyDTO.getEndDate());
            warranty.setOrderService(orderServiceOptional.get());
            return ResponseEntity.ok(warrantyRepository.save(warranty));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWarranty(@PathVariable Long id) {
        if (warrantyRepository.existsById(id)) {
            warrantyRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
        */
}
