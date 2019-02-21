package ir.piana.dev.microservice.context.authorize;

import ir.piana.dev.microservice.core.authorize.QPRoleManageable;
import ir.piana.dev.microservice.core.authorize.QPRoleProvidable;

import java.util.List;

/**
 * @author Mohammad Rahmati, 2/19/2019
 */
public class QPRoleManager implements QPRoleManageable {
    @Override
    public void injectRequiredRoles(List<String> requiredRoleNames) {

    }

    @Override
    public long createRolesId(List<QPRoleProvidable> roles) {
        return 0;
    }

    @Override
    public boolean hasAnyRole(long requiredRolesId, long authenticatedRolesId) {
        return false;
    }
}
