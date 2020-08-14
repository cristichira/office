package com.app.office.appointment.api.enumeration;

public enum AppointmentState {
    PENDING(1),
    CONFIRMED(1),
    CANCELED(1),
    DECLINED(2),
    CANCELED_AFTER_CONFIRMATION(1),
    DECLINED_AFTER_CONFIRMATION(1),
    DONE(2);

    private final int order;

    AppointmentState(int order) {
        this.order = order;
    }

    public int getOrder() {
        return order;
    }
}
