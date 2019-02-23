package ir.piana.dev.microservice.sample.usermodule;

import ir.piana.dev.microservice.core.module.QPBaseModule;
import ir.piana.dev.microservice.sample.usermodule.repo.SpringConfig;

/**
 * @author Mohammad Rahmati, 2/21/2019
 */
public class UserModule extends QPBaseModule {
    @Override
    protected void configBeforeRegisterQPModule() throws Exception {

    }

    @Override
    protected void initBeforeRegisterQPModule() throws Exception {

    }

    @Override
    protected void configForSpringContext() throws Exception {
        getSpringContext()
                .register(SpringConfig.class);
    }

    @Override
    protected void configAfterRegisterQPModule() throws Exception {

    }

    @Override
    protected void initAfterRegisterQPModule() throws Exception {

    }

    @Override
    protected void startQPModule() throws Exception {
    }

    @Override
    protected void stopQPModule() throws Exception {

    }

    @Override
    protected void destroyQPModule() throws Exception {

    }
}
