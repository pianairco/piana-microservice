package ir.piana.dev.microservice.core.http;

import ir.piana.dev.microservice.core.authenticate.QPPrincipal;

/**
 * @author Mohammad Rahmati, 2/16/2019
 */
public interface QPHttpHandler {
    void handle(QPPrincipal principal,
                QPHttpInjectableConfig config,
                QPHttpRequest request,
                QPHttpResponse response) throws QPHttpException;
//    QPHttpResponse handle(QPHttpRequest request);
}
