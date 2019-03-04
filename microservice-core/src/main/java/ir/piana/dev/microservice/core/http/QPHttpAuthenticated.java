package ir.piana.dev.microservice.core.http;

import ir.piana.dev.microservice.core.authenticate.QPPrincipal;
import ir.piana.dev.microservice.core.authorize.QPRoleProvidable;

import java.util.List;

/**
 * @author Mohammad Rahmati, 2/18/2019
 */
public interface QPHttpAuthenticated {
    QPPrincipal getPrincipal();
    List<QPRoleProvidable> getAuthenticatedRoles();
}
