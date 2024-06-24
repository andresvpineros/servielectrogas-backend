package co.com.msservielectrogas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.com.msservielectrogas.entity.Clients;
import co.com.msservielectrogas.entity.OrderService;

@Repository
public interface IOrderServiceRepository extends JpaRepository<OrderService, Integer>{    
	
	OrderService findById(Long id);

    @Query("SELECT os FROM OrderService os WHERE os.order.id = :orderId")
    List<OrderService> findByOrderId(Long orderId);
}