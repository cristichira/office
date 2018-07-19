package com.app.office.user.api.dto;

import com.app.office.shared.api.dto.NameIdDTO;
import com.app.office.user.api.enumeration.UserRole;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private String mobilePhone;
    private boolean emailConfirmed = false;
    private boolean adminConfirmed = false;
    private NameIdDTO manager;
    private List<UserRole> roles = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public boolean isEmailConfirmed() {
        return emailConfirmed;
    }

    public void setEmailConfirmed(boolean emailConfirmed) {
        this.emailConfirmed = emailConfirmed;
    }

    public boolean isAdminConfirmed() {
        return adminConfirmed;
    }

    public void setAdminConfirmed(boolean adminConfirmed) {
        this.adminConfirmed = adminConfirmed;
    }

    public NameIdDTO getManager() {
        return manager;
    }

    public void setManager(NameIdDTO manager) {
        this.manager = manager;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }
}
