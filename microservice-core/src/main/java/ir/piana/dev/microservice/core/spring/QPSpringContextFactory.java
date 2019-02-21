package ir.piana.dev.microservice.core.spring;

import org.springframework.beans.factory.config.BeanDefinitionCustomizer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Mohammad Rahmati, 2/21/2019
 */
public class QPSpringContextFactory {
    private Map<String, QPSpringContext>
            springContextMap;

    private static QPSpringContextFactory contextFactory;

    private QPSpringContextFactory(
            Map<String, QPSpringContext> springContextMap) {
        this.springContextMap = springContextMap;
    }

    public static QPSpringContextFactory getContextFactory() {
        if(contextFactory != null)
            return contextFactory;
        synchronized (QPSpringContextFactory.class) {
            Map<String, QPSpringContext> springContextMap =
                    new LinkedHashMap<>();
            springContextMap.put("default",
                    new QPSpringContextImpl(
                            new AnnotationConfigApplicationContext()));
            contextFactory = new QPSpringContextFactory(
                    springContextMap);
        }
        return contextFactory;
    }

    public QPSpringContext getApplicationContext(){
        return this.getApplicationContext("default");
    }

    public QPSpringContext getApplicationContext(String contextName){
        if (springContextMap.containsKey(contextName))
            return springContextMap.get(contextName);
        QPSpringContextImpl springContext = new QPSpringContextImpl(
                new AnnotationConfigApplicationContext());
        springContextMap.put(contextName, springContext);
        return springContext;
    }

    private static class QPSpringContextImpl
            implements QPSpringContext {
        private AnnotationConfigApplicationContext applicationContext;

        public QPSpringContextImpl(
                AnnotationConfigApplicationContext applicationContext) {
            this.applicationContext = applicationContext;
//            this.applicationContext.refresh();
        }

        @Override
        public void refresh() {
            this.applicationContext.refresh();
        }

        @Override
        public <T> QPSpringContext register(Class<T> beanClass) {
            this.applicationContext.register(beanClass);
//            this.applicationContext.refresh();
            return this;
        }

        @Override
        public <T> QPSpringContext registerBean(
                String beanName, Class<T> beanClass) {
            applicationContext.registerBean(beanName, beanClass,
                    beanDefinition -> {});
            return this;
        }

        @Override
        public <T> T getBean(String beanName, Class<T> beanClass) {
            return applicationContext.getBean(beanName, beanClass);
        }

        @Override
        public <T> T getBean(Class<T> beanClass) {
            return applicationContext.getBean(beanClass);
        }
    }
}
