package ir.piana.dev.microservice.core.authenticate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Mohammad Rahmati, 2/24/2019
 */
@MappedSuperclass
public abstract class QPPrincipalEntity extends QPPrincipal {
    private String username;

    public QPPrincipalEntity() {
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
