package ir.piana.dev.microservice.context.authorize;

import ir.piana.dev.microservice.core.authorize.QPRoleProvidable;

/**
 * @author Mohammad Rahmati, 3/2/2019
 */
public class BasicRoleProvidable implements QPRoleProvidable {
    private String name;
    private String sign;

    public BasicRoleProvidable(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSign() {
        return sign;
    }
}
