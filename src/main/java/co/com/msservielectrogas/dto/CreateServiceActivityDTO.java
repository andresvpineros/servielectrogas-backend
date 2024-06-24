package co.com.msservielectrogas.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.Duration;

@Data
public class CreateServiceActivityDTO {
    private String description;
    private LocalDateTime activityDate;
    private Duration duration;
    private Integer priority;
    private Integer status;
    private Long orderId;
    private Integer technicianId;
}