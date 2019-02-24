package ir.piana.dev.microservice.module.basicauth.entity;

import ir.piana.dev.microservice.core.authenticate.QPPrincipalEntity;

import javax.persistence.*;

/**
 * @author Mohammad Rahmati, 2/3/2019
 */
@Entity
@Table(name = "user")
@DiscriminatorValue("0")
public class BasicUserEntity extends QPPrincipalEntity {
    private String password;
    private String roles;

    public BasicUserEntity() {
    }

    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "ROLES")
    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
