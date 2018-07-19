package com.app.office.user.api;

import com.app.office.user.api.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO findByEmail(String username);

    UserDTO findById(Long id);

    List<UserDTO> find();

    Long save(UserDTO userDTO);

    void delete(Long id);
}
