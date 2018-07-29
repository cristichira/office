package com.app.office.appointment.impl.service;

import com.app.office.appointment.api.dto.AppointmentDTO;
import com.app.office.appointment.domain.Appointment;
import com.app.office.user.impl.service.UserMapper;
import org.mapstruct.Mapper;

@Mapper(uses = {UserMapper.class})
public abstract class AppointmentMapper {

    public abstract AppointmentDTO toDTO(Appointment appointment);
}
