package ir.piana.dev.microservice.core.http;

/**
 * @author Mohammad Rahmati, 2/18/2019
 */
public interface QPHttpAuthenticator {
    QPHttpAuthenticated authenticate(
            QPHttpRequest request);
}
