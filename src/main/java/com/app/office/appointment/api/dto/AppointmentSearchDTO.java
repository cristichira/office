package com.app.office.appointment.api.dto;

import com.app.office.appointment.api.enumeration.AppointmentState;

import java.util.Date;
import java.util.Set;

public class AppointmentSearchDTO {
    private Set<Long> serviceIds;
    private Long userId;
    private Date startDate;
    private Date endDate;
    private AppointmentState state;

    public Set<Long> getServiceIds() {
        return serviceIds;
    }

    public void setServiceIds(Set<Long> serviceIds) {
        this.serviceIds = serviceIds;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public AppointmentState getState() {
        return state;
    }

    public void setState(AppointmentState state) {
        this.state = state;
    }
}
