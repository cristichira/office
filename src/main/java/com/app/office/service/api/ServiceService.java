package com.app.office.service.api;

import com.app.office.service.api.dto.ServiceDTO;

import java.util.List;

public interface ServiceService {
    List<ServiceDTO> find();

    ServiceDTO findById(Long id);

    Long save(ServiceDTO serviceDTO);

    void delete(Long id);
}
