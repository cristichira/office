package com.app.office.appointment.api.dto;

import com.app.office.appointment.api.enumeration.AppointmentState;
import com.app.office.shared.api.dto.NameIdDTO;

import java.util.Date;

public class AppointmentDTO {
    private Long id;
    private NameIdDTO user;
    private NameIdDTO service;
    private Date createdDate;
    private Date scheduledDate;
    private AppointmentState state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NameIdDTO getUser() {
        return user;
    }

    public void setUser(NameIdDTO user) {
        this.user = user;
    }

    public NameIdDTO getService() {
        return service;
    }

    public void setService(NameIdDTO service) {
        this.service = service;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(Date scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public AppointmentState getState() {
        return state;
    }

    public void setState(AppointmentState state) {
        this.state = state;
    }
}
