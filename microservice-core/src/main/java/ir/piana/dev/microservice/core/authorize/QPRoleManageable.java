package ir.piana.dev.microservice.core.authorize;

import java.util.List;

/**
 * @author Mohammad Rahmati, 2/19/2019
 */
public interface QPRoleManageable {
    void injectRequiredRoles(List<String> requiredRoleNames);
    long createRolesId(List<QPRoleProvidable> roles);
    boolean hasAnyRole(long requiredRolesId, long authenticatedRolesId);
}
