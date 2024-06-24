package co.com.msservielectrogas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.msservielectrogas.entity.Clients;

@Repository
public interface IClientRepository  extends JpaRepository<Clients, Integer>{
	
	boolean existsByPhone(String phone);
	
	boolean existsByAddress(String address);
	
	boolean existsByDocument(String document);
	
	Clients findById(Long id);

	Clients findByPhone(String phone);
	
	Clients findByPhoneOrDocument(String phone, String document);
	
	Clients findByPhoneOrDocumentOrAddress(String phone, String document, String address);
	
    @Query("SELECT c FROM Clients c WHERE c.phone LIKE CONCAT('%', :query, '%') OR c.document LIKE CONCAT('%', :query, '%')")
    List<Clients> searchClients(@Param("query") String query);
    
    @Query("SELECT c FROM Clients c WHERE c.phone LIKE CONCAT('%', :phone, '%') OR c.document LIKE CONCAT('%', :document, '%')")
    Optional<Clients> findByDocumentOrPhone(String document, String phone);
}
