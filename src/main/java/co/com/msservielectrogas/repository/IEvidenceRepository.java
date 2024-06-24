package co.com.msservielectrogas.repository;

import co.com.msservielectrogas.entity.Evidence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEvidenceRepository extends JpaRepository<Evidence, Long> {
    Optional<Evidence> findByOrderServiceId(Long orderServiceId);
}
