package ir.piana.dev.microservice.core.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Mohammad Rahmati, 2/21/2019
 */
class QPPianaSpringContextFactory {
//    private Map<String, AnnotationConfigApplicationContext>
    private Map<String, AnnotationConfigApplicationContext>
            springContextMap;

    private static QPPianaSpringContextFactory contextFactory;

    private QPPianaSpringContextFactory(
            Map<String, AnnotationConfigApplicationContext> springContextMap) {
        this.springContextMap = springContextMap;
    }

    public static QPPianaSpringContextFactory getContextFactory() {
        if(contextFactory != null)
            return contextFactory;
        synchronized (QPPianaSpringContextFactory.class) {
            Map<String, AnnotationConfigApplicationContext> springContextMap =
                    new LinkedHashMap<>();
            springContextMap.put("default",
                    new AnnotationConfigApplicationContext());
            contextFactory = new QPPianaSpringContextFactory(
                    springContextMap);
        }
        return contextFactory;
    }

    public AnnotationConfigApplicationContext getApplicationContext(){
        return this.getApplicationContext("default");
    }

    public AnnotationConfigApplicationContext getApplicationContext(String contextName){
        return springContextMap.get(contextName);
    }

    public void registerApplicationContext(String name) {
        if (!springContextMap.containsKey(name))
            springContextMap.put(name,
                    new AnnotationConfigApplicationContext());
    }
}
