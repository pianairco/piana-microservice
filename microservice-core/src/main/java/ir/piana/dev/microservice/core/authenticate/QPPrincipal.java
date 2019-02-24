package ir.piana.dev.microservice.core.authenticate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Mohammad Rahmati, 2/24/2019
 */
@Entity
@Table(name = "principal")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type",
        discriminatorType = DiscriminatorType.INTEGER)
public abstract class QPPrincipal implements Serializable {
    protected Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
