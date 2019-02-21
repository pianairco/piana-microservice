package ir.piana.dev.microservice.core.http;

/**
 * @author Mohammad Rahmati, 2/18/2019
 */
public interface QPHttpAuthenticated {
    <T> T getAuthenticatedId();
    long getAuthenticatedRoles();
    boolean verifyRequiredRoles(long requiredRoles);
}
