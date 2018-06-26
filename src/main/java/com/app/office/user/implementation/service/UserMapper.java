package com.app.office.user.implementation.service;

import com.app.office.user.domain.User;
import com.app.office.user.api.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper
public abstract class UserMapper {

    public abstract UserDTO toDTO(User user);
}
