package co.com.msservielectrogas.specification;

import co.com.msservielectrogas.entity.Clients;
import co.com.msservielectrogas.entity.Order;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.criteria.Join;

import org.springframework.data.jpa.domain.Specification;

public class OrderSpecifications {

	public static Specification<Order> hasObservations(String observations) {
	    return (root, query, builder) -> {
	        if (observations == null) {
	            return null;
	        } else {
	            return builder.like(builder.lower(root.get("observations")), "%" + observations.toLowerCase() + "%");
	        }
	    };
	}
    
    public static Specification<Order> hasOrderId(Long orderId) {
        return (root, query, builder) -> orderId == null ? null : builder.equal(root.get("id"), orderId);
    }
    
    public static Specification<Order> hasClientName(String clientName) {
        return (root, query, builder) -> {
            if (clientName == null) {
                return null;
            } else {
                Join<Order, Clients> clientJoin = root.join("client");
                return builder.like(builder.lower(clientJoin.get("names")), "%" + clientName.toLowerCase() + "%");
            }
        };
    }
    
    public static Specification<Order> hasClientDocument(String clientDocument) {
        return (root, query, builder) -> {
            if (clientDocument == null) {
                return null;
            } else {
                Join<Order, Clients> clientJoin = root.join("client");
                return builder.like(clientJoin.get("document"), "%" + clientDocument + "%");
            }
        };
    }
    
    public static Specification<Order> hasClientPhone(String clientPhone) {
        return (root, query, builder) -> {
            if (clientPhone == null) {
                return null;
            } else {
                Join<Order, Clients> clientJoin = root.join("client");
                return builder.like(clientJoin.get("phone"), "%" + clientPhone + "%");
            }
        };
    }
    
    public static Specification<Order> searchOrders(String search) {
        return Specification.where(
            hasObservations(search)
                .or(hasOrderId(parseLong(search)))
                .or(hasClientName(search))
                .or(hasClientDocument(search))
                .or(hasClientPhone(search))
        );
    }
    
    public static Specification<Order> hasStatus(String status) {
        return (root, query, builder) -> status == null ? null : builder.equal(root.get("status"), status);
    }
    
    public static Specification<Order> hasCreatedAtBetween(String startDate, String endDate) {
        return (root, query, builder) -> {
            if (startDate == null && endDate == null) {
                return null;
            } else {
                LocalDateTime startDateTime = null;
                LocalDateTime endDateTime = null;
                
                if (startDate != null) {
                    LocalDate localStartDate = LocalDate.parse(startDate);
                    startDateTime = localStartDate.atStartOfDay();
                }
                if (endDate != null) {
                    LocalDate localEndDate = LocalDate.parse(endDate);
                    endDateTime = localEndDate.atStartOfDay().plusDays(1); // Add one day to include the whole end date
                }

                if (startDateTime == null) {
                    return builder.lessThanOrEqualTo(root.get("createdAt"), endDateTime);
                } else if (endDateTime == null) {
                    return builder.greaterThanOrEqualTo(root.get("createdAt"), startDateTime);
                } else {
                    return builder.between(root.get("createdAt"), startDateTime, endDateTime);
                }
            }
        };
    }


    
    private static Long parseLong(String s) {
        try {
            return Long.parseLong(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}