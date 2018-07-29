package com.app.office.service.impl.service;

import com.app.office.service.api.dto.ServiceDTO;
import com.app.office.service.domain.Service;
import com.app.office.user.impl.service.UserMapper;
import org.mapstruct.Mapper;

@Mapper(uses = {UserMapper.class})
public abstract class ServiceMapper {

    public abstract ServiceDTO toDTO(Service service);
}
