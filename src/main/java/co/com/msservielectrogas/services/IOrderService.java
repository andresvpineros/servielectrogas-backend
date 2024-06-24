package co.com.msservielectrogas.services;

import co.com.msservielectrogas.entity.Order;
import co.com.msservielectrogas.entity.OrderService;
import co.com.msservielectrogas.entity.Schedules;
import co.com.msservielectrogas.entity.Clients;
import co.com.msservielectrogas.entity.Users;
import co.com.msservielectrogas.dto.OrderDTO;
import co.com.msservielectrogas.dto.OrderServiceDTO;
import co.com.msservielectrogas.dto.ApiResponseDTO;
import co.com.msservielectrogas.dto.ClientDTO;
import co.com.msservielectrogas.dto.CreateOrderDTO;
import co.com.msservielectrogas.dto.UserDTO;
import co.com.msservielectrogas.repository.*;
import co.com.msservielectrogas.specification.OrderSpecifications;
import co.com.msservielectrogas.enums.EPriority;
import co.com.msservielectrogas.enums.EStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.poi.ss.usermodel.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IOrderService {

    @Autowired
    private IOrderRepository orderRepository;
    
    @Autowired
    private IUsersRepository userRepository;
    
    @Autowired
    private IClientRepository clientRepository;
    
    @Autowired
    private ISchedulesRepository scheduleRepository;
    
    @Autowired
    private IClientService clientService;
    
    @Autowired
    private IUserService userService;
    
    @Autowired
    private IOrderServiceService orderServiceService;
    
    @Autowired
    private IOrderServiceRepository orderServiceRepository;
    @Autowired
    private IServicesRepository servicesRepository;

    public Page<OrderDTO> getAllOrders(String search, String selectedStatus, String startDate, String endDate, Pageable pageable) {
        Specification<Order> spec = Specification.where(null); 

        if (search != null && !search.isEmpty()) {
            spec = spec.and(OrderSpecifications.searchOrders(search));
        }

        if (selectedStatus != null && !selectedStatus.isEmpty()) {
            spec = spec.and(OrderSpecifications.hasStatus(selectedStatus));
        }
        
        if ((startDate != null && !startDate.isEmpty()) || (endDate != null && !endDate.isEmpty())) {
            spec = spec.and(OrderSpecifications.hasCreatedAtBetween(startDate, endDate));
        }
        
        return orderRepository.findAll(spec, pageable)
                .map(this::convertToDTO);
    }
    
    public List<OrderDTO> getOrders(String selectedStatus, String startDate, String endDate) {
        Specification<Order> spec = Specification.where(OrderSpecifications.hasStatus(selectedStatus))
                .and(OrderSpecifications.hasCreatedAtBetween(startDate, endDate));

        return orderRepository.findAll(spec).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public OrderDTO getOrderById(Long id) {
    	Order order = orderRepository.findById(id);
    	
        if (order == null) {
            return null;
        }
        return convertToDTOWithOrderServices(order);
    }
    
    public ApiResponseDTO<OrderDTO> createOrder(CreateOrderDTO createOrderDTO) {
    	
    	ClientDTO clientDTO = clientService.getClientById(createOrderDTO.getClientId());
    	
        if (clientDTO == null) {
            return new ApiResponseDTO<>("El Client no se ha encontrado", HttpStatus.BAD_REQUEST.value(), null);
        }

        UserDTO userDTO = userService.getUserById(createOrderDTO.getTechnicianId());
        
        if (userDTO == null) {
            return new ApiResponseDTO<>("El Técnico no se ha encontrado", HttpStatus.BAD_REQUEST.value(), null);
        }
        
        Clients client = new Clients();
        client.setId(clientDTO.getId());
        client.setDocument(clientDTO.getDocument());
        client.setNames(clientDTO.getNames());
        client.setPhone(clientDTO.getPhone());
        client.setPhone1(clientDTO.getPhone1());
        client.setPhone2(clientDTO.getPhone2());
        client.setEmail(clientDTO.getEmail());
        client.setAddress(clientDTO.getAddress());
        client.setNameOfApplicant(clientDTO.getNameOfApplicant());
        client.setNumberOrderVendor(clientDTO.getNumberOrderVendor());
        client.setType(clientDTO.getType());

        Order order = new Order();
        order.setObservations(createOrderDTO.getObservations());
        order.setStatus(createOrderDTO.getStatus());
        order.setClient(client);
        order.setCreatedAt(LocalDateTime.now());
        order.setTotalCharged(createOrderDTO.getTotalCharged());
        order.setAditionalCharged(createOrderDTO.getAditionalCharged());

        order = orderRepository.save(order);
        
        // Create Order Services assigned to the Order

        List<OrderServiceDTO> createdOrderServiceDTOs = new ArrayList<>();
        for (OrderServiceDTO orderServiceDTO : createOrderDTO.getOrderServices()) {
            OrderServiceDTO createdOrderServiceDTO = orderServiceService.createOrderService(orderServiceDTO, order.getId(), createOrderDTO.getTechnicianId());
            createdOrderServiceDTOs.add(createdOrderServiceDTO);
        }
        
        // Create Schedule assigned to the Order

        Users user = new Users();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());

        Schedules schedule = new Schedules();
        schedule.setDate(createOrderDTO.getScheduleDate());
        schedule.setHour(createOrderDTO.getScheduleTime());
        schedule.setOrders(orderRepository.findById(order.getId()));
        schedule.setUsers(userRepository.findById(user.getId()).orElse(null));
        
        scheduleRepository.save(schedule);
        
        OrderDTO orderDTO = convertToDTO(order);
        orderDTO.setOrderServices(createdOrderServiceDTOs);
        
        return new ApiResponseDTO<>("Order created successfully", HttpStatus.CREATED.value(), orderDTO);
    }
    

    @Transactional
    public ApiResponseDTO<OrderDTO> editOrderAndRelatedEntities(Long orderId, OrderDTO orderDTO) {
        Order orderToUpdate = orderRepository.findById(orderId);

        if (orderToUpdate == null) {
            return new ApiResponseDTO<>("La orden no existe", HttpStatus.NOT_FOUND.value(), null);
        }

        Clients client = clientRepository.findById(orderDTO.getClientId());

        if (client == null) {
            return new ApiResponseDTO<>("El cliente no existe", HttpStatus.BAD_REQUEST.value(), null);
        }

        orderToUpdate.setObservations(orderDTO.getObservations());
        orderToUpdate.setStatus(orderDTO.getStatus());
        orderToUpdate.setClient(client);
        orderToUpdate.setTotalCharged(orderDTO.getTotalCharged());
        orderToUpdate.setAditionalCharged(orderDTO.getAditionalCharged());
        orderToUpdate.setUpdatedAt(LocalDateTime.now());
        orderToUpdate = orderRepository.save(orderToUpdate);

        List<OrderServiceDTO> updatedOrderServiceDTOs = new ArrayList<>();
        if (orderDTO.getOrderServices() != null) {
            for (OrderServiceDTO osDTO : orderDTO.getOrderServices()) {
                OrderService optionalOrderService = orderServiceRepository.findById(osDTO.getId());
                OrderService orderService;
                if (optionalOrderService != null) {
                    orderService = optionalOrderService;
                } else {
                    orderService = new OrderService();
                }

                orderService.setOrder(orderToUpdate);
                orderService.setService(servicesRepository.findById(osDTO.getServiceId()).orElse(null));
                orderService.setObservations(osDTO.getObservations());
                orderService.setOrderServiceDate(osDTO.getOrderServiceDate());
                orderService.setDuration(osDTO.getDuration());
                orderService.setPriority(osDTO.getPriority());
                orderService.setStatus(osDTO.getStatus());
                orderService.setTechnician(userRepository.findById(orderDTO.getTechnicianId()).orElse(null));

                if (osDTO.getAlreadyCreated()) {
                    orderService.setCreatedAt(osDTO.getCreatedAt());
                } else {
                    orderService.setCreatedAt(LocalDateTime.now());
                }

                orderServiceRepository.save(orderService);
                updatedOrderServiceDTOs.add(osDTO); 
            }
        }

        Schedules scheduleToUpdate = scheduleRepository.findByOrders(orderId);
        if (scheduleToUpdate != null) {
            Users user = userRepository.findById(orderDTO.getTechnicianId()).orElse(null);
            if (user != null) {
                scheduleToUpdate.setUsers(user);
            }
            scheduleRepository.save(scheduleToUpdate);
        }

        OrderDTO updatedOrderDTO = convertToDTOWithOrderServices(orderToUpdate);
        updatedOrderDTO.setOrderServices(updatedOrderServiceDTOs);

        return new ApiResponseDTO<>("Orden actualizada exitosamente", HttpStatus.OK.value(), updatedOrderDTO);
    }

    
    public ApiResponseDTO<Void> importOrders(MultipartFile file) {
        try {
            try (InputStream is = file.getInputStream(); Workbook workbook = new XSSFWorkbook(is)) {
                Sheet sheet = workbook.getSheetAt(0);
                for (Row row : sheet) {
                    if (row.getRowNum() == 0) {
                        continue;
                    }
                    Order order = new Order();
                    Schedules schedule = new Schedules();

                    String clientIdentifier = getCellValueAsString(row.getCell(0));
                    Optional<Clients> optionalClient = clientRepository.findByDocumentOrPhone(clientIdentifier, clientIdentifier);
                    if (optionalClient.isPresent()) {
                        order.setClient(optionalClient.get());
                    } else {
                        throw new IllegalArgumentException("Cliente no encontrado: " + clientIdentifier);
                    }

                    order.setObservations(getCellValueAsString(row.getCell(1)));

                    String statusString = getCellValueAsString(row.getCell(2)).toUpperCase();
                    EStatus[] statuses = EStatus.values();
                    int statusIndex = -1;
                    for (int i = 0; i < statuses.length; i++) {
                        if (statuses[i].name().equals(statusString)) {
                            statusIndex = i;
                            break;
                        }
                    }
                    if (statusIndex == -1) {
                        throw new IllegalArgumentException("Estado inválido: " + statusString);
                    }
                    
                    order.setStatus(statusIndex);
                    order.setTotalCharged((long) Double.parseDouble(getCellValueAsString(row.getCell(3))));
                    order.setCreatedAt(LocalDateTime.now());
                    String userEmail = getCellValueAsString(row.getCell(4));
                    if (userEmail == null || userEmail.isEmpty()) {
                        throw new IllegalArgumentException("El correo electrónico no puede estar vacío");
                    }
                    
                    Users technician = userRepository.findByEmail(userEmail);
                    if (technician != null) {
                    	schedule.setUsers(technician);
                    } else {
                        throw new IllegalArgumentException("Cliente no encontrado: " + clientIdentifier);
                    }
                                        
                    String dateString = getCellValueAsString(row.getCell(5));
                    if (dateString != null && !dateString.isEmpty()) {
                        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
                        try {
                            Date parsedDate = dateFormatter.parse(dateString);
                            schedule.setDate(parsedDate);
                        } catch (ParseException e) {
                            throw new IllegalArgumentException("Formato de fecha inválido: " + dateString);
                        }
                    }

                    Cell timeCell = row.getCell(6);
                    if (timeCell != null) {
                        try {
                            LocalTime parsedTime;
                            if (timeCell.getCellType() == CellType.NUMERIC) {
                                Date javaDate = timeCell.getDateCellValue();
                                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                                parsedTime = LocalTime.parse(sdf.format(javaDate));
                            } else {
                                String timeString = getCellValueAsString(timeCell);
                                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                                parsedTime = LocalTime.parse(timeString, timeFormatter);
                            }
                            schedule.setHour(parsedTime);
                        } catch (Exception e) {
                            throw new IllegalArgumentException("Formato de hora inválido: " + getCellValueAsString(timeCell));
                        }
                    }
                    
                    order = orderRepository.save(order);
                    
                    schedule.setOrders(order);

                    scheduleRepository.save(schedule);
                 }
            }
            return new ApiResponseDTO<>("Importación completada satisfactoriamente", HttpStatus.OK.value(), null);
        } catch (IllegalArgumentException e) {
            return new ApiResponseDTO<>("Error parseando la fecha: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
        } catch (Exception e) {
            return new ApiResponseDTO<>("Error importando las ordenes: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
        }
    }

    public void exportOrdersToExcel(HttpServletResponse response, List<OrderDTO> orders) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Orders");

        createHeaderRow(sheet);
        createDataRows(sheet, orders);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=Ordenes_de_trabajo_SERVIELECTROGAS.xlsx");

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
    
    private OrderDTO convertToDTOWithOrderServices(Order order) {
        OrderDTO orderDTO = convertToDTO(order);
        List<OrderServiceDTO> orderServiceDTOs = orderServiceRepository.findByOrderId(order.getId()).stream()
                .map(this::convertToOrderServiceDTO)
                .collect(Collectors.toList());
        orderDTO.setOrderServices(orderServiceDTOs);
        return orderDTO;
    }
    
    // [ UTILS ] 

    private String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    return dateFormat.format(cell.getDateCellValue());
                } else {
                    return BigDecimal.valueOf(cell.getNumericCellValue()).toPlainString();
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                switch (cell.getCachedFormulaResultType()) {
                    case STRING:
                        return cell.getRichStringCellValue().getString();
                    case NUMERIC:
                        return BigDecimal.valueOf(cell.getNumericCellValue()).toPlainString();
                    case BOOLEAN:
                        return Boolean.toString(cell.getBooleanCellValue());
                    default:
                        return "";
                }
            default:
                return "";
        }
    }
    
    private OrderDTO convertToDTO(Order order) {
        EStatus statusName = EStatus.values()[order.getStatus().intValue()];
        
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setObservations(order.getObservations());
        orderDTO.setStatusName(statusName);
        orderDTO.setStatus(order.getStatus());
        orderDTO.setTotalCharged(order.getTotalCharged());
        orderDTO.setAditionalCharged(order.getAditionalCharged());
        orderDTO.setClientId(order.getClient().getId());
        orderDTO.setClientNames(order.getClient().getNames());
        orderDTO.setClientDocument(order.getClient().getDocument());
        orderDTO.setClientPhone(order.getClient().getPhone());
        orderDTO.setClientAddress(order.getClient().getAddress());

        Schedules schedule = scheduleRepository.findByOrders(order.getId());
        if (schedule != null) {
        	orderDTO.setTechnicianId(schedule.getUsers().getId());
        	orderDTO.setTechnicianName(schedule.getUsers().getName());
            orderDTO.setScheduleDate(schedule.getDate());
            orderDTO.setScheduleTime(schedule.getHour());
        } else {
            throw new IllegalArgumentException("Agendamiento no encontrado: " + schedule);
        }

        orderDTO.setCreatedAt(order.getCreatedAt());
        orderDTO.setUpdatedAt(order.getUpdatedAt());
        return orderDTO;
    }

    private OrderServiceDTO convertToOrderServiceDTO(OrderService orderService) {
        EPriority priorityName = EPriority.values()[orderService.getPriority().intValue()];
        EStatus statusName = EStatus.values()[orderService.getStatus().intValue()];

        OrderServiceDTO orderServiceDTO = new OrderServiceDTO();
        orderServiceDTO.setId(orderService.getId());
        orderServiceDTO.setOrderId(orderService.getOrder().getId());
        orderServiceDTO.setService(orderService.getService());
        orderServiceDTO.setServiceId(orderService.getService().getId());
        orderServiceDTO.setObservations(orderService.getObservations());
        orderServiceDTO.setOrderServiceDate(orderService.getOrderServiceDate());
        orderServiceDTO.setDuration(orderService.getDuration());
        orderServiceDTO.setPriorityName(priorityName);
        orderServiceDTO.setPriority(orderService.getPriority());
        orderServiceDTO.setStatusName(statusName);
        orderServiceDTO.setStatus(orderService.getStatus());
        orderServiceDTO.setCreatedAt(orderService.getCreatedAt());
        orderServiceDTO.setTechnicianName(orderService.getTechnician().getName());
        orderServiceDTO.setTechnicianId(orderService.getTechnician().getId());
        return orderServiceDTO;
    }
    
    private void createHeaderRow(Sheet sheet) {
        Row headerRow = sheet.createRow(0);
        CellStyle headerStyle = sheet.getWorkbook().createCellStyle();
        Font headerFont = sheet.getWorkbook().createFont();
        headerFont.setBold(true);
        headerStyle.setFont(headerFont);

        String[] headers = {
        		"ID", 
        		"Nombre Cliente", 
        		"Identificación Cliente (Documento o Teléfono)", 
        		"Dirección Cliente", 
        		"Observaciones", 
        		"Estado de Orden", 
        		"Cobro Total", 
        		"Cobro Adicional", 
        		"Fecha de Creación", 
        		"Técnico", 
        		"Fecha de Agendamiento", 
        		"Hora de Agendamiento"
        };
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }
    }

    private void createDataRows(Sheet sheet, List<OrderDTO> orders) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        int rowNum = 1;
        
        for (OrderDTO order : orders) {
        	EStatus statusName = EStatus.values()[order.getStatus().intValue()];
            Row row = sheet.createRow(rowNum++);

            row.createCell(0).setCellValue(order.getId());
            row.createCell(1).setCellValue(order.getClientNames());
            row.createCell(2).setCellValue((order.getClientDocument() != null ? order.getClientDocument() : order.getClientPhone()));
            row.createCell(3).setCellValue(order.getClientAddress());
            row.createCell(4).setCellValue(order.getObservations());
            row.createCell(5).setCellValue(statusName.toString());
            row.createCell(6).setCellValue(order.getTotalCharged());
            row.createCell(7).setCellValue((order.getAditionalCharged() != null ? order.getAditionalCharged() : 0));
            
            String formattedDate = order.getCreatedAt().format(formatter);
            row.createCell(8).setCellValue(formattedDate);
            row.createCell(9).setCellValue(order.getTechnicianName());
            row.createCell(10).setCellValue(order.getScheduleDate());
            row.createCell(11).setCellValue(order.getScheduleTime().toString());
        }
    }
}