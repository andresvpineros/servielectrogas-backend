package co.com.msservielectrogas.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.time.LocalTime;

@Data
public class CreateOrderDTO {
    private String observations;
	private List<OrderServiceDTO> orderServices;
	private Integer status;
    private Long clientId;
    private Integer technicianId;
    private Date scheduleDate;
    private LocalTime scheduleTime;
    private Long totalCharged;
    private Long aditionalCharged;
}