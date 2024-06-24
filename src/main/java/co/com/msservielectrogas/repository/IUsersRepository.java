package co.com.msservielectrogas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.com.msservielectrogas.entity.Users;

public interface IUsersRepository extends JpaRepository<Users, Integer>{

	@Query(value = "SELECT id, email, name, password, rol\r\n"
			+ "	FROM public.users WHERE name = :name OR email = :email", nativeQuery = true)
	Users findByNameOrEmail(String name, String email);
	
	boolean existsByName(String name);
	
	boolean existsByEmailAndPassword(String email, String password);
	
	Users findByEmailAndPassword(String email, String password);
	
    Users findByEmail(String email);
	
    Optional<Users> findById(Integer id);
	
	@Query(value = "SELECT o.id, o.status, o.total_charged, cl.\"names\", cl.phone, cl.address, s.id_schedule, s.date, s.hour, s.users_id, u.\"name\"\r\n"
			+ "FROM public.users AS u\r\n"
			+ "INNER JOIN public.schedules AS s ON s.users_id = u.id\r\n"
			+ "INNER JOIN public.orders AS o ON o.id = s.fk_orders\r\n"
			+ "INNER JOIN public.clients AS cl ON cl.id = o.clients_id", nativeQuery = true)
	List<String> findAllSchedules();
	
    @Query("SELECT c FROM Users c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :search, '%'))")
    List<Users> searchUsers(String search);
}
