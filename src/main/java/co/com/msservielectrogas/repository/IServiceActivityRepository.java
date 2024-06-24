package co.com.msservielectrogas.repository;

import co.com.msservielectrogas.entity.ServiceActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IServiceActivityRepository extends JpaRepository<ServiceActivity, Long>, JpaSpecificationExecutor<ServiceActivity> {
}
