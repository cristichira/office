package com.app.office.user.impl.service;

import com.app.office.user.domain.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {

    public static Specification<User> unrestricted() {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.and();
    }

    public static Specification<User> usernameContains(String username) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("username"), "%" + username + "%");
    }

    public static Specification<User> username(String username) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("username"), username);
    }
}
