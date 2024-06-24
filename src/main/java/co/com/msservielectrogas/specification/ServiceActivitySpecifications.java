package co.com.msservielectrogas.specification;

import co.com.msservielectrogas.entity.ServiceActivity;
import org.springframework.data.jpa.domain.Specification;

public class ServiceActivitySpecifications {

    public static Specification<ServiceActivity> hasDescription(String description) {
        return (root, query, builder) -> description == null ? null : builder.like(root.get("description"), "%" + description + "%");
    }
}