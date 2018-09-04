package com.app.office.appointment.impl.service;

import com.app.office.appointment.domain.Appointment;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collection;

public class AppointmentSpecification {

    public static Specification<Appointment> unrestricted() {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.and();
    }

    public static Specification<Appointment> serviceIdsIn(Collection<Long> serviceIds) {
        return (root, criteriaQuery, criteriaBuilder) ->
                serviceIds.isEmpty() ? criteriaBuilder.isTrue(criteriaBuilder.literal(false)) : root.get("serviceId").in(serviceIds);
    }

    public static Specification<Appointment> userId(Long userId) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("userId"), userId);
    }
}
