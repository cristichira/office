package com.app.office.appointment.api.dto;

import com.app.office.appointment.api.enumeration.AppointmentState;

public class AppointmentChangeStateDTO {
    private Long appointmentId;
    private AppointmentState state;

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public AppointmentState getState() {
        return state;
    }

    public void setState(AppointmentState state) {
        this.state = state;
    }
}
