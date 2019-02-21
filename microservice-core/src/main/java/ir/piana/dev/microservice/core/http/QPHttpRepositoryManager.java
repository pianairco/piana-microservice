package ir.piana.dev.microservice.core.http;

import ir.piana.dev.microservice.core.QPException;

/**
 * @author Mohammad Rahmati, 2/18/2019
 */
public interface QPHttpRepositoryManager {
    QPHttpHandler resolve(String handlerName) throws QPException;
}
