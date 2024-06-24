package co.com.msservielectrogas.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.com.msservielectrogas.entity.Schedules;

@Repository
public interface ISchedulesRepository extends JpaRepository<Schedules, Integer>, JpaSpecificationExecutor<Schedules>{

	@Query(value = "SELECT id_schedule, date, hour, fk_orders, users_id\r\n"
			+ "	FROM public.schedules WHERE users_id = :idUser", nativeQuery = true)
	List<Schedules> findByUsers(Integer idUser);
	
	@Query(value = "SELECT id_schedule, date, hour, fk_orders, users_id\r\n"
			+ "	FROM public.schedules WHERE fk_orders = :idOrder", nativeQuery = true)
	Schedules findByOrders(Long idOrder);
	
	@Query(value = "SELECT s.date, o.id, cl.number_order_vendor, cl.names, cl.document,  cl.phone, cl.phone1, cl.phone2, cl.address, u.name, se.services_type, se.services_description \r\n"
			+ "FROM public.schedules AS s \r\n"
			+ "INNER JOIN public.users AS u ON u.id = s.users_id\r\n"
			+ "INNER JOIN public.orders AS o ON o.id = s.fk_orders\r\n"
			+ "INNER JOIN public.orders_services AS os ON os.orders_id = o.id\r\n"
			+ "INNER JOIN public.services AS se ON se.id = os.services_id\r\n"
			+ "INNER JOIN public.clients AS cl ON cl.id = o.clients_id WHERE s.date = :date OR o.status = :status", nativeQuery = true)
	List<String> findByDateOrStatus(Date date, String status);
	
	List<Schedules> findByDate(Date date);
	
	List<Schedules> findByHour(String hour);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	void deleteById(Integer id);
}
