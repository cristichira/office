package com.app.office.service.domain;

import com.app.office.shared.domain.EntityObject;
import com.app.office.shared.domain.INameIdEntity;
import com.app.office.user.domain.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "oap_service")
public class Service extends EntityObject implements INameIdEntity {

    @NotBlank
    private String name;

    @Column(name = "owner_id", nullable = false)
    private Long ownerId;

    @ManyToOne
    @JoinColumn(name = "owner_id", insertable = false, updatable = false)
    private User owner;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public User getOwner() {
        return owner;
    }
}
