package co.com.msservielectrogas.repository;

import co.com.msservielectrogas.entity.Warranty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWarrantyRepository extends JpaRepository<Warranty, Long>{
}
