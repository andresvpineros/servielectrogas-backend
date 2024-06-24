package co.com.msservielectrogas.specification;

import co.com.msservielectrogas.entity.Schedules;
import co.com.msservielectrogas.entity.Users;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.criteria.Join;

import org.springframework.data.jpa.domain.Specification;

public class ScheduleSpecifications {

    public static Specification<Schedules> hasTechnicianName(String technicianName) {
        return (root, query, builder) -> {
            if (technicianName == null) {
                return null;
            } else {
                Join<Schedules, Users> userJoin = root.join("user");
                return builder.like(userJoin.get("name"), "%" + technicianName.toLowerCase() + "%");
            }
        };
    }
    
    public static Specification<Schedules> searchSchedules(String search) {
        return Specification.where(
        		hasTechnicianName(search)
        );
    }

    
    public static Specification<Schedules> hasDate(String date) {
        return (root, query, builder) -> {
            if (date == null) {
                return null;
            } else {
                LocalDateTime dateTime = null;
                
                if (date != null) {
                    LocalDate localStartDate = LocalDate.parse(date);
                    dateTime = localStartDate.atStartOfDay();
                }

                if (dateTime != null) {
                    return builder.greaterThanOrEqualTo(root.get("date"), dateTime);
                } else {
                    return builder.greaterThanOrEqualTo(root.get("date"), dateTime);
                }
            }
        };
    }
}