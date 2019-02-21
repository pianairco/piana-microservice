package ir.piana.dev.microservice.context.http;

import ir.piana.dev.microservice.core.QPException;
import ir.piana.dev.microservice.core.http.QPHttpHandler;
import ir.piana.dev.microservice.core.http.QPHttpRepositoryManager;
import ir.piana.dev.microservice.context.http.construct.QPRepositoryConstruct;

import java.lang.reflect.Field;

/**
 * @author Mohammad Rahmati, 2/18/2019
 */
public class QPHttpPrototypeRepositoryManager
        implements QPHttpRepositoryManager {
    private QPRepositoryConstruct repositoryConstruct;

    public QPHttpPrototypeRepositoryManager(
            QPRepositoryConstruct repositoryConstruct) {
        this.repositoryConstruct = repositoryConstruct;
    }

    @Override
    public QPHttpHandler resolve(String handlerName) throws QPException {
        try {
            Field field = repositoryConstruct.getaClass()
                    .getDeclaredField(handlerName);
            if (QPHttpHandler.class.isAssignableFrom(field.getType())) {
                QPHttpHandler handler = (QPHttpHandler) field
                        .get(repositoryConstruct.getaClass().newInstance());
                return handler;
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        throw new QPException(handlerName
                .concat(" not exist in repository named ")
                .concat(repositoryConstruct.getName()));
    }
}
