package com.app.office.user.domain;

import com.app.office.shared.domain.EntityObject;
import com.app.office.shared.domain.INameIdEntity;
import com.app.office.user.api.enumeration.UserRole;

import javax.persistence.*;

@Entity
@Table(name = "oapp_user", uniqueConstraints = {
        @UniqueConstraint(name = "uc_user_name", columnNames = "username")
})
public class User extends EntityObject implements INameIdEntity {
    private String username;
    private String password;
    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    @Override
    public String getName() {
        return username;
    }

    public String getName5() {
        return username;
    }

    @Override
    public void setName(String name) {
        this.username = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
