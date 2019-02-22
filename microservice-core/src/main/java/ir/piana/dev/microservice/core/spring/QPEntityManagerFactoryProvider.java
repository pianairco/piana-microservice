package ir.piana.dev.microservice.core.spring;

import javax.persistence.EntityManagerFactory;

public interface QPEntityManagerFactoryProvider {
    EntityManagerFactory getEntityManagerFactory();
}
