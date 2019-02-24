package ir.piana.dev.microservice.module.basicauth;

import ir.piana.dev.microservice.core.authenticate.QPPrincipalEntity;
import ir.piana.dev.microservice.core.authenticate.QPPrincipal;
import ir.piana.dev.microservice.core.http.QPHttpAuthenticated;
import ir.piana.dev.microservice.core.http.QPHttpAuthenticator;
import ir.piana.dev.microservice.core.http.QPHttpRequest;
import ir.piana.dev.microservice.core.module.QPBaseModule;
import ir.piana.dev.microservice.module.basicauth.entity.BasicUserEntity;
import ir.piana.dev.microservice.module.basicauth.repo.UserRepository;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @author Mohammad Rahmati, 2/23/2019
 */
public class BasicAuthenticationModule
        extends QPBaseModule
        implements QPHttpAuthenticator {
    @Override
    protected void configBeforeRegisterQPModule() throws Exception {

    }

    @Override
    protected void initBeforeRegisterQPModule() throws Exception {

    }

    @Override
    protected void configForSpringContext() throws Exception {

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

    @Override
    public QPHttpAuthenticated authenticate(QPHttpRequest request) {
        UserRepository userRepository = getSpringContext()
                .getBean(UserRepository.class);
        String authorization = request.getHeader("Authorization");
        try {
            String userPass = new String(
                    Base64.getDecoder().decode(authorization
                            .substring("Basic ".length())), "UTF-8");
            String[] split = userPass.split(":");
            BasicUserEntity userEntity = userRepository
                    .findByUsernameAndPassword(split[0], split[1]);
            if(userEntity != null)
                return new BasicHttpAuthenticated(userEntity);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    static class BasicHttpAuthenticated
            implements QPHttpAuthenticated {
        private QPPrincipalEntity userEntity;

        public BasicHttpAuthenticated(BasicUserEntity userEntity) {
            this.userEntity = userEntity;
        }

        public QPPrincipal getPrincipal() {
            return userEntity;
        }

//        @Override
//        public BasicUserEntity getAuthenticated() {
//            return userEntity;
//        }

        @Override
        public long getAuthenticatedRoles() {
            return 0;
        }

        @Override
        public boolean verifyRequiredRoles(long requiredRoles) {
            return true;
        }
    }
}
