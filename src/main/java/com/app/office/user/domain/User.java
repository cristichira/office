package com.app.office.user.domain;

import com.app.office.shared.domain.EntityObject;
import com.app.office.user.api.enumeration.UserRole;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "oap_user", uniqueConstraints = {
        @UniqueConstraint(name = "uc_user_email", columnNames = "email")
})
public class User extends EntityObject {

    @Email
    @Column(nullable = false)
    private String email;
    @Length(min = 6, max = 255)
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private String mobilePhone;
    private boolean emailConfirmed = false;
    private boolean adminConfirmed = false;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    @CollectionTable(name = "oap_user_role")
    private List<UserRole> roles = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "manager_id", insertable = false, updatable = false)
    private User manager;

    @Column(name = "manager_id")
    private Long managerId;

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

    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    public User getManager() {
        return manager;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }
}
