package com.app.office.user.impl.service;

import com.app.office.shared.api.dto.NameIdDTO;
import com.app.office.shared.impl.NameIdMapper;
import com.app.office.user.api.dto.UserDTO;
import com.app.office.user.domain.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Mapper(uses = {NameIdMapper.class})
public abstract class UserMapper {

    @Mapping(target = "name", source = "email")
    abstract NameIdDTO toNameIdDTO(User user);

    abstract UserDTO toDTO(User user);

    @Mapping(target = "managerId", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "roles", ignore = true)
    abstract User toEntity(UserDTO userDTO, @MappingTarget User user);

    @AfterMapping
    void afterMapToEntity(UserDTO userDTO, @MappingTarget User user) {
        if (userDTO.getPassword() != null) {
            user.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
        }
    }
}
