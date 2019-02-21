package ir.piana.dev.microservice.core.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Mohammad Rahmati, 2/21/2019
 */
public class PianaSpringContextFactory {
//    private Map<String, AnnotationConfigApplicationContext>
    private Map<String, AnnotationConfigApplicationContext>
            springContextMap;

    private static PianaSpringContextFactory contextFactory;

    private PianaSpringContextFactory(
            Map<String, AnnotationConfigApplicationContext> springContextMap) {
        this.springContextMap = springContextMap;
    }

    public static PianaSpringContextFactory getContextFactory() {
        if(contextFactory != null)
            return contextFactory;
        synchronized (PianaSpringContextFactory.class) {
            Map<String, AnnotationConfigApplicationContext> springContextMap =
                    new LinkedHashMap<>();
            springContextMap.put("default",
                    new AnnotationConfigApplicationContext());
            contextFactory = new PianaSpringContextFactory(
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
