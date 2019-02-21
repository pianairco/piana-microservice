package ir.piana.dev.microservice.sample.module;

import ir.piana.dev.microservice.core.module.QPBaseModule;

/**
 * @author Mohammad Rahmati, 2/21/2019
 */
public class SampleModule extends QPBaseModule {
    @Override
    protected void configBeforeRegisterQPModule() throws Exception {

    }

    @Override
    protected void initBeforeRegisterQPModule() throws Exception {

    }

    @Override
    protected void configForSpringContext() throws Exception {
        pianaSpringContextFactory
                .getApplicationContext()
//                .register(UserRepository.class)
                .register(QPJpaConfig.class)
                .refresh();
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
