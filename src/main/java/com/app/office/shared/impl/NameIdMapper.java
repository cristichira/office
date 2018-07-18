package com.app.office.shared.impl;

import com.app.office.shared.api.dto.NameIdDTO;
import com.app.office.shared.domain.INameIdEntity;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper
public interface NameIdMapper {
    NameIdDTO toDto(INameIdEntity entity);

    List<NameIdDTO> toDto(List<? extends INameIdEntity> entities);

    Set<NameIdDTO> toDto(Set<? extends INameIdEntity> entities);
}
