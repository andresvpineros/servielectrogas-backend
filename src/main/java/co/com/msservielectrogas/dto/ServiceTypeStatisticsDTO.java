package co.com.msservielectrogas.dto;

public class ServiceTypeStatisticsDTO {
    private int month;
    private String serviceType;
    private long count;

    public ServiceTypeStatisticsDTO(int month, String serviceType, long count) {
        this.month = month;
        this.serviceType = serviceType;
        this.count = count;
    }

    // Getters y setters
    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
