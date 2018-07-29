package com.app.office.service.impl.service;

import com.app.office.service.domain.Service;
import org.springframework.data.jpa.domain.Specification;

public class ServiceSpecification {

    public static Specification<Service> unrestricted() {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.and();
    }

    public static Specification<Service> ownerId(Long ownerId) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("ownerId"), ownerId);
    }
}
