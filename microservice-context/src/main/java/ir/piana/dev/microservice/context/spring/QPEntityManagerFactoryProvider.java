package ir.piana.dev.microservice.context.spring;

import javax.persistence.EntityManagerFactory;

public interface QPEntityManagerFactoryProvider {
    EntityManagerFactory getEntityManagerFactory();
}
