package ir.piana.dev.microservice.sample.useraddress.entity;

import ir.piana.dev.microservice.core.authenticate.QPPrincipal;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Mohammad Rahmati, 2/3/2019
 */
@Entity
@Table(name = "user_address")
public class UserAddressEntity implements Serializable {
    private Long id;
    private QPPrincipal principalEntity;
    private String address;

    public UserAddressEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "ADDRESS")
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @ManyToOne
    @JoinColumn(name = "principal_id")
    public QPPrincipal getPrincipalEntity() {
        return principalEntity;
    }

    public void setPrincipalEntity(QPPrincipal principalEntity) {
        this.principalEntity = principalEntity;
    }
}
