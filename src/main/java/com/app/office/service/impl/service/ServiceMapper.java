package com.app.office.service.impl.service;

import com.app.office.service.api.dto.ServiceDTO;
import com.app.office.service.domain.Service;
import com.app.office.shared.impl.NameIdMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {NameIdMapper.class})
public abstract class ServiceMapper {

    @Mapping(target = "owner.name", source = "owner.email")
    abstract ServiceDTO toDTO(Service service);
}
