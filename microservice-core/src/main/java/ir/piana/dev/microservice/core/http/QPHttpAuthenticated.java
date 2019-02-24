package ir.piana.dev.microservice.core.http;

import ir.piana.dev.microservice.core.authenticate.QPPrincipal;

/**
 * @author Mohammad Rahmati, 2/18/2019
 */
public interface QPHttpAuthenticated {
    QPPrincipal getPrincipal();
    long getAuthenticatedRoles();
    boolean verifyRequiredRoles(long requiredRoles);
}
