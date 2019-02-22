package ir.piana.dev.microservice.core.spring;

/**
 * @author Mohammad Rahmati, 2/19/2019
 */
public interface QPSpringContext {
//    void refresh();
    <T> QPSpringContext register(Class<T> beanClass);
    <T> QPSpringContext registerBean(String beanName, Class<T> beanClass);
    <T> T getBean(String beanName, Class<T> beanClass);
    <T> T getBean(Class<T> beanClass);
}
