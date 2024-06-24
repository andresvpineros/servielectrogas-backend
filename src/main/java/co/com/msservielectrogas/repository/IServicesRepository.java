package co.com.msservielectrogas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.com.msservielectrogas.entity.Services;
import co.com.msservielectrogas.entity.Users;

@Repository
public interface IServicesRepository extends JpaRepository<Services, Integer>{
	
	List<Services> findByServicesType(String type);
	
	Services findByServicesDescription(String servicesDescription);

	Optional<Services> findById(Integer id);
	
	@Query(value = "SELECT id, price, services_description, services_type\r\n"
			+ "	FROM public.services \r\n"
			+ "	INNER JOIN public.orders_services ON orders_services.services_id = services.id\r\n"
			+ "	WHERE orders_services.orders_id = :orderId", nativeQuery = true)
	List<Services> findByOrdersId(Long orderId);
	
    @Query("SELECT c FROM Services c WHERE LOWER(c.servicesDescription) LIKE LOWER(CONCAT('%', :search, '%'))")
    List<Services> searchServices(String search);
}
