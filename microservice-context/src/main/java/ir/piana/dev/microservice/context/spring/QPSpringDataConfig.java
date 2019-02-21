package ir.piana.dev.microservice.context.spring;

import ir.piana.dev.microservice.core.module.QPBaseModule;
import org.springframework.orm.jpa.JpaTransactionManager;

import javax.persistence.EntityManagerFactory;

/**
 * @author Mohammad Rahmati, 1/22/2019
 */
//@EnableAspectJAutoProxy(proxyTargetClass = true)
public abstract class QPSpringDataConfig {
    protected abstract String getJpaModuleName();

    protected final EntityManagerFactory entityManagerFactory() {
        QPEntityManagerFactoryProvider module = QPBaseModule
                .getModule(getJpaModuleName());
        return module.getEntityManagerFactory();
    }

    protected final JpaTransactionManager transactionManager(
            EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }
}
