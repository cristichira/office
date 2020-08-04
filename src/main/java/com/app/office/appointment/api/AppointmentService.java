package com.app.office.appointment.api;

import com.app.office.appointment.api.dto.AppointmentChangeStateDTO;
import com.app.office.appointment.api.dto.AppointmentDTO;
import com.app.office.appointment.api.dto.AppointmentSearchDTO;

import java.util.List;

public interface AppointmentService {

    Long save(AppointmentDTO appointmentDTO);

    List<AppointmentDTO> find(AppointmentSearchDTO appointmentSearchDTO);

    Long changeState(AppointmentChangeStateDTO appointmentChangeStateDTO);
}
