package com.app.office.service.impl.service;

import com.app.office.service.api.ServiceService;
import com.app.office.service.api.dto.ServiceDTO;
import com.app.office.service.api.dto.ServiceSearchDTO;
import com.app.office.service.impl.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ServiceServiceImpl implements ServiceService {

    private final ServiceMapper serviceMapper;
    private final ServiceRepository serviceRepository;

    @Autowired
    public ServiceServiceImpl(ServiceMapper serviceMapper,
                              ServiceRepository serviceRepository) {
        this.serviceMapper = serviceMapper;
        this.serviceRepository = serviceRepository;
    }

    @Override
    public List<ServiceDTO> find(ServiceSearchDTO serviceSearchDTO) {
        Specification<com.app.office.service.domain.Service> query = ServiceSpecification.unrestricted();

        if (serviceSearchDTO.getOwnerId() != null) {
            query = query.and(ServiceSpecification.ownerId(serviceSearchDTO.getOwnerId()));
        }
        return serviceRepository.findAll(query).stream().map(serviceMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public ServiceDTO findById(Long id) {
        return serviceRepository.findById(id).map(serviceMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find service with id: " + id));
    }

    @Override
    public Long save(ServiceDTO serviceDTO) {
        com.app.office.service.domain.Service service = serviceDTO.getId() != null ? serviceRepository.findById(serviceDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Cannot find service with id: " + serviceDTO.getId()))
                : new com.app.office.service.domain.Service();
        service.setName(serviceDTO.getName());
        service.setOwnerId(serviceDTO.getOwner().getId());
        return serviceRepository.save(service).getId();
    }

    @Override
    public void delete(Long id) {
        serviceRepository.deleteById(id);
    }

    @Override
    public Set<Long> findServiceIdsOfUserId(Long userId) {
        Specification<com.app.office.service.domain.Service> query = ServiceSpecification.ownerId(userId);
        return serviceRepository.findAll(query).stream().map(com.app.office.service.domain.Service::getId).collect(Collectors.toSet());
    }
}
