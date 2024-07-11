package co.com.msservielectrogas.repository;

import co.com.msservielectrogas.entity.Schedules;
import co.com.msservielectrogas.entity.Warranty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IWarrantyRepository extends JpaRepository<Warranty, Long>{

	@Query(value = "SELECT id, end_date, start_date, reason, order_service_id\r\n"
			+ "	FROM public.warranties WHERE order_service_id = :idOrderService", nativeQuery = true)
	Warranty findByOrderServices(Long idOrderService);
}
