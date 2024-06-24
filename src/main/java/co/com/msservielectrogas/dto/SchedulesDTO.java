package co.com.msservielectrogas.dto;

import java.util.Date;
import java.time.LocalTime;

public class SchedulesDTO {

    private Integer idSchedule;
    private Date date;
    private LocalTime hour;
    private Long orderId;
    private Integer technicianId;
	private String technicianName;

    // Default constructor
    public SchedulesDTO() {
    }

    // Parameterized constructor
    public SchedulesDTO(Date date, LocalTime hour, Long orderId, Integer technicianId) {
        this.date = date;
        this.hour = hour;
        this.orderId = orderId;
        this.technicianId = technicianId;
    }
   

    public SchedulesDTO(Integer idSchedule, Date date, LocalTime hour, Long orderId, Integer technicianId) {
        this.idSchedule = idSchedule;
        this.date = date;
        this.hour = hour;
        this.orderId = orderId;
        this.technicianId = technicianId;
    }
    
    public SchedulesDTO(Integer idSchedule, Date date, LocalTime hour, Long orderId, Integer technicianId, String technicianName) {
        this.idSchedule = idSchedule;
        this.date = date;
        this.hour = hour;
        this.orderId = orderId;
        this.technicianId = technicianId;
        this.technicianName = technicianName;
    }

    // Getters and Setters
    public Integer getId() {
        return idSchedule;
    }

    public void setId(Integer id) {
        this.idSchedule = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LocalTime getHour() {
        return hour;
    }

    public void setHour(LocalTime hour) {
        this.hour = hour;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getTechnicianId() {
        return technicianId;
    }

    public void setTechnicianId(Integer technicianId) {
        this.technicianId = technicianId;
    }
    
    public String getTechnicianName() {
        return technicianName;
    }

    public void setTechnicianName(String technicianName) {
        this.technicianName = technicianName;
    }
}
