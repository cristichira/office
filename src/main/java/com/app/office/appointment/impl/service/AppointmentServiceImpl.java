package com.app.office.appointment.impl.service;

import com.app.office.appointment.api.AppointmentService;
import com.app.office.appointment.api.dto.AppointmentChangeStateDTO;
import com.app.office.appointment.api.dto.AppointmentDTO;
import com.app.office.appointment.api.dto.AppointmentSearchDTO;
import com.app.office.appointment.api.enumeration.AppointmentStateComparator;
import com.app.office.appointment.domain.Appointment;
import com.app.office.appointment.impl.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentMapper appointmentMapper;
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentServiceImpl(AppointmentMapper appointmentMapper,
                                  AppointmentRepository appointmentRepository) {
        this.appointmentMapper = appointmentMapper;
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Long save(AppointmentDTO appointmentDTO) {
        final Appointment appointment = appointmentDTO.getId() != null ? appointmentRepository.findById(appointmentDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Cannot find service with id: " + appointmentDTO.getId()))
                : new Appointment();
        appointment.setServiceId(appointmentDTO.getService().getId());
        appointment.setUserId(appointmentDTO.getUser().getId());
        appointment.setScheduledDate(appointmentDTO.getScheduledDate());
        appointment.setState(appointmentDTO.getState());
        return appointmentRepository.save(appointment).getId();
    }

    @Override
    public List<AppointmentDTO> find(AppointmentSearchDTO appointmentSearchDTO) {
        Specification<Appointment> query = AppointmentSpecification.unrestricted();

        if (appointmentSearchDTO.getServiceIds() != null) {
            query = query.and(AppointmentSpecification.serviceIdsIn(appointmentSearchDTO.getServiceIds()));
        }
        if (appointmentSearchDTO.getUserId() != null) {
            query = query.and(AppointmentSpecification.userId(appointmentSearchDTO.getUserId()));
        }

        final Sort sort = Sort.by(
                Sort.Order.desc("state"),
                Sort.Order.asc("scheduledDate"));

        return appointmentRepository.findAll(query)
                .stream()
                .sorted((a1, a2) -> {
                    AppointmentStateComparator appointmentStateComparator = new AppointmentStateComparator();
                    int compare = appointmentStateComparator.compare(a1.getState(), a2.getState());
                    if (compare == 0) {
                        return a1.getScheduledDate().compareTo(a2.getScheduledDate());
                    } else {
                        return compare;
                    }
                })
                .map(appointmentMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public Long changeState(AppointmentChangeStateDTO appointmentChangeStateDTO) {
        Appointment appointment = appointmentRepository.findById(appointmentChangeStateDTO.getAppointmentId())
                .orElseThrow(() -> new EntityNotFoundException("Cannot find appointment with id: " + appointmentChangeStateDTO.getAppointmentId()));
        appointment.setState(appointmentChangeStateDTO.getState());
        appointmentRepository.save(appointment);
        return appointment.getService().getId();
    }
}
