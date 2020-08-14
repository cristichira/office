package com.app.office.appointment.api.enumeration;

import java.util.Comparator;

public class AppointmentStateComparator implements Comparator<AppointmentState> {

    @Override
    public int compare(AppointmentState appointmentState, AppointmentState t1) {
        return appointmentState.getOrder() - t1.getOrder();
    }
}
