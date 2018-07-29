package com.app.office.service.api;

import com.app.office.service.api.dto.ServiceDTO;
import com.app.office.service.api.dto.ServiceSearchDTO;

import java.util.List;

public interface ServiceService {
    List<ServiceDTO> find(ServiceSearchDTO serviceSearchDTO);

    ServiceDTO findById(Long id);

    Long save(ServiceDTO serviceDTO);

    void delete(Long id);
}
