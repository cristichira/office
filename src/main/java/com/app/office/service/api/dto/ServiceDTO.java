package com.app.office.service.api.dto;

import com.app.office.shared.api.dto.NameIdDTO;

public class ServiceDTO extends NameIdDTO {
    private NameIdDTO owner;

    public NameIdDTO getOwner() {
        return owner;
    }

    public void setOwner(NameIdDTO owner) {
        this.owner = owner;
    }
}
