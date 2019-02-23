package ir.piana.dev.microservice.core.http;

import ir.piana.dev.microservice.core.module.QPSpringContext;

/**
 * @author Mohammad Rahmati, 2/16/2019
 */
public interface QPHttpHandler {
    void handle(QPHttpInjectableConfig config,
                QPHttpRequest request,
                QPHttpResponse response) throws QPHttpException;
//    QPHttpResponse handle(QPHttpRequest request);
}
