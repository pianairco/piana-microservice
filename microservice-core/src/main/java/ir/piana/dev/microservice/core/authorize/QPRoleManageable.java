package ir.piana.dev.microservice.core.authorize;

import ir.piana.dev.microservice.core.QPException;
import ir.piana.dev.microservice.core.authenticate.QPPrincipal;

import java.util.List;

/**
 * @author Mohammad Rahmati, 2/19/2019
 */
public interface QPRoleManageable {
    long registerRole(String roleName) throws QPException;
    boolean isRegistered(QPPrincipal principal);
    void registerPrincipalRoles(
            QPPrincipal principal,
            List<QPRoleProvidable> roleProvidables) throws QPException;
    void registerResourceRoles(
            String resource,
            List<QPRoleProvidable> roleProvidables) throws QPException;
//    void injectRequiredRoles(List<String> requiredRoleNames);
    long createRolesId(List<QPRoleProvidable> roleNames) throws QPException;
    long hasAnyRole(long requiredRolesId, long authenticatedRolesId);
    long hasAnyRole(QPPrincipal principal, long requiredRolesId);
}
