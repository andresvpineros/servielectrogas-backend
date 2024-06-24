package co.com.msservielectrogas.controller;

import co.com.msservielectrogas.dto.ApiResponseDTO;
import co.com.msservielectrogas.dto.CreateOrderDTO;
import co.com.msservielectrogas.dto.OrderDTO;
import co.com.msservielectrogas.services.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private IOrderService orderService;
    
    @GetMapping
    public ResponseEntity<ApiResponseDTO<Page<OrderDTO>>> getAllOrders(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String selectedStatus,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String[] sort) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(getSortOrders(sort)));

        Page<OrderDTO> serviceActivities = orderService.getAllOrders(search, selectedStatus, startDate, endDate, pageable);
        ApiResponseDTO<Page<OrderDTO>> response = new ApiResponseDTO<>("Success", HttpStatus.OK.value(), serviceActivities);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<OrderDTO>> getOrderById(@PathVariable Long id) {
        OrderDTO order = orderService.getOrderById(id);
        if (order != null) {
            ApiResponseDTO<OrderDTO> response = new ApiResponseDTO<>("Success", HttpStatus.OK.value(), order);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponseDTO<OrderDTO> response = new ApiResponseDTO<>("Orden de trabajo no encontrada", HttpStatus.NOT_FOUND.value(), null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping
    public ResponseEntity<ApiResponseDTO<OrderDTO>> createOrder(@RequestBody CreateOrderDTO createOrderDTO) {
        ApiResponseDTO<OrderDTO> response = orderService.createOrder(createOrderDTO);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }
    
    @GetMapping("/export")
    public void exportOrdersToExcel(HttpServletResponse response,
            						@RequestParam(required = false) String search,
                                    @RequestParam(required = false) String selectedStatus,
                                    @RequestParam(required = false) String startDate,
                                    @RequestParam(required = false) String endDate) throws IOException {
        List<OrderDTO> orders = orderService.getOrders(selectedStatus, startDate, endDate);
        orderService.exportOrdersToExcel(response, orders);
    }
    
    @PostMapping("/import")
    public ResponseEntity<ApiResponseDTO<Void>> importOrders(@RequestParam("file") MultipartFile file) {
        ApiResponseDTO<Void> response = orderService.importOrders(file);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
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
    
    @PutMapping("/{orderId}")
    public ResponseEntity<ApiResponseDTO<OrderDTO>> editOrderAndRelatedEntities(
        @PathVariable Long orderId,
        @RequestBody OrderDTO orderDTO) {

    ApiResponseDTO<OrderDTO> response = orderService.editOrderAndRelatedEntities(orderId, orderDTO);

    if (response.getData()!= null) {
        return ResponseEntity.ok(response);
    } else {
        return ResponseEntity.status(HttpStatus.valueOf(response.getCode())).body(response);
    }
}
}
