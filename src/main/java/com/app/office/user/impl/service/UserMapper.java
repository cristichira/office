package com.app.office.user.impl.service;

import com.app.office.shared.api.dto.NameIdDTO;
import com.app.office.user.api.dto.UserDTO;
import com.app.office.user.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public abstract class UserMapper {

    @Mapping(target = "name", source = "username")
    abstract NameIdDTO toNameIdDTO(User user);

    abstract UserDTO toDTO(User user);

    @Mapping(target = "name", ignore = true)
    abstract User toEntity(UserDTO userDTO, @MappingTarget User user);
}
