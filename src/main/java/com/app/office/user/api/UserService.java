package com.app.office.user.api;

import com.app.office.user.api.dto.UserDTO;
import com.app.office.user.api.enumeration.UserRole;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;
import java.util.List;

public interface UserService extends UserDetailsService {

    UserDTO getCurrentUser();

    UserDTO findByEmail(String username);

    UserDTO findById(Long id);

    List<UserDTO> find();

    Long save(UserDTO userDTO);

    void delete(Long id);

    void assignRoles(Long userId, Collection<UserRole> roles);
}
