package co.com.msservielectrogas.dto;

import java.util.Date;

public class TechnicianStatisticsDTO {
    private String technicianName;
    private Date month;

    private Long count;

    // Constructor adecuado para la consulta HQL
    public TechnicianStatisticsDTO(String technicianName, Date month, Long count) {
        this.technicianName = technicianName;

        this.month = month;
        this.count = count;
    }

    // Getters y setters
    public String getTechnicianName() {
        return technicianName;
    }

    public void setTechnicianName(String technicianName) {
        this.technicianName = technicianName;
    }

    public Date getMonth() {
        return month;
    }

    public void setMonth(Date month) {
        this.month = month;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
