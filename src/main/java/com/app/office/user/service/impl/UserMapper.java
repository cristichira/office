package com.app.office.user.service.impl;

import com.app.office.user.domain.User;
import com.app.office.user.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper
public abstract class UserMapper {

    public abstract UserDTO toDTO(User user);
}
