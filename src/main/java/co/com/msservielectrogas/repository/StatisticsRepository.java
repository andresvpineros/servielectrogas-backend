package co.com.msservielectrogas.repository;

import co.com.msservielectrogas.dto.ServiceReportDTO;
import co.com.msservielectrogas.dto.ServiceTypeStatisticsDTO;
import co.com.msservielectrogas.dto.TechnicianEffectivenessDTO;
import co.com.msservielectrogas.dto.TechnicianSettlementDTO;
import co.com.msservielectrogas.dto.TechnicianStatisticsDTO;
import co.com.msservielectrogas.dto.WarrantiesByTechnicianDTO;
import co.com.msservielectrogas.dto.WarrantiesByTypeDTO;
import co.com.msservielectrogas.dto.WarrantyStatisticsDTO;
import co.com.msservielectrogas.entity.OrderService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface StatisticsRepository extends CrudRepository<OrderService, Integer> {
/* 
    @Query("SELECT new co.com.msservielectrogas.dto.TechnicianStatisticsDTO(u.name, FUNCTION('DATE_TRUNC', 'month', os.orderServiceDate), COUNT(*)) " +
           "FROM OrderService os " +
           "JOIN os.technician u " +
           "GROUP BY u.name, FUNCTION('DATE_TRUNC', 'month', os.orderServiceDate) " +
           "ORDER BY u.name, FUNCTION('DATE_TRUNC', 'month', os.orderServiceDate)")
    List<TechnicianStatisticsDTO> getTechnicianStatistics();*/
	
    @Query("SELECT new co.com.msservielectrogas.dto.TechnicianStatisticsDTO(u.name, FUNCTION('DATE_TRUNC', 'month', os.createdAt), COUNT(*)) " +
    	       "FROM OrderService os " +
    	       "JOIN os.technician u " +
    	       "WHERE os.createdAt >= :startDate AND os.createdAt < :endDate " +
    	       "GROUP BY u.name, FUNCTION('DATE_TRUNC', 'month', os.createdAt) " +
    	       "ORDER BY u.name, FUNCTION('DATE_TRUNC', 'month', os.createdAt)")
    	List<TechnicianStatisticsDTO> getTechnicianStatistics(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT new co.com.msservielectrogas.dto.WarrantyStatisticsDTO(w.reason, FUNCTION('DATE_TRUNC', 'month', os.orderServiceDate), COUNT(*)) " +
            "FROM Warranty w " +
            "JOIN w.orderService os " +
            "GROUP BY w.reason, FUNCTION('DATE_TRUNC', 'month', os.orderServiceDate) " +
            "ORDER BY w.reason, FUNCTION('DATE_TRUNC', 'month', os.orderServiceDate)")
     List<TechnicianStatisticsDTO> getWarrantyTechnicianStatistics();

    @Query("SELECT new co.com.msservielectrogas.dto.WarrantyStatisticsDTO(w.reason, FUNCTION('DATE_TRUNC', 'month', os.orderServiceDate), COUNT(*)) " +
    	       "FROM Warranty w " +
    	       "JOIN w.orderService os " +
    	       "GROUP BY w.reason, FUNCTION('DATE_TRUNC', 'month', os.orderServiceDate) " +
    	       "ORDER BY w.reason, FUNCTION('DATE_TRUNC', 'month', os.orderServiceDate)")
    	List<WarrantyStatisticsDTO> getWarrantyStatistics();

   
    @Query("SELECT new co.com.msservielectrogas.dto.ServiceTypeStatisticsDTO(MONTH(os.orderServiceDate), s.servicesDescription, COUNT(os.id)) " +
    	       "FROM OrderService os " +
    	       "JOIN os.service s " +
    	       "GROUP BY MONTH(os.orderServiceDate), s.servicesDescription")
    	List<ServiceTypeStatisticsDTO> getServiceTypeStatistics();

    @Query("SELECT new co.com.msservielectrogas.dto.TechnicianEffectivenessDTO(u.name, MONTH(os.orderServiceDate), AVG(os.duration)) " +
    	       "FROM OrderService os " +
    	       "JOIN os.technician u " +
    	       "GROUP BY u.name, MONTH(os.orderServiceDate) " +
    	       "ORDER BY u.name, MONTH(os.orderServiceDate)")
    	List<TechnicianEffectivenessDTO> getTechnicianEffectivenessStatistics();

    @Query("SELECT new co.com.msservielectrogas.dto.WarrantiesByTechnicianDTO(t.name, MONTH(w.startDate), COUNT(w.id)) " +
    	       "FROM Warranty w " +
    	       "JOIN w.orderService os " +
    	       "JOIN os.technician t " +
    	       "GROUP BY t.name, MONTH(w.startDate)")
    	List<WarrantiesByTechnicianDTO> getWarrantiesByTechnician();

    @Query("SELECT new co.com.msservielectrogas.dto.WarrantiesByTypeDTO(w.reason, MONTH(w.startDate), COUNT(w)) " +
    	       "FROM Warranty w " +
    	       "GROUP BY w.reason, MONTH(w.startDate)")
    	List<WarrantiesByTypeDTO> getWarrantiesByType();

    
  
    @Query("SELECT new co.com.msservielectrogas.dto.ServiceReportDTO(u.name, u.name, o.observations, SUM(o.totalCharged), o.createdAt) " +
    	       "FROM OrderService os " +
    	       "JOIN os.order o " +
    	       "JOIN os.technician u " +
    	       "GROUP BY u.name, o.observations, o.createdAt")
    	List<ServiceReportDTO> getServiceReports();


    @Query("SELECT new co.com.msservielectrogas.dto.TechnicianSettlementDTO(u.email, u.name, SUM(o.totalCharged), FUNCTION('TO_CHAR', o.createdAt, 'MM-YYYY')) " +
    	       "FROM OrderService os " +
    	       "JOIN os.order o " +
    	       "JOIN os.technician u " +
    	       "GROUP BY u.email, u.name, FUNCTION('TO_CHAR', o.createdAt, 'MM-YYYY')")
    	List<TechnicianSettlementDTO> getTechnicianSettlements();

}
