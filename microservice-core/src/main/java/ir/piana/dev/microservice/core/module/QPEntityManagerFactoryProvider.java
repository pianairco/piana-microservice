package ir.piana.dev.microservice.core.module;

import javax.persistence.EntityManagerFactory;

public interface QPEntityManagerFactoryProvider {
    EntityManagerFactory getEntityManagerFactory();
}
